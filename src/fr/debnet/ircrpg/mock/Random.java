/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.mock;

import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Queue;

/**
 *
 * @author Marc
 */
public class Random {
    
    private java.util.Random random;
    private Queue<Double> defaultValues;
    
    public Random() {
        this.random = new java.util.Random(Calendar.getInstance().getTimeInMillis());
        this.defaultValues = new ArrayDeque<Double>();
    }
    
    public void addDefaultValue(double value) {
        if (value < 0 || value >= 1) {
            throw new IllegalArgumentException("Default random value must be between 0 inclusive and 1 exclusive.");
        }
        this.defaultValues.offer(value);
    }
    
    public double nextDouble() {
        double value = random.nextDouble();
        if (this.defaultValues.size() > 0) {
            value = this.defaultValues.poll();
        }
        return value;
    }
}
