/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.data;

/**
 * HSQL parameter
 * @author Marc
 */
public class Parameter<T> {
    
    private String name;
    private T value;

    /**
     * Constructor
     * @param name Name
     * @param value Value
     */
    public Parameter(String name, T value) {
        this.name = name;
        this.value = value;
    }
    
    /**
     * Create a new parameter
     * @param <T> Type
     * @param name Name
     * @param value Value
     * @return 
     */
    public static <T> Parameter Create(String name, T value) {
        return new Parameter(name, value);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
