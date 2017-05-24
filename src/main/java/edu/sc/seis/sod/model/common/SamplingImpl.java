
package edu.iris.Fissures.model;

import java.io.Serializable;

import edu.iris.Fissures.Sampling;

/**
 * SamplingImpl.java
 *
 *
 * Created: Wed Aug 11 11:02:00 1999
 *
 * @author Philip Crotwell
 * @version
 */

public class SamplingImpl extends Sampling {
    protected SamplingImpl() {}

    public static Serializable createEmpty() { return new SamplingImpl(); }

    public SamplingImpl(int numPoints, TimeInterval interval) {
        if (interval.value == Double.POSITIVE_INFINITY && numPoints == 1) {
            // in this case, the DMC database likely had 0 sps, and converted
            // it to 1 sample in inifinity seconds. So we change to
            // 0 samples in 1 second, which is probably more correct
            interval = new TimeInterval(1, UnitImpl.SECOND);
            numPoints = 0;
        }
        this.interval = interval;
        this.numPoints = numPoints;
    }

    public static SamplingImpl createSamplingImpl(Sampling samp) {
        if (samp instanceof SamplingImpl)  return (SamplingImpl)samp;

        return new SamplingImpl(samp.numPoints,
                                TimeInterval.createTimeInterval(samp.interval));
    }


    /** Gets the sample period. Returns a Quantity object that has
     Units of time, usually seconds.
     @return the sample period in units of time
     */
    public TimeInterval getPeriod() {
        return (TimeInterval)(getTimeInterval().divideBy(numPoints));
    }

    /** Gets the sample frequency. Returns a Quantity object that has
     units of 1/time, usually Hz.
     @return the sample frequency.
     */
    public QuantityImpl getFrequency() {
        return getTimeInterval().inverse().multiplyBy(numPoints);
    }

    public int getNumPoints() { return numPoints; }
    
    protected void setNumPoints(int n) {
        this.numPoints = n;
    }

    public TimeInterval getTimeInterval() { return TimeInterval.createTimeInterval(interval); }

    protected void setTimeInterval(QuantityImpl i) {
        this.interval = i;
    }
    
    public String toString() { return numPoints +" in "+interval; }

    public int hashCode(){
        int result = 38;
        result += 37* result + getTimeInterval().hashCode();
        result += 37 * result + getNumPoints();
        return result;
    }

    public boolean equals(Object o) {
        if (super.equals(o))  return true;
        if (o instanceof SamplingImpl) {
            SamplingImpl sampImpl = (SamplingImpl)o;
            if (sampImpl.getPeriod().equals(getPeriod()))  return true;
        }
        return false;
    }

} // SamplingImpl
