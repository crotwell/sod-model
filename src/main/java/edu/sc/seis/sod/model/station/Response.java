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
// IDL:iris.edu/Fissures/IfNetwork/Response:1.0
//
/***/

final public class Response 
{
    public
    Response()
    {
    }

    public
    Response(Sensitivity the_sensitivity,
             Stage[] stages)
    {
        this.the_sensitivity = the_sensitivity;
        this.stages = stages;
    }

    public Sensitivity the_sensitivity;
    public Stage[] stages;
}