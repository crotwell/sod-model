package edu.sc.seis.sod.model.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class ISOTimeTest {

    @BeforeClass
    public static void loadISOTime() {
       Class c = ISOTime.class; 
    }
    
    @Test
    public void testRawParseMicroseconds() throws ParseException {
        String isoTenth = "2012-10-21T23:59:50.1Z";
        String isoMilli = "2012-10-21T23:59:50.123Z";
        String isoMicro = "2012-10-21T23:59:50.123456Z";
        String isoTenthMilli = "2012-10-21T23:59:50.1234Z";
        SimpleDateFormat fMilli = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        SimpleDateFormat fMicro = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX");
        
        // this fails with java.text.ParseException: Unparseable date: 
        //Date dateTenth = fMilli.parse(isoTenth);
        
        // this works
        Date dateMilli = fMilli.parse(isoMilli);
        
        // this fails with java.text.ParseException: Unparseable date: 
        //Date dateMicro = fMilli.parse(isoMicro);
        
        // this works
        Date dateMicroB = fMicro.parse(isoMicro);
        
        // this fails with java.text.ParseException: Unparseable date: 
        //Date dateMilliB = fMicro.parse(isoMilli);
        
        // this fails with java.text.ParseException: Unparseable date: 
        //Date dateTenthMilliB = fMicro.parse(isoTenthMilli);
    }
    @Test
    public void testISOTimeString() {
        ISOTime iso = new ISOTime("2012102J235959.000Z");
        assertTrue(true);
    }

    @Test
    public void testISOTimeString2() {
        ISOTime iso = new ISOTime("2012102J235959.000Z");
        assertTrue(true);
    }

    @Test
    public void testGetMicros() {
        ISOTime iso = new ISOTime("2012102J235959.123Z");
        assertEquals("000", iso.getMicroseconds());
        iso = new ISOTime("2012102J235959.1234Z");
        assertEquals("400", iso.getMicroseconds());
        iso = new ISOTime("2012102J235959.12345Z");
        assertEquals("450", iso.getMicroseconds());
        iso = new ISOTime("2012102J235959.123456Z");
        assertEquals("456", iso.getMicroseconds());
    }

    @Test
    public void testMSDRoundTrip() {
        String s = "2012-03-01T23:59:59.";
        NumberFormat f = new DecimalFormat("0000");
        for (int i = 0; i < 10000; i++) {
            String micros = f.format(i);
            if (i%10 == 0) {
                // skip 0 as we don't add 4th digit if zero
                micros = micros.substring(0, 3);
            }
            String testS = s + micros + "Z";
            MicroSecondDate d = new MicroSecondDate(testS);
            assertEquals(i%10, d.getMicroSeconds()/100);
            assertEquals(testS, d.getISOString());
        }
        assertTrue(true);
    }
}
