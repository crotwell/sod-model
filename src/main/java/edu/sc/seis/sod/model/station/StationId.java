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

// Version: 4.0.5

package edu.sc.seis.sod.model.station;

import edu.sc.seis.sod.model.common.MicroSecondDate;

//
// IDL:iris.edu/Fissures/IfNetwork/StationId:1.0
//
/** Identifies a station. The additional begin_time is needed as station
 *  codes can be reused if, for example the station moves only a short 
 *  distance. The begin_time should be equal to the begining
 *  effective time of the station, which is also in the Station object. 
 **/

final public class StationId  {
    public
    StationId()
    {
    }

    public
    StationId(NetworkId network_id,
              String station_code,
              MicroSecondDate begin_time)
    {
        this.network_id = network_id;
        this.station_code = station_code;
        this.begin_time = begin_time;
    }

    public NetworkId network_id;
    public String station_code;
    public MicroSecondDate begin_time;
}
