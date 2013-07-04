/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg;

import fr.debnet.ircbot.Colors;
import fr.debnet.ircrpg.annotations.Property;

/**
 *
 * @author Marc
 */
public class Strings {
    
    // Format time
    @Property(name = "format.time")
    public static String FORMAT_TIME;
    
    /* Activities */
    @Property(name = "activity.none")
    public static String ACTIVITY_NONE;
    @Property(name = "activity.waiting")
    public static String ACTIVITY_WAITING;
    @Property(name = "activity.working")
    public static String ACTIVITY_WORKING;
    @Property(name = "activity.training")
    public static String ACTIVITY_TRAINING;
    @Property(name = "activity.resting")
    public static String ACTIVITY_RESTING;
    
    /* Status */
    @Property(name = "status.normal")
    public static String STATUS_NORMAL;
    @Property(name = "status.poisoned")
    public static String STATUS_POISONED;
    @Property(name = "status.paralyzed")
    public static String STATUS_PARALYZED;
    @Property(name = "status.dead")
    public static String STATUS_DEAD;
    
    /* Potions */
    @Property(name = "potion.health")
    public static String POTION_HEALTH;
    @Property(name = "potion.mana")
    public static String POTION_MANA;
    @Property(name = "potion.remedy")
    public static String POTION_REMEDY;
    
    /* Stats */
    @Property(name = "stat.health")
    public static String STAT_HEALTH;
    @Property(name = "stat.mana")
    public static String STAT_MANA;
    @Property(name = "stat.attack")
    public static String STAT_ATTACK;
    @Property(name = "stat.defense")
    public static String STAT_DEFENSE;
    
    /* Commands */
    @Property(name = "command.help")
    public static String COMMAND_HELP;
    @Property(name = "command.login")
    public static String COMMAND_LOGIN;
    @Property(name = "command.register")
    public static String COMMAND_REGISTER;
    @Property(name = "command.infos")
    public static String COMMAND_INFOS;
    @Property(name = "command.attack")
    public static String COMMAND_ATTACK;
    @Property(name = "command.magic")
    public static String COMMAND_MAGIC;
    @Property(name = "command.steal")
    public static String COMMAND_STEAL;
    @Property(name = "command.work")
    public static String COMMAND_WORK;
    @Property(name = "command.rest")
    public static String COMMAND_REST;
    @Property(name = "command.train")
    public static String COMMAND_TRAIN;
    @Property(name = "command.return")
    public static String COMMAND_RETURN;
    @Property(name = "command.buy")
    public static String COMMAND_BUY;
    @Property(name = "command.sell")
    public static String COMMAND_SELL;
    @Property(name = "command.look")
    public static String COMMAND_LOOK;
    @Property(name = "command.drink")
    public static String COMMAND_DRINK;
    @Property(name = "command.learn")
    public static String COMMAND_LEARN;
    @Property(name = "command.levelup")
    public static String COMMAND_LEVELUP;
    @Property(name = "command.stats")
    public static String COMMAND_STATS;
    
    /* Command helps */
    @Property(name = "help.help")
    public static String HELP_HELP;
    @Property(name = "help.login")
    public static String HELP_LOGIN;
    @Property(name = "help.register")
    public static String HELP_REGISTER;
    @Property(name = "help.infos")
    public static String HELP_INFOS;
    @Property(name = "help.attack")
    public static String HELP_ATTACK;
    @Property(name = "help.magic")
    public static String HELP_MAGIC;
    @Property(name = "help.steal")
    public static String HELP_STEAL;
    @Property(name = "help.work")
    public static String HELP_WORK;
    @Property(name = "help.rest")
    public static String HELP_REST;
    @Property(name = "help.train")
    public static String HELP_TRAIN;
    @Property(name = "help.return")
    public static String HELP_RETURN;
    @Property(name = "help.buy")
    public static String HELP_BUY;
    @Property(name = "help.sell")
    public static String HELP_SELL;
    @Property(name = "help.look")
    public static String HELP_LOOK;
    @Property(name = "help.drink")
    public static String HELP_DRINK;
    @Property(name = "help.learn")
    public static String HELP_LEARN;
    @Property(name = "help.levelup")
    public static String HELP_LEVELUP;
    @Property(name = "help.stats")
    public static String HELP_STATS;
    
    /* Returns */
    // General returns
    @Property(name = "return.ok")
    public static String RETURN_OK;
    @Property(name = "return.persistance_error")
    public static String RETURN_PERSISTANCE_ERROR;
    @Property(name = "return.unknown_error")
    public static String RETURN_UNKNOWN_ERROR;
    // Update returns
    @Property(name = "return.poison_cured")
    public static String RETURN_POISON_CURED;
    @Property(name = "return.paralysis_cured")
    public static String RETURN_PARALYSIS_CURED;
    @Property(name = "return.death_cured")
    public static String RETURN_DEATH_CURED;
    @Property(name = "return.killed_by_poison")
    public static String RETURN_KILLED_BY_POISON;
    @Property(name = "return.working_ended")
    public static String RETURN_WORKING_ENDED;
    @Property(name = "return.resting_ended")
    public static String RETURN_RESTING_ENDED;
    @Property(name = "return.training_ended")
    public static String RETURN_TRAINING_ENDED;
    @Property(name = "return.waiting_ended")
    public static String RETURN_WAITING_ENDED;
    @Property(name = "return.level_up")
    public static String RETURN_LEVEL_UP;
    // General returns (player)
    @Property(name = "return.unknown_player")
    public static String RETURN_UNKNOWN_PLAYER;
    @Property(name = "return.player_offline")
    public static String RETURN_PLAYER_OFFLINE;
    @Property(name = "return.player_dead")
    public static String RETURN_PLAYER_DEAD;
    @Property(name = "return.player_paralyzed")
    public static String RETURN_PLAYER_PARALYZED;
    @Property(name = "return.player_poisoned")
    public static String RETURN_PLAYER_POISONED;
    @Property(name = "return.player_busy")
    public static String RETURN_PLAYER_BUSY;
    @Property(name = "return.player_killed")
    public static String RETURN_PLAYER_KILLED;
    // General returns (target)
    @Property(name = "return.unknown_target")
    public static String RETURN_UNKNOWN_TARGET;
    @Property(name = "return.target_offline")
    public static String RETURN_TARGET_OFFLINE;
    @Property(name = "return.target_dead")
    public static String RETURN_TARGET_DEAD;
    @Property(name = "return.target_paralyzed")
    public static String RETURN_TARGET_PARALYZED;
    @Property(name = "return.target_poisoned")
    public static String RETURN_TARGET_POISONED;
    @Property(name = "return.target_busy")
    public static String RETURN_TARGET_BUSY;
    @Property(name = "return.target_killed")
    public static String RETURN_TARGET_KILLED;
    @Property(name = "return.target_cured")
    public static String RETURN_TARGET_CURED;
    // Fighting returns
    @Property(name = "return.spell_not_learned")
    public static String RETURN_SPELL_NOT_LEARNED;
    @Property(name = "return.not_enough_mana")
    public static String RETURN_NOT_ENOUGH_MANA;
    @Property(name = "return.not_self_attack")
    public static String RETURN_NOT_SELF_ATTACK;
    @Property(name = "return.not_self_spell")
    public static String RETURN_NOT_SELF_SPELL;
    @Property(name = "return.attack_succeed")
    public static String RETURN_ATTACK_SUCCEED;
    @Property(name = "return.attack_failed")
    public static String RETURN_ATTACK_FAILED;
    @Property(name = "return.defense_succeed")
    public static String RETURN_DEFENSE_SUCCEED;
    @Property(name = "return.defense_failed")
    public static String RETURN_DEFENSE_FAILED;
    @Property(name = "return.magic_succeed")
    public static String RETURN_MAGIC_SUCCEED;
    @Property(name = "return.magic_failed")
    public static String RETURN_MAGIC_FAILED;
    @Property(name = "return.magic_damage_health")
    public static String RETURN_MAGIC_DAMAGE_HEALTH;
    @Property(name = "return.magic_restore_health")
    public static String RETURN_MAGIC_RESTORE_HEALTH;    
    @Property(name = "return.no_strike_back")
    public static String RETURN_NO_STRIKE_BACK;
    // Stealing returns
    @Property(name = "return.not_self_theft")
    public static String RETURN_NOT_SELF_THEFT;
    @Property(name = "return.theft_failed")
    public static String RETURN_THEFT_FAILED;
    @Property(name = "return.theft_succeed")
    public static String RETURN_THEFT_SUCCEED;
    // Drinking returns
    @Property(name = "return.unknown_potion")
    public static String RETURN_UNKNOWN_POTION;
    @Property(name = "return.not_enough_health_potions")
    public static String RETURN_NOT_ENOUGH_HEALTH_POTIONS;
    @Property(name = "return.not_enough_mana_potions")
    public static String RETURN_NOT_ENOUGH_MANA_POTIONS;
    @Property(name = "return.not_enough_remedy_potions")
    public static String RETURN_NOT_ENOUGH_REMEDY_POTIONS;
    @Property(name = "return.health_already_restored")
    public static String RETURN_HEALTH_ALREADY_RESTORED;
    @Property(name = "return.mana_already_restored")
    public static String RETURN_MANA_ALREADY_RESTORED;
    @Property(name = "return.no_negative_status")
    public static String RETURN_NO_NEGATIVE_STATUS;
    @Property(name = "return.health_restored")
    public static String RETURN_HEALTH_RESTORED;
    @Property(name = "return.mana_restored")
    public static String RETURN_MANA_RESTORED;
    // Activity returns
    @Property(name = "return.player_not_busy")
    public static String RETURN_PLAYER_NOT_BUSY;
    @Property(name = "return.player_is_waiting")
    public static String RETURN_PLAYER_IS_WAITING;
    @Property(name = "return.not_worked_enough")
    public static String RETURN_NOT_WORKED_ENOUGH;
    @Property(name = "return.not_rested_enough")
    public static String RETURN_NOT_RESTED_ENOUGH;
    @Property(name = "return.not_trained_enough")
    public static String RETURN_NOT_TRAINED_ENOUGH;
    // Level up returns
    @Property(name = "return.not_enough_skill_points")
    public static String RETURN_NOT_ENOUGH_SKILL_POINTS;
    @Property(name = "return.health_increased")
    public static String RETURN_HEALTH_INCREASED;
    @Property(name = "return.mana_increased")
    public static String RETURN_MANA_INCREASED;
    @Property(name = "return.attack_increased")
    public static String RETURN_ATTACK_INCREASED;
    @Property(name = "return.defense_increased")
    public static String RETURN_DEFENSE_INCREASED;
    // Buy/sell returns
    @Property(name = "return.unknown_item")
    public static String RETURN_UNKNOWN_ITEM;
    @Property(name = "return.item_already_bought")
    public static String RETURN_ITEM_ALREADY_BOUGHT;
    @Property(name = "return.item_not_in_inventory")
    public static String RETURN_ITEM_NOT_IN_INVENTORY;
    @Property(name = "return.item_level_too_high")
    public static String RETURN_ITEM_LEVEL_TOO_HIGH;
    @Property(name = "return.item_price_too_high")
    public static String RETURN_ITEM_PRICE_TOO_HIGH;
    @Property(name = "return.item_stock_empty")
    public static String RETURN_ITEM_STOCK_EMPTY;
    @Property(name = "return.admin_only_item")
    public static String RETURN_ADMIN_ONLY_ITEM;
    @Property(name = "return.item_successfully_bought")
    public static String RETURN_ITEM_SUCCESSFULLY_BOUGHT;
    @Property(name = "return.item_successfully_sold")
    public static String RETURN_ITEM_SUCCESSFULLY_SOLD;
    @Property(name = "return.potion_price_too_high")
    public static String RETURN_POTION_PRICE_TOO_HIGH;
    @Property(name = "return.potion_successfully_bought")
    public static String RETURN_POTION_SUCCESSFULLY_BOUGHT;
    @Property(name = "return.potion_successfully_sold")
    public static String RETURN_POTION_SUCCESSFULLY_SOLD;
    // Spell returns
    @Property(name = "return.unknown_spell")
    public static String RETURN_UNKNOWN_SPELL;
    @Property(name = "return.spell_already_learned")
    public static String RETURN_SPELL_ALREADY_LEARNED;
    @Property(name = "return.spell_level_too_high")
    public static String RETURN_SPELL_LEVEL_TOO_HIGH;
    @Property(name = "return.spell_price_too_high")
    public static String RETURN_SPELL_PRICE_TOO_HIGH;
    @Property(name = "return.admin_only_spell")
    public static String RETURN_ADMIN_ONLY_SPELL;
    @Property(name = "return.spell_successfully_learned")
    public static String RETURN_SPELL_SUCCESSFULLY_LEARNED;
    // Logon returns
    @Property(name = "return.username_already_taken")
    public static String RETURN_USERNAME_ALREADY_TAKEN;
    @Property(name = "return.username_not_found")
    public static String RETURN_USERNAME_NOT_FOUND;
    @Property(name = "return.wrong_password")
    public static String RETURN_WRONG_PASSWORD;
    @Property(name = "return.already_online")
    public static String RETURN_ALREADY_ONLINE;
    
    static {
        Config.loadProperties("strings.properties", Strings.class);
    }
    
    public static String formatMessage(String message) {
        String value = message;
        value = value.replaceAll("<n>", Colors.NORMAL);
        value = value.replaceAll("<b>", Colors.BOLD);
        value = value.replaceAll("<i>", Colors.REVERSE);
        value = value.replaceAll("<u>", Colors.UNDERLINE);
        value = value.replaceAll("<black>", Colors.BLACK);
        value = value.replaceAll("<blue>", Colors.BLUE);
        value = value.replaceAll("<brown>", Colors.BROWN);
        value = value.replaceAll("<cyan>", Colors.CYAN);
        value = value.replaceAll("<dblue>", Colors.DARK_BLUE);
        value = value.replaceAll("<dgray>", Colors.DARK_GRAY);
        value = value.replaceAll("<dgreen>", Colors.DARK_GREEN);
        value = value.replaceAll("<green>", Colors.GREEN);
        value = value.replaceAll("<lgray>", Colors.LIGHT_GRAY);
        value = value.replaceAll("<magenta>", Colors.MAGENTA);
        value = value.replaceAll("<olive>", Colors.OLIVE);
        value = value.replaceAll("<purple>", Colors.PURPLE);
        value = value.replaceAll("<red>", Colors.RED);
        value = value.replaceAll("<teal>", Colors.TEAL);
        value = value.replaceAll("<white>", Colors.WHITE);
        value = value.replaceAll("<yellow>", Colors.YELLOW);
        return value;
    }
}
