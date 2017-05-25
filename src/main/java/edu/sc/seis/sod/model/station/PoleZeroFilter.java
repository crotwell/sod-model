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
// IDL:iris.edu/Fissures/IfNetwork/PoleZeroFilter:1.0
//
/** A pole zero based response filter. This corresponds to blockette 53
 *in the SEED manual. */

final public class PoleZeroFilter implements org.omg.CORBA.portable.IDLEntity
{
    public
    PoleZeroFilter()
    {
    }

    public
    PoleZeroFilter(ComplexNumberErrored[] poles,
                   ComplexNumberErrored[] zeros)
    {
        this.poles = poles;
        this.zeros = zeros;
    }

    public ComplexNumberErrored[] poles;
    public ComplexNumberErrored[] zeros;
}