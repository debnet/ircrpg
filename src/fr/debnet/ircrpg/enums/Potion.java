package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;
import fr.debnet.ircrpg.interfaces.IEnum;

/**
 *
 * @author Marc
 */
public enum Potion implements IEnum {
    
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
    
    public static Potion from(String name) {
        for (Potion potion : Potion.values()) {
            if (potion.text == null) continue;
            if (potion.text.equalsIgnoreCase(name)) {
                return potion;
            }
        }
        return Potion.NONE;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
        
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
