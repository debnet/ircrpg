package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.commons.Config;
import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.enums.Return;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Result;
import java.util.Calendar;
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
public class UpdateTest {
    
    private static final double EPSILON = 0.01;
    
    private Game game;
    private Player player;
    
    public UpdateTest() {
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
        // Override configuration
        Config.loadConfig("config.tests.properties");
        // Create player
        Result r = this.game.registerPlayer("p1", null, "p1", null);
        assertTrue("Creating player", r.isSuccess());
        // Change player last time updatePlayer
        Calendar time = Calendar.getInstance();
        time.add(Calendar.HOUR, -24);
        this.player = this.game.getPlayerByUsername("p1");
        this.player.setLastUpdate(time);
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testUpdateNormal() {
        // Description
        System.out.println("- normal update:");
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
    }
    
    @Test
    public void testUpdatePoisonCured() {
        // Description
        System.out.println("- poison cured:");
        // Change player status
        this.player.setStatus(Status.POISONED);
        this.player.setStatusDuration(Config.HOUR);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_POISON_CURED));
        assertEquals("HP lost", -10d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateParalysisCured() {
        // Description
        System.out.println("- paralysis cured:");
        // Change player status
        this.player.setStatus(Status.PARALYZED);
        this.player.setStatusDuration(Config.HOUR);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_PARALYSIS_CURED));
        assertEquals("HP changes", 0d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateDeathCured() {
        // Description
        System.out.println("- death cured:");
        // Change player status
        this.player.setStatus(Status.DEAD);
        this.player.setStatusDuration(Config.HOUR * 24);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_DEATH_CURED));
        assertEquals("HP changes", 100d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateKilledByPoison() {
        // Description
        System.out.println("- killed by poison:");
        // Change player status
        this.player.setStatus(Status.POISONED);
        this.player.setStatusDuration(Config.HOUR);
        this.player.setCurrentHealth(1d);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_KILLED_BY_POISON));
        assertEquals("HP changes", -10d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateResting() {
        // Description
        System.out.println("- player resting:");
        // Change player activity
        this.player.setActivity(Activity.RESTING);
        this.player.setCurrentHealth(50d);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_RESTING_ENDED));
        assertEquals("HP changes", 20d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdatePraying() {
        // Description
        System.out.println("- player praying:");
        // Change player activity
        this.player.setActivity(Activity.PRAYING);
        this.player.setCurrentHealth(20d);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_PRAYING_ENDED));
        assertEquals("MP changes", 10d, r.getPlayerManaChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateWorking() {
        // Description
        System.out.println("- player working:");
        // Change player activity
        this.player.setActivity(Activity.WORKING);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_WORKING_ENDED));
        assertEquals("Gold changes", 20d, r.getPlayerGoldChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateTraining() {
        // Description
        System.out.println("- player training:");
        // Change player activity
        this.player.setActivity(Activity.TRAINING);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_TRAINING_ENDED));
        assertEquals("XP changes", 20d, r.getPlayerExperienceChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateWaiting() {
        // Description
        System.out.println("- player waiting:");
        // Change player activity
        this.player.setActivity(Activity.WAITING);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_WAITING_ENDED));
    }
    
    @Test
    public void testUpdateLevelUp() {
        // Description
        System.out.println("- player level up:");
        // Change player activity
        this.player.addExperience(1000d);
        // Update player
        Result r = this.game.updatePlayer(this.player, false, false);
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_LEVEL_UP));
        assertEquals("Level", 5, this.player.getLevel(), EPSILON);
        assertEquals("Skill points", 4, this.player.getSkillPoints(), EPSILON);
    }
}