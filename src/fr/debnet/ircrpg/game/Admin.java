package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.commons.Config;
import fr.debnet.ircrpg.commons.Strings;
import fr.debnet.ircrpg.data.DAO;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Type;
import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.interfaces.MappedEntity;
import fr.debnet.ircrpg.models.Event;
import fr.debnet.ircrpg.models.Item;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Spell;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class Admin {
    
    // Logger
    private static final Logger logger = Logger.getLogger(Admin.class.getName());
    
    private Game game;
    private Map<Player, IEntity> objects;
    
    /**
     * Constructor
     * @param game Game instance 
     */
    public Admin(Game game) {
        this.game = game;
        this.objects = new HashMap<>();
    }
    
    /**
     * Create a new object
     * @param admin Admin's nickname
     * @param type Model type
     * @return True is success, false else
     */
    public boolean newObject(String admin, String type) {
        Player player = this.game.getPlayerByNickname(admin);
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
     * @param admin Admin's nickname
     * @param type Model type
     * @param code Object code
     * @return True if success, false else
     */
    public boolean editObject(String admin, String type, String code) {
        Player player = this.game.getPlayerByNickname(admin);
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
     * @param admin Admin's nickname
     * @param property Property name
     * @param value Value
     * @return True if success, false else
     */
    public boolean setObject(String admin, String property, String value) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return false;
        // Get current object
        IEntity object = this.objects.get(player);
        if (object == null) return false;
        
        try {
            // Get property setter
            PropertyDescriptor descriptor = new PropertyDescriptor(property, object.getClass());
            Method setter = descriptor.getWriteMethod();
            Class<?> objectType = descriptor.getPropertyType();
            Type type = Type.from(objectType);
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
                case DOUBLE:
                    setter.invoke(object, Double.parseDouble(value));
                    break;
                case FLOAT:
                    setter.invoke(object, Float.parseFloat(value));
                    break;
                case LONG:
                    setter.invoke(object, Long.parseLong(value));
                    break;
                case ENUM:
                    setter.invoke(object, Enum.valueOf((Class<Enum>) objectType, value));
                    break;
                default:
                    return false;
            }
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage());
            return false;
        }
        // Return
        return true;
    }
    
    /**
     * Get property value of the current object
     * @param admin Admin's nickname
     * @param property Property name
     * @return Returned value
     */
    public String getObject(String admin, String property) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return null;
        // Get current object
        IEntity object = this.objects.get(player);
        if (object == null) return null;
        
        try {
            // Get property getter
            PropertyDescriptor descriptor = new PropertyDescriptor(property, object.getClass());
            Method getter = descriptor.getReadMethod();
            // Get value
            return getter.invoke(object).toString();
        } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage());
            return null;
        }
    }
    
    /**
     * Insert or updatePlayer current object in database
     * @param admin Admin's nickname
     * @return True if success, false else
     */
    public boolean saveObject(String admin) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return false;
        // Get current object
        IEntity object = this.objects.get(player);
        if (object == null) return false;
        // Save object
        if (object.getId() == 0l)
            return DAO.addObject(object) != null;
        else return DAO.setObject(object);
    }
    
    /**
     * Delete object from database
     * @param admin Admin's nickname
     * @param type Model type
     * @param code Object code
     * @return True if success, false else
     */
    public boolean deleteObject(String admin, String type, String code) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return false;
        // Edit object
        boolean success = this.editObject(admin, type, code);
        if (!success) return false;
        // Delete object
        IEntity object = this.objects.get(player);
        this.objects.remove(player);
        return DAO.delObject(object);
    }
    
    /**
     * Return current object map
     * @param admin Admin's nickname
     * @return Object mapping
     */
    public String dumpObject(String admin) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return null;
        // Get current object
        IEntity object = this.objects.get(player);
        if (object == null) return null;
        // Return object map
        MappedEntity entity = (MappedEntity)object;
        List<String> properties = new ArrayList<>();
        for (Map.Entry<String, Object> entry : entity.toMap().entrySet()) {
            Class<?> objectType = entry.getValue().getClass();
            Type type = Type.from(objectType);
            if (type != Type.OBJECT) {
                properties.add(String.format(type != Type.STRING ? "%s = %s" : "%s = \"%s\"", 
                    entry.getKey(), entry.getValue().toString()));
            }
        }
        return properties.toString();
    }
    
    /**
     * Get config value
     * @param admin Admin's nickname
     * @param key Config key
     * @return Config value
     */
    public String getConfig(String admin, String key) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return null;
        // Get config
        return Config.getConfig(key);
    }
    
    /**
     * Set config value
     * @param admin Admin's nickname
     * @param key Config key
     * @param value Config value
     * @return True if success, false else
     */
    public boolean setConfig(String admin, String key, String value) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return false;
        // Set config
        return Config.setConfig(key, value); 
    }
    
    /**
     * Get list of config keys
     * @param admin Admin's nickname
     * @return List of config keys
     */
    public String listConfig(String admin) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return null;
        // Get config list
        return Config.toMap().toString();
    }
    
    /**
     * Reload a entity list or config/string
     * @param admin Admin's nickname
     * @param type Type to reload
     * @return True if success, false else
     */
    public boolean reload(String admin, String type) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return false;
        // Get model type
        Model model = Model.from(type);
        // Reload
        switch (model) {
            case PLAYER:
                this.game.reloadPlayers();
                break;
            case ITEM:
                this.game.reloadItems();
                break;
            case SPELL:
                this.game.reloadSpells();
                break;
            case EVENT:
                this.game.reloadEvents();
                break;
            default:
                switch (type) {
                    case Strings.ADMIN_CONFIG:
                        Config.loadProperties("config.properties", Config.class);
                        break;
                    case Strings.ADMIN_STRINGS:
                        Config.loadProperties("strings.properties", Strings.class);
                        break;
                    case Strings.ADMIN_QUEUES:
                        this.game.reloadQueues();
                        break;
                    default:
                        return false;
                }
                break;
        }
        // Return
        return true;
    }
    
    /**
     * Disconnect all players online
     * @param admin Admin's nickname
     * @return True if success, false else
     */
    public boolean disconnectPlayers(String admin) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return false;
        // Disconnect all players
        this.game.disconnectPlayers();
        return true;
    }
    
    /**
     * Reconnect a list of players
     * @param admin Admin's nickname
     * @param nicknames Players' nicknames
     * @return True if success, false else
     */
    public boolean reconnectPlayers(String admin, String[] nicknames) {
        Player player = this.game.getPlayerByNickname(admin);
        if (player == null || !player.getAdmin()) return false;
        // Reconnect all players
        for (String nickname : nicknames) {
            player = this.game.getPlayerByNickname(nickname);
            if (player != null) {
                player.setOnline(true);
                player.setLastUpdate(Calendar.getInstance());
                this.game.updatePlayer(player, true, false);
            }
        }
        return true;
    }
}
