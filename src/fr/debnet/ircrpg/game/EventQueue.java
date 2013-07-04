/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.robot.Robot;

/**
 *
 * @author Marc
 */
public class EventQueue extends Thread {

    private Game game;
    private Robot robot;
    
    public EventQueue(Game game, Robot robot) {
        this.game = game;
        this.robot = robot;
    }
    
    @Override
    public void run() {
        
    }
}
