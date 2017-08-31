package edu.sc.seis.sod.model.common;

import java.time.Instant;

import edu.sc.seis.seisFile.fdsnws.stationxml.BaseNodeType;

/**
 * @author groves Created on Oct 28, 2004
 */
public class TimeFormatter {


    @Deprecated
    public static synchronized String format(Instant t) {
        return BaseNodeType.toISOString(t);
    }

}