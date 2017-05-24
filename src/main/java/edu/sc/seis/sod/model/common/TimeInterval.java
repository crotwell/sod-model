
package edu.sc.seis.sod.model.common;


/**
 * TimeInterval.java
 *
 *
 * Created: Wed Sep  1 20:23:31 1999
 *
 * @author Philip Crotwell
 * @version
 */

public class TimeInterval extends QuantityImpl {

    public TimeInterval(QuantityImpl interval) {
        this(interval.getValue(), UnitImpl.createUnitImpl(interval.getUnit()));
    }

    public TimeInterval(MicroSecondDate begin, MicroSecondDate end) {
        this(end.getMicroSecondTime() - begin.getMicroSecondTime(),
             UnitImpl.MICROSECOND);
    }

    /** Creates a TimeInterval with the units and value.
     * @throws IllegalArgumentException if the units are not time.
     */
    public TimeInterval(double f, UnitImpl the_unit) {
        super(f, the_unit);
        if ( ! the_unit.isConvertableTo(UnitImpl.TIME)) {
            throw new IllegalArgumentException("TimeInterval "+f+" "+getUnit()+
                                                   " does not have units of time.");
        }
    }

    /** Creates a TimeInterval froma  Quantity, making sure that the
     *  units are time.
     * @throws IllegalArgumentException if the units are not time.
     */
    public static TimeInterval createTimeInterval(QuantityImpl q) {
        if (q instanceof TimeInterval) {
            return (TimeInterval)q;
        }
        return new TimeInterval(q);
    }

    /** overrides covertTo() in QuantityImpl to return a TimeInterval instead
     *  of a QuantityImpl.
     */
    public QuantityImpl convertTo(UnitImpl newUnit) {
        QuantityImpl q = super.convertTo(newUnit);
        return new TimeInterval(q);
    }

    /** overrides multiplyBy() in QuantityImpl to return a TimeInterval instead
     *  of a QuantityImpl.
     */
    public QuantityImpl multiplyBy(double f) {
        QuantityImpl newq = super.multiplyBy(f);
        return new TimeInterval(newq);
    }

    /** overrides divideBy() in QuantityImpl to return a TimeInterval instead
     *  of a QuantityImpl.
     */
    public QuantityImpl divideBy(double f) {
        QuantityImpl newq = super.divideBy(f);
        return new TimeInterval(newq);
    }

    public TimeInterval add(TimeInterval t) {
        QuantityImpl newq = super.add(t);
        return new TimeInterval(newq);
    }

    public TimeInterval subtract(TimeInterval t) {
        QuantityImpl newq = super.subtract(t);
        return new TimeInterval(newq);
    }

} // TimeInterval
