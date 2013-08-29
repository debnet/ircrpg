package fr.debnet.ircrpg.game.queues;

import fr.debnet.ircrpg.interfaces.IQueue;
import fr.debnet.ircrpg.interfaces.INotifiable;
import fr.debnet.ircrpg.game.Game;
import fr.debnet.ircrpg.models.Event;
import fr.debnet.ircrpg.models.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class EventQueue extends Thread implements IQueue {
    
    private static IQueue queue;
    
    public static IQueue getInstance(Game game) {
        if (queue == null || queue.getGame() != game)
            queue = new EventQueue(game);
        return queue;
    }
    
    private Game game;
    private boolean run = true;
    private final List<INotifiable> notifiables;
    
    private Map<Player, List<Event>> events;
    
    public EventQueue(Game game) {
        this.game = game;
        this.notifiables = new ArrayList<INotifiable>();
        this.events = new HashMap<Player, List<Event>>();
        this.start();
    }
    
    @Override
    public void run() {
        while (run) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EventQueue.class.getName()).log(Level.SEVERE, null, ex);
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
    public void update(Player player) {
        if (!player.getOnline()) return;
        // TODO: 
    }
    
    @Override
    public void interrupt() {
        this.run = false;
    }
    
    @Override
    public Game getGame() {
        return this.game;
    }
}
