package edu.sc.seis.sod.model.seismogram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import edu.sc.seis.seisFile.TimeUtils;
import edu.sc.seis.sod.model.common.FissuresException;
import edu.sc.seis.sod.model.common.SamplingImpl;
import edu.sc.seis.sod.model.common.UnitImpl;
import edu.sc.seis.sod.model.station.ChannelId;

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class LocalSeismogramImplTest
{

    LocalSeismogramImpl localseismogramimpl = null;
    byte[] dataBits = new byte[99];
    int bytesPerSample = 3;



    public LocalSeismogramImpl createInstance() throws Exception {
    String name = "Test";

    // generate bytesPerSample data as all 1
    for ( int i=0; i<dataBits.length; i++) {
        if ( i % bytesPerSample == bytesPerSample-1 ) {
        dataBits[i] = 1;
        } // end of if ()
    } // end of for ()
        TimeSeriesDataSel bits = new TimeSeriesDataSel();
    EncodedData[] edata = new EncodedData[1];
    edata[0] = new EncodedData((short)edu.iris.dmc.seedcodec.B1000Types.INT24,
                  dataBits,
                   dataBits.length / bytesPerSample,
                   false);
        bits.encoded_values(edata);


        Instant time =
        TimeUtils.parseISOString("19991231T235959.000Z");
        String id = "Nowhere: "+name;
        SamplingImpl sampling =
            SamplingImpl.ofSamplesSeconds(20, 1.0);
        ChannelId channelID = new ChannelId("XX",
                        "FAKE",
                        "00",
                        "BHZ",
                                            time);


        Property[] props = new Property[1];
        props[0] = new Property("Name", name);
        LocalSeismogramImpl seis =
        new LocalSeismogramImpl(id,
                    time,
                    dataBits.length / bytesPerSample,
                    sampling,
                    UnitImpl.COUNT,
                    channelID,
                    bits);
        seis.setProperties(props);
        return seis;
    }

    @Before
    public void setUp() throws Exception {
    localseismogramimpl = createInstance();
    }

    @Test
    public void testGetNumPoints() throws Exception {
    assertEquals(dataBits.length/bytesPerSample,
             localseismogramimpl.getNumPoints());
    }

    
    public void testSerialize() throws Exception {
        File tmp = File.createTempFile("seis", "_tmp");
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(tmp)));
        out.writeObject( localseismogramimpl);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(tmp)));
        LocalSeismogramImpl inSeis = (LocalSeismogramImpl)in.readObject();
        tmp.delete();
        assertEquals(localseismogramimpl.get_as_longs()[0], inSeis.get_as_longs()[0]);
    }


    @Test
    public void testIs_long() throws Exception {
    assertFalse(localseismogramimpl.is_long());
    }

    @Test
    public void testIs_float() throws Exception {
    assertFalse(localseismogramimpl.is_float());
    }

    @Test
    public void testIs_double() throws Exception {
    assertFalse(localseismogramimpl.is_double());
    }

    @Test
    public void testIs_short() throws Exception {
    assertFalse(localseismogramimpl.is_short());
    }

    @Test
    public void testIs_encoded() throws Exception {
    assertTrue(localseismogramimpl.is_encoded());
    }

    @Test
    public void testCan_convert_to_long() throws Exception {
    assertTrue(localseismogramimpl.can_convert_to_long());
    }

    @Test
    public void testCan_convert_to_float() throws Exception {
    assertTrue(localseismogramimpl.can_convert_to_float());
    }

    @Test
    public void testCan_convert_to_double() throws Exception {
    assertTrue(localseismogramimpl.can_convert_to_double());
    }

    @Test
    public void testCan_convert_to_short() throws Exception {
    assertFalse(localseismogramimpl.can_convert_to_short());
    }

    @Test
    public void testGet_as_longs() throws Exception {
    int[] out = localseismogramimpl.get_as_longs();
    assertNotNull("get as longs returned null", out);
    assertEquals("length of get_as_longs and getNumPoints not the same."
             +out.length+" "+localseismogramimpl.getNumPoints(),
             out.length,
             localseismogramimpl.getNumPoints());
    }

    @Test
    public void testGet_as_shorts() throws Exception {
        try {
    short[] out = localseismogramimpl.get_as_shorts();
            assertTrue("want the exception to throw, so should never get here",false);
        } catch(FissuresException e) {}
    }

    @Test
    public void testGet_as_floats() throws Exception {
    float[] out = localseismogramimpl.get_as_floats();
    assertNotNull("get as floats returned null", out);
    assertEquals("length of get_as_floats and getNumPoints not the same."
             +out.length+" "+localseismogramimpl.getNumPoints(),
             out.length,
             localseismogramimpl.getNumPoints());
    }

    @Test
    public void testGet_as_doubles() throws Exception {
    double[] out = localseismogramimpl.get_as_doubles();
    assertNotNull("get as doubles returned null", out);
    assertEquals("length of get_as_doubles and getNumPoints not the same."
             +out.length+" "+localseismogramimpl.getNumPoints(),
             out.length,
             localseismogramimpl.getNumPoints());
    }

    @Test
    public void testGet_as_encoded() throws Exception {
    EncodedData[] out = localseismogramimpl.get_as_encoded();
    assertNotNull("encoded", out);
    }


}
