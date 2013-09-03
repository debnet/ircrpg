package fr.debnet.ircrpg.enums;

import java.util.Arrays;
import java.util.List;

/**
 *
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
    ENUM        ("IEnum");
    
    private final List<String> types;

    private Type(String... types) {
        this.types = Arrays.asList(types);
    }
    
    public static Type from(Class<?> clazz) {
        String value = clazz.getSimpleName();
        for (Type type : Type.values()) {
            if (type.types.contains(value)) {
                return type;
            }
            for (Class<?> i : clazz.getInterfaces()) {
                if (type.types.contains(i.getSimpleName())) {
                    return type;
                }
            }
        }
        return Type.OBJECT;
    }
}
