package edu.sc.seis.sod.model.common;

import java.time.ZonedDateTime;

/** Placeholder. */
public class TimeRange extends MicroSecondTimeRange {

    /** temp hack */
    @Deprecated
    public TimeRange() {
        super((MicroSecondDate)null, (MicroSecondDate)null);
    }
    
    public TimeRange(TimeRange timeRange) {
        super(timeRange);
        // TODO Auto-generated constructor stub
    }

    public TimeRange(MicroSecondDate time, MicroSecondDate anotherTime) {
        super(time, anotherTime);
        // TODO Auto-generated constructor stub
    }

    public TimeRange(MicroSecondDate beginTime, TimeInterval interval) {
        super(beginTime, interval);
        // TODO Auto-generated constructor stub
    }

    public TimeRange(MicroSecondTimeRange timeRange, MicroSecondTimeRange timeRange2) {
        super(timeRange, timeRange2);
        // TODO Auto-generated constructor stub
    }

    public TimeRange(ZonedDateTime time, ZonedDateTime anotherTime) {
        super(new MicroSecondDate(time), new MicroSecondDate(anotherTime));
    }

    public TimeRange(ZonedDateTime beginTime, TimeInterval interval) {
        super(new MicroSecondDate(beginTime), interval);
    }
}
