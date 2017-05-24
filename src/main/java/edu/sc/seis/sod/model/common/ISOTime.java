package edu.sc.seis.sod.model.common;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * ISOTime.java Created: Fri Jul 9 13:45:39 1999
 * 
 * @author Philip Crotwell
 */
public class ISOTime {

    public static final String TIME_UNKNOWN = "TIME_UNKNOWN";

    public static final MicroSecondDate future = 
        (new ISOTime("2499001J00:00:00.000Z")).getDate();

    /** future plus one day so that is is after(future)
     */
    public static final MicroSecondDate futurePlusOne = 
        (new ISOTime("2499002J00:00:00.000Z")).getDate();

    
    /**
     * parses a ISO8601 string into its component parts. Currently we only
     * support the 4 month based, full formats, <br>
     * yyyy-mm-ddThh:mm:ss.ddddZTD <br>yyyy-mm-ddThhmmss.ddddZTD <br>
     * yyyymmddThh:mm:ss.ddddZTD <br>yyyymmddThhmmss.ddddZTD, <br>plus the
     * similar julian day formats, <br>yyyyjjjThh:mm:ss.ddddZTD and
     * yyyyjjjThhmmss.ddddZTD. The time zone part, ZTD, is either Z for zulu, ie
     * UTC, or a offset from UTC in the form of +hh:mm or -hh:mm.
     */
    public ISOTime(String s) throws UnsupportedFormat {
        orig = s;
        // check for TIME_UNKNOWN and default DMC 2599 values
        if(s.equals(TIME_UNKNOWN)
                || s.equals("2599-12-31T23:59:59.0000GMT")) {
            date = future;
        } else {
            String clean = cleanDate(s);
            Date d = null;
            for(int i = 0; i < dateFormats.length; i++) {
                synchronized(dateFormats[i]) {
                    d = dateFormats[i].parse(clean, new ParsePosition(0));
                }
                if(d != null) {
                    break;
                }
            }
            if(d == null) {
                // no patterns worked
                throw new UnsupportedFormat(s);
            }
            date = new MicroSecondDate(d.getTime() * 1000 + Integer.parseInt(getMicroseconds()));
        } // end of else
    }

    public ISOTime(int year, int jday, int hour, int minute, float second) {
        this(getISOString(year, jday, hour, minute, second));
    }

    public static String getISOString(int year,
                                      int jday,
                                      int hour,
                                      int minute,
                                      float second) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat xxFormat = new DecimalFormat("00", symbols);
        DecimalFormat xxxFormat = new DecimalFormat("000", symbols);
        DecimalFormat floatFormat = new DecimalFormat("00.000#", symbols);
        return xxxFormat.format(year) + xxxFormat.format(jday) + "J"
                + xxFormat.format(hour) + xxFormat.format(minute)
                + floatFormat.format(second) + "Z";
    }
    
    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    private static final DateFormat[] dateFormats;

    public static final String[] patterns = {"yyyy-MM-dd'T'HH:mm:ss.SSSz",
                                             "yyyyDDD'J'HHmmss.SSSz",
                                             "yyyyMMddHHmmss.SSSz",
                                             "yyyyMMdd'T'HH:mm:ss.SSSz",
                                             "yyyyDDD'J'HH:mm:ss.SSSz",
                                             "yyyyMMdd'T'HHmmss.SSSz",
                                             "yyyy-MM-dd'T'HHmmss.SSSz",
                                             "yyyyMMdd HH:mm:ss.SSSz",
                                             "yyyyMMdd HHmmss.SSSz",
                                             "yyyy-MM-dd HH:mm:ss.SSSz",
                                             "yyyy-MM-dd HHmmss.SSSz",
                                             "yyyyDDD'J'HH:mm:ssz",
                                             "yyyyDDD'J'HHmmssz",
                                             "yyyyMMdd'T'HH:mm:ssz",
                                             "yyyyMMdd'T'HHmmssz",
                                             "yyyy-MM-dd'T'HH:mm:ssz",
                                             "yyyy-MM-dd'T'HHmmssz",
                                             "yyyyMMdd HH:mm:ssz",
                                             "yyyyMMdd HHmmssz",
                                             "yyyy-MM-dd HH:mm:ssz",
                                             "yyyy-MM-dd HHmmssz",
                                             "yyyyMMddHHmmssz"};

    static int[] matches = new int[patterns.length];
    static {
        dateFormats = new DateFormat[patterns.length];
        for(int i = 0; i < patterns.length; i++) {
            dateFormats[i] = new SimpleDateFormat(patterns[i]);
            dateFormats[i].setLenient(false);
            dateFormats[i].setTimeZone(UTC);
        }
    }

    protected String cleanDate(String s) {
        int zoneIndex = s.indexOf('Z');
        if(zoneIndex == -1) {
            //not zulu, try +
            zoneIndex = s.indexOf('+');
            if(zoneIndex == -1) {
                // not +, try -, but remember it might be used within first
                // 8 charaters for separating year, month and day, yyyy-mm-dd
                zoneIndex = s.indexOf('-', 8);
            }
        }
        if(zoneIndex == -1) {
            // no time zone info
            zoneIndex = s.length();
        }
        // check for more than 3 digits, millis not supported by
        // java's SimpleDateFormat
        int endIndex;
        if(s.lastIndexOf('.') == -1) {
            // no decimal seconds
            endIndex = zoneIndex;
            microSeconds = "0";
        } else if(zoneIndex - s.lastIndexOf('.') > 3) {
            endIndex = s.lastIndexOf('.') + 4;
            microSeconds = s.substring(s.lastIndexOf('.')+4, zoneIndex);
        } else {
            endIndex = zoneIndex;
            microSeconds = "0";
        }
        while(microSeconds.length() < 3) {
            microSeconds += "0";
        }
        
        String out = s.substring(0, endIndex);
        // System.out.println("ISOTime out="+out+" z-.="+(zoneIndex -
        // s.lastIndexOf('.')));
        // pad out to 3 decimal points...
        if(zoneIndex - s.lastIndexOf('.') == 3) {
            out += "0";
        } else if(zoneIndex - s.lastIndexOf('.') == 2) {
            out += "00";
        } else if(zoneIndex - s.lastIndexOf('.') == 1) {
            out += "000";
        }
        // check for Julian day format, yyyyddd, if found
        // change T to J so parser uses correct pattern
        if(out.indexOf('T') == 7) {
            out = out.replace('T', 'J');
        }
        if(zoneIndex == s.length()) {
            // assume GMT time???
            out += "GMT";
            // for local time...
            //out += TimeZone.getDefault().getID();
        } else if(s.charAt(zoneIndex) == 'Z') {
            // assume GMT
            out += "GMT";
        } else {
            String tzString = s.substring(zoneIndex);
            if(tzString.length() == 3) {
                // assume TZ info is only GMT+hh, so to
                // work around bug in SImpleDateFormat we
                // must add :00 for 0 minutes
                tzString += ":00";
            }
            out += "GMT" + tzString;
        }
        return out;
    }

    String microSeconds;
    
    protected MicroSecondDate date;

    protected String orig;

    public String getOrigString() {
        return orig;
    }
    
    public String getMicroseconds() {
        return microSeconds;
    }

    /**
     * Get a java.unil.Calendar object initialized to be this ISOTime. Note that
     * a reduction of precision may occur as the default GregorianCalendar only
     * supports millisecond precision.
     */
    public Calendar getCalendar() {
        Calendar cal = Calendar.getInstance(UTC);
        cal.setTime(date);
        return cal;
    }

    public MicroSecondDate getDate() {
        return date;
    }

    public static String getISOString(MicroSecondDate ms) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        df.setTimeZone(UTC);
        String s = df.format(ms);
        long micros = ms.getMicroSeconds();
        if(micros == 0) {
            // nothing
        } else if(micros < 10) {
            s += "00" + micros;
        } else if(micros < 100) {
            if(micros % 10 == 0) {
                s += "0" + (micros / 10);
            } else {
                s += "0" + micros;
            }
        } else {
            if(micros % 100 == 0) {
                s += micros / 100;
            } else if(micros % 10 == 0) {
                s += micros / 10;
            } else {
                s += micros;
            }
        }
        return s + "Z";
    }

    public static String getISOString(Date ms) {
        if(ms instanceof MicroSecondDate) { return getISOString((MicroSecondDate)ms); }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        df.setTimeZone(UTC);
        return df.format(ms);
    }

    public String toString() {
        java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.FULL,
                                                                           java.text.DateFormat.FULL);
        df.setTimeZone(UTC);
        return df.format(getCalendar().getTime());
        //  return getYear()+" "+getMonth()+" "+getDay()+" "+getHour()+" "+
        //    getMinute()+" "+getSecond();
    }

    /**
     * just for testing, parses and outputs a few ISO8601 strings.
     */
    public static void main(String[] args) {
        String[] s = {"19990101T120030.1111Z",
                      "1999-01-01T000030.1111Z",
                      "19990101T00:00:30.1111Z",
                      "1999-01-01T00:00:30.1111Z",
                      "1999001T00:00:30.1111Z",
                      "1999001T000030.1111Z",
                      "1999-12-21T00:00:30.1234Z",
                      "2048-01-01T16:00:30.1111+01",
                      "1999-01-01T00:59:30.1111+01:30",
                      "1999-01-01T23:59:59.999-01:30",
                      "19990101T120030Z"};
        try {
            for(int i = 0; i < s.length; i++) {
                ISOTime iso = new ISOTime(s[i]);
                System.out.println(iso + " = " + iso.getOrigString());
            }
            System.out.println(ISOTime.getISOString(1999, 1, 2, 3, 3.456f));
        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
} // ISOTime
