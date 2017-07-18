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

package edu.sc.seis.sod.model.common;

//
// IDL:iris.edu/Fissures/Location:1.0
//
/** A location on or in the earth. */

public class Location
{
    public
    Location()
    {
    }

    public
    Location(float latitude,
             float longitude,
             QuantityImpl elevation,
             QuantityImpl depth) {
        this(latitude, longitude, elevation, depth, LocationType.GEOGRAPHIC);
    }

    public
    Location(float latitude,
             float longitude,
             QuantityImpl elevation,
             QuantityImpl depth,
             LocationType type)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.depth = depth;
        this.type = type;
    }

    public float latitude;
    public float longitude;
    public QuantityImpl elevation;
    public QuantityImpl depth;
    public LocationType type;
    
    
}
