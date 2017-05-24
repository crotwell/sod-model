package edu.sc.seis.sod.model.station;

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
        return a.station_code.equals(b.station_code)
                && NetworkIdUtil.areEqual(a.network_id, b.network_id)
                && a.begin_time.equals(b.begin_time);
    }

    public static String toString(StationImpl sta) {
        return toString(sta.get_id());
    }

    public static String toString(StationId id) {
        return NetworkIdUtil.toString(id.network_id) + NetworkIdUtil.DOT + id.station_code
                + NetworkIdUtil.DOT + id.begin_time.getISOString();
    }

    public static String toStringFormatDates(StationImpl sta) {
        return toStringFormatDates(sta.get_id());
    }

    public static String toStringFormatDates(StationId id) {
        return NetworkIdUtil.toStringFormatDates(id.network_id) + NetworkIdUtil.DOT
                + id.station_code + NetworkIdUtil.DOT + TimeFormatter.format(id.begin_time);
    }

    public static String toStringNoDates(StationImpl sta) {
        return toStringNoDates(sta.get_id());
    }

    public static String toStringNoDates(StationId id) {
        return NetworkIdUtil.toStringNoDates(id.network_id) + NetworkIdUtil.DOT
                + id.station_code;
    }

    public static boolean areEqual(StationImpl a, StationImpl b) {
        return areEqual(a.get_id(), b.get_id());
    }
} // StationIdUtil
