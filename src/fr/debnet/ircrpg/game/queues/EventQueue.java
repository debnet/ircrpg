/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.game.queues;

import fr.debnet.ircrpg.game.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class EventQueue extends Thread implements IGameQueue {

    private Game game;
    private boolean run = true;
    private final List<INotifiable> notifiables;
    
    public EventQueue(Game game) {
        this.game = game;
        this.notifiables = new ArrayList<INotifiable>();
        this.start();
    }
    
    @Override
    public void run() {
        while (run) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean register(INotifiable notifiable) {
        if (!this.notifiables.contains(notifiable)) {
            synchronized(this.notifiables) {
                this.notifiables.add(notifiable);
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        
    }
}
