package fr.debnet.ircrpg.helpers;

import java.util.EnumSet;

/**
 * Checks for spells
 * 
 * @author Marc
 */
public enum CheckSpell
{
    NONE,
    IS_ALREADY_LEARNED,
    IS_ADMIN_ONLY,
    CAN_BE_LEARNED,
    CAN_BE_AFFORDED;
    
    public static EnumSet<CheckSpell> from(CheckSpell... checks) {
        return EnumSet.of(CheckSpell.NONE, checks);
    }
}
