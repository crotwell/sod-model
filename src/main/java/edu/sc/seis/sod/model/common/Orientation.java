
package edu.sc.seis.sod.model.common;

import edu.sc.seis.seisFile.fdsnws.stationxml.Channel;

/** represents the orientation of a single component of a seismometer.
 **/

public class Orientation {
    
    public
    Orientation()
    {
    }
    
    public Orientation(Channel chan) {
        this(chan.getAzimuth().getValue(), chan.getDip().getValue());
    }

    public
    Orientation(float azimuth,
                float dip)
    {
        this.azimuth = azimuth;
        this.dip = dip;
    }

    
    public float getAzimuth() {
        return azimuth;
    }

    
    public float getDip() {
        return dip;
    }

    public float azimuth;
    public float dip;
}
