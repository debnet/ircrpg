package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Stat {
    NONE        (0, null),
    HEALTH      (1, Strings.STAT_HEALTH),
    MANA        (2, Strings.STAT_MANA),
    ATTACK      (3, Strings.STAT_ATTACK),
    DEFENSE     (4, Strings.STAT_DEFENSE);
        
    private final int value;
    private final String text;

    private Stat(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return this.value;
    }
    
    public String getText() {
        return this.text;
    }
    
    public String toString() {
        return this.text;
    }
}
