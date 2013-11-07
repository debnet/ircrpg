package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.enums.Potion;
import fr.debnet.ircrpg.enums.Return;
import fr.debnet.ircrpg.commons.Config;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.models.Result;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdebureaux
 */
public class DrinkTest {
    
    private static final double EPSILON = 0.01;
    
    private Game game;
    private Player player;
    
    public DrinkTest() {
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
        this.player = this.game.getPlayerByUsername("p1");
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testDrinkErrorNotEnoughPotions() {
        // Description
        System.out.println("- drink error (not enough potions):");
        // Drink health potion
        Result r = this.game.drinkPotion("p1", Potion.HEALTH.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.NOT_ENOUGH_HEALTH_POTIONS));
        // Drink health potion
        r = this.game.drinkPotion("p1", Potion.MANA.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.NOT_ENOUGH_MANA_POTIONS));
        // Drink health potion
        r = this.game.drinkPotion("p1", Potion.REMEDY.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.NOT_ENOUGH_REMEDY_POTIONS));
    }
    
    @Test
    public void testDrinkHealthPotion() {
        // Description
        System.out.println("- drink health potion:");
        // Modify player
        this.player.addHealthPotions(1);
        this.player.addHealth(-10d);
        // Drink health potion
        Result r = this.game.drinkPotion("p1", Potion.HEALTH.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.HEALTH_RESTORED));
        assertEquals("Health potions", 0, this.player.getHealthPotions().intValue());
        assertEquals("HP restored", 50d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testDrinkManaPotion() {
        // Description
        System.out.println("- drink mana potion:");
        // Modify player
        this.player.addManaPotions(1);
        this.player.addMana(-10d);
        // Drink health potion
        Result r = this.game.drinkPotion("p1", Potion.MANA.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.MANA_RESTORED));
        assertEquals("Mana potions", 0, this.player.getManaPotions().intValue());
        assertEquals("MP restored", 25d, r.getPlayerManaChanges(), EPSILON);
    }
    
    @Test
    public void testDrinkRemedyCureParalysis() {
        // Description
        System.out.println("- drink remedy (cure paralysis):");
        // Modify player
        this.player.addRemedyPotions(1);
        this.player.setStatus(Status.PARALYZED);
        this.player.setStatusDuration(Long.MAX_VALUE);
        // Drink health potion
        Result r = this.game.drinkPotion("p1", Potion.REMEDY.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_PARALYSIS_CURED));
        assertEquals("Remedy potions", 0, this.player.getManaPotions().intValue());
        assertEquals("Player status", Status.NORMAL, this.player.getStatus());
    }
    
    @Test
    public void testDrinkRemedyCurePoison() {
        // Description
        System.out.println("- drink remedy (cure poison):");
        // Modify player
        this.player.addRemedyPotions(1);
        this.player.setStatus(Status.POISONED);
        this.player.setStatusDuration(Long.MAX_VALUE);
        // Drink health potion
        Result r = this.game.drinkPotion("p1", Potion.REMEDY.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_POISON_CURED));
        assertEquals("Remedy potions", 0, this.player.getManaPotions().intValue());
        assertEquals("Player status", Status.NORMAL, this.player.getStatus());
    }
    
    @Test
    public void testDrinkErrorFullHealth() {
        // Description
        System.out.println("- health already full error :");
        // Modify player
        this.player.addHealthPotions(1);
        // Drink health potion
        Result r = this.game.drinkPotion("p1", Potion.HEALTH.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.HEALTH_ALREADY_RESTORED));
        assertEquals("Health potions", 1, this.player.getHealthPotions().intValue());
        assertEquals("HP restored", 0d, r.getPlayerHealthChanges(), EPSILON);
    }
    
    @Test
    public void testDrinkErrorFullMana() {
        // Description
        System.out.println("- mana already full error :");
        // Modify player
        this.player.addManaPotions(1);
        // Drink health potion
        Result r = this.game.drinkPotion("p1", Potion.MANA.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.MANA_ALREADY_RESTORED));
        assertEquals("Mana potions", 1, this.player.getManaPotions().intValue());
        assertEquals("MP restored", 0d, r.getPlayerManaChanges(), EPSILON);
    }
    
    @Test
    public void testDrinkErrorNoNegativeStatus() {
        // Description
        System.out.println("- no negative status error :");
        // Modify player
        this.player.addRemedyPotions(1);
        // Drink health potion
        Result r = this.game.drinkPotion("p1", Potion.REMEDY.toString());
        // Returns
        System.out.println(r);
        //System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.NO_NEGATIVE_STATUS));
        assertEquals("Remedy potions", 1, this.player.getRemedyPotions().intValue());
    }
}
