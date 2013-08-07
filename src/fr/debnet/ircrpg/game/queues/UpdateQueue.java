/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.game.queues;

import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.game.Game;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Result;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class UpdateQueue extends Thread implements IGameQueue {

    private Game game;
    private boolean run = true;
    private final List<INotifiable> notifiables;
    
    private Player player;
    private Calendar date;
    
    public UpdateQueue(Game game) {
        this.game = game;
        this.notifiables = new ArrayList<INotifiable>();
        this.start();
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
    public void run() {
        while (run) {
            if (this.player != null && this.date != null) {
                Calendar now = Calendar.getInstance();
                if (now.after(this.date)) {
                    Result result = this.game.update(this.player);
                    for (INotifiable notifiable : this.notifiables) {
                        notifiable.notify(result);
                    }
                    this.update();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update() {
        if (this.player != null && !this.player.getOnline()) {
            this.player = null;
            this.date = null;
        }
        for (Player player : this.game.getAllPlayers()) {
            if (!player.getOnline()) {
                break;
            }
            if (player.getActivity() != Activity.NONE) {
                Calendar nextDate = Calendar.getInstance();
                nextDate.add(Calendar.MILLISECOND, -player.getActivityDuration());
                switch (player.getActivity()) {
                    case WAITING:
                        nextDate.add(Calendar.MINUTE, Config.ACTIVITY_PENALTY);
                        break;
                    case RESTING:
                        nextDate.add(Calendar.MINUTE, Config.RESTING_TIME_MAX);
                        break;
                    case TRAINING:
                        nextDate.add(Calendar.MINUTE, Config.TRAINING_TIME_MAX);
                        break;
                    case WORKING:
                        nextDate.add(Calendar.MINUTE, Config.WORKING_TIME_MAX);
                        break;
                }
                if (this.date == null || nextDate.before(this.date)) {
                    this.player = player;
                    this.date = nextDate;
                }
            }
            if (player.getStatus() != Status.NORMAL) {
                Calendar nextDate = Calendar.getInstance();
                nextDate.add(Calendar.MILLISECOND, player.getStatusDuration());
                if (this.date == null || nextDate.before(this.date)) {
                    this.player = player;
                    this.date = nextDate;
                }
            }
        }
    }
}
