package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Potion {
    NONE        (0x0, null),
    HEALTH      (0x1, Strings.POTION_HEALTH),
    MANA        (0x2, Strings.POTION_MANA),
    REMEDY      (0x3, Strings.POTION_REMEDY);
    
    private final int value;
    private final String text;

    private Potion(int value, String text) {
        this.value = value;
        this.text = text;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}
