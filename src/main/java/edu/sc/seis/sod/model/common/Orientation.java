
package edu.sc.seis.sod.model.common;

/** represents the orientation of a single component of a seismometer.
 **/

public class Orientation {
    
    public
    Orientation()
    {
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
