package fr.debnet.ircrpg.enums;

/**
 *
 * @author Marc
 */
public enum Type {
    OBJECT  (null),
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
        return Type.OBJECT;
    }
}
