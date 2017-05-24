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
// IDL:iris.edu/Fissures/IfNetwork/RecordingStyle:1.0
//
/***/

final public class RecordingStyle {
    
    private static RecordingStyle [] values_ = new RecordingStyle[4];
    private int value_;

    public final static int _CONTINUOUS = 0;
    public final static RecordingStyle CONTINUOUS = new RecordingStyle(_CONTINUOUS);
    public final static int _TRIGGERED = 1;
    public final static RecordingStyle TRIGGERED = new RecordingStyle(_TRIGGERED);
    public final static int _OTHER = 2;
    public final static RecordingStyle OTHER = new RecordingStyle(_OTHER);
    public final static int _UNKNOWN = 3;
    public final static RecordingStyle UNKNOWN = new RecordingStyle(_UNKNOWN);

    protected
    RecordingStyle(int value)
    {
        values_[value] = this;
        value_ = value;
    }

    public int
    value()
    {
        return value_;
    }

    public static RecordingStyle
    from_int(int value)
    {
        return values_[value];
    }
}
