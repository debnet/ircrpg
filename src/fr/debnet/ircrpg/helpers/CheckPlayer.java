/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.helpers;

import java.util.EnumSet;

/**
 * Checks for players
 * 
 * @author Marc
 */
public enum CheckPlayer
{
    NONE,
    IS_DEAD,
    IS_PARALYZED,
    IS_BUSY,
    IS_TARGET;
    
    public static EnumSet<CheckPlayer> from(CheckPlayer... checks) {
        return EnumSet.of(CheckPlayer.NONE, checks);
    }
}
