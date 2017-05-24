package edu.sc.seis.sod.model.station;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import edu.sc.seis.sod.model.common.ISOTime;
import edu.sc.seis.sod.model.common.Time;
import edu.sc.seis.sod.model.common.TimeFormatter;

/**
 * NetworkIdUtil.java Created: Wed Jan 31 13:07:45 2001
 * 
 * @author Philip Crotwell
 * @version
 */
public class NetworkIdUtil {

    public static boolean isTemporary(NetworkId id) {
        return isTemporary(id.network_code);
    }

    public static boolean isTemporary(String code) {
        return tempNetPattern.matcher(code).matches();
    }

    private static Pattern tempNetPattern = Pattern.compile("[1-9XYZ].?");

    /**
     * Compares two networkIds. Dates are only checked for temporary networks.
     */
    public static boolean areEqual(NetworkId a, NetworkId b) {
        if(!a.network_code.equals(b.network_code)) {
            return false;
        }
        // only compare dates if temp network, ie network code starts with X, Y,
        // Z or number
        return !isTemporary(a)
                || a.begin_time.equals(b.begin_time);
    }

    public static String toString(NetworkAttrImpl net) {
        return toString(net.get_id());
    }

    public static String toString(NetworkId id) {
        return id.network_code
                + DOT
                + id.begin_time.getISOString();
    }

    public static NetworkId fromString(String s) {
        StringTokenizer st = new StringTokenizer(s, DOT);
        return new NetworkId(st.nextToken(),
                             new Time(new ISOTime(st.nextToken()).getDate()));
    }

    public static StringTokenizer getTokenizerAfterNetworkId(String s) {
        StringTokenizer st = new StringTokenizer(s, DOT);
        st.nextToken();
        st.nextToken();
        return st;
    }

    public static String toStringFormatDates(NetworkAttrImpl net) {
        return toStringFormatDates(net.get_id());
    }

    public static String toStringFormatDates(NetworkId id) {
        return id.network_code + DOT + TimeFormatter.format(id.begin_time);
    }

    public static String toStringNoDates(NetworkAttrImpl net) {
        return toStringNoDates(net.get_id());
    }

    public static String toStringNoDates(NetworkId id) {
        // passcal networks need year
        if(id.network_code.startsWith("X") || id.network_code.startsWith("Y")
                || id.network_code.startsWith("Z")) {
            return id.network_code + getTwoCharYear(id);
        }
        return id.network_code;
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
