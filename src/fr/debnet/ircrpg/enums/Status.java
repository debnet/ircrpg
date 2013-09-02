package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Status {
    NONE        (0x0, null),
    NORMAL      (0x1, Strings.STATUS_NORMAL),
    POISONED    (0x2, Strings.STATUS_POISONED),
    PARALYZED   (0x3, Strings.STATUS_PARALYZED),
    DEAD        (0x4, Strings.STATUS_DEAD);
    
    private final int value;
    private final String text;

    private Status(int value, String text) {
        this.value = value;
        this.text = text;
    }
    
    public static Status from(String name) {
        for (Status status : Status.values()) {
            if (status.text == null) continue;
            if (status.text.equalsIgnoreCase(name)) {
                return status;
            }
        }
        return Status.NONE;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}
