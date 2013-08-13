package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.enums.Return;
import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.enums.Status;
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
    private static final int HOUR = 3600000;
    
    private Game game;
    private Player player;
    
    public UpdateTest() {
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
        Result r = this.game.register("p1", null, "p1", null);
        assertTrue("Creating player", r.isSuccess());
        // Change player last time update
        Calendar time = Calendar.getInstance();
        time.add(Calendar.HOUR, -1);
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
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
    }
    
    @Test
    public void testUpdatePoisonCured() {
        // Description
        System.out.println("- poison cured:");
        // Change player status
        this.player.setStatus(Status.POISONED);
        this.player.setStatusDuration(HOUR);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.POISON_CURED));
        assertEquals("HP lost", -5d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateParalysisCured() {
        // Description
        System.out.println("- paralysis cured:");
        // Change player status
        this.player.setStatus(Status.PARALYZED);
        this.player.setStatusDuration(HOUR);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.PARALYSIS_CURED));
        assertEquals("HP changes", 0d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateDeathCured() {
        // Description
        System.out.println("- death cured:");
        // Change player status
        this.player.setStatus(Status.DEAD);
        this.player.setStatusDuration(HOUR);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.DEATH_CURED));
        assertEquals("HP changes", 100d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateKilledByPoison() {
        // Description
        System.out.println("- killed by poison:");
        // Change player status
        this.player.setStatus(Status.POISONED);
        this.player.setStatusDuration(HOUR * 2);
        this.player.setCurrentHealth(1d);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.KILLED_BY_POISON));
        assertEquals("HP changes", -5d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateResting() {
        // Description
        System.out.println("- player resting:");
        // Change player activity
        this.player.setActivity(Activity.RESTING);
        this.player.setActivityDuration(HOUR);
        this.player.setCurrentHealth(50d);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.RESTING_ENDED));
        assertEquals("HP changes", 5d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateWorking() {
        // Description
        System.out.println("- player working:");
        // Change player activity
        this.player.setActivity(Activity.WORKING);
        this.player.setActivityDuration(HOUR);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.WORKING_ENDED));
        assertEquals("Gold changes", 5d, r.getPlayerGoldChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateTraining() {
        // Description
        System.out.println("- player training:");
        // Change player activity
        this.player.setActivity(Activity.TRAINING);
        this.player.setActivityDuration(HOUR);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.TRAINING_ENDED));
        assertEquals("XP changes", 5d, r.getPlayerExperienceChanges(), EPSILON);
    }
    
    @Test
    public void testUpdateWaiting() {
        // Description
        System.out.println("- player waiting:");
        // Change player activity
        this.player.setActivity(Activity.WAITING);
        this.player.setActivityDuration(HOUR);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.WAITING_ENDED));
    }
    
    @Test
    public void testUpdateLevelUp() {
        // Description
        System.out.println("- player level up:");
        // Change player activity
        this.player.addExperience(1000d);
        // Update player
        Result r = this.game.update(this.player);
        // Returns
        assertTrue(r.isSuccess());
        System.out.println(r);
        // Test return values
        assertTrue(r.getReturns().contains(Return.LEVEL_UP));
        assertEquals("Level", 5, this.player.getLevel(), EPSILON);
        assertEquals("Skill points", 4, this.player.getSkillPoints(), EPSILON);
    }
}