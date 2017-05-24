package edu.sc.seis.sod.model.common;

import java.sql.Timestamp;
import java.util.Date;

/** Placeholder. */
public class Time extends MicroSecondDate {

    public Time() {
        // TODO Auto-generated constructor stub
    }

    public Time(long microseconds) {
        super(microseconds);
        // TODO Auto-generated constructor stub
    }

    public Time(long microseconds, int leapSeconds) {
        super(microseconds, leapSeconds);
        // TODO Auto-generated constructor stub
    }

    public Time(Date d) {
        super(d);
        // TODO Auto-generated constructor stub
    }

    public Time(MicroSecondDate d) {
        super(d);
        // TODO Auto-generated constructor stub
    }

    public Time(Timestamp ts, int leapSeconds) {
        super(ts, leapSeconds);
        // TODO Auto-generated constructor stub
    }

    public Time(Timestamp ts) {
        super(ts);
        // TODO Auto-generated constructor stub
    }

    public Time(String isoTimeString) {
        super(isoTimeString);
        // TODO Auto-generated constructor stub
    }

    // temp hack...
    @Deprecated
    public String getFissuresTime() {
        return getISOString();
    }
    
}
