package edu.sc.seis.sod.model.common;

import java.time.Duration;
import java.time.Instant;

import edu.sc.seis.seisFile.fdsnws.stationxml.BaseNodeType;
import edu.sc.seis.seisFile.fdsnws.stationxml.Station;
import edu.sc.seis.sod.model.seismogram.LocalSeismogramImpl;
import edu.sc.seis.sod.model.seismogram.RequestFilter;

/** Placeholder. */
public class TimeRange {
    
    public TimeRange(BaseNodeType node) {
        this(node.getStartDateTime(), node.getEndDateTime());
    }

    public TimeRange(LocalSeismogramImpl ls) {
        this(ls.getBeginTime(), ls.getEndTime());
    }

    public TimeRange(RequestFilter rf) {
        this(rf.start_time, rf.end_time);
    }

    public TimeRange(TimeRange timeRange) {
        this(timeRange.getBeginTime(), timeRange.getEndTime());
    }

    /**
     * Creates a new MicroSecondTimeRange. The order of the times passed in
     * doesn't matter
     */
    public TimeRange(Instant time,
                     Instant anotherTime) {
        if(time.isBefore(anotherTime)) {
            this.beginTime = time;
            this.endTime = anotherTime;
        } else {
            this.beginTime = anotherTime;
            this.endTime = time;
        }
        this.interval = Duration.between(beginTime, endTime);
    }

    public TimeRange(Instant beginTime, Duration interval) {
        this(beginTime, beginTime.plus(interval));
    }

    public TimeRange(TimeRange timeRange,
                     TimeRange timeRange2) {
        this(timeRange.getBeginTime().isBefore(timeRange2.getBeginTime()) ? timeRange.getBeginTime()
                     : timeRange2.getBeginTime(),
             timeRange.getEndTime().isAfter(timeRange2.getEndTime()) ? timeRange.getEndTime()
                     : timeRange2.getEndTime());
    }

    /**
     *@deprecated - just calls contains(MicroSecondDate)
     */
    public boolean intersects(Instant newTime) {
        return contains(newTime);
    }


    public boolean contains(Instant newTime) {
        return (beginTime.isBefore(newTime) || beginTime.equals(newTime))
                && (endTime.isAfter(newTime) || endTime.equals(newTime));
    }

    public boolean intersects(TimeRange time) {
        return endTime.isAfter(time.getBeginTime())
                && beginTime.isBefore(time.getEndTime());
    }
    
    public TimeRange intersection(TimeRange time) {
        if (intersects(time)) {
            return new TimeRange(beginTime.isAfter(time.getBeginTime())?beginTime:time.getBeginTime(),
                    endTime.isBefore(time.getEndTime())?endTime:time.getEndTime());
        }
        return null;
    }

    public TimeRange shale(double shift, double scale) {
        if(shift == 0 && scale == 1) {
            return this;
        }
        Duration timeShift = interval.multipliedBy(Math.abs(shift));
        Instant newBeginTime;
        if(shift < 0) {
            newBeginTime = beginTime.minus(timeShift);
        } else {
            newBeginTime = beginTime.plus(timeShift);
        }
        return new TimeRange(newBeginTime,
                             interval.multipliedBy(scale));
    }

    public TimeRange shift(Duration shift) {
        return new TimeRange(beginTime.plus(shift),
                                        endTime.plus(shift));
    }

    public TimeRange shift(double percentage) {
        if(percentage == 0) {
            return this;
        }
        Duration shift = interval.multipliedBy(Math.abs(percentage));
        if(percentage < 0) {
            return new TimeRange(beginTime.minus(shift),
                                            endTime.minus(shift));
        }
        return new TimeRange(beginTime.plus(shift),
                                        endTime.plus(shift));
    }

    /**
     * Returns the beginning time for this range
     */
    public Instant getBeginTime() {
        return beginTime;
    }

    /**
     * Returns the ending time for this range
     */
    public Instant getEndTime() {
        return endTime;
    }

    /**
     * Returns the interval that this range comprises
     */
    public Duration getInterval() {
        return interval;
    }

    public UnitRangeImpl getMillis() {
        return new UnitRangeImpl(beginTime.toEpochMilli(),
                                 endTime.toEpochMilli(),
                                 UnitImpl.MILLISECOND);
    }

    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(getClass() != other.getClass())
            return false;
        TimeRange mstrTime = (TimeRange)other;
        if(beginTime.equals(mstrTime.getBeginTime())
                && endTime.equals(mstrTime.getEndTime())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + beginTime.hashCode();
        result = 37 * result + endTime.hashCode();
        return result;
    }

    public String toString() {
        return beginTime + " to " + endTime;
    }
    

    private final Instant beginTime;

    private final Instant endTime;

    private final Duration interval;
}
