package edu.sc.seis.sod.model.common;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class SamplingTest   {
    
    @Before
    public void setUp() {
        sampling = (SamplingImpl)createInstance();
    }

    @Test
    public void testGetFrequency() {
        QuantityImpl freq = sampling.getFrequency();
        assertTrue(freq.getUnit().isConvertableTo(UnitImpl.HERTZ));
    }
    
    @Test
    public void testEqualsHash() {
        assertTrue("equal", createInstance().equals(createInstance()));
        assertFalse("not equal", createInstance().equals(createNotEqualInstance()));
    }
    
    protected Object createInstance(){
        return SamplingImpl.ofSamplesSeconds(20, 1.0);
    }
    
    protected Object createNotEqualInstance(){
        return SamplingImpl.ofSamplesSeconds(30, 1.0);
    }

    SamplingImpl sampling;
}
