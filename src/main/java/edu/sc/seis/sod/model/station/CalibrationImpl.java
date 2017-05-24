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

//
// IDL:iris.edu/Fissures/IfNetwork/Calibration:1.0
//
/***/

public class CalibrationImpl {
    //
    // IDL:iris.edu/Fissures/IfNetwork/Calibration/frequency:1.0
    //
    /** frequency in hertz (HZ) */

    public float frequency;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Calibration/sensitivity:1.0
    //
    /***/

    public float sensitivity;

    //
    // IDL:iris.edu/Fissures/IfNetwork/Calibration/when:1.0
    //
    /***/

    public MicroSecondDate when;
    
    private CalibrationImpl() {
    }

    public static CalibrationImpl createEmpty() {
	return new CalibrationImpl();
    }

    public  CalibrationImpl(float frequency,
                            float sensitivity) {
        this.frequency = frequency;
        this.sensitivity = sensitivity;
    }

}
