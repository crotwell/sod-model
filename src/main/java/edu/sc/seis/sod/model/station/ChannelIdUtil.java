package edu.sc.seis.sod.model.station;

import java.util.StringTokenizer;

import edu.sc.seis.sod.model.common.ISOTime;
import edu.sc.seis.sod.model.common.TimeFormatter;


/**
 * ChannelIdUtil.java Created: Wed Jan 24 14:33:39 2001
 * 
 * @author Philip Crotwell
 * @version
 */
public class ChannelIdUtil {

    public static boolean areEqual(ChannelId a, ChannelId b) {
        return a.station_code.equals(b.station_code)
                && a.site_code.equals(b.site_code)
                && a.channel_code.equals(b.channel_code)
                && NetworkIdUtil.areEqual(a.network_id, b.network_id)
                && a.begin_time.equals(b.begin_time);
    }
    
    public static boolean areEqualExceptForBeginTime(ChannelId a, ChannelId b) {
        return a.station_code.equals(b.station_code)
        && a.site_code.equals(b.site_code)
        && a.channel_code.equals(b.channel_code)
        && NetworkIdUtil.areEqual(a.network_id, b.network_id);
    }
    
    public static boolean areEqual(ChannelImpl a, ChannelImpl b) {
        return areEqual(a.getId(), b.getId()) &&
        a.getName() == b.getName() &&
        a.getOrientation().azimuth == b.getOrientation().azimuth &&
        a.getOrientation().dip == b.getOrientation().dip &&
        a.getSamplingInfo().numPoints == b.getSamplingInfo().numPoints &&
        a.getSamplingInfo().interval.getUnit().equals(b.getSamplingInfo().interval.getUnit()) &&
        a.getSamplingInfo().interval.getValue() == b.getSamplingInfo().interval.getValue();
    }

    public static String toStringNoDates(ChannelId id) {
        return NetworkIdUtil.toStringNoDates(id.network_id) + NetworkIdUtil.DOT
                + id.station_code + NetworkIdUtil.DOT + id.site_code + NetworkIdUtil.DOT + id.channel_code;
    }

    public static String toStringNoDates(ChannelImpl chan) {
        return toStringNoDates(chan.get_id());
    }

    public static String toString(ChannelId id) {
        return NetworkIdUtil.toString(id.network_id)
                + NetworkIdUtil.DOT
                + id.station_code
                + NetworkIdUtil.DOT
                + id.site_code
                + NetworkIdUtil.DOT
                + id.channel_code
                + NetworkIdUtil.DOT
                + id.begin_time;
    }

    public static ChannelId fromString(String s) {
        NetworkId netId = NetworkIdUtil.fromString(s);
        StringTokenizer st = NetworkIdUtil.getTokenizerAfterNetworkId(s);
        return new ChannelId(netId,
                             st.nextToken(),
                             st.nextToken(),
                             st.nextToken(),
                             new ISOTime(st.nextToken()).getDate());
    }

    public static String toStringFormatDates(ChannelId id) {
        return NetworkIdUtil.toStringFormatDates(id.network_id) + NetworkIdUtil.DOT
                + id.station_code + NetworkIdUtil.DOT + id.site_code + NetworkIdUtil.DOT + id.channel_code
                + NetworkIdUtil.DOT + TimeFormatter.format(id.begin_time);
    }
    
    public static String getBandCode(ChannelId id) {
        return ""+id.channel_code.charAt(0);
    }
    
    public static String getGainCode(ChannelId id) {
        return ""+id.channel_code.charAt(1);
    }
    
    public static String getOrientationCode(ChannelId id) {
        return ""+id.channel_code.charAt(2);
    }

    public static float minSPSForBandCode(String bandCode) {
        return minSPSForBandCode(bandCode.charAt(0));
    }
    
    public static float minSPSForBandCode(char bandCode) {
        float minSps = 0;
        switch (bandCode) {
            case 'F': 
            case 'G': 
                minSps = 1000;
                break;
            case 'D': 
            case 'C': 
                minSps = 250;
                break;
            case 'E': 
                minSps = 80;
                break;
            case 'S': 
                minSps = 10;
                break;
            case 'H': 
                minSps = 80;
                break;
            case 'B': 
                minSps = 10;
                break;
            case 'M': 
                minSps = 1;
                break;
            case 'L': 
                minSps = 1;
                break;
            case 'V': 
                minSps = 0.1f;
                break;
            case 'U': 
                minSps = 0.01f;
                break;
            case 'R': 
                minSps = 0.001f;
                break;
            default: 
                minSps = 1;
        }
        return minSps;
    }

    public static int hashCode(ChannelId id) {
        return 12 + toString(id).hashCode();
    }
} // ChannelIdUtil
