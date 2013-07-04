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
public enum Activity {
    NONE        (0, Strings.ACTIVITY_NONE),
    WAITING     (1, Strings.ACTIVITY_WAITING),
    WORKING     (2, Strings.ACTIVITY_WORKING),
    TRAINING    (3, Strings.ACTIVITY_TRAINING),
    RESTING     (4, Strings.ACTIVITY_RESTING);
    
    private final int value;
    private final String text;

    private Activity(int value, String text) {
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
