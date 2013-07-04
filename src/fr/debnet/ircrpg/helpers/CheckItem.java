/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    CAN_BE_AFFORDED;
    
    public static EnumSet<CheckItem> from(CheckItem... checks) {
        return EnumSet.of(CheckItem.NONE, checks);
    }
}