package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Status {
    NONE        (0, null),
    NORMAL      (1, Strings.STATUS_NORMAL),
    POISONED    (2, Strings.STATUS_POISONED),
    PARALYZED   (3, Strings.STATUS_PARALYZED),
    DEAD        (4, Strings.STATUS_DEAD);
    
    private final int value;
    private final String text;

    private Status(int value, String text) {
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
