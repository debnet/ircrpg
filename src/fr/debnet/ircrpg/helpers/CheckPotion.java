package fr.debnet.ircrpg.helpers;

import java.util.EnumSet;

/**
 * Checks for potions
 * @author Marc
 */
public enum CheckPotion
{
    NONE,
    HAS_ENOUGH,
    CAN_CURE,
    CAN_BE_AFFORDED;
    
    public static EnumSet<CheckPotion> from(CheckPotion... checks) {
        return EnumSet.of(CheckPotion.NONE, checks);
    }
}
