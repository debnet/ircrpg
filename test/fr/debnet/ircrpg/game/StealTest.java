package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.commons.Config;
import fr.debnet.ircrpg.enums.Return;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Result;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
 */
public class StealTest {
    
    private static final double EPSILON = 0.01;
    
    private Game game;
    
    public StealTest() {
        // Override configuration
        Config.loadConfig("config.tests.properties");
        // New game
        this.game = new Game();
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        // Create player
        Result r = this.game.registerPlayer("p1", null, "p1", null);
        assertTrue("Creating player", r.isSuccess());
        // Create enemy
        r = this.game.registerPlayer("p2", null, "p2", null);
        assertTrue("Creating enemy", r.isSuccess());
        // Add gold to enemy
        Player p2 = this.game.getPlayerByNickname("p2");
        p2.addGold(100d);
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testStealErrorUnknownPlayers() {
        // Description
        System.out.println("- unknown players error:");
        // Create fightPlayer
        Result r = this.game.stealPlayer("u1", "p2");
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.UNKNOWN_PLAYER));
        // Create theft
        r = this.game.stealPlayer("p1", "u2");
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.UNKNOWN_TARGET));
    }
    
    @Test
    public void testStealErrorOfflinePlayers() {
        // Description
        System.out.println("- offline players error:");
        // Set player 1 offline
        Player p1 = this.game.getPlayerByUsername("p1");
        p1.setOnline(false);
        // Create theft
        Result r = this.game.stealPlayer("p1", "p2");
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_OFFLINE));
        // Set player 1 online and player 2 offline
        p1.setOnline(true);
        Player p2 = this.game.getPlayerByUsername("p2");
        p2.setOnline(false);
        // Create theft
        r = this.game.stealPlayer("p1", "p2");
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.TARGET_OFFLINE));
    }
    
    @Test
    public void testStealSucceed() {
        // Description
        System.out.println("- steal succeed:");
        // Mock for random values
        this.game.getRandom().addValue(0.1d);
        this.game.getRandom().addValue(0.5d);
        // Create theft
        Result r = this.game.stealPlayer("p1", "p2");
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.THEFT_SUCCEED));
        // Test attacker changes
        assertEquals("Attacker gold changes", 18d, r.getPlayerGoldChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender gold changes", -18d, r.getTargetGoldChanges(), EPSILON);
    }
    
    @Test
    public void testStealFailed() {
        // Description
        System.out.println("- steal failed:");
        // Mock for random values
        this.game.getRandom().addValue(0.5d);
        // Create theft
        Result r = this.game.stealPlayer("p1", "p2");
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.THEFT_FAILED));
        // Test attacker changes
        assertEquals("Attacker gold changes", 0d, r.getPlayerGoldChanges(), EPSILON);
        assertEquals("Attacker HP changes", -5d, r.getPlayerHealthChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender gold changes", 0d, r.getTargetGoldChanges(), EPSILON);
        assertEquals("Defender XP changes", 15d, r.getTargetExperienceChanges(), EPSILON);
    }
}