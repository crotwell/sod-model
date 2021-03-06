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

package edu.sc.seis.sod.model.event;

//
// IDL:iris.edu/Fissures/IfEvent/OriginNotFound:1.0
//
/***/

final public class OriginNotFound extends Exception
{
    public
    OriginNotFound()
    {
        super();
    }

    public
    OriginNotFound(String origin)
    {
        this.origin = origin;
    }

    public
    OriginNotFound(String reason,
                   String origin)
    {
        super(reason);
        this.origin = origin;
    }

    public String origin;
}
