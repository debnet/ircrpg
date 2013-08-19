package fr.debnet.ircrpg.helpers;

import fr.debnet.ircrpg.Strings;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Marc
 */
public class Time {
    
    private int minutes;
    private int seconds;
    
    public Time(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }
    
    public Time(int microseconds) {
        this.minutes = (int)TimeUnit.MICROSECONDS.toSeconds(microseconds / 60);
        this.seconds = (int)TimeUnit.MICROSECONDS.toSeconds(microseconds % 60);
    }
    
    public int getMinutes() {
        return this.minutes;
    }
    
    public int getSeconds() {
        return this.seconds;
    }
    
    @Override
    public String toString() {
        return String.format(Strings.FORMAT_TIME, this.minutes, this.seconds);
    }
}
