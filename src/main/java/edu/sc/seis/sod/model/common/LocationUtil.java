/**
 * LocationUtil.java
 * 
 * @author Created by Omnicore CodeGuide
 */
package edu.sc.seis.sod.model.common;


public class LocationUtil {

    public static int hash(Location l) {
        int result = 47;
        result = 37 * result + l.depth.hashCode();
        result = 37 * result + l.elevation.hashCode();
        result = 37 * result + Float.floatToIntBits(l.latitude);
        result = 37 * result + Float.floatToIntBits(l.longitude);
        return result;
    }

    public static boolean areEqual(Location a, Location b) {
        if(a == b) { return true; }
        return a.depth.equals(b.depth) && a.elevation.equals(b.elevation)
                && a.latitude == b.latitude && a.longitude == b.longitude;
    }
}