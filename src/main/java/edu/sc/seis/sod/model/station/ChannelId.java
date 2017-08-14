
package edu.sc.seis.sod.model.station;

import java.time.ZonedDateTime;

import edu.sc.seis.seisFile.fdsnws.stationxml.Channel;

/** Identifies a Channel. The additional startTime is needed 
 *  as channel
 *  codes are frequently reused if, for example a new sensor is install
 *  in place of the old one, which properly creates a new Channel.
 *  The startTime should be equal to the beginning
 *  effective time of the channel, which is also in the Channel object. 
 **/

public class ChannelId {
    
    ChannelId() {
    }
    
    public ChannelId(Channel chan) {
        this(chan.getNetwork().getNetworkId(), chan.getStationCode(), chan.getLocCode(), chan.getChannelCode(), chan.getStartDateTime());
    }

    public
    ChannelId(String networkId,
              String stationCode,
              String locCode,
              String channelCode,
              ZonedDateTime startTime)
    {
        this.setNetworkId(networkId);
        this.setStationCode(stationCode);
        this.setLocCode(locCode);
        this.setChannelCode(channelCode);
        this.setStartTime(startTime);
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    private String networkId;
    private String stationCode;
    private String locCode;
    private String channelCode;
    private ZonedDateTime startTime;
}
