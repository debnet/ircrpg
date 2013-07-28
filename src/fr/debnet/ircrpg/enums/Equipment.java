/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Equipment {
    NONE        ( 0, null),
    WEAPON      ( 1, Strings.EQUIPMENT_WEAPON),
    SHIELD      ( 2, Strings.EQUIPMENT_SHIELD),
    HEAD        ( 3, Strings.EQUIPMENT_HEAD),
    CHEST       ( 4, Strings.EQUIPMENT_CHEST),
    ARMS        ( 5, Strings.EQUIPMENT_ARMS),
    LEGS        ( 6, Strings.EQUIPMENT_LEGS),
    FEET        ( 7, Strings.EQUIPMENT_FEET),
    AMULET      ( 8, Strings.EQUIPMENT_AMULET),
    RING        ( 9, Strings.EQUIPMENT_RING),
    BACK        (10, Strings.EQUIPMENT_BACK);
    
    private final int value;
    private final String text;

    private Equipment(int value, String text) {
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
