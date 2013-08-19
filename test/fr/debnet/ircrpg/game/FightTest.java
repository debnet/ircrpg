package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.enums.Return;
import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Spell;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.helpers.Helpers;
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
public class FightTest {
    
    private static final double EPSILON = 0.01;
    
    private Game game;
    
    public FightTest() {
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
        // Create enemy
        r = this.game.register("p2", null, "p2", null);
        assertTrue("Creating enemy", r.isSuccess());
        // Create spell
        Spell s = new Spell();
        s.setCode("s");
        s.setHealthDamage(10d);
        s.setManaCost(10d);
        boolean b = this.game.addSpell(s);
        assertTrue("Creating spell", b);
        // Learn spell to player
        this.game.getPlayerByUsername("p1").addSpell(s);
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testFightErrorUnknownPlayers() {
        // Description
        System.out.println("- unknown players error:");
        // Create fight
        Result r = this.game.fight("u1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.UNKNOWN_PLAYER));
        // Create fight
        r = this.game.fight("p1", "u2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.UNKNOWN_TARGET));
    }
    
    @Test
    public void testFightErrorOfflinePlayers() {
        // Description
        System.out.println("- offline players error:");
        // Set player 1 offline
        Player p1 = this.game.getPlayerByUsername("p1");
        p1.setOnline(false);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_OFFLINE));
        // Set player 1 online and player 2 offline
        p1.setOnline(true);
        Player p2 = this.game.getPlayerByUsername("p2");
        p2.setOnline(false);
        // Create fight
        r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.TARGET_OFFLINE));
    }
    
    @Test
    public void testFightAttackAndDefenseSucceed() {
        // Description
        System.out.println("- both attack and defense succeed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.5d);
        this.game.getRandom().addDefaultValue(0.5d);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.ATTACK_SUCCEED));
        assertTrue(r.hasReturn(Return.DEFENSE_SUCCEED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 30d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", -5d, r.getPlayerHealthChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 15d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", -5d, r.getTargetHealthChanges(), EPSILON);
    }
    
    @Test
    public void testFightAttackFailedDefenseSucceed() {
        // Description
        System.out.println("- attack failed but defense succeed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.6d);
        this.game.getRandom().addDefaultValue(0.4d);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.ATTACK_FAILED));
        assertTrue(r.hasReturn(Return.DEFENSE_SUCCEED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 0d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", -6d, r.getPlayerHealthChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 15d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", 0d, r.getTargetHealthChanges(), EPSILON);
    }
    
    @Test
    public void testFightAttackSucceedDefenseFailed() {
        // Description
        System.out.println("- attack succeed but defense failed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.4d);
        this.game.getRandom().addDefaultValue(0.6d);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.ATTACK_SUCCEED));
        assertTrue(r.hasReturn(Return.DEFENSE_FAILED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 30d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", 0d, r.getPlayerHealthChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 0d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", -6d, r.getTargetHealthChanges(), EPSILON);
    }
    
    @Test
    public void testFightAttackAndDefenseFailed() {
        // Description
        System.out.println("- both attack and defense failed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.9d);
        this.game.getRandom().addDefaultValue(0.9d);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.ATTACK_FAILED));
        assertTrue(r.hasReturn(Return.DEFENSE_FAILED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 0d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", 0d, r.getPlayerHealthChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 0d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", 0d, r.getTargetHealthChanges(), EPSILON);
    }
    
    @Test
    public void testFightOffensiveSpellSucceed() {
        // Description
        System.out.println("- offensive spell succeed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.5d);
        // Create fight
        Result r = this.game.fight("p1", "p2", "s");
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.MAGIC_SUCCEED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 30d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", 0d, r.getPlayerHealthChanges(), EPSILON);
        assertEquals("Attacker MP changes", -10d, r.getPlayerManaChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 0d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", -10d, r.getTargetHealthChanges(), EPSILON);
    }
    
    @Test
    public void testFightOffensiveSpellFailed() {
        // Description
        System.out.println("- offensive spell failed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.9d);
        // Create fight
        Result r = this.game.fight("p1", "p2", "s");
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.MAGIC_FAILED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 0d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", 0d, r.getPlayerHealthChanges(), EPSILON);
        assertEquals("Attacker MP changes", -10d, r.getPlayerManaChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 0d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", 0d, r.getTargetHealthChanges(), EPSILON);
    }
    
    @Test
    public void testFightStatusSpellSucceed() {
        // Description
        System.out.println("- status spell succeed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.1d);
        // Modify spell
        Spell s = this.game.getSpellByCode("s");
        s.setHealthDamage(0d);
        s.setStatus(Status.POISONED);
        s.setStatusDuration(Long.MAX_VALUE);
        // Create fight
        Result r = this.game.fight("p1", "p2", "s");
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.MAGIC_SUCCEED));
        assertTrue(r.hasReturn(Return.TARGET_POISONED));
        assertTrue("Defender poisoned", r.getTarget().getStatus() == Status.POISONED);
        assertTrue("Defender poison duration", r.getTarget().getStatusDuration() > 0);
        // Test attacker changes
        assertEquals("Attacker XP changes", 30d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker MP changes", -10d, r.getPlayerManaChanges(), EPSILON);
    }
    
    @Test
    public void testFightErrorNotEnoughMana() {
        // Description
        System.out.println("- spell error (not enough mana):");
        // Modify spell
        Spell s = this.game.getSpellByCode("s");
        s.setManaCost(100d);
        // Create fight
        Result r = this.game.fight("p1", "p2", "s");
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.NOT_ENOUGH_MANA));
    }
    
    @Test
    public void testFightErrorSpellNotLearned() {
        // Description
        System.out.println("- spell error (not learned):");
        // Remove learned spells from player
        Player p = this.game.getPlayerByUsername("p1");
        p.getSpells().clear();
        // Create fight
        Result r = this.game.fight("p1", "p2", "s");
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.SPELL_NOT_LEARNED));
    }
    
    @Test
    public void testFightErrorPlayerStatus() {
        // Description
        System.out.println("- player status error:");
        // Change player status
        Player p = this.game.getPlayerByUsername("p1");
        p.setStatus(Status.PARALYZED);
        p.setStatusDuration(Config.HOUR);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_PARALYZED));
        // Change player status
        p.setStatus(Status.DEAD);
        p.setStatusDuration(Config.HOUR);
        // Create fight
        r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.PLAYER_DEAD));
        // Change player status
        p.setStatus(Status.NORMAL);
        p = this.game.getPlayerByUsername("p2");
        p.setStatus(Status.DEAD);
        p.setStatusDuration(Config.HOUR);
        // Create fight
        r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertFalse(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.TARGET_DEAD));
    }
    
    @Test
    public void testFightErrorPlayerActivity() {
        // Description
        System.out.println("- player activity error:");
        // Change player status
        Activity[] activities = new Activity[] { 
            Activity.RESTING, Activity.TRAINING, Activity.WORKING 
        };
        for (Activity activity : activities) {
            // Change player activity
            Player p = this.game.getPlayerByUsername("p1");
            p.setActivity(activity);
            p.setActivityDuration(0l);
            // Create fight
            Result r = this.game.fight("p1", "p2", null);
            // Returns
            System.out.println(r);
            System.out.println(Helpers.getMessage(r));
            assertFalse(r.isSuccess());
            // Test return values
            assertTrue(r.hasReturn(Return.PLAYER_BUSY));
            // Reset player status
            p.setActivity(Activity.WAITING);
            p.setActivityDuration(0l);
            // Change player activity
            p = this.game.getPlayerByUsername("p2");
            p.setActivity(activity);
            p.setActivityDuration(0l);
            // Create fight
            r = this.game.fight("p1", "p2", null);
            // Returns
            System.out.println(r);
            System.out.println(Helpers.getMessage(r));
            assertFalse(r.isSuccess());
            // Test return values
            assertTrue(r.hasReturn(Return.TARGET_BUSY));
            // Reset player status
            p.setActivity(Activity.WAITING);
            p.setActivityDuration(0l);
        }
    }
    
    @Test
    public void testFightDefenderKilled() {
        // Description
        System.out.println("- defender killed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.5d);
        // Change player attack
        Player p = this.game.getPlayerByUsername("p1");
        p.setAttack(1000);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.ATTACK_SUCCEED));
        assertTrue(r.hasReturn(Return.TARGET_KILLED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 30d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", 0d, r.getPlayerHealthChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 0d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", -500d, r.getTargetHealthChanges(), EPSILON);
    }
    
    @Test
    public void testFightAttackerKilled() {
        // Description
        System.out.println("- attacker killed:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.9d);
        this.game.getRandom().addDefaultValue(0.5d);
        // Change player defense
        Player p = this.game.getPlayerByUsername("p2");
        p.setDefense(1000);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.ATTACK_FAILED));
        assertTrue(r.hasReturn(Return.DEFENSE_SUCCEED));
        assertTrue(r.hasReturn(Return.PLAYER_KILLED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 0d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", -500d, r.getPlayerHealthChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 15d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", 0d, r.getTargetHealthChanges(), EPSILON);
    }
    
    @Test
    public void testFightExperienceBonus() {
        // Description
        System.out.println("- experience bonus:");
        // Mock for random values
        this.game.getRandom().addDefaultValue(0.5d);
        this.game.getRandom().addDefaultValue(0.5d);
        // Change player level
        Player p = this.game.getPlayerByUsername("p2");
        p.setLevel(2);
        // Create fight
        Result r = this.game.fight("p1", "p2", null);
        // Returns
        System.out.println(r);
        System.out.println(Helpers.getMessage(r));
        assertTrue(r.isSuccess());
        // Test return values
        assertTrue(r.hasReturn(Return.ATTACK_SUCCEED));
        assertTrue(r.hasReturn(Return.DEFENSE_SUCCEED));
        // Test attacker changes
        assertEquals("Attacker XP changes", 33d, r.getPlayerExperienceChanges(), EPSILON);
        assertEquals("Attacker HP changes", -5d, r.getPlayerHealthChanges(), EPSILON);
        // Test defender changes
        assertEquals("Defender XP changes", 13.5d, r.getTargetExperienceChanges(), EPSILON);
        assertEquals("Defender HP changes", -5d, r.getTargetHealthChanges(), EPSILON);
    }
}