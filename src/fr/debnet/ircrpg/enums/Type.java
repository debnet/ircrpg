package fr.debnet.ircrpg.enums;

/**
 *
 * @author Marc
 */
public enum Type {
    OBJECT      (null, null),
    STRING      ("char", "String"),
    BOOLEAN     ("boolean", "Boolean"),
    INTEGER     ("int", "Integer"),
    DECIMAL     ("double", "Double");
    
    private final String primitive;
    private final String object;

    private Type(String primitive, String object) {
        this.primitive = primitive;
        this.object = object;
    }
    
    public static Type from(Class<?> clazz) {
        String value = clazz.getSimpleName();
        for (Type type : Type.values()) {
            if (value.equals(type.primitive) || value.equals(type.object)) {
                return type;
            }
        }
        return Type.OBJECT;
    }
}
