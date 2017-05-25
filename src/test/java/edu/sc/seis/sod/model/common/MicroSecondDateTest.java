package edu.sc.seis.sod.model.common;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;



public class MicroSecondDateTest {

    @Test
    public void testAdd() {
        MicroSecondDate d = new MicroSecondDate("2012227J03:19:39.7000");
        TimeInterval t = new TimeInterval(1638.0/32760.0, UnitImpl.SECOND);
        d = d.add((TimeInterval)(t.multiplyBy(406.000001)));
        assertEquals("micros", 0, d.getMicroSeconds(), 0.00001);
        
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(d);
        System.out.println(d+"  "+cal.getTime());
        int tenthMilli = (int)(cal.get(Calendar.MILLISECOND) * 10 + (Math.round(d.getMicroSeconds() / 100.0)));
        
        int year = cal.get(Calendar.YEAR);
        int jday = cal.get(Calendar.DAY_OF_YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        assertEquals("year", 2012, year);
        assertEquals("jday", 227, jday);
        assertEquals("hour", 3, hour);
        assertEquals("min", 20, min);
        assertEquals("sec", 0, sec);
        assertEquals("tenthMilli", 0, tenthMilli);
    }
    
}
