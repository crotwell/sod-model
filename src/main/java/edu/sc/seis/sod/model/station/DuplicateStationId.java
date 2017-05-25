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

//
// IDL:iris.edu/Fissures/IfNetwork/DuplicateStationId:1.0
//
/***/

final public class DuplicateStationId extends Exception
{

    public
    DuplicateStationId(StationId station)
    {
        this.station = station;
    }

    public
    DuplicateStationId(String reason,
                       StationId station)
    {
        super(reason);
        this.station = station;
    }

    public StationId station;
}