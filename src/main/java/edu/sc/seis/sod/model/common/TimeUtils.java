package edu.iris.Fissures.model;

import edu.iris.Fissures.Time;

/**
 * TimeUtils.java
 *
 *
 * Created: Fri May  4 13:46:12 2001
 *
 * @author Philip Crotwell
 */

public class TimeUtils {

    public TimeUtils (){
	
    }

    public static boolean areEqual(Time a, Time b) {
        return a.date_time.equals(b.date_time) && (
                a.leap_seconds_version == b.leap_seconds_version ||
                (a.leap_seconds_version <= 0 && b.leap_seconds_version <= 0))
                || new MicroSecondDate(a).equals(new MicroSecondDate(b));
    }
    
    public static final MicroSecondDate future = 
        (new ISOTime("2499001J00:00:00.000Z")).getDate();

    /** future plus one day so that is is after(future)
     */
    public static final MicroSecondDate futurePlusOne = 
        (new ISOTime("2499002J00:00:00.000Z")).getDate();

    public static final edu.iris.Fissures.Time timeUnknown = 
        new edu.iris.Fissures.Time(edu.iris.Fissures.TIME_UNKNOWN.value);

}// TimeUtils
