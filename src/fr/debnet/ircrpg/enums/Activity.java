package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;
import fr.debnet.ircrpg.interfaces.IEnum;

/**
 *
 * @author Marc
 */
public enum Activity implements IEnum {
    
    NONE        (0x0, Strings.ACTIVITY_NONE),
    WAITING     (0x1, Strings.ACTIVITY_WAITING),
    WORKING     (0x2, Strings.ACTIVITY_WORKING),
    TRAINING    (0x3, Strings.ACTIVITY_TRAINING),
    RESTING     (0x4, Strings.ACTIVITY_RESTING);
    
    private final int value;
    private final String text;

    private Activity(int value, String text) {
        this.value = value;
        this.text = text;
    }
    
    public static Activity from(String name) {
        for (Activity activity : Activity.values()) {
            if (activity.text == null) continue;
            if (activity.text.equalsIgnoreCase(name)) {
                return activity;
            }
        }
        return Activity.NONE;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
    
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
