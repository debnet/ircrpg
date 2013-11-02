/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg;

/**
 *
 * @author Marc
 */
public class Parameter<T> {
    
    private String name;
    private T value;

    public Parameter(String name, T value) {
        this.name = name;
        this.value = value;
    }
    
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
