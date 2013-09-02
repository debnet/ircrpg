/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.DAO;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Type;
import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.interfaces.MappedEntity;
import fr.debnet.ircrpg.models.Event;
import fr.debnet.ircrpg.models.Item;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Spell;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class Admin {
    
    private Game game;
    private Map<Player, IEntity> objects;
    
    /**
     * Constructor
     * @param game Game instance 
     */
    public Admin(Game game) {
        this.game = game;
        this.objects = new HashMap<Player, IEntity>();
    }
    
    /**
     * Create a new object
     * @param nickname Player's nickname
     * @param type Model type
     * @return True is success, false else
     */
    public boolean newObject(String nickname, String type) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return false;
        // Get model type
        Model model = Model.from(type);
        // Remove previous object
        this.objects.remove(player);
        // Create object
        IEntity object = null;
        switch (model) {
            case PLAYER:
                object = new Player();
                break;
            case ITEM:
                object = new Item();
                break;
            case SPELL:
                object = new Spell();
                break;
            case EVENT:
                object = new Event();
                break;
        }
        // Check object
        if (object == null) return false;
        this.objects.put(player, object);
        // Return
        return true;
    }
    
    /**
     * Edit existing object
     * @param nickname Player's nickname
     * @param type Model type
     * @param code Object code
     * @return True if success, false else
     */
    public boolean editObject(String nickname, String type, String code) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return false;
        // Get model type
        Model model = Model.from(type);
        // Remove previous object
        this.objects.remove(player);
        // Get object
        IEntity object = null;
        switch(model) {
            case PLAYER:
                object = this.game.getPlayerByNickname(code);
                break;
            case ITEM:
                object = this.game.getItemByCode(code);
                break;
            case SPELL:
                object = this.game.getSpellByCode(code);
                break;
            case EVENT:
                object = this.game.getEventByCode(code);
                break;
        }
        // Check object
        if (object == null) return false;
        this.objects.put(player, object);
        // Return
        return true;
    }
    
    /**
     * Set property of an object
     * @param nickname Player's nickname
     * @param property Property name
     * @param value Value
     * @return True if success, false else
     */
    public boolean setObject(String nickname, String property, String value) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return false;
        // Get current object
        IEntity object = this.objects.get(player);
        if (object == null) return false;
        
        try {
            // Get property setter
            PropertyDescriptor descriptor = new PropertyDescriptor(property, object.getClass());
            Method setter = descriptor.getWriteMethod();
            Type type = Type.from(descriptor.getPropertyType());
            // Set value
            switch (type) {
                case STRING:
                    setter.invoke(object, value);
                    break;
                case BOOLEAN:
                    setter.invoke(object, Boolean.parseBoolean(value));
                    break;
                case INTEGER:
                    setter.invoke(object, Integer.parseInt(value));
                    break;
                case DECIMAL:
                    setter.invoke(object, Double.parseDouble(value));
                    break;
                default:
                    return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        // Return
        return true;
    }
    
    /**
     * Get property value of the current object
     * @param nickname Player's nickname
     * @param property Property name
     * @return Returned value
     */
    public String getObject(String nickname, String property) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return null;
        // Get current object
        IEntity object = this.objects.get(player);
        if (object == null) return null;
        
        try {
            // Get property getter
            PropertyDescriptor descriptor = new PropertyDescriptor(property, object.getClass());
            Method getter = descriptor.getReadMethod();
            Type type = Type.from(descriptor.getPropertyType());
            // Get value
            return getter.invoke(object).toString();
        } catch (Exception ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Insert or update current object in database
     * @param nickname Player's nickname
     * @return True if success, false else
     */
    public boolean saveObject(String nickname) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return false;
        // Get current object
        IEntity object = this.objects.get(player);
        if (object == null) return false;
        // Save object
        if (object.getId() == 0)
            return DAO.addObject(object) != 0l;
        else return DAO.setObject(object);
    }
    
    /**
     * Delete object from database
     * @param nickname Player's nickname
     * @param type Model type
     * @param code Object code
     * @return True if success, false else
     */
    public boolean deleteObject(String nickname, String type, String code) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return false;
        // Edit object
        boolean success = this.editObject(nickname, type, code);
        if (!success) return false;
        // Delete object
        IEntity object = this.objects.get(player);
        this.objects.remove(player);
        return DAO.delObject(object);
    }
    
    /**
     * Return current object map
     * @param nickname Player's nickname
     * @return Object mapping
     */
    public String dumpObject(String nickname) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return null;
        // Get current object
        IEntity object = this.objects.get(player);
        if (object == null) return null;
        // Return object map
        MappedEntity entity = (MappedEntity)object;
        return entity.toMap().toString();
    }
    
    /**
     * Get config value
     * @param nickname Player's nickname
     * @param key Config key
     * @return Config value
     */
    public String getConfig(String nickname, String key) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return null;
        // Get config
        return Config.getConfig(key);
    }
    
    /**
     * Set config value
     * @param nickname Player's nickname
     * @param key Config key
     * @param value Config value
     * @return True if success, false else
     */
    public boolean setConfig(String nickname, String key, String value) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return false;
        // Set config
        return Config.setConfig(key, value); 
    }
    
    /**
     * Get list of config keys
     * @param nickname Player's nickname
     * @return List of config keys
     */
    public String listConfig(String nickname) {
        Player player = this.game.getPlayerByNickname(nickname);
        if (player == null || !player.getAdmin()) return null;
        // Get config list
        return Config.getFields().toString();
    }
}
