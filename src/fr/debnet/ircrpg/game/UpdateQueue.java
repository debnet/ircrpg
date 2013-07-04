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
public class UpdateQueue extends Thread {

    private Game game;
    private Robot robot;
    
    public UpdateQueue(Game game, Robot robot) {
        this.game = game;
        this.robot = robot;
    }
    
    @Override
    public void run() {
        
    }
}
