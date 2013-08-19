package fr.debnet.ircrpg;

import fr.debnet.ircrpg.annotations.Property;
import fr.debnet.ircrpg.enums.Type;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class Config {
    
    /* IRC config */
    @Property(name = "irc.enabled") 
    public static boolean IRC_ENABLED;
    @Property(name = "irc.nickname") 
    public static String IRC_NICKNAME;
    @Property(name = "irc.server") 
    public static String IRC_SERVER;
    @Property(name = "irc.port") 
    public static int IRC_PORT;
    @Property(name = "irc.channel") 
    public static String IRC_CHANNEL;
    @Property(name = "irc.charset") 
    public static String IRC_CHARSET;
    @Property(name = "irc.password") 
    public static String IRC_PASSWORD;
    
    /* Game config */
    @Property(name = "game.debug") 
    public static boolean DEBUG;
    @Property(name = "game.persistance") 
    public static boolean PERSISTANCE;
    @Property(name = "game.rate.experience") 
    public static double RATE_EXPERIENCE;
    @Property(name = "game.rate.gold") 
    public static double RATE_GOLD;
    @Property(name = "game.rate.health") 
    public static double RATE_HEALTH;
    @Property(name = "game.rate.level") 
    public static int RATE_LEVEL;
    @Property(name = "game.rate.skill") 
    public static int RATE_SKILL;
    @Property(name = "game.skill.health") 
    public static int SKILL_HEALTH;
    @Property(name = "game.skill.mana") 
    public static int SKILL_MANA;
    @Property(name = "game.skill.attack") 
    public static int SKILL_ATTACK;
    @Property(name = "game.skill.defense") 
    public static int SKILL_DEFENSE;
    @Property(name = "game.potion.health.restore") 
    public static double POTION_HEALTH_RESTORE;
    @Property(name = "game.potion.mana.restore") 
    public static double POTION_MANA_RESTORE;
    @Property(name = "game.potion.health.cost") 
    public static int POTION_HEALTH_COST;
    @Property(name = "game.potion.mana.cost") 
    public static int POTION_MANA_COST;
    @Property(name = "game.potion.remedy.cost") 
    public static int POTION_REMEDY_COST;
    @Property(name = "game.start.health") 
    public static int START_HEALTH;
    @Property(name = "game.start.mana") 
    public static int START_MANA;
    @Property(name = "game.start.attack") 
    public static int START_ATTACK;
    @Property(name = "game.start.defense") 
    public static int START_DEFENSE;
    @Property(name = "game.start.gold")
    public static int START_GOLD;
    @Property(name = "game.accuracy.attack") 
    public static double ATTACK_ACCURACY;
    @Property(name = "game.accuracy.defense") 
    public static double DEFENSE_ACCURACY;
    @Property(name = "game.accuracy.magic") 
    public static double MAGIC_ACCURACY;
    @Property(name = "game.working.min") 
    public static int WORKING_TIME_MIN;
    @Property(name = "game.working.max") 
    public static int WORKING_TIME_MAX;
    @Property(name = "game.resting.min") 
    public static int RESTING_TIME_MIN;
    @Property(name = "game.resting.max") 
    public static int RESTING_TIME_MAX;
    @Property(name = "game.training.min") 
    public static int TRAINING_TIME_MIN;
    @Property(name = "game.training.max") 
    public static int TRAINING_TIME_MAX;
    @Property(name = "game.activity.penalty") 
    public static int ACTIVITY_PENALTY;
    @Property(name = "game.death.penalty") 
    public static int DEATH_PENALTY;
    @Property(name = "game.poison.effect") 
    public static double POISON_EFFECT;
    @Property(name = "game.sell.malus") 
    public static double SELL_MALUS;
    @Property(name = "game.experience.attack") 
    public static int EXPERIENCE_ATTACK;
    @Property(name = "game.experience.defense") 
    public static int EXPERIENCE_DEFENSE;
    @Property(name = "game.experience.bonus") 
    public static double EXPERIENCE_BONUS;
    @Property(name = "game.theft.chance") 
    public static double THEFT_CHANCE;
    @Property(name = "game.theft.gold") 
    public static double THEFT_GOLD;
    
    /* Hibernate config */
    public static Map<String, String> HIBERNATE_CONFIG;
    
    /* Constants */
    public static final long HOUR = 3600000;
    public static final long MINUTE = 60000;
    public static final long SECOND = 1000;
    
    static {
        Config.loadConfig("config.properties");
    }
    
    /**
     * Load configuration from property file
     * @param filename Property file
     */
    public static void loadConfig(String filename) {
        Config.loadProperties(filename, Config.class);
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(filename));
            
            Config.HIBERNATE_CONFIG = new HashMap<String, String>();
            for (String key : properties.stringPropertyNames()) {
                if (key.startsWith("hibernate.")) {
                    Config.HIBERNATE_CONFIG.put(key, properties.getProperty(key));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).severe(ex.getLocalizedMessage());
            System.exit(-1);
        }
    }
    
    /**
     * Get the value of a config key
     * @param key Config key
     * @return Value
     */
    public static String getConfig(String key) {
        try {
           Field[] declaredFields = Config.class.getDeclaredFields();
            for (Field field : declaredFields) {
                if (Modifier.isStatic(field.getModifiers()) && field.getName().equalsIgnoreCase(key)) {
                    Class<?> type = field.getType();
                    if (type.isAssignableFrom(Integer.TYPE)) {
                        return String.valueOf(field.getInt(null));
                    } else if (type.isAssignableFrom(Double.TYPE)) {
                        return String.valueOf(field.getDouble(null));
                    } else if (type.isAssignableFrom(Boolean.TYPE)) {
                        return String.valueOf(field.getBoolean(null));
                    }
                    return null;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Config.class.getName()).severe(ex.getLocalizedMessage());
        }
        return null;
    }
    
    /**
     * Set the value of a config key
     * @param key Config key
     * @param value Value to set
     * @return True if success, false else
     */
    public static boolean setConfig(String key, String value) {
        try {
            Field[] declaredFields = Config.class.getDeclaredFields();
            for (Field field : declaredFields) {
                if (Modifier.isStatic(field.getModifiers()) && field.getName().equalsIgnoreCase(key)) {
                    Class<?> type = field.getType();
                    if (type.isAssignableFrom(Integer.TYPE)) {
                        int v = Integer.parseInt(value);
                        field.setInt(null, v);
                    } else if (type.isAssignableFrom(Double.TYPE)) {
                        double v = Double.parseDouble(value);
                        field.setDouble(null, v);
                    } else if (type.isAssignableFrom(Boolean.TYPE)) {
                        boolean v = Boolean.parseBoolean(value);
                        field.setBoolean(null, v);
                    } else field.set(null, value);
                    return true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Config.class.getName()).severe(ex.getLocalizedMessage());
        }
        return false;
    }
    
    /**
     * Load all properties from a propery file
     * @param filename Property file
     * @param object Object to set (must have static fields and property annotations)
     */
    public static void loadProperties(String filename, Class object) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(filename));
            
            for (Field field : object.getDeclaredFields()) {
                Property property = field.getAnnotation(Property.class);
                if (property != null) {
                    String name = property.name();
                    Type type = Type.fromString(field.getType().getSimpleName());
                    switch (type) {
                        case STRING:
                            field.set(null, properties.getProperty(name));
                            break;
                        case BOOLEAN:
                            field.setBoolean(null, Boolean.parseBoolean(properties.getProperty(name)));
                            break;
                        case INTEGER:
                            field.setInt(null, Integer.parseInt(properties.getProperty(name)));
                            break;
                        case DECIMAL:
                            field.setDouble(null, Double.parseDouble(properties.getProperty(name)));
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Config.class.getName()).severe(ex.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
