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
        final Game game = new Game();
        final Robot robot = new Robot(game);
        if (Config.IRC_ENABLED) robot.connect();
        
        // Catch SIGTERM and disconnecting all players
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                game.disconnectAll();
            }
        });
    }
}
