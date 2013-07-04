/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.annotations;

/**
 *
 * @author Marc
 */
public enum Type {
    NONE    (null),
    STRING  ("String"),
    BOOLEAN ("boolean"),
    INTEGER ("int"),
    DECIMAL ("double");
    
    private final String value;

    private Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    
    public static Type fromString(String value) {
        for (Type t : Type.values()) {
            if (value.equalsIgnoreCase(t.value)) {
                return t;
            }
        }
        return Type.NONE;
    }
}
