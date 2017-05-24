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

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.sc.seis.sod.model.common.Location;
import edu.sc.seis.sod.model.common.MicroSecondDate;
import edu.sc.seis.sod.model.common.Time;
import edu.sc.seis.sod.model.common.TimeRange;

//
// IDL:iris.edu/Fissures/IfNetwork/Station:1.0
//
/***/

public class StationImpl implements Serializable
{


    /** Uniquely identifies this station. */
    protected StationId id;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/name:1.0
    //
    /**
     * A name for this station. The form of this is not specified, but it should
     * be usable as a display for a person.
     */
    protected String name;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/my_location:1.0
    //
    /**
     * The location of the station. Note that following the SEED conventions on
     * station, the actual location of the recording instruments may be slightly
     * offset from this location, up to 1 kilometer.
     */
    private Location my_location;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/effective_time:1.0
    //
    /**
     * The effective time of the stations. The begin should be at or before any
     * data is recorded. If the station is still active, the end time should be
     * TIME_UNKNOWN as defined in IfConstants.idl.
     */
    private TimeRange effective_time;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/operator:1.0
    //
    /**
     * The operator of the station. This the form of this is not specified but
     * it should be usable as a display for a person.
     */
    protected String operator;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/description:1.0
    //
    /**
     * An additional description of the station, if needed. The form is not
     * specified, but it should be usable as a display for a person.
     */
    protected String description;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/comment:1.0
    //
    /** An a additional comment about the station, with unspecified form. */
    protected String comment;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/my_network:1.0
    //
    /** The network attributes of the network that this station belongs. */
    private NetworkAttrImpl my_network;
    
    protected StationImpl() {
    }

    public static StationImpl createEmpty() {
    return new StationImpl();
    }

    public StationImpl(StationId id,
                       String name,
                       Location my_location,
                       TimeRange effective_time,
                       String operator,
                       String description,
                       String comment,
                       NetworkAttrImpl my_network) {
        this.id = id;
        this.name = name;
        this.setLocation(my_location);
        this.setEffectiveTime(effective_time);
        this.operator = operator;
        this.description = description;
        this.comment = comment;
        this.setNetworkAttr(my_network);
    }

    /** creates a StationImpl where the begin effective time is
     *  extracted from the StationId and the end is unknown, ie
     *  still operational. */
    public StationImpl(StationId id,
                       String name,
                       Location my_location,
                       String operator,
                       String description,
                       String comment,
                       NetworkAttrImpl my_network) {
        this(id,
         name,
         my_location,
         new TimeRange(id.begin_time,
                       (MicroSecondDate)null),
         operator,
         description,
         comment,
         my_network);
    }
    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/get_id:1.0
    //
    public StationId
    get_id()
    {
        return getId();
    }

    //
    // IDL:iris.edu/Fissures/IfNetwork/Station/get_code:1.0
    //
    public String
    get_code()
    {
        return getId().station_code;
    }
    
    // hibernate

    
    protected int dbid;
    protected void setDbid(int dbid) {
        this.dbid = dbid;
    }
    public int getDbid() {
        return dbid;
    }

    public void associateInDB(StationImpl indb) {
        setDbid(indb.getDbid());
    }

    public static StationImpl[] implize(StationImpl[] stations) {
        StationImpl[] out = new StationImpl[stations.length];
        for (int i = 0; i < stations.length; i++) {
            out[i] = (StationImpl)stations[i];
        }
        return out;
    }

    /** Same as getNetworkAttr(), but returns a NetworkAttrImpl to avoid the cast. */
    public NetworkAttrImpl getNetworkAttrImpl() {
        return (NetworkAttrImpl)getNetworkAttr();
    }
    
    public String toString() {
        return StationIdUtil.toStringNoDates(this);
    }
    

    // added manually
    public String getName() {
        return name;
    }

    public Location getLocation() {
        return my_location;
    }

    public TimeRange getEffectiveTime() {
        return effective_time;
    }

    public String getOperator() {
        return operator;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public NetworkAttrImpl getNetworkAttr() {
        return my_network;
    }

    public StationId getId() {
        if(id != null && id.network_id == null && getNetworkAttr() != null) {
            id.network_id = getNetworkAttr().getId();
        }
        return id;
    }

    public static void intern(StationId id) {
        id.network_id = NetworkAttrImpl.intern(id.network_id);
        id.station_code = NetworkAttrImpl.intern(id.station_code);
        id.begin_time = NetworkAttrImpl.intern(id.begin_time);
    }

    public static StationImpl intern(StationImpl station) {
        synchronized(knownStations) {
            String key = station.getNetworkAttr().getId().network_code
                    + station.getNetworkAttr().getId().begin_time.getISOString()
                    + ":" + station.getId().station_code+station.getId().begin_time.getISOString();
            
            StationImpl interned = null;
            if(knownStations.containsKey(key)) {
                // don't return here, still have to check for null due to weak reference
                interned = knownStations.get(key).get();
            }
            if(interned == null) {
                knownStations.put(key, new WeakReference<StationImpl>(station));
                intern(station.getId());
                station.setNetworkAttr(NetworkAttrImpl.intern(station.getNetworkAttr()));
                station.getId().network_id = station.getNetworkAttr().getId();
                station.setName(NetworkAttrImpl.intern(station.getName()));
                station.setDescription(NetworkAttrImpl.intern(station.getDescription()));
                station.setOperator(NetworkAttrImpl.intern(station.getOperator()));
                station.setComment(NetworkAttrImpl.intern(station.getComment()));
                station.setEffectiveTime(NetworkAttrImpl.intern(station.getEffectiveTime()));
                return station;
            }
            return interned;
        }
    }

    private static Map<String, WeakReference<StationImpl>> knownStations = Collections.synchronizedMap(new HashMap<String, WeakReference<StationImpl>>());

    protected void setId(StationId id) {
        this.id = id;
        if(id.network_id != null) {
            if(getNetworkAttr() != null) {
                getNetworkAttr().setId(id.network_id);
            }
        } else {
            if(getNetworkAttr() != null) {
                id.network_id = getNetworkAttr().get_id();
            }
        }
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location loc) {
        this.my_location = loc;
    }

    protected void setEffectiveTime(TimeRange eff) {
        this.effective_time = eff;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNetworkAttr(NetworkAttrImpl attr) {
        this.my_network = attr;
        if(my_network != null) {
            if(id != null && my_network.get_id() != null) {
                id.network_id = my_network.get_id();
            }
        }
    }

    public Time getBeginTime() {
        return get_id().begin_time;
    }
    
    public void setBeginTime(Time beginTime) {
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
    
    public void setEndTime(Time endTime) {
        if (getEffectiveTime() == null) {
            setEffectiveTime(new TimeRange(null, endTime));
        } else {
            setEffectiveTime(new TimeRange(getEffectiveTime().getBeginTime(), endTime));
        }
    }

}
