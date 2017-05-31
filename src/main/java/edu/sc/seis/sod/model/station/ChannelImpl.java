// **********************************************************************
//
// Generated by the ORBacus IDL to Java Translator
//
// Copyright (c) 2000
// Object Oriented Concepts, Inc.
// Billerica, MA, USA
//
// All Rights Reserved
//
// **********************************************************************

// Version: 4.0.3

package edu.sc.seis.sod.model.station;

import edu.sc.seis.sod.model.common.MicroSecondDate;
import edu.sc.seis.sod.model.common.MicroSecondTimeRange;
import edu.sc.seis.sod.model.common.Orientation;
import edu.sc.seis.sod.model.common.SamplingImpl;
import edu.sc.seis.sod.model.common.Time;
import edu.sc.seis.sod.model.common.TimeRange;

//
// IDL:iris.edu/Fissures/IfNetwork/Channel:1.0
//
/***/

public class ChannelImpl {


    /** Uniquely identifies this channel. Note that channel codes can be 
     *  reused within a site, and hence a time is attached to the channel
     *  to uniquely identify it. */

    protected ChannelId id;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Channel/name:1.0
    //
    /** A name for this channel. The form is not specified, but it should be
     *  usable as a display for a person. If no name is needed, then a zero
     *length string should be used.*/

    private String name;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Channel/an_orientation:1.0
    //
    /** The orientation of the recording instrument for this channel. This
     *should follow the SEED conventions. */

    private Orientation an_orientation;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Channel/sampling_info:1.0
    //
    /** The nominal sampling rate for this channel. This is the sampling rate
     *  that the channel is intended to record at, but due to instrument
     *  problems, clock errors and timing errors, the actual sampling rate
     *  at any particular time may differ from this value. */

    private SamplingImpl sampling_info;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Channel/effective_time:1.0
    //
    /** The effective time of the channel. The begin should be at or before
     *  the first data is recorded on this channel. Note that it is illegal
     *  have more than one channel within a site with the same code with
     *  overlaping effective times. */

    private TimeRange effective_time;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Channel/my_site:1.0
    //
    /** The site that this channel belongs to.*/

    private SiteImpl my_site;
    
    public ChannelImpl(ChannelId id,
                       String name,
                       Orientation an_orientation,
                       SamplingImpl sampling_info,
                       TimeRange effective_time,
                       SiteImpl my_site) {
        this.id = id;
        this.setName(name);
        this.setOrientation(an_orientation);
        this.setSamplingInfo(sampling_info);
        this.setEffectiveTime(effective_time);
        this.setSite(my_site);
    }

    public ChannelImpl(ChannelId id,
                       String name,
                       Orientation an_orientation,
                       SamplingImpl sampling_info,
                       TimeRange effective_time,
                       SiteImpl my_site,
                       int dbid) {
        this.id = id;
        this.setName(name);
        this.setOrientation(an_orientation);
        this.setSamplingInfo(sampling_info);
        this.setEffectiveTime(effective_time);
        this.setSite(my_site);
        this.dbid = dbid;
    }

    /** for hibernate */
    protected ChannelImpl() {
    }

    /** Only for use for CORBA object serialization. */
    public static ChannelImpl createEmpty() {
    return new ChannelImpl();
    }

    //
    // IDL:iris.edu/Fissures/IfNetwork/Channel/get_id:1.0
    //
    public ChannelId
    get_id()
    {
        return getId();
    }

    //
    // IDL:iris.edu/Fissures/IfNetwork/Channel/get_code:1.0
    //
    public String
    get_code()
    {
        return getId().channel_code;
    }
    
    /** Same as getNetworkAttr(), but returns a NetworkAttrImpl to avoid casting. */
    public NetworkAttrImpl getNetworkAttrImpl() {
        return (NetworkAttrImpl)getNetworkAttr();
    }
    
    /** Same as getStation(), but returns a StationImpl to avoid casting. */
    public StationImpl getStationImpl() {
        return (StationImpl)getStation();
    }

    /** Same as getSite(), but returns a SiteImpl to avoid casting. */
    public SiteImpl getSiteImpl() {return (SiteImpl)getSite(); }

    /**
     * Calculates a default azimuth based on the orientation code,
     * 0 for Z and N, 90 for E, -1 otherwise
     * @param orientationCode
     * @return
     */
    public static int getAzimuth(String chanCode) {
        if (chanCode.endsWith("Z") || chanCode.endsWith("N")) {
            return 0;
        } else if (chanCode.endsWith("E")) {
            return 90;
        }
        return -1;
    }
    /**
     * Calculates a default dip based on the orientation code,
     * -90 for Z, 0 for N and E, -1 otherwise
     * @param orientationCode
     * @return
     */
    public static int getDip(String chanCode) {
        if (chanCode.endsWith("E") || chanCode.endsWith("N")) {
            return 0;
        } else if (chanCode.endsWith("Z")) {
            return -90;
        }
        return -1;
    }
    
    protected int dbid;
    protected void setDbid(int dbid) {
        this.dbid = dbid;
    }
    public int getDbid() {
        return dbid;
    }

    public void associateInDB(ChannelImpl indb) {
        setDbid(indb.getDbid());
    }
    
    public static ChannelImpl[] implize(ChannelImpl[] chan) {
        if (chan instanceof ChannelImpl[]) {
            return (ChannelImpl[])chan;
        }
        ChannelImpl[] out = new ChannelImpl[chan.length];
        for(int i = 0; i < out.length; i++) {
            out[i] = (ChannelImpl)chan[i];
        }
        return out;
    }
    
    public String toString() {
        return ChannelIdUtil.toStringNoDates(this);
    }
    
    public String getName() {return name;}

    public Orientation getOrientation() {return an_orientation;}

    public SamplingImpl getSamplingInfo() {return sampling_info;}

    public TimeRange getEffectiveTime() { return effective_time; }

    public SiteImpl getSite() {return my_site; }

    public StationImpl getStation() {
        return getSite().getStation();
    }
    
    public NetworkAttrImpl getNetworkAttr() {
        return getStation().getNetworkAttr();
    }
    
    public static ChannelImpl intern(ChannelImpl channel) {
        intern(channel.get_id());
        channel.setSite(SiteImpl.intern(channel.getSite()));
        NetworkAttrImpl.intern(channel.getEffectiveTime());
        channel.setName(NetworkAttrImpl.intern(channel.getName()));
        return channel;
    }

    private static void intern(ChannelId id) {
        id.channel_code = NetworkAttrImpl.intern(id.channel_code);
        id.network_id = NetworkAttrImpl.intern(id.network_id);
        id.station_code = NetworkAttrImpl.intern(id.station_code);
        id.site_code = NetworkAttrImpl.intern(id.site_code);
        id.begin_time = NetworkAttrImpl.intern(id.begin_time);
    }
    
    public void setSite(SiteImpl site) {
        this.my_site = site;
    }

 // hibernate
    public ChannelId getId() {
        if (id.network_id == null) {
            // lazy loading by hibernate
            id.network_id = getSite().getStation().getNetworkAttr().getId();
        }
        return id;
    }
    protected void setId(ChannelId id) {
        if (this.id != null) {
            if (id.network_id == null) {
                id.network_id = this.id.network_id;
            }
        }
        this.id = id;
        if (id.begin_time == null && getEffectiveTime() != null) {
            id.begin_time = getEffectiveTime().getBeginTime();
        }
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOrientation(Orientation orientation) {
        this.an_orientation = orientation;
    }
    public void setSamplingInfo(SamplingImpl sampling_info) {
        this.sampling_info = sampling_info;
    }
    
    public void setEffectiveTime(TimeRange effectiveTime) {
        this.effective_time = effectiveTime;
    }

    public MicroSecondDate getBeginTime() {
        return get_id().begin_time;
    }
    
    public void setBeginTime(MicroSecondDate beginTime) {
        if (getEffectiveTime() == null) {
            setEffectiveTime(new TimeRange(beginTime, (MicroSecondDate)null));
        } else {
            setEffectiveTime(new TimeRange(beginTime, getEffectiveTime().getEndTime()));
        }
        if (id != null) {
            id.begin_time = beginTime;
        }
    }
    
    public MicroSecondDate getEndTime() {
        return getEffectiveTime().getEndTime();
    }
    
    public void setEndTime(MicroSecondDate endTime) {
        if (getEffectiveTime() == null) {
            setEffectiveTime(new TimeRange(null, endTime));
        } else {
            setEffectiveTime(new TimeRange(getEffectiveTime().getBeginTime(), endTime));
        }
    }
}
