package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Equipment {
    NONE        (0x0, null),
    WEAPON      (0x1, Strings.EQUIPMENT_WEAPON),
    SHIELD      (0x2, Strings.EQUIPMENT_SHIELD),
    HEAD        (0x3, Strings.EQUIPMENT_HEAD),
    CHEST       (0x4, Strings.EQUIPMENT_CHEST),
    ARMS        (0x5, Strings.EQUIPMENT_ARMS),
    LEGS        (0x6, Strings.EQUIPMENT_LEGS),
    FEET        (0x7, Strings.EQUIPMENT_FEET),
    AMULET      (0x8, Strings.EQUIPMENT_AMULET),
    RING        (0x9, Strings.EQUIPMENT_RING),
    BACK        (0xA, Strings.EQUIPMENT_BACK);
    
    private final int value;
    private final String text;

    private Equipment(int value, String text) {
        this.value = value;
        this.text = text;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}
