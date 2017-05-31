
package edu.sc.seis.sod.model.station;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sc.seis.sod.model.common.DistAz;
import edu.sc.seis.sod.model.common.Location;
import edu.sc.seis.sod.model.common.Orientation;
import edu.sc.seis.sod.model.event.CacheEvent;

public class ChannelGroup {

    public ChannelGroup(ChannelImpl[] channels) {
        assert channels.length == 3;
        this.channels = channels;
    }
    
    protected ChannelGroup() {
    }

    protected int dbid;
    
    public void setDbid(int i) {
        dbid = i;
    }
    
    public int getDbid() {
        return dbid;
    }
    
    public ChannelImpl[] getChannels() {
        return channels;
    }

    public boolean contains(ChannelImpl c) {
        return getIndex(c) != -1;
    }

    /**
     * Finds the vertical channel. If no channel has a dip of -90 then null is
     * returned.
     */
    public ChannelImpl getVertical() {
        for(int i = 0; i < channels.length; i++) {
            if(channels[i].getOrientation().dip == -90) {
                return channels[i];
            }
            if(channels[i].getOrientation().dip == 90) {
                // flipped Z, often happens in seed as people think of channel up having positive value
                // even though convention is 90 dip is down
                return channels[i];
            }
        }
        return null;
    }

    /**
     * Finds the 2 horizontal channels.
     */
    public ChannelImpl[] getHorizontal() {
        int[] indices = getHorizontalIndices();
        ChannelImpl[] out = new ChannelImpl[indices.length];
        for(int i = 0; i < indices.length; i++) {
            out[i] = channels[indices[i]];
        }
        return out;
    }

    private int[] getHorizontalIndices() {
        int first = -1;
        for(int i = 0; i < channels.length; i++) {
            if(channels[i].getOrientation().dip == 0) {
                if(first == -1) {
                    first = i;
                } else {
                    return new int[] {first, i};
                }
            }
        }
        if(first == -1) {
            return new int[0];
        } else {
            return new int[] {first};
        }
    }

    /**
     * Gets the horizontals as X and Y, so that the first channel's azimuth is equal to
     * the seconds + 90 degrees, ie x -> east (90) and y -> north (0). If this is not possible, within 2 degrees,
     *  then a zero length array is returned.
     */
    public ChannelImpl[] getHorizontalXY() {
        return getHorizontalXY(2);
    }

    /**
     * Gets the horizontals as X and Y, so that the first channel's azimuth is equal to
     * the seconds + 90 degrees, ie x -> east (90) and y -> north (0). 
     * If this is not possible, within tolerance degrees,
     *  then a zero length array is returned.
     */
    public ChannelImpl[] getHorizontalXY(float toleranceDegrees) {
        ChannelImpl[] out = getHorizontal();
        if(out.length != 2) {
            out = new ChannelImpl[0];
        } else if(Math.abs(((360+out[0].getOrientation().azimuth - out[1].getOrientation().azimuth) % 360) - 90) < toleranceDegrees ) {
            // in right order
        } else if(Math.abs(((360+out[1].getOrientation().azimuth - out[0].getOrientation().azimuth) % 360) - 90) < toleranceDegrees ) {
            ChannelImpl tmp = out[0];
            out[0] = out[1];
            out[1] = tmp;
        } else {
            out = new ChannelImpl[0];
        }
        return out;
    }

    /**
     * Gets the channel that corresponds to this channelId from the
     * ChannelGroup. The Event is needed in case this channel id comes from a
     * seismogram that has been rotated to GCP, ie it has R or T as its
     * orientation code.
     */
    public ChannelImpl getChannel(ChannelId chanId, CacheEvent event) {
        for(int i = 0; i < channels.length; i++) {
            if(ChannelIdUtil.areEqual(chanId, channels[i].get_id())) {
                return channels[i];
            }
        }
        if(SiteIdUtil.areSameSite(chanId, channels[0].get_id())
                && chanId.channel_code.substring(0, 2)
                        .equals(channels[0].get_code().substring(0, 2))) {
            if(chanId.channel_code.endsWith("R")) {
                return getRadial(event);
            } else if(chanId.channel_code.endsWith("T")) {
                return getTransverse(event);
            }
        }
        return null;
    }

    /**
     * replaces the horizontal components with their radial and transverse
     * versions in the ChannelGroup This should only be called if the
     * seismograms that are accompanying this ChannelGroup through the vector
     * process sequence have been rotated.
     */
    public void makeTransverseAndRadial(int transverseIndex,
                                        int radialIndex,
                                        CacheEvent event) {
        channels[radialIndex] = getRadial(event);
        channels[transverseIndex] = getTransverse(event);
    }

    public ChannelImpl getRadial(CacheEvent event) {
        return getRadial(event.extractOrigin().getLocation());
    }

    public ChannelImpl getRadial(Location eventLoc) {
        DistAz distAz = new DistAz(channels[0].getSite().getLocation(), eventLoc);
        return new ChannelImpl(replaceChannelOrientation(channels[0].get_id(),
                                                                "R"),
                               channels[0].getName() + "Radial",
                               new Orientation((float)distAz.getRadialAzimuth(),
                                               0),
                               channels[0].getSamplingInfo(),
                               channels[0].getEffectiveTime(),
                               channels[0].getSite());
    }

    public ChannelImpl getTransverse(CacheEvent event) {
        return getTransverse(event.extractOrigin().getLocation());
    }

    public ChannelImpl getTransverse(Location eventLoc) {
        DistAz distAz = new DistAz(channels[0].getSite().getLocation(), eventLoc);
        return new ChannelImpl(replaceChannelOrientation(channels[0].get_id(),
                                                                "T"),
                               channels[0].getName() + "Transverse",
                               new Orientation((float)distAz.getTransverseAzimuth(),
                                               0),
                               channels[0].getSamplingInfo(),
                               channels[0].getEffectiveTime(),
                               channels[0].getSite());
    }

    private int getIndex(ChannelImpl chan) {
        for(int i = 0; i < channels.length; i++) {
            if(channels[i].equals(chan))
                return i;
        }
        // didn't find by object equals, check for ids
        for(int i = 0; i < channels.length; i++) {
            if(ChannelIdUtil.areEqual(channels[i].get_id(), chan.get_id())) {
                return i;
            }
        }
        return -1;
    }
    
    public ChannelImpl getChannel1() {
        return getChannels()[0];
    }
    
    public ChannelImpl getChannel2() {
        return getChannels()[1];
    }
    
    public ChannelImpl getChannel3() {
        return getChannels()[2];
    }
    
    public StationImpl getStation() {
        return getChannel1().getStationImpl();
    }
    
    public NetworkAttrImpl getNetworkAttr() {
        return getStation().getNetworkAttrImpl();
    }
    
    public boolean areEqual(ChannelGroup other) {
        ChannelImpl[] otherChans = other.getChannels();
        for (int i = 0; i < otherChans.length; i++) {
            if (ChannelIdUtil.areEqual(getChannel1(), otherChans[i])) {
                for (int j = 0; j < otherChans.length; j++) {
                    if(j==i) {continue;}
                    if (ChannelIdUtil.areEqual(getChannel2(), otherChans[j])) {
                        for (int k = 0; k < otherChans.length; k++) {
                            if(k==i || k==i) {continue;}
                            if (ChannelIdUtil.areEqual(getChannel3(), otherChans[k])) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private void setChannel(int index, ChannelImpl chan) {
        if (channels == null) {
         channels = new ChannelImpl[3];   
        }
        channels[index] = chan;
    }
    
    protected void setChannel1(ChannelImpl chan) {
        setChannel(0, chan);
    }
    
    protected void setChannel2(ChannelImpl chan) {
        setChannel(1, chan);
    }
    
    protected void setChannel3(ChannelImpl chan) {
        setChannel(2, chan);
    }

    public static ChannelId replaceChannelOrientation(ChannelId chanId, String orientation) {
        return new ChannelId(chanId.network_id,
                             chanId.station_code,
                             chanId.site_code,
                             chanId.channel_code.substring(0, 2)
                                     + orientation,
                                     chanId.begin_time);
    }
    
    private ChannelImpl[] channels;

    private static final Logger logger = LoggerFactory.getLogger(ChannelGroup.class);
}
