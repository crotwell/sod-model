package edu.sc.seis.sod.model.station;

import edu.sc.seis.seisFile.fdsnws.stationxml.Station;
import edu.sc.seis.sod.model.common.TimeFormatter;

/**
 * StationIdUtil.java Created: Wed Jan 31 13:06:03 2001
 * 
 * @author Philip Crotwell
 * @version
 */
public class StationIdUtil {

    public static boolean areEqual(StationId a, StationId b) {
        if(a == b) {
            return true;
        }
        return a.getStationCode().equals(b.getStationCode())
                && a.getNetworkId().equals(b.getNetworkId())
                && a.begin_time.equals(b.begin_time);
    }

    public static String toString(Station sta) {
        return toString(new StationId(sta));
    }

    public static String toString(StationId id) {
        return id.getNetworkId() + NetworkIdUtil.DOT + id.getStationCode()
                + NetworkIdUtil.DOT + id.begin_time.getISOString();
    }

    public static String toStringFormatDates(Station sta) {
        return toStringFormatDates(new StationId(sta));
    }

    public static String toStringFormatDates(StationId id) {
        return id.getNetworkId() + NetworkIdUtil.DOT
                + id.getStationCode() + NetworkIdUtil.DOT + TimeFormatter.format(id.begin_time);
    }

    public static String toStringNoDates(Station sta) {
        return toStringNoDates(new StationId(sta));
    }

    public static String toStringNoDates(StationId id) {
        return id.getNetworkId() + NetworkIdUtil.DOT
                + id.getStationCode();
    }

    public static boolean areEqual(Station a, Station b) {
        return areEqual(new StationId(a), new StationId(b));
    }
} // StationIdUtil
