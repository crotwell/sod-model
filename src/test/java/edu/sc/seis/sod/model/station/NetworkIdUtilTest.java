package edu.sc.seis.sod.model.station;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class NetworkIdUtilTest {

    @Test
    public void testTempNetId() {
        String[] tempCodes = new String[] { "XA", "YB", "ZC", "1A", "22", "34" };
        String[] permCodes = new String[] { "II", "G", "W7" };
        for (String netCode : tempCodes) {
            assertTrue(netCode, NetworkIdUtil.isTemporary(netCode));
        }
        for (String netCode : permCodes) {
            assertFalse(netCode, NetworkIdUtil.isTemporary(netCode));
        }
    }
}
