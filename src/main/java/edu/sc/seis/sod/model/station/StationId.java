
package edu.sc.seis.sod.model.station;

import java.time.ZonedDateTime;

import edu.sc.seis.seisFile.fdsnws.stationxml.Station;
import edu.sc.seis.sod.model.common.MicroSecondDate;

/** Identifies a station. The additional begin_time is needed as station
 *  codes can be reused if, for example the station moves only a short 
 *  distance. The begin_time should be equal to the beginning
 *  effective time of the station, which is also in the Station object. 
 **/

public class StationId  {
    public StationId() {
    }

    public StationId(Station station) {
        this(station.getNetworkId(), station.getStationCode(), station.getStartDateTime());
    }
    

    
    public
    StationId(String network_id,
              String station_code,
              ZonedDateTime startTime)
    {
        this.setNetworkId(network_id);
        this.setStationCode(station_code);
        this.startTime = startTime;
    }
    
    @Deprecated
    public
    StationId(NetworkId network_id,
              String station_code,
              MicroSecondDate begin_time)
    {
        this.setNetworkId(network_id.toString());
        this.setStationCode(station_code);
        this.begin_time = begin_time;
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


    public ZonedDateTime getStartTime() {
        return startTime;
    }


    private String networkId;
    private String stationCode;
    public MicroSecondDate begin_time;
    
    private ZonedDateTime startTime;
}
