

package edu.sc.seis.sod.model.seismogram;

import java.time.Duration;
import java.time.Instant;

import edu.sc.seis.seisFile.ChannelTimeWindow;
import edu.sc.seis.seisFile.fdsnws.stationxml.Channel;
import edu.sc.seis.sod.model.station.ChannelId;


final public class RequestFilter {
    
    RequestFilter()
    {
    }
    
    public static RequestFilter of(LocalSeismogramImpl seismogram) {
        RequestFilter out = new RequestFilter();
        out.channel_id = seismogram.getChannelID();
        out.start_time = seismogram.begin_time;
        out.end_time = seismogram.getEndTime();
        return out;
    }


    public
    RequestFilter(ChannelId channel,
                  Instant start_time,
                  Instant end_time)
    {
        this.channel_id = channel;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public
    RequestFilter(Channel channel,
                  Instant start_time,
                  Instant end_time)
    {
        this.channel_id = new ChannelId(channel);
        this.start_time = start_time;
        this.end_time = end_time;
    }
    
    public ChannelTimeWindow asChannelTimeWindow() {
        return new ChannelTimeWindow(channel_id.getNetworkId(),
                                     channel_id.getStationCode(),
                                     channel_id.getLocCode(),
                                     channel_id.getChannelCode(),
                                     start_time,
                                     end_time);
    }
    
    public Duration getDuration() {
        return Duration.between(start_time, end_time);
    }

    public ChannelId channel_id;
    public Instant start_time;
    public Instant end_time;
}
