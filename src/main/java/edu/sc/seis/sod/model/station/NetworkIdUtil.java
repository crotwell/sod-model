package edu.sc.seis.sod.model.station;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.sun.msv.reader.GrammarReader.ChainPrefixResolver;

import edu.sc.seis.seisFile.fdsnws.stationxml.Network;
import edu.sc.seis.sod.model.common.ISOTime;
import edu.sc.seis.sod.model.common.MicroSecondDate;
import edu.sc.seis.sod.model.common.TimeFormatter;

/**
 * NetworkIdUtil.java Created: Wed Jan 31 13:07:45 2001
 * 
 * @author Philip Crotwell
 * @version
 */
public class NetworkIdUtil {

    public static boolean isTemporary(Network net) {
        return isTemporary(net.getNetworkCode());
    }

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
    @Deprecated
    public static boolean areEqual(String a, String b) {
        if(a.equals(b)) {
            return true;
        }
        return false;
    }
    
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
    

    /**
     * Compares two networkIds. Dates are only checked for temporary networks.
     */
    public static boolean areEqual(Network a, Network b) {
        if(!a.getNetworkCode().equals(b.getNetworkCode())) {
            return false;
        }
        // only compare dates if temp network, ie network code starts with X, Y,
        // Z or number
        return !isTemporary(a.getNetworkCode())
                || a.getStartDateTime().equals(b.getStartDateTime());
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

    public static String getTwoCharYear(Network net) {
        int yy = net.getStartDateTime().get(ChronoField.YEAR);
        if (yy > 2000) {
            return ""+(yy-2000);
        } else {
            return ""+(yy-1900);
        }
    }

    public static String getYear(Network net) {
        return ""+net.getStartDateTime().get(ChronoField.YEAR);
    }

    public static String getYear(NetworkId id) {
        return id.begin_time.getISOString().substring(0, 4);
    }

    public static int hashCode(NetworkId id) {
        return 57 + toString(id).hashCode();
    }
    
    public static final String DOT = ".";

    public static String formId(String netCode, Instant time) {
        String out = netCode;
        if (isTemporary(netCode)) {
            out += time.get(ChronoField.YEAR);
        }
        return out;
    }

    @Deprecated
    public static String formId(String netCode, MicroSecondDate time) {
        String out = netCode;
        if (isTemporary(netCode)) {
            out += time.toInstant().get(ChronoField.YEAR);
        }
        return out;
    }
    
} // NetworkIdUtil
