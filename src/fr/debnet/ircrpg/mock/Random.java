package fr.debnet.ircrpg.mock;

import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Queue;

/**
 * Random implementation for mock purposes
 * @author Marc
 */
public class Random extends java.util.Random {
    
    private Queue<Double> values;
    
    /**
     * Constructor
     */
    public Random() {
        super(Calendar.getInstance().getTimeInMillis());
        this.values = new ArrayDeque<>();
    }
    
    /**
     * Add a value in the queue
     * @param value Value
     */
    public void addValue(double value) {
        if (value < 0 || value >= 1) {
            throw new IllegalArgumentException("Default random value must be between 0 inclusive and 1 exclusive.");
        }
        this.values.offer(value);
    }
    
    /**
     * Get first value in the queue or a random value
     * @return Value
     */
    @Override
    public double nextDouble() {
        if (this.values.size() > 0)
            return this.values.poll();
        else return super.nextDouble();
    }
}
