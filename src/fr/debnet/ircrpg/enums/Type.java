package fr.debnet.ircrpg.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Types
 * @author Marc
 */
public enum Type {
    OBJECT      (),
    STRING      ("String"),
    CHAR        ("char"),
    BOOLEAN     ("boolean", "Boolean"),
    INTEGER     ("int", "Integer"),
    DOUBLE      ("double", "Double"),
    FLOAT       ("float", "Float"),
    LONG        ("long", "Long"),
    ENUM        ();
    
    private final List<String> types;

    private Type(String... types) {
        this.types = Arrays.asList(types);
    }
    
    public static Type from(Class<?> clazz) {
        if (clazz.isEnum()) return ENUM;
        String value = clazz.getSimpleName();
        for (Type type : Type.values()) {
            if (type.types.contains(value)) {
                return type;
            }
        }
        return Type.OBJECT;
    }
}
