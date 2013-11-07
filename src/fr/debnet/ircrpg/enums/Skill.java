package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.commons.Strings;
import fr.debnet.ircrpg.interfaces.IEnum;

/**
 *
 * @author Marc
 */
public enum Skill implements IEnum {
    
    NONE        (0x0, null),
    HEALTH      (0x1, Strings.SKILL_HEALTH),
    MANA        (0x2, Strings.SKILL_MANA),
    ATTACK      (0x3, Strings.SKILL_ATTACK),
    DEFENSE     (0x4, Strings.SKILL_DEFENSE);
        
    private final int value;
    private final String text;

    private Skill(int value, String text) {
        this.value = value;
        this.text = text;
    }
    
    public static Skill from(String name) {
        for (Skill skill : Skill.values()) {
            if (skill.text == null) continue;
            if (skill.text.equalsIgnoreCase(name)) {
                return skill;
            }
        }
        return Skill.NONE;
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
