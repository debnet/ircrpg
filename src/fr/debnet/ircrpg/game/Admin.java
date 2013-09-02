/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.models.Player;
import java.util.HashMap;
import java.util.Map;

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
    
}
