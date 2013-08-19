package fr.debnet.ircrpg.interfaces;

import fr.debnet.ircrpg.game.Game;

/**
 *
 * @author Marc
 */
public interface IQueue {
    
    boolean register(INotifiable notifiable);
    
    void update();
    
    void interrupt();
    
    Game getGame();
}
