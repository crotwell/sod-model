package edu.sc.seis.sod.model.seismogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.sc.seis.sod.model.common.MicroSecondDate;
import edu.sc.seis.sod.model.common.TimeInterval;
import edu.sc.seis.sod.model.station.ChannelIdUtil;

/**
 * @author groves Created on Nov 2, 2004
 */
public class RequestFilterUtil {

    public static boolean areEqual(RequestFilter one, RequestFilter two) {
        if(one == two) { return true; }
        return ChannelIdUtil.areEqual(one.channel_id, two.channel_id)
                && one.start_time.equals(two.start_time)
                && one.end_time.equals(two.end_time);
    }

    public static boolean containsWildcard(RequestFilter rf) {
        return rf.channel_id.getNetworkId().equals("*") ||
        rf.channel_id.getStationCode().equals("*") ||
        rf.channel_id.getLocCode().equals("*") ||
        rf.channel_id.getChannelCode().equals("*");
    }

    public static boolean containsWildcard(List<RequestFilter> rfList) {
        for (RequestFilter rf : rfList) {
            if (containsWildcard(rf)) {return true;}
        }
        return false;
    }

    public static Map<String, List<RequestFilter>> splitByChannel(List<RequestFilter> rf) {
        HashMap<String, List<RequestFilter>> out = new HashMap<String, List<RequestFilter>>();
        for (RequestFilter requestFilter : rf) {
            String key = ChannelIdUtil.toStringNoDates(requestFilter.channel_id);
            if (!out.containsKey(key)) {
                out.put(key, new ArrayList<RequestFilter>());
            }
            out.get(key).add(requestFilter);
        }
        return out;
    }

    public static String toString(RequestFilter[] rf) {
        if (rf == null) { return "empty request/n";}
        return toString(Arrays.asList(rf));
    }

    public static String toString(RequestFilter[][] rf) {
        if (rf == null) { return "empty request/n";}
        String out = "";
        for (int i = 0; rf != null && i < rf.length; i++) {
            out += i + "\n" + toString(Arrays.asList(rf[i])) + "\n";
        }
        return out;
    }

    public static String toString(List<RequestFilter> rf) {
        if (rf == null) { return "empty request/n";}
        String s = "Request length=" + rf.size() + "\n";
        for (RequestFilter requestFilter : rf) {
            s += toString(requestFilter) + "\n";
        }
        return s;
    }

    public static String toString(RequestFilter rf) {
        return ChannelIdUtil.toStringNoDates(rf.channel_id) + " from "
                + rf.start_time.getISOString() + " to " + rf.end_time.getISOString();
    }

    public static RequestFilter[] removeSmallRequests(RequestFilter[] rf, TimeInterval minSize) {
        List<RequestFilter> out = new ArrayList<RequestFilter>();
        for (int i = 0; i < rf.length; i++) {
            MicroSecondDate b = new MicroSecondDate(rf[i].start_time);
            MicroSecondDate e = new MicroSecondDate(rf[i].end_time);
            if (e.subtract(b).greaterThan(minSize)) {
                out.add(rf[i]);
            }
        }
        return out.toArray(new RequestFilter[0]);
    }
}