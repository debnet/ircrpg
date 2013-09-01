package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Stat {
    NONE        (0x0, null),
    HEALTH      (0x1, Strings.STAT_HEALTH),
    MANA        (0x2, Strings.STAT_MANA),
    ATTACK      (0x3, Strings.STAT_ATTACK),
    DEFENSE     (0x4, Strings.STAT_DEFENSE);
        
    private final int value;
    private final String text;

    private Stat(int value, String text) {
        this.value = value;
        this.text = text;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}
