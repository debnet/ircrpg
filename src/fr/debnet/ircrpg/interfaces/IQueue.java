package fr.debnet.ircrpg.interfaces;

/**
 *
 * @author Marc
 */
public interface IQueue {
    
    boolean register(INotifiable notifiable);
    
    void update();
}
