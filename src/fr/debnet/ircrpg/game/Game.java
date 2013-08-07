/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.game;

import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.DAO;
import fr.debnet.ircrpg.Strings;
import fr.debnet.ircrpg.enums.Action;
import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Potion;
import fr.debnet.ircrpg.enums.Return;
import fr.debnet.ircrpg.enums.Stat;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.game.queues.EventQueue;
import fr.debnet.ircrpg.game.queues.IGameQueue;
import fr.debnet.ircrpg.game.queues.INotifiable;
import fr.debnet.ircrpg.game.queues.UpdateQueue;
import fr.debnet.ircrpg.helpers.CheckItem;
import fr.debnet.ircrpg.helpers.CheckPlayer;
import fr.debnet.ircrpg.helpers.CheckPotion;
import fr.debnet.ircrpg.helpers.CheckSpell;
import fr.debnet.ircrpg.helpers.Helpers;
import fr.debnet.ircrpg.mock.Random;
import fr.debnet.ircrpg.models.Item;
import fr.debnet.ircrpg.models.Modifiers;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Result;
import fr.debnet.ircrpg.models.Spell;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marc
 */
public class Game {

    private Random random;
    private static final int HOUR = 3600000;
    
    private Map<String, Player> playersByNickname;
    private Map<String, Player> playersByUsername;
    private Map<String, Item> itemsByCode;
    private Map<String, Spell> spellsByCode;
    
    private List<IGameQueue> queues;

    /**
     * Constructor : initialize the game by fetching in memory all data from database
     */
    public Game() {
        this.random = new Random();
        this.playersByNickname = new HashMap<String, Player>();
        this.playersByUsername = new HashMap<String, Player>();
        this.itemsByCode = new HashMap<String, Item>();
        this.spellsByCode = new HashMap<String, Spell>();
        this.queues = new ArrayList<IGameQueue>();

        if (Config.PERSISTANCE) {
            List<Player> players = DAO.<Player>getObjectList("from " + Model.PLAYER);
            List<Item> items = DAO.<Item>getObjectList("from " + Model.ITEM);
            List<Spell> spells = DAO.<Spell>getObjectList("from " + Model.SPELL);

            // Get all players
            for (Player player : players) {
                this.playersByNickname.put(player.getNickname(), player);
                this.playersByUsername.put(player.getUsername(), player);
            }
            // Get all items
            for (Item item : items) {
                this.itemsByCode.put(item.getCode(), item);
            }
            // Get all spells
            for (Spell spell : spells) {
                this.spellsByCode.put(spell.getCode(), spell);
            }
        }
        
        // Run queues
        this.queues.add(new UpdateQueue(this));
        this.queues.add(new EventQueue(this));
    }
    
    /**
     * Register a notifiable object to all queues
     * @param notifiable Notifiable object
     */
    public void registerNotifiable(INotifiable notifiable) {
        for (IGameQueue queue : this.queues) {
            queue.register(notifiable);
        }
    }
    
    /**
     * Update queues
     */
    public void updateQueues() {
        for (IGameQueue queue : this.queues) {
            queue.update();
        }
    }

    /**
     * Get all players
     * @return Collection of all players
     */
    public Collection<Player> getAllPlayers() {
        return this.playersByNickname.values();
    }
    
    /**
     * Get player by nickname
     * @param nickname Nickname
     * @return Player instance or null if not found
     */
    public Player getPlayerByNickname(String nickname) {
        if (this.playersByNickname.containsKey(nickname)) {
            return this.playersByNickname.get(nickname);
        }
        return null;
    }

    /**
     * Get player by username
     * @param username Username
     * @return Player instance or null if not found
     */
    public Player getPlayerByUsername(String username) {
        if (this.playersByUsername.containsKey(username)) {
            return this.playersByUsername.get(username);
        }
        return null;
    }
    
    /**
     * Get item by its code
     * @param item Item code (unique)
     * @return Item instance or null if not found
     */
    public Item getItemByCode(String item) {
        if (this.itemsByCode.containsKey(item)) {
            return this.itemsByCode.get(item);
        }
        return null;
    }
    
    /**
     * Get spell by its code
     * @param spell Spell code (unique)
     * @return Spell instance or null if not found
     */
    public Spell getSpellByCode(String spell) {
        if (this.spellsByCode.containsKey(spell)) {
            return this.spellsByCode.get(spell);
        }
        return null;
    }
    
    /**
     * Update player data
     * @param sender Player's nickname
     * @return Result
     */
    public Result updatePlayerByNickname(String sender) {
        Player player = this.getPlayerByNickname(sender);
        if (player != null) {
            return this.update(player);
        }
        return null;
    }
    
    /**
     * Update player data
     * @see #update(fr.debnet.ircrpg.models.Player, boolean, fr.debnet.ircrpg.Result) 
     * @param player Player instance
     * @return Result
     */
    public Result update(Player player) {
        return this.update(player, false);
    }
    
    /**
     * Update player data (activity, status and experience)
     * @param player Player instance
     * @param save Save the player's information in database
     * @return Result
     */
    protected Result update(Player player, boolean save) {
        Result result = new Result(Action.UPDATE, player);
        // Get time difference
        java.util.Calendar lastUpdate = player.getLastUpdate();
        Calendar current = Calendar.getInstance();
        int diff = (int) (current.getTimeInMillis() - lastUpdate.getTimeInMillis());
        // Items modifiers
        Modifiers modifiers = new Modifiers(player);
        // Status
        double hours = diff > player.getStatusDuration()
            ? player.getStatusDuration() * 1d / HOUR : diff * 1d / HOUR;
        switch (player.getStatus()) {
            case POISONED: {
                // Removing status if timeout
                if (diff >= player.getStatusDuration()) {
                    result.addReturn(Return.POISON_CURED);
                    player.setStatus(Status.NORMAL);
                    player.setStatusDuration(0);
                } else {
                    player.addStatusDuration(-diff);
                }
                // Lower health points
                double damage = (player.getMaxHealth() * 
                    (Config.POISON_EFFECT + modifiers.getPoisonEffect())) * hours;
                player.addHealth(-damage);
                // Update statistics
                player.addDamageTaken(damage);
                // Update return
                result.addPlayerHealthChanges(-damage);
                break;
            }
            case PARALYZED: {
                // Removing status if timeout
                if (diff >= player.getStatusDuration()) {
                    result.addReturn(Return.PARALYSIS_CURED);
                    player.setStatus(Status.NORMAL);
                    player.setStatusDuration(0);
                } else {
                    player.addStatusDuration(-diff);
                }
                break;
            }
            case DEAD: {
                // Removing status if timeout
                if (diff >= player.getStatusDuration()) {
                    result.addReturn(Return.DEATH_CURED);
                    player.setStatus(Status.NORMAL);
                    player.setStatusDuration(0);
                    double hp = player.getMaxHealth() + modifiers.getHealth();
                    player.setCurrentHealth(hp);
                    double mp = player.getMaxMana() + modifiers.getMana();
                    player.setCurrentMana(mp);
                    // Update return
                    result.addPlayerHealthChanges(hp);
                    result.addPlayerManaChanges(mp);
                } else {
                    player.addStatusDuration(-diff);
                }
                break;
            }
        }
        // Activity
        hours = diff / HOUR;
        switch (player.getActivity()) {
            case RESTING: {
                // Restoring health points
                double heal = (Config.RATE_HEALTH + modifiers.getHealthRate()) * hours;
                int maxHp = player.getMaxHealth() + modifiers.getHealth();
                double hp = player.getCurrentHealth() + heal;
                hp = hp > maxHp ? maxHp : hp;
                player.setCurrentHealth(hp);
                // Removing activity if timeout
                player.addActivityDuration(diff);
                if (player.getActivityDuration() >= Config.RESTING_TIME_MAX) {
                    player.setActivity(Activity.WAITING);
                    player.setActivityDuration(0);
                    result.addReturn(Return.RESTING_ENDED);
                }
                // Update statistics
                player.addTimeResting((int) (hours * HOUR));
                // Update return
                result.addPlayerHealthChanges(heal);
                break;
            }
            case TRAINING: {
                // Add experience points
                double xp = (Config.RATE_EXPERIENCE + modifiers.getExperienceRate()) * hours;
                player.addExperience(xp);
                // Removing activity if timeout
                player.addActivityDuration(diff);
                if (player.getActivityDuration() >= Config.TRAINING_TIME_MAX) {
                    player.setActivity(Activity.WAITING);
                    player.setActivityDuration(0);
                    result.addReturn(Return.TRAINING_ENDED);
                }
                // Update statistics
                player.addTimeTraining((int) (hours * HOUR));
                // Update return
                result.addPlayerExperienceChanges(xp);
                break;
            }
            case WORKING: {
                // Earn gold coins 
                double gold = (Config.RATE_GOLD + modifiers.getGoldRate()) * hours;
                player.addGold(gold);
                // Removing activity if timeout
                player.addActivityDuration(diff);
                if (player.getActivityDuration() >= Config.WORKING_TIME_MAX) {
                    player.setActivity(Activity.WAITING);
                    player.setActivityDuration(0);
                    result.addReturn(Return.WORKING_ENDED);
                }
                // Update statistics
                player.addTimeWorking((int) (hours * HOUR));
                // Update return
                result.addPlayerGoldChanges(gold);
                break;
            }
            case WAITING: {
                player.addActivityDuration(diff);
                if (player.getActivityDuration() >= Config.ACTIVITY_PENALTY) {
                    player.setActivity(Activity.NONE);
                    player.setActivityDuration(0);
                    result.addReturn(Return.WAITING_ENDED);
                }
                break;
            }
        }
        // Check if player is dead poisonned
        if (player.getCurrentHealth() <= 0) {
            // Change status
            player.setStatus(Status.DEAD);
            player.setStatusDuration(Config.DEATH_PENALTY * HOUR);
            player.setCurrentHealth(0d);
            // Reset activity
            player.setActivity(Activity.NONE);
            player.setActivityDuration(0);
            // Update return
            result.addReturn(Return.KILLED_BY_POISON);
            // Update statistics
            player.addDeaths(1);
        }
        // Experience gained
        for (int level = player.getLevel() + 1; level < 100; level++) {
            int needed = ((level * (level - 1)) / 2) * Config.RATE_LEVEL;
            if (player.getExperience() >= needed) {
                player.addLevel(1);
                player.addSkillPoints(Config.RATE_SKILL);
                result.addReturn(Return.LEVEL_UP);
            } else {
                break;
            }
        }
        // Save object
        player.addTimeIngame(diff);
        player.setLastUpdate(current);
        if (Config.PERSISTANCE && save) {
            if (!DAO.<Player>setObject(player)) {
                result.addReturn(Return.PERSISTANCE_ERROR);
                return result;
            }
        }
        // Update queues
        this.updateQueues();
        // Return
        result.setSuccess(true);
        if (Config.PERSISTANCE && !result.getReturns().isEmpty()) {
            DAO.<Result>addObject(result);
        }
        return result;
    }

    /**
     * Fight between two players (with spell or not)
     * @param sender Attacker's nickname
     * @param target Defender's nickname
     * @param spell Spell code (or null if physical fight)
     * @return Result
     */
    public Result fight(String sender, String target, String magic) {
        Result result = new Result(Action.FIGHT);
        boolean self = sender.equals(target);
        // Get attacker
        Player attacker = this.getPlayer(result, sender);
        if (attacker == null) return result;
        // Get defender
        Player defender;
        if (!self) {
            defender = this.getPlayer(result, target, true);
            if (defender == null) return result;
        } else defender = attacker;
        // Check attacker
        if (!Helpers.checkPlayer(result, attacker, 
            CheckPlayer.from(
                CheckPlayer.IS_BUSY, 
                CheckPlayer.IS_DEAD, 
                CheckPlayer.IS_PARALYZED
            )
        )) return result;
        // Check defender
        if (!self && !Helpers.checkPlayer(result, defender, 
            CheckPlayer.from(
                CheckPlayer.IS_BUSY, 
                CheckPlayer.IS_DEAD, 
                CheckPlayer.IS_PARALYZED, 
                CheckPlayer.IS_TARGET
            )
        )) return result;
        // Get spell
        Spell spell = null;
        if (magic != null) {
            spell = this.getSpellByCode(magic);
            if (spell == null) {
                // Update return
                result.addReturn(Return.UNKNOWN_SPELL);
                return result;
            } else if (!attacker.getSpells().contains(spell)) {
                // Update return
                result.addReturn(Return.SPELL_NOT_LEARNED);
                return result;
            }
        }
        // Items modifiers
        Modifiers attackerModifiers = attacker.getModifiers();
        Modifiers defenderModifiers = defender.getModifiers();
        // Attacker phase
        if (spell == null) {
            if (self) {
                // Update return
                result.addReturn(Return.NOT_SELF_ATTACK);
                return result;
            }
            // With no spell
            double accuracy = Config.ATTACK_ACCURACY + attackerModifiers.getAttackAccuracy();
            double chance = random.nextDouble();
            if (chance > accuracy) {
                // Update return
                result.addReturn(Return.ATTACK_FAILED);
            } else {
                // Update return
                result.addReturn(Return.ATTACK_SUCCEED);
                // Health change
                double damage = (attacker.getAttack() + attackerModifiers.getAttack()) * (1d - chance);
                defender.addHealth(-damage);
                // Update return
                result.addTargetHealthChanges(-damage);
                // Update statistics
                attacker.addDamageGiven(damage);
                defender.addDamageTaken(damage);
                // Is defender dead?
                if (defender.getCurrentHealth() <= 0) {
                    defender.setStatus(Status.DEAD);
                    defender.setStatusDuration(Config.DEATH_PENALTY * HOUR);
                    defender.setCurrentHealth(0d);
                    // Update return
                    result.addReturn(Return.TARGET_KILLED);
                    // Update statistics
                    attacker.addKills(1);
                    defender.addDeaths(1);
                }
                // Experience gained (attacker)
                double bonus = 1 + (defender.getLevel() - attacker.getLevel()) * Config.EXPERIENCE_BONUS;
                bonus = bonus < 0 ? 0 : bonus;
                double xp = Config.EXPERIENCE_ATTACK * bonus;
                attacker.addExperience(xp);
                // Update return
                result.addPlayerExperienceChanges(xp);
            }
            // Defender phase
            if (defender.getStatus() != Status.DEAD) {
                if (defender.getStatus() == Status.PARALYZED) {
                    // Update return
                    result.addReturn(Return.NO_STRIKE_BACK);
                } else {
                    accuracy = Config.DEFENSE_ACCURACY + defenderModifiers.getDefenseAccuracy();
                    chance = random.nextDouble();
                    if (chance > accuracy) {
                        result.addReturn(Return.DEFENSE_FAILED);
                    } else {
                        // Update return
                        result.addReturn(Return.DEFENSE_SUCCEED);
                        // Health change
                        double damage = (defender.getDefense() + defenderModifiers.getDefense()) * (1d - chance);
                        attacker.addHealth(-damage);
                        // Update return
                        result.addPlayerHealthChanges(-damage);
                        // Update statistics
                        defender.addDamageGiven(damage);
                        attacker.addDamageTaken(damage);
                        // Is attacker dead?
                        if (attacker.getCurrentHealth() <= 0) {
                            attacker.setStatus(Status.DEAD);
                            attacker.setStatusDuration(Config.DEATH_PENALTY * HOUR);
                            attacker.setCurrentHealth(0d);
                            // Update return
                            result.addReturn(Return.PLAYER_KILLED);
                            // Update statistics
                            defender.addKills(1);
                            attacker.addDeaths(1);
                        }
                        // Experience gained (defenser)
                        double bonus = 1 + (attacker.getLevel() - defender.getLevel()) * Config.EXPERIENCE_BONUS;
                        bonus = bonus < 0 ? 0 : bonus;
                        double xp = Config.EXPERIENCE_DEFENSE * bonus;
                        defender.addExperience(xp);
                        // Update statistics
                        result.addTargetExperienceChanges(xp);
                    }
                }
            }
        } else {
            result.setAction(Action.MAGIC);
            // With spell
            if (self && !spell.getIsSelf()) {
                // Update return
                result.addReturn(Return.NOT_SELF_SPELL);
                return result;
            }
            if (attacker.getCurrentMana() < spell.getManaCost()) {
                // Update return
                result.addReturn(Return.NOT_ENOUGH_MANA);
                return result;
            }
            double accuracy = Config.MAGIC_ACCURACY + attackerModifiers.getMagicAccuracy();
            if (random.nextDouble() > accuracy) {
                result.addReturn(Return.MAGIC_FAILED);
            } else {
                // Update return
                result.addReturn(Return.MAGIC_SUCCEED);
                result.setDetails(spell.getName());
                // Health change
                double hp = defender.getCurrentHealth();
                double maxHp = defender.getMaxHealth() + defenderModifiers.getHealth();
                hp -= spell.getHealthDamage();
                hp = hp > maxHp ? maxHp : hp < 0 ? 0 : hp;
                defender.setCurrentHealth(hp);
                // Update return
                result.addTargetHealthChanges(-spell.getHealthDamage());
                // Update statistics
                if (spell.getHealthDamage() > 0) {
                    attacker.addDamageGiven(spell.getHealthDamage());
                    defender.addDamageTaken(spell.getHealthDamage());
                    // Update return
                    result.addReturn(Return.MAGIC_DAMAGE_HEALTH);
                } else if (spell.getHealthDamage() < 0) {
                    // Update return
                    result.addReturn(Return.MAGIC_RESTORE_HEALTH);
                }
                // Is defender dead?
                if (hp <= 0) {
                    defender.setStatus(Status.DEAD);
                    defender.setStatusDuration(Config.DEATH_PENALTY * HOUR);
                    defender.setCurrentHealth(0d);
                    // Update return
                    result.addReturn(Return.TARGET_KILLED);
                    // Update statistics
                    attacker.addKills(1);
                    defender.addDeaths(1);
                } else {
                    // Status change
                    if (spell.getStatus() != Status.NONE) {
                        defender.setStatus(spell.getStatus());
                        defender.setStatusDuration(spell.getStatusDuration());
                        // Update return
                        switch (spell.getStatus()) {
                            case PARALYZED:
                                result.addReturn(Return.TARGET_PARALYZED);
                                break;
                            case POISONED:
                                result.addReturn(Return.TARGET_POISONED);
                                break;
                            case NORMAL:
                                result.addReturn(Return.TARGET_CURED);
                                break;
                        }
                    }
                }
                // Experience earned (if offensive spell)
                if (!spell.getIsSelf()) {
                    double bonus = 1 + (defender.getLevel() - attacker.getLevel()) * Config.EXPERIENCE_BONUS;
                    bonus = bonus < 0 ? 0 : bonus;
                    double xp = Config.EXPERIENCE_ATTACK * bonus;
                    attacker.addExperience(xp);
                    // Update statistics
                    result.addPlayerExperienceChanges(xp);
                }
            }
            // Mana consumption
            attacker.addMana(-spell.getManaCost());
            // Update return
            result.addPlayerManaChanges(-spell.getManaCost());
        }
        // Update players
        this.update(attacker);
        if (!self) this.update(defender);
        // Return
        result.setSuccess(true);
        if (Config.PERSISTANCE) {
            DAO.<Result>addObject(result);
        }
        return result;
    }
    
    /**
     * Steal a player
     * @param sender Player's nickname
     * @param target Target's nickname
     * @return Result
     */
    public Result steal(String sender, String target) {
        Result result = new Result(Action.STEAL);
        if (sender.equals(target)) {
            result.addReturn(Return.NOT_SELF_THEFT);
            return result;
        }
        // Get attacker
        Player attacker = this.getPlayer(result, sender);
        if (attacker == null) return result;
        // Get defender
        Player defender = this.getPlayer(result, target, true);
        if (defender == null) return result;
        // Items modifiers
        Modifiers attackerModifiers = attacker.getModifiers();
        Modifiers defenderModifiers = defender.getModifiers();
        // Check attacker
        if (!Helpers.checkPlayer(result, attacker, 
            CheckPlayer.from(
                CheckPlayer.IS_BUSY,
                CheckPlayer.IS_DEAD,
                CheckPlayer.IS_PARALYZED
            )
        )) return result;
        // Check defender
        if (!Helpers.checkPlayer(result, defender, 
            CheckPlayer.from(
                CheckPlayer.IS_BUSY,
                CheckPlayer.IS_DEAD,
                CheckPlayer.IS_TARGET
            )
        )) return result;
        // Attacker phase
        double accuracy = Config.THEFT_CHANCE + attackerModifiers.getTheftChance();
        double chance = random.nextDouble();
        if (chance > accuracy) {
            // Update return
            result.addReturn(Return.THEFT_FAILED);
            // Health change
            double damage = (defender.getDefense() + defenderModifiers.getDefense()) * (1d - chance);
            attacker.addHealth(-damage);
            // Update return
            result.addPlayerHealthChanges(-damage);
            // Update statistics
            defender.addDamageGiven(damage);
            attacker.addDamageTaken(damage);
            // Is attacker dead?
            if (attacker.getCurrentHealth() <= 0) {
                attacker.setStatus(Status.DEAD);
                attacker.setStatusDuration(Config.DEATH_PENALTY * HOUR);
                attacker.setCurrentHealth(0d);
                // Update return
                result.addReturn(Return.PLAYER_KILLED);
                // Update statistics
                defender.addKills(1);
                attacker.addDeaths(1);
            }
            // Experience gained (defenser)
            double bonus = 1 + (attacker.getLevel() - defender.getLevel()) * Config.EXPERIENCE_BONUS;
            bonus = bonus < 0 ? 0 : bonus;
            double xp = Config.EXPERIENCE_DEFENSE * bonus;
            defender.addExperience(xp);
            // Update statistics
            result.addTargetExperienceChanges(xp);
        } else {
            // Update return
            result.addReturn(Return.THEFT_SUCCEED);
            // Gold stolen
            double gold = (Config.THEFT_GOLD + attackerModifiers.getTheftGold()) 
                    * defender.getGold() * random.nextDouble();
            // Update players
            attacker.addGold(gold);
            defender.addGold(-gold);
            // Update return
            result.addPlayerGoldChanges(gold);
            result.addTargetGoldChanges(-gold);
            // Update statistics
            attacker.addMoneyStolen(gold);
        }
        // Update players
        this.update(attacker);
        this.update(defender);
        // Return
        result.setSuccess(true);
        if (Config.PERSISTANCE) {
            DAO.<Result>addObject(result);
        }
        return result;
    }
    
    /**
     * Drink a potion
     * @param sender Player's nickname
     * @param potion Potion type
     * @return Result
     */
    public Result drink(String sender, Potion potion) {
        Result result = new Result(Action.DRINK);
        // Get the player
        Player player = this.getPlayer(result, sender);
        if (player == null) return result;
        // Check player
        if (!Helpers.checkPlayer(result, player, 
            CheckPlayer.from(
                CheckPlayer.IS_BUSY,
                CheckPlayer.IS_DEAD
            )
        )) return result;
        // Player modifiers
        Modifiers modifiers = player.getModifiers();
        // Check potion
        if (!Helpers.checkPotion(result, player, potion, 
            CheckPotion.from(
                CheckPotion.CAN_CURE,
                CheckPotion.HAS_ENOUGH
            )
        )) return result;
        // Potion type        
        switch (potion) {
            case HEALTH: {
                double hp = player.getCurrentHealth();
                double maxHp = player.getMaxHealth() + modifiers.getHealth();
                double heal = maxHp * (Config.POTION_HEALTH_RESTORE + modifiers.getPotionHealth());
                hp = hp + heal > maxHp ? maxHp : hp + heal;
                // Update player
                player.setCurrentHealth(hp);
                player.addHealthPotions(-1);
                // Update return
                result.addPlayerHealthChanges(heal);
                result.addReturn(Return.HEALTH_RESTORED);
                break;
            }
            case MANA: {
                double mp = player.getCurrentMana();
                double maxMp = player.getMaxMana()+ modifiers.getMana();
                double heal = maxMp * (Config.POTION_MANA_RESTORE + modifiers.getPotionMana());
                mp = mp + heal > maxMp ? maxMp : mp + heal;
                // Update player
                player.setCurrentMana(mp);
                player.addManaPotions(-1);
                // Update return
                result.addPlayerManaChanges(heal);
                result.addReturn(Return.MANA_RESTORED);
                break;
            }
            case REMEDY: {
                Status status = player.getStatus();
                // Update player
                player.setStatus(Status.NORMAL);
                player.setStatusDuration(0);
                player.addRemedyPotions(-1);
                // Update return
                switch (status) {
                    case PARALYZED: 
                        result.addReturn(Return.PARALYSIS_CURED);
                        break;
                    case POISONED:
                        result.addReturn(Return.POISON_CURED);
                        break;
                }
                break;
            }
        }
        // Update player
        this.update(player);
        // Return
        result.setSuccess(true);
        if (Config.PERSISTANCE) {
            DAO.<Result>addObject(result);
        }
        return result;
    }
    
    /**
     * Start an activity
     * @param sender Player's nickname
     * @param activity Activity
     * @return Result
     */
    public Result startActivity(String sender, Activity activity) {
        Result result = new Result(Action.START_ACTIVITY);
        // Get the player
        Player player = this.getPlayer(result, sender);
        if (player == null) return result;
        // Check player
        if (!Helpers.checkPlayer(result, player, 
            CheckPlayer.from(
                CheckPlayer.IS_DEAD,
                CheckPlayer.IS_PARALYZED
            )
        )) return result;
        // Check the activity penalty
        if (player.getActivity() == Activity.WAITING) {
            result.addReturn(Return.PLAYER_IS_WAITING);
            return result;
        }
        // Check the activity of the player
        if (player.getActivity() != Activity.NONE) {
            result.addReturn(Return.PLAYER_BUSY);
            return result;
        }
        // Set player activity
        player.setActivity(activity);
        player.setActivityDuration(0);
        // Update player
        this.update(player);
        // Return
        result.setSuccess(true);
        return result;
    }
    
    /**
     * End the current player activity
     * @param sender Player's nickname
     * @return Result
     */
    public Result endActivity(String sender) {
        Result result = new Result(Action.END_ACTIVITY);
        // Get the player
        Player player = this.getPlayer(result, sender);
        if (player == null) return result;
        // Earned
        double earned = player.getActivityDuration() * 1d / HOUR;
        // Activity check
        switch (player.getActivity()) {
            case NONE:
            case WAITING: {
                result.addReturn(Return.PLAYER_NOT_BUSY);
                return result;
            }
            case RESTING: {
                if (player.getActivityDuration() < Config.RESTING_TIME_MIN) {
                    result.addReturn(Return.NOT_RESTED_ENOUGH);
                    return result;
                }
                earned *= Config.RATE_HEALTH;
                break;
            }
            case WORKING: {
                if (player.getActivityDuration() < Config.WORKING_TIME_MIN) {
                    result.addReturn(Return.NOT_WORKED_ENOUGH);
                    return result;
                }
                earned *= Config.RATE_GOLD;
                break;
            }
            case TRAINING: {
                if (player.getActivityDuration() < Config.TRAINING_TIME_MIN) {
                    result.addReturn(Return.NOT_TRAINED_ENOUGH);
                    return result;
                }
                earned *= Config.RATE_EXPERIENCE;
                break;
            }
        }
        // Clear activity
        player.setActivity(Activity.WAITING);
        player.setActivityDuration(0);
        // Update player
        this.update(player);
        // Return
        result.setValue(earned);
        result.setSuccess(true);
        return result;
    }
    
    /**
     * Upgrade a player
     * @param sender Player's nickname
     * @param stat Stat to raise
     * @return Result
     */
    public Result upgrade(String sender, Stat stat) {
        Result result = new Result(Action.UPGRADE);
        // Get the player
        Player player = this.getPlayer(result, sender);
        if (player == null) return result;
        // Check player
        if (!Helpers.checkPlayer(result, player, 
            CheckPlayer.from(
                CheckPlayer.IS_BUSY,
                CheckPlayer.IS_DEAD,
                CheckPlayer.IS_PARALYZED
            )
        )) return result;
        // Check player skill points
        if (player.getSkillPoints() < 1) {
            result.addReturn(Return.NOT_ENOUGH_SKILL_POINTS);
            return result;
        }
        // Rising stats
        switch (stat) {
            case HEALTH: {
                player.addMaxHealth(Config.SKILL_HEALTH);
                player.addHealth(Config.SKILL_HEALTH);
                result.addReturn(Return.HEALTH_INCREASED);
                result.setValue(Config.SKILL_HEALTH);
                break;
            }
            case MANA: {
                player.addMaxMana(Config.SKILL_MANA);
                player.addMana(Config.SKILL_MANA);
                result.addReturn(Return.MANA_INCREASED);
                result.setValue(Config.SKILL_MANA);
                break;
            }
            case ATTACK: {
                player.addAttack(Config.SKILL_ATTACK);
                result.addReturn(Return.ATTACK_INCREASED);
                result.setValue(Config.SKILL_ATTACK);
                break;
            }
            case DEFENSE: {
                player.addDefense(Config.SKILL_DEFENSE);
                result.addReturn(Return.DEFENSE_INCREASED);
                result.setValue(Config.SKILL_DEFENSE);
                break;
            }
        }
        // Update player
        this.update(player);
        // Return
        result.setSuccess(true);
        return result;
    }
    
    /**
     * Buy an item in the shop
     * @param sender Player's nickname
     * @param code Item's code
     * @return Result
     */
    public Result buy(String sender, String code) {
        Result result = new Result(Action.BUY);
        // Get the player
        Player player = this.getPlayer(result, sender);
        if (player == null) return result;
        // Check if item is potion
        Potion potion = Potion.NONE;
        for (Potion p : Potion.values()) {
            if (p.getText().equalsIgnoreCase(code)) {
                potion = p;
                break;
            }
        }
        // Check item
        Item item = null;
        if (potion == Potion.NONE) {
            item = this.getItemByCode(code);
            if (!Helpers.checkItem(result, player, item, 
                CheckItem.from(
                    CheckItem.CAN_BE_AFFORDED,
                    CheckItem.CAN_BE_WORN,
                    CheckItem.HAS_ENOUGH_STOCK,
                    CheckItem.IS_ADMIN_ONLY,
                    CheckItem.IS_ALREADY_BOUGHT,
                    CheckItem.TYPE_ALREADY_EQUIPPED
                )
            )) return result;
        }
        // Check player
        if (!Helpers.checkPlayer(result, player, 
            CheckPlayer.from(
                CheckPlayer.IS_BUSY,
                CheckPlayer.IS_DEAD,
                CheckPlayer.IS_PARALYZED
            )
        )) return result;
        // Add item in player's inventory
        if (item != null) {
            player.addItem(item);
            player.addGold(-item.getGoldCost());
            result.addReturn(Return.ITEM_SUCCESSFULLY_BOUGHT);
            result.setDetails(item.getName());
            // Increase current health
            if (item.getHealthModifier() > 0) {
                player.addHealth(item.getHealthModifier());
            }
            // Increase current mana
            if (item.getManaModifier() > 0) {
                player.addMana(item.getManaModifier());
            }
            // Update return
            result.addPlayerGoldChanges(-item.getGoldCost());
            // Update statistics
            player.addMoneySpent(item.getGoldCost());
            // Decrease stock
            item.addStock(-1);
        } else {
            // Check potion price
            if (!Helpers.checkPotion(result, player, potion, 
                CheckPotion.from(
                    CheckPotion.CAN_BE_AFFORDED
                )
            )) return result;
            // Potion type
            switch (potion) {
                case HEALTH: {
                    player.addHealthPotions(1);
                    player.addGold(-Config.POTION_HEALTH_COST);
                    // Update return
                    result.addPlayerGoldChanges(-Config.POTION_HEALTH_COST);
                    // Update statistics
                    player.addMoneySpent(Config.POTION_HEALTH_COST);
                    break;
                }
                case MANA: {
                    player.addManaPotions(1);
                    player.addGold(-Config.POTION_MANA_COST);
                    // Update return
                    result.addPlayerGoldChanges(-Config.POTION_MANA_COST);
                    // Update statistics
                    player.addMoneySpent(Config.POTION_MANA_COST);
                    break;
                }
                case REMEDY: {
                    player.addRemedyPotions(1);
                    player.addGold(-Config.POTION_REMEDY_COST);
                    // Update return
                    result.addPlayerGoldChanges(-Config.POTION_REMEDY_COST);
                    // Update statistics
                    player.addMoneySpent(Config.POTION_REMEDY_COST);
                    break;
                }
            }
            result.addReturn(Return.POTION_SUCCESSFULLY_BOUGHT);
            result.setDetails(potion.getText());
        }
        if (Config.PERSISTANCE) {
            if (!DAO.<Item>setObject(item)) {
                result.addReturn(Return.PERSISTANCE_ERROR);
                return result;
            }
        }
        // Update player
        this.update(player);
        // Return
        result.setSuccess(true);
        return result;
    }
    
    /**
     * Sell an item
     * @param sender Player's nickname
     * @param code Item's code
     * @return result
     */
    public Result sell(String sender, String code) {
        Result result = new Result(Action.SELL);
        // Get the player
        Player player = this.getPlayer(result, sender);
        if (player == null) return result;
        // Check item
        Item item = this.getItemByCode(code);
        if (!Helpers.checkItem(result, player, item, 
            CheckItem.from(
                CheckItem.IS_ALREADY_BOUGHT
            )
        )) return result;
        // Sell item
        player.removeItem(item);
        player.addGold(item.getGoldCost() * Config.SELL_MALUS);
        result.addReturn(Return.ITEM_SUCCESSFULLY_SOLD);
        result.setDetails(item.getName());
        // Increase stock
        item.addStock(1);
        if (Config.PERSISTANCE) {
            DAO.<Item>setObject(item);
        }
        // Update player
        this.update(player);
        // Return
        result.setSuccess(true);
        return result;
    }
    
    /**
     * Learn a spell
     * @param sender Player's nickname
     * @param code Spell's code
     * @return Result
     */
    public Result learn(String sender, String code) {
        Result result = new Result(Action.LEARN);
        // Get the player
        Player player = this.getPlayer(result, sender);
        if (player == null) return result;
        // Check spell
        Spell spell = this.getSpellByCode(code);
        if (!Helpers.checkSpell(result, player, spell, 
            CheckSpell.from(
                CheckSpell.CAN_BE_AFFORDED,
                CheckSpell.IS_ADMIN_ONLY,
                CheckSpell.IS_ALREADY_LEARNED,
                CheckSpell.CAN_BE_LEARNED
            )
        )) return result;
        // Check player
        if (!Helpers.checkPlayer(result, player, 
            CheckPlayer.from(
                CheckPlayer.IS_BUSY,
                CheckPlayer.IS_DEAD,
                CheckPlayer.IS_PARALYZED
            )
        )) return result;
        // Add item in player's inventory
        player.addSpell(spell);
        player.addGold(-spell.getGoldCost());
        result.setDetails(spell.getName());
        // Update return
        result.addPlayerGoldChanges(-spell.getGoldCost());
        result.addReturn(Return.SPELL_SUCCESSFULLY_LEARNED);
        // Update statistics
        player.addMoneySpent(spell.getGoldCost());
        // Update player
        this.update(player);
        // Return
        result.setSuccess(true);
        return result;
    }
    
    /**
     * Show the items of a player
     * @param sender Player's nickname
     * @return List of items in a string
     */
    public String showItems(String sender) {
        Player player = this.getPlayerByNickname(sender);
        if (player == null) return null;
        // Return
        StringBuilder builder = new StringBuilder();
        for (Item item : player.getItems()) {
            builder.append(String.format(Strings.FORMAT_PLAYER_ITEMS, item.getName(), item.getCode()));
        }
        return builder.toString();
    }
    
    /**
     * Show the spells of a player
     * @param sender Player's nickname
     * @return List of spells in a string
     */
    public String showSpells(String sender) {
        Player player = this.getPlayerByNickname(sender);
        if (player == null) return null;
        // Return
        StringBuilder builder = new StringBuilder();
        for (Spell spell : player.getSpells()) {
            builder.append(String.format(Strings.FORMAT_PLAYER_SPELLS, spell.getName(), spell.getCode()));
        }
        return builder.toString();
    }
    
    public String showInfos(String sender) {
        Player player = this.getPlayerByNickname(sender);
        if (player == null) return null;
        // Build player's data
        
        // Return
        StringBuilder builder = new StringBuilder();
        
        return builder.toString();
    }
    
    public String showStats(String sender) {
        Player player = this.getPlayerByNickname(sender);
        if (player == null) return null;
        return "";
    }

    /**
     * Log in a player
     * @param username Player's username
     * @param password Player's password
     * @param nickname Player's nickname
     * @param hostname Player's hostname
     * @return Result
     */
    public Result login(String username, String password, String nickname, String hostname) {
        Result result = new Result(Action.LOGIN);
        result.setDetails(username);
        Player player = this.getPlayerByUsername(username);
        if (player != null) {
            if (player.getPassword().equals(password)) {
                this.playersByNickname.remove(player.getNickname());
                this.playersByNickname.remove(nickname);
                this.playersByNickname.put(nickname, player);
                player.setNickname(nickname);
                player.setHostname(hostname);
                player.setOnline(true);
                if (this.update(player, true).isSuccess()) {
                    result.addReturn(Return.LOGIN_SUCCEED);
                    result.setSuccess(true);
                } else result.addReturn(Return.PERSISTANCE_ERROR);
            } else result.addReturn(Return.WRONG_PASSWORD);
        } else result.addReturn(Return.USERNAME_NOT_FOUND);
        return result;
    }
    
    /**
     * Try to identify a player from his nickname and hostname only
     * If fail, the player must use the login function to proceed
     * @param nickname Player's nickname
     * @param hostname Player's hostname
     * @return Result
     */
    public Result tryRelogin(String nickname, String hostname) {
        Result result = new Result(Action.LOGIN);
        result.setDetails(nickname);
        Player player = this.getPlayerByNickname(nickname);
        if (player != null) {
            if (hostname.equals(player.getHostname())) {
                player.setOnline(true);
                if (this.update(player, true).isSuccess()) {
                    result.addReturn(Return.LOGIN_SUCCEED);
                    result.setSuccess(true);
                }
            }
        }
        return result;
    }

    /**
     * Log out a player
     * @param nickname Player's nickname
     * @return Result
     */
    public Result logout(String nickname) {
        Result result = new Result(Action.LOGOUT);
        result.setDetails(nickname);
        Player player = this.getPlayerByNickname(nickname);
        if (player != null) {
            player.setOnline(false);
            if (this.update(player, true).isSuccess()) {
                result.addReturn(Return.LOGOUT_SUCCEED);
                result.setSuccess(true);
            } else result.addReturn(Return.PERSISTANCE_ERROR);
        } else result.addReturn(Return.NOT_ONLINE);
        return result;
    }

    /**
     * Register a new player
     * @param username Username
     * @param password Password
     * @param nickname Nickname
     * @param hostname Hostname
     * @return Result
     */
    public Result register(String username, String password, String nickname, String hostname) {
        Result result = new Result(Action.REGISTER);
        if (!this.playersByUsername.containsKey(username)) {
            if (!this.playersByNickname.containsKey(nickname)) {
                Player player = new Player();
                player.setUsername(username);
                player.setPassword(password);
                player.setNickname(nickname);
                player.setHostname(hostname);
                player.setOnline(true);
                this.playersByUsername.put(username, player);
                this.playersByNickname.put(nickname, player);
                if (this.update(player, true).isSuccess()) {
                    result.addReturn(Return.REGISTER_SUCCEED);
                    result.setSuccess(true);
                } else result.addReturn(Return.PERSISTANCE_ERROR);
            } else result.addReturn(Return.NICKNAME_IN_USE);
        } else result.addReturn(Return.USERNAME_ALREADY_TAKEN);
        return result;
    }
    
    /**
     * Get and check player
     * @see #getPlayer(fr.debnet.ircrpg.game.Result, java.lang.String) 
     * @param result [out] Result
     * @param name Player's nickname
     * @return Player if found and online, null else
     */
    private Player getPlayer(Result result, String name) {
        return this.getPlayer(result, name, false);
    }
    
    /**
     * Get and check player
     * @param result [out] Result
     * @param name Player's nickname
     * @param target Is target ?
     * @return Player if found and online, null else
     */
    private Player getPlayer(Result result, String name, boolean target) {
        // Check if the player exists
        Player player = this.getPlayerByNickname(name);
        if (player == null) {
            result.addReturn(target ? Return.UNKNOWN_TARGET : Return.UNKNOWN_PLAYER);
            result.setDetails(name);
            return null;
        }
        // Add player
        if (target) result.setTarget(player); else result.setPlayer(player);
        // Check if the player is logged in
        if (!player.getOnline()) {
            result.addReturn(target ? Return.TARGET_OFFLINE : Return.PLAYER_OFFLINE);
            return null;
        }
        return player;
    }
    
    /**
     * Get random generator 
     * @return Random generator (mocked)
     */
    protected Random getRandom() {
        return this.random;
    }
    
    /**
     * Explicitly add an item in the list
     * @param item Item instance
     * @return True if the item is successfully added, false sinon
     */
    protected boolean addItem(Item item) {
        if (!this.itemsByCode.containsKey(item.getCode())) {
            this.itemsByCode.put(item.getCode(), item);
            if (Config.PERSISTANCE) {
                return DAO.<Item>addObject(item) != 0;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Explicitly add a spell in the list
     * @param spell Spell instance
     * @return True if the spell is successfully added, false sinon
     */
    protected boolean addSpell(Spell spell) {
        if (!this.spellsByCode.containsKey(spell.getCode())) {
            this.spellsByCode.put(spell.getCode(), spell);
            if (Config.PERSISTANCE) {
                return DAO.<Spell>addObject(spell) != 0;
            }
            return true;
        }
        return false;
    }
}
