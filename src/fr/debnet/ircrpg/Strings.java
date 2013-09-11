package fr.debnet.ircrpg;

import fr.debnet.ircbot.Colors;
import fr.debnet.ircrpg.annotations.Property;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Static strings class
 * @author Marc
 */
public class Strings {
    
    @Property(name = "welcome")
    public static String WELCOME;
    
    /* Time */
    @Property(name = "format.time_seconds")
    public static String FORMAT_TIME_SECONDS;
    @Property(name = "format.time_minutes")
    public static String FORMAT_TIME_MINUTES;
    @Property(name = "format.time_hours")
    public static String FORMAT_TIME_HOURS;
    
    /* Formats */
    @Property(name = "format.none")
    public static String FORMAT_NONE;
    @Property(name = "format.player_infos")
    public static String FORMAT_PLAYER_INFOS;
    @Property(name = "format.player_stats")
    public static String FORMAT_PLAYER_STATS;
    @Property(name = "format.player_items")
    public static String FORMAT_PLAYER_ITEMS;
    @Property(name = "format.player_spells")
    public static String FORMAT_PLAYER_SPELLS;
    @Property(name = "format.shop_items")
    public static String FORMAT_SHOP_ITEMS;
    @Property(name = "format.shop_spells")
    public static String FORMAT_SHOP_SPELLS;
    @Property(name = "format.item_name")
    public static String FORMAT_ITEM_NAME;
    @Property(name = "format.spell_name")
    public static String FORMAT_SPELL_NAME;
    @Property(name = "format.item_infos")
    public static String FORMAT_ITEM_INFOS;
    @Property(name = "format.spell_infos")
    public static String FORMAT_SPELL_INFOS;
    @Property(name = "format.potion_health")
    public static String FORMAT_POTION_HEALTH;
    @Property(name = "format.potion_mana")
    public static String FORMAT_POTION_MANA;
    @Property(name = "format.potion_remedy")
    public static String FORMAT_POTION_REMEDY;
    @Property(name = "format.list_players")
    public static String FORMAT_LIST_PLAYERS;
    
    /* Equipment */
    @Property(name = "equipment.weapon")
    public static String EQUIPMENT_WEAPON;
    @Property(name = "equipment.shield")
    public static String EQUIPMENT_SHIELD;
    @Property(name = "equipment.head")
    public static String EQUIPMENT_HEAD;
    @Property(name = "equipment.chest")
    public static String EQUIPMENT_CHEST;
    @Property(name = "equipment.arms")
    public static String EQUIPMENT_ARMS;
    @Property(name = "equipment.legs")
    public static String EQUIPMENT_LEGS;
    @Property(name = "equipment.feet")
    public static String EQUIPMENT_FEET;
    @Property(name = "equipment.amulet")
    public static String EQUIPMENT_AMULET;
    @Property(name = "equipment.ring")
    public static String EQUIPMENT_RING;
    @Property(name = "equipment.back")
    public static String EQUIPMENT_BACK;
    
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
    @Property(name = "skill.health")
    public static String SKILL_HEALTH;
    @Property(name = "skill.mana")
    public static String SKILL_MANA;
    @Property(name = "skill.attack")
    public static String SKILL_ATTACK;
    @Property(name = "skill.defense")
    public static String SKILL_DEFENSE;
    
    /* Commands */
    @Property(name = "command.help")
    public static String COMMAND_HELP;
    @Property(name = "command.login")
    public static String COMMAND_LOGIN;
    @Property(name = "command.logout")
    public static String COMMAND_LOGOUT;
    @Property(name = "command.register")
    public static String COMMAND_REGISTER;
    @Property(name = "command.password")
    public static String COMMAND_PASSWORD;
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
    @Property(name = "command.items")
    public static String COMMAND_ITEMS;
    @Property(name = "command.spells")
    public static String COMMAND_SPELLS;
    @Property(name = "command.players")
    public static String COMMAND_PLAYERS;
    
    /* Admin commands */
    @Property(name = "command.new")
    public static String COMMAND_NEW;
    @Property(name = "command.edit")
    public static String COMMAND_EDIT;
    @Property(name = "command.set")
    public static String COMMAND_SET;
    @Property(name = "command.get")
    public static String COMMAND_GET;
    @Property(name = "command.save")
    public static String COMMAND_SAVE;
    @Property(name = "command.delete")
    public static String COMMAND_DELETE;
    @Property(name = "command.map")
    public static String COMMAND_MAP;
    @Property(name = "command.config")
    public static String COMMAND_CONFIG;
    @Property(name = "command.reload")
    public static String COMMAND_RELOAD;
    @Property(name = "command.disconnect")
    public static String COMMAND_DISCONNECT;
    @Property(name = "command.reconnect")
    public static String COMMAND_RECONNECT;
    
    /* Command helps */
    @Property(name = "help.help")
    public static String HELP_HELP;
    @Property(name = "help.login")
    public static String HELP_LOGIN;
    @Property(name = "help.logout")
    public static String HELP_LOGOUT;
    @Property(name = "help.register")
    public static String HELP_REGISTER;
    @Property(name = "help.password")
    public static String HELP_PASSWORD;
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
    @Property(name = "help.items")
    public static String HELP_ITEMS;
    @Property(name = "help.spells")
    public static String HELP_SPELLS;
    @Property(name = "help.players")
    public static String HELP_PLAYERS;
    
    /* Returns */
    // General returns
    @Property(name = "return.ok")
    public static String RETURN_OK;
    @Property(name = "return.persistance_error")
    public static String RETURN_PERSISTANCE_ERROR;
    @Property(name = "return.unknown_error")
    public static String RETURN_UNKNOWN_ERROR;
    @Property(name = "return.unknown_command")
    public static String RETURN_UNKNOWN_COMMAND;
    @Property(name = "return.admin_command_succeed")
    public static String RETURN_ADMIN_COMMAND_SUCCEED;
    @Property(name = "return.admin_command_failed")
    public static String RETURN_ADMIN_COMMAND_FAILED;
    @Property(name = "return.action_too_fast")
    public static String RETURN_ACTION_TOO_FAST;
    // General returns (player)
    @Property(name = "return.unknown_player")
    public static String RETURN_UNKNOWN_PLAYER;
    @Property(name = "return.offline_player")
    public static String RETURN_OFFLINE_PLAYER;
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
    @Property(name = "return.player_level_up")
    public static String RETURN_PLAYER_LEVEL_UP;
    @Property(name = "return.player_poison_cured")
    public static String RETURN_PLAYER_POISON_CURED;
    @Property(name = "return.player_paralysis_cured")
    public static String RETURN_PLAYER_PARALYSIS_CURED;
    @Property(name = "return.player_death_cured")
    public static String RETURN_PLAYER_DEATH_CURED;
    @Property(name = "return.player_killed_by_poison")
    public static String RETURN_PLAYER_KILLED_BY_POISON;
    @Property(name = "return.player_working_ended")
    public static String RETURN_PLAYER_WORKING_ENDED;
    @Property(name = "return.player_resting_ended")
    public static String RETURN_PLAYER_RESTING_ENDED;
    @Property(name = "return.player_training_ended")
    public static String RETURN_PLAYER_TRAINING_ENDED;
    @Property(name = "return.player_waiting_ended")
    public static String RETURN_PLAYER_WAITING_ENDED;
    // General returns (target)
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
    @Property(name = "return.target_level_up")
    public static String RETURN_TARGET_LEVEL_UP;
    @Property(name = "return.target_poison_cured")
    public static String RETURN_TARGET_POISON_CURED;
    @Property(name = "return.target_paralysis_cured")
    public static String RETURN_TARGET_PARALYSIS_CURED;
    @Property(name = "return.target_death_cured")
    public static String RETURN_TARGET_DEATH_CURED;
    @Property(name = "return.target_killed_by_poison")
    public static String RETURN_TARGET_KILLED_BY_POISON;
    @Property(name = "return.target_working_ended")
    public static String RETURN_TARGET_WORKING_ENDED;
    @Property(name = "return.target_resting_ended")
    public static String RETURN_TARGET_RESTING_ENDED;
    @Property(name = "return.target_training_ended")
    public static String RETURN_TARGET_TRAINING_ENDED;
    @Property(name = "return.target_waiting_ended")
    public static String RETURN_TARGET_WAITING_ENDED;
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
    @Property(name = "return.start_working")
    public static String RETURN_START_WORKING;
    @Property(name = "return.start_resting")
    public static String RETURN_START_RESTING;
    @Property(name = "return.start_training")
    public static String RETURN_START_TRAINING;
    // Level up returns
    @Property(name = "return.unknown_skill")
    public static String RETURN_UNKNOWN_SKILL;
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
    @Property(name = "return.type_already_equipped")
    public static String RETURN_TYPE_ALREADY_EQUIPPED;
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
    @Property(name = "return.nickname_in_use")
    public static String RETURN_NICKNAME_IN_USE;
    @Property(name = "return.wrong_password")
    public static String RETURN_WRONG_PASSWORD;
    @Property(name = "return.already_online")
    public static String RETURN_ALREADY_ONLINE;
    @Property(name = "return.not_online")
    public static String RETURN_NOT_ONLINE;
    @Property(name = "return.register_succeed")
    public static String RETURN_REGISTER_SUCCEED;
    @Property(name = "return.login_succeed")
    public static String RETURN_LOGIN_SUCCEED;
    @Property(name = "return.logout_succeed")
    public static String RETURN_LOGOUT_SUCCEED;
    @Property(name = "return.password_changed")
    public static String RETURN_PASSWORD_CHANGED;
    
    /* Logger */
    
    
    /* Constants */
    // Colors
    private static final String _NORMAL = "n";
    private static final String _BOLD = "b";
    private static final String _ITALIC = "i";
    private static final String _UNDERLINED = "u";
    private static final String _BLACK = "black";
    private static final String _BLUE = "blue";
    private static final String _BROWN = "brown";
    private static final String _CYAN = "cyan";
    private static final String _DARK_BLUE = "dblue";
    private static final String _DARK_GRAY = "dgray";
    private static final String _DARK_GREEN = "dgreen";
    private static final String _GREEN = "green";
    private static final String _LIGHT_GRAY = "lgray";
    private static final String _MAGENTA = "magenta";
    private static final String _OLIVE = "olive";
    private static final String _PURPLE = "purple";
    private static final String _RED = "red";
    private static final String _TEAL = "teal";
    private static final String _WHITE = "white";
    private static final String _YELLOW = "yellow";
    // Admin
    public static final String ADMIN_CONFIG = "config";
    public static final String ADMIN_STRINGS = "strings";
    public static final String ADMIN_QUEUES = "queues";
    
    // Map of colors
    private final static Map<String, Object> COLORS = new HashMap<String, Object>() {
        {
            this.put(Strings._NORMAL, Colors.NORMAL);
            this.put(Strings._BOLD, Colors.BOLD);
            this.put(Strings._ITALIC, Colors.REVERSE);
            this.put(Strings._UNDERLINED, Colors.UNDERLINE);
            this.put(Strings._BLACK, Colors.BLACK);
            this.put(Strings._BLUE, Colors.BLUE);
            this.put(Strings._BROWN, Colors.BROWN);
            this.put(Strings._CYAN, Colors.CYAN);
            this.put(Strings._DARK_BLUE, Colors.DARK_BLUE);
            this.put(Strings._DARK_GRAY, Colors.DARK_GRAY);
            this.put(Strings._DARK_GREEN, Colors.DARK_GREEN);
            this.put(Strings._GREEN, Colors.GREEN);
            this.put(Strings._LIGHT_GRAY, Colors.LIGHT_GRAY);
            this.put(Strings._MAGENTA, Colors.MAGENTA);
            this.put(Strings._OLIVE, Colors.OLIVE);
            this.put(Strings._PURPLE, Colors.PURPLE);
            this.put(Strings._RED, Colors.RED);
            this.put(Strings._TEAL, Colors.TEAL);
            this.put(Strings._WHITE, Colors.WHITE);
            this.put(Strings._YELLOW, Colors.YELLOW);
        }
    };
    
    // Pattern for formatting "<fieldName(,format)>"
    public static final Pattern FORMAT_PATTERN = 
            Pattern.compile("<(\\w+\\.?\\w*);?(%?[a-z0-9\\=\\.\\+]*)>");
    
    static {
        Config.loadProperties("strings.properties", Strings.class);
    }
    
    /**
     * Format a message with colors
     * @param message Message to format
     * @return Formatted string
     */
    public static String formatMessage(String message) {
        return Strings.format(message, Strings.COLORS);
    }
    
    /**
     * Join many strings with a delimiter
     * @param strings Collection of strings
     * @param delimiter Delimiter
     * @return Formatted string
     */
    public static String join(Collection<String> strings, String delimiter) {
        StringBuilder builder = new StringBuilder();
        Iterator iter = strings.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            if (iter.hasNext()) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
    
    /**
     * Cut a string in parts of the same given size
     * @param string Input string
     * @param size Size of each part
     * @return List of strings
     */
    public static List<String> slice(String string, int size) {
        List<String> parts = new ArrayList<>();
        int length = string.length();
        for (int i = 0; i < length; i += size)
            parts.add(string.substring(i, Math.min(length, i + size)));
        return parts;
    }
    
    /**
     * Format a template string with a dictionary of values
     * @param template Template string
     * @param values Dictionary of values
     * @return Formatted string
     */
    public static String format(String template, Map<String, Object> values) {
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = FORMAT_PATTERN.matcher(template);
        while (matcher.find()) {
            String key = matcher.group(1);
            if (values.containsKey(key)) {
                String format = matcher.group(2);
                boolean hasFormat = !"".equals(format);
                boolean isAbsolute = hasFormat && format.contains("=");
                if (isAbsolute) format = format.replace("=", "");
                
                Object value = values.get(key);
                if (value != null) {
                    String result = hasFormat ? String.format(format, value) : value.toString();
                    if (isAbsolute) result = result.replace("-", "");
                    matcher.appendReplacement(buffer, result);
                }
            }
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
