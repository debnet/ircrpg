package fr.debnet.ircrpg.helpers;

import java.util.EnumSet;

/**
 * Checks for items
 * 
 * @author Marc
 */
public enum CheckItem
{
    NONE,
    HAS_ENOUGH_STOCK,
    IS_ALREADY_BOUGHT,
    IS_ADMIN_ONLY,
    CAN_BE_WORN,
    CAN_BE_AFFORDED,
    TYPE_ALREADY_EQUIPPED;
    
    public static EnumSet<CheckItem> from(CheckItem... checks) {
        return EnumSet.of(CheckItem.NONE, checks);
    }
}
