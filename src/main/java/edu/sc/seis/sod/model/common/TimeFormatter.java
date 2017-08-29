package edu.sc.seis.sod.model.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

import edu.sc.seis.seisFile.fdsnws.stationxml.BaseNodeType;

/**
 * @author groves Created on Oct 28, 2004
 */
public class TimeFormatter {


    @Deprecated
    public static synchronized String format(Instant t) {
        return BaseNodeType.toISOString(t);
    }

    public static synchronized String format(MicroSecondDate t) {
        return formatter.format(t);
    }

    private static final DateFormat formatter;

	static {
		formatter = new SimpleDateFormat(ISOTime.patterns[3]);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
}