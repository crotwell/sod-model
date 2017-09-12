package edu.sc.seis.sod.model.seismogram;

import java.time.Duration;
import java.time.Instant;

import edu.sc.seis.sod.model.common.ParameterRef;
import edu.sc.seis.sod.model.common.QuantityImpl;
import edu.sc.seis.sod.model.common.SamplingImpl;
import edu.sc.seis.sod.model.common.UnitImpl;
import edu.sc.seis.sod.model.station.ChannelId;

/**
 * MotionVectorAttrImpl.java
 *
 *
 * Created: Thu Dec  6 22:00:42 2001
 * The strange extends is to avoid diamond of death while still reusing this
 * class in LocalMotionVector.
 *
 * @author Philip Crotwell
 */

public class MotionVectorAttrImpl implements java.io.Serializable {


    protected String id;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/properties:1.0
    //
    /***/

    public Property[] properties;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/begin_time:1.0
    //
    /***/

    public Instant begin_time;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/num_points:1.0
    //
    /***/

    public int num_points;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/sampling_info:1.0
    //
    /***/

    public SamplingImpl sampling_info;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/y_unit:1.0
    //
    /***/

    public UnitImpl y_unit;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/channel_group:1.0
    //
    /***/

    public ChannelId[] channel_group;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/parm_ids:1.0
    //
    /***/

    public ParameterRef[] parm_ids;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/time_corrections:1.0
    //
    /** These last two items deal with time corrections.<br>
     *the time adjustments that have been applied to the begin time. */

    public Duration[] time_corrections;

    //
    // IDL:iris.edu/Fissures/IfSeismogramDC/MotionVectorAttr/sample_rate_history:1.0
    //
    /** Sampling rate changed that have been applied */

    public SamplingImpl[] sample_rate_history;


    
    protected MotionVectorAttrImpl() {
    }

    public MotionVectorAttrImpl(String id,
			    Property[] properties,
			    Instant begin_time,
			    int num_points,
			    SamplingImpl sampling_info,
			    UnitImpl y_unit,
			    ChannelId[] channel_group,
			    ParameterRef[] parm_ids,
			    Duration[] time_corrections,
			    SamplingImpl[] sample_rate_history) {
	this.id = id;
	this.properties = properties;
	this.begin_time = begin_time;
	this.num_points = num_points;
	this.sampling_info = sampling_info;
	this.y_unit = y_unit;
	this.channel_group = channel_group;
	this.parm_ids = parm_ids;
	this.time_corrections = time_corrections;
	this.sample_rate_history = sample_rate_history;
    }

    /** A factory method to create an empty LocalDataSetImpl. 
     *  This is to be used only by the ORB for unmarshelling
     *  valuetypes that have been sent via IIOP.
     */
    public static java.io.Serializable createEmpty() {
        return new MotionVectorAttrImpl();
    }

    public String get_id() {
	return id;
    }
    
}// MotionVectorAttrImpl
