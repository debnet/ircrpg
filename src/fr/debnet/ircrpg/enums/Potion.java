package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Potion {
    NONE        (0, null),
    HEALTH      (1, Strings.POTION_HEALTH),
    MANA        (2, Strings.POTION_MANA),
    REMEDY      (3, Strings.POTION_REMEDY);
    
    private final int value;
    private final String text;

    private Potion(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return this.value;
    }
    
    public String getText() {
        return this.text;
    }
}
