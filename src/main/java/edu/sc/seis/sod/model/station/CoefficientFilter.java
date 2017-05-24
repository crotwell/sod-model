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
// IDL:iris.edu/Fissures/IfNetwork/CoefficientFilter:1.0
//
/** A coefficient based response filter. This corresponds to blockette 54
 *in the SEED manual. */

final public class CoefficientFilter implements org.omg.CORBA.portable.IDLEntity
{
    public
    CoefficientFilter()
    {
    }

    public
    CoefficientFilter(CoefficientErrored[] numerator,
                      CoefficientErrored[] denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public CoefficientErrored[] numerator;
    public CoefficientErrored[] denominator;
}
