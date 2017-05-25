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


//
// IDL:iris.edu/Fissures/IfNetwork/DataAcqSys:1.0
//
/***/

public class DataAcqSysImpl  extends SeismicHardwareImpl
{
    
    public RecordingStyle style;

	protected DataAcqSysImpl() {
    }

    public static DataAcqSysImpl createEmpty() {
	return new DataAcqSysImpl();
    }

    public DataAcqSysImpl(int id_number,
                           String manufacturer,
                           String serial_number,
                           String model,
                           RecordingStyle style) {
        this.id_number = id_number;
        this.manufacturer = manufacturer;
        this.serial_number = serial_number;
        this.model = model;
        this.style = style;
    }

}