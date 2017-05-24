package edu.iris.Fissures.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import edu.iris.Fissures.Time;
import edu.iris.Fissures.network.TimeFormatter;

/**
 * subclass of the java.util.Date class to extend the precision to microseconds
 * and to eventually handle leap seconds.
 * 
 * @author H. Philip Crotwell
 */
public class MicroSecondDate extends Date implements Serializable {

    protected long microseconds;

    protected int leapSecondVersion;

    protected LeapSecondHistory leapHistory;

    public MicroSecondDate() {
        super();
        this.leapSecondVersion = 0;
        this.microseconds = 0;
    }

    public MicroSecondDate(long microseconds) {
        this(microseconds, 0);
    }

    public MicroSecondDate(long microseconds, int leapSeconds) {
        super(microseconds / 1000);
        this.microseconds = microseconds % 1000;
        this.leapSecondVersion = leapSeconds;
    }

    public MicroSecondDate(Date d) {
        this(d.getTime() * 1000, 0);
    }

    public MicroSecondDate(MicroSecondDate d) {
        this(d.getMicroSecondTime());
    }

    public MicroSecondDate(Timestamp ts, int leapSeconds) {
        // convert getTime from milliseconds to microseconds and add
        // nanos div by 1000 to get microseconds and modulo 1000 to
        // remove milliseconds that are already in the getTime
        // do timestamp and nanos have duplicate info
        this(fixTimestamp(ts), leapSeconds);
    }

    /**
     * fixBadJavaDatabaseTimestampNanos13Vs14Bug
     * 
     * @return microseconds from the timestamp
     */
    static long fixTimestamp(Timestamp ts) {
        long tsFracSeconds = ts.getTime() % 1000;
        if(tsFracSeconds == 0) {
            // trust nanos for all frac seconds including millis
            return ts.getTime() * 1000 + ts.getNanos() / 1000;
        } else {
            // ts has millis, nanos has micro and nano but no millis
            return ts.getTime() * 1000 + (ts.getNanos() / 1000) % 1000;
        }
    }

    public MicroSecondDate(Timestamp ts) {
        this(ts, 0);
    }

    public MicroSecondDate(Time t) {
        this(new ISOTime(t.date_time).getDate().getMicroSecondTime(),
             t.leap_seconds_version);
    }

    public MicroSecondDate(String isoTimeString) {
        this(new ISOTime(isoTimeString).getDate().getMicroSecondTime());
    }

    public long getMicroSecondTime() {
        return super.getTime() * 1000 + microseconds;
    }

    /**
     * Returns the microseconds as a number between 0 and 999, the milliseconds
     * are kept in the Date superclass.
     * 
     * @return the microseconds as a number between 0 and 999.
     */
    public long getMicroSeconds() {
        return microseconds;
    }

    public Timestamp getTimestamp() {
        Timestamp t = new Timestamp(getTime());
        int nanos = t.getNanos();
        nanos += microseconds * 1000;
        t.setNanos(nanos);
        return t;
    }

    public edu.iris.Fissures.Time getFissuresTime() {
        return new Time(ISOTime.getISOString(this), this.leapSecondVersion);
    }
    
    public int getLeapSecondVersion() {
        return this.leapSecondVersion;
    }

    public MicroSecondDate add(TimeInterval interval) {
        if(interval == null) { throw new IllegalArgumentException("Cannot add() a null TimeInterval"); }
        return new MicroSecondDate(getMicroSecondTime()
                + Math.round(interval.convertTo(UnitImpl.MICROSECOND)
                        .getValue()));
    }

    public MicroSecondDate subtract(TimeInterval interval) {
        if(interval == null) { throw new IllegalArgumentException("Cannot subtract() a null TimeInterval"); }
        return new MicroSecondDate(getMicroSecondTime()
                - Math.round(interval.convertTo(UnitImpl.MICROSECOND)
                        .getValue()));
    }

    /**
     * Returns the timewidth of this - otherDate.
     */
    public TimeInterval subtract(MicroSecondDate otherDate) {
        if(otherDate == null) { throw new IllegalArgumentException("Cannot difference() a null MicroSecondDate"); }
        return new TimeInterval(getMicroSecondTime()
                - otherDate.getMicroSecondTime(), UnitImpl.MICROSECOND);
    }

    /**
     * Returns the TimeInterval between the two times. It is always positive
     * regardless of which time comes before the other.
     */
    public TimeInterval difference(MicroSecondDate otherDate) {
        if(otherDate == null) { throw new IllegalArgumentException("Cannot difference() a null MicroSecondDate"); }
        long otherTime = otherDate.getMicroSecondTime();
        long myTime = getMicroSecondTime();
        if(before(otherDate)) { return new TimeInterval(otherTime - myTime,
                                                        UnitImpl.MICROSECOND); }
        return new TimeInterval(myTime - otherTime, UnitImpl.MICROSECOND);
    }

    public boolean equals(Object otherDate) {
        if(otherDate == this) { return true; }
        if(super.equals(otherDate)) { //Date portion is equal
            //If MicroSecondDate, compare microseconds
            if(otherDate instanceof MicroSecondDate) {
                MicroSecondDate oMSD = (MicroSecondDate)otherDate;
                return getMicroSecondTime() == oMSD.getMicroSecondTime()
                        && (leapSecondVersion == oMSD.leapSecondVersion 
                                || (leapSecondVersion <=0 && oMSD.leapSecondVersion <= 0));
            }
            //otherwise, just return true since it's just a date and we need to
            // be symmetric
            return true;
        }
        return false;
    }

    //We use Date's implementation of hashCode since MicroSecondDates are
    // always equal to Dates and must return the same hash value according to
    // the contract in Object. super.hashCode() is explicitly invoked here just
    // to make the relationship clear
    public int hashCode() {
        return super.hashCode();
    }

    public boolean after(Date otherDate) {
        if(super.after(otherDate)) { return true; }
        if(super.equals(otherDate) && otherDate instanceof MicroSecondDate) {
            if(getMicroSecondTime() > ((MicroSecondDate)otherDate).getMicroSecondTime()) return true;
        }
        return false;
    }

    public boolean before(Date otherDate) {
        if(super.before(otherDate)) { return true; }
        if(super.equals(otherDate) && otherDate instanceof MicroSecondDate) {
            if(getMicroSecondTime() < ((MicroSecondDate)otherDate).getMicroSecondTime()) return true;
        }
        return false;
    }

    public String toString() {
        return TimeFormatter.format(this);
    }
}
