package fr.debnet.ircrpg;

import fr.debnet.ircrpg.game.Game;
import fr.debnet.ircrpg.helpers.Helpers;
import fr.debnet.ircrpg.mock.Random;
import fr.debnet.ircrpg.models.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class for load tests
 * @author marc
 */
public class LoadTest extends Thread {
    
    private static Game game;
    private static List<String> players = new ArrayList<>();
    private static Random random = new Random();
    
    /**
     * Main method
     * @param args Arguments 
     */
    public static void main(String[] args) {
        Config.loadConfig("config.tests.properties");
        game = new Game();
        for (int i = 1; i < 10000; i++) {
            String username = String.format("Username%04d", i);
            String nickname = String.format("Nickname%04d", i);
            Result result = game.register(username, null, nickname, null);
            if (result.isSuccess()) {
                players.add(nickname);
                LoadTest loadTest = new LoadTest(nickname);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String player;
    
    /**
     * Constructor
     * @param player Player 
     */
    public LoadTest(String player) {
        this.player = player;
        this.start();
    }
    
    @Override
    public void run() {
        while(true) {
            int index = (int) (random.nextDouble() * players.size());
            if (index != 0) {
                String enemy = players.get(index);
                Result result = game.fight(this.player, enemy, null);
                String message = String.format("[%s] %s", this.player, Helpers.getMessage(result));
                System.out.println(message);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
