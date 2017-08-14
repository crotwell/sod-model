package edu.sc.seis.sod.model.station;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import edu.sc.seis.seisFile.fdsnws.stationxml.Network;
import edu.sc.seis.sod.model.common.ISOTime;
import edu.sc.seis.sod.model.common.TimeFormatter;

/**
 * NetworkIdUtil.java Created: Wed Jan 31 13:07:45 2001
 * 
 * @author Philip Crotwell
 * @version
 */
public class NetworkIdUtil {

    public static boolean isTemporary(NetworkId id) {
        return isTemporary(id.networkCode);
    }

    public static boolean isTemporary(String code) {
        return tempNetPattern.matcher(code).matches();
    }

    private static Pattern tempNetPattern = Pattern.compile("[1-9XYZ].?");

    /**
     * Compares two networkIds. Dates are only checked for temporary networks.
     */
    public static boolean areEqual(NetworkId a, NetworkId b) {
        if(!a.networkCode.equals(b.networkCode)) {
            return false;
        }
        // only compare dates if temp network, ie network code starts with X, Y,
        // Z or number
        return !isTemporary(a)
                || a.begin_time.equals(b.begin_time);
    }

    @Deprecated
    public static String toString(Network net) {
        return net.getNetworkId();
    }

    public static String toString(NetworkId id) {
        return id.networkCode
                + DOT
                + id.begin_time.getISOString();
    }

    @Deprecated
    public static NetworkId fromString(String s) {
        StringTokenizer st = new StringTokenizer(s, DOT);
        return new NetworkId(st.nextToken(),
                             new ISOTime(st.nextToken()).getDate());
    }

    public static StringTokenizer getTokenizerAfterNetworkId(String s) {
        StringTokenizer st = new StringTokenizer(s, DOT);
        st.nextToken();
        st.nextToken();
        return st;
    }

    public static String toStringFormatDates(Network net) {
        return net.getNetworkId();
    }

    public static String toStringFormatDates(NetworkId id) {
        return id.networkCode + DOT + TimeFormatter.format(id.begin_time);
    }

    public static String toStringNoDates(Network net) {
        return net.getNetworkId();
    }

    public static String toStringNoDates(NetworkId id) {
        // passcal networks need year
        if(id.networkCode.startsWith("X") || id.networkCode.startsWith("Y")
                || id.networkCode.startsWith("Z")) {
            return id.networkCode + getTwoCharYear(id);
        }
        return id.networkCode;
    }

    public static String getTwoCharYear(NetworkId id) {
        return id.begin_time.getISOString().substring(2, 4);
    }

    public static String getYear(NetworkId id) {
        return id.begin_time.getISOString().substring(0, 4);
    }

    public static int hashCode(NetworkId id) {
        return 57 + toString(id).hashCode();
    }
    
    public static final String DOT = ".";
    
} // NetworkIdUtil
