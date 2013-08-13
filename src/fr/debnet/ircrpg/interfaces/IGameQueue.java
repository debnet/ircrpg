package fr.debnet.ircrpg.interfaces;

/**
 *
 * @author Marc
 */
public interface IGameQueue {
    
    boolean register(INotifiable notifiable);
    
    void update();
}
