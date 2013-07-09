/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.game.queues;

/**
 *
 * @author Marc
 */
public interface IGameQueue {
    
    boolean register(INotifiable notifiable);
    
    void update();
}
