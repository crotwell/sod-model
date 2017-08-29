
package edu.sc.seis.sod.model.station;

import java.time.temporal.ChronoField;

import edu.sc.seis.seisFile.fdsnws.stationxml.Network;
import edu.sc.seis.sod.model.common.MicroSecondDate;

/** Identifies a network. The additional begin_time is needed for temporary
 *  PASSCAL networks. The begin_time should be equal to the beginning 
 *  effective time of the network, which is also in the NetworkAttributes. 
 **/

@Deprecated
public class NetworkId 
{
    public NetworkId() {
    }
    
    public NetworkId(String code, Integer startYear) {
        this.networkCode = code;
        this.startYear = startYear;
    }
    
    public NetworkId(Network net) {
        this(net.getCode(), net.getStartDateTime().get(ChronoField.YEAR));
    }

    public NetworkId(String network_code,
              MicroSecondDate startTime)
    {
        this.networkCode = network_code;
        this.begin_time = begin_time;
    }

    public String networkCode;
    
    Integer startYear = null; // only temp networks need
    
    public MicroSecondDate begin_time;
    
}
