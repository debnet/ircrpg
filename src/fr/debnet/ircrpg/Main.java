package fr.debnet.ircrpg;

import fr.debnet.ircrpg.game.Game;
import fr.debnet.ircrpg.robot.Robot;

/**
 * Main class
 * @author Marc
 */
public class Main {
    
    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        Robot robot = new Robot(game);
        if (Config.IRC_ENABLED) robot.connect();
    }
}
