package fr.debnet.ircrpg.interfaces;

import fr.debnet.ircrpg.game.Game;
import fr.debnet.ircrpg.models.Player;

/**
 *
 * @author Marc
 */
public interface IQueue {
    
    boolean register(INotifiable notifiable);
    
    void update(Player player);
    
    void interrupt();
    
    Game getGame();
}
