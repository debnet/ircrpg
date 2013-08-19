package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public class Time {
    
    private int hours;
    private int minutes;
    private int seconds;
    
    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    
    public Time(long microseconds) {
        this.hours = (int) (microseconds / Config.HOUR); 
        this.minutes = (int) ((microseconds % Config.HOUR) / Config.MINUTE);
        this.seconds = (int) ((microseconds % Config.MINUTE) / Config.SECOND);
    }
    
    public int getHours() {
        return this.hours;
    }
    
    public int getMinutes() {
        return this.minutes;
    }
    
    public int getSeconds() {
        return this.seconds;
    }
    
    @Override
    public String toString() {
        if (this.hours > 0) 
            return String.format(Strings.FORMAT_TIME_HOURS, this.hours, this.minutes, this.seconds);
        else if (this.minutes > 0) 
            return String.format(Strings.FORMAT_TIME_MINUTES, this.minutes, this.seconds);
        else 
            return String.format(Strings.FORMAT_TIME_SECONDS, this.seconds);
    }
}
