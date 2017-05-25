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
        return new SamplingImpl(20, new TimeInterval(1, UnitImpl.SECOND));
    }
    
    protected Object createNotEqualInstance(){
        return new SamplingImpl(30, new TimeInterval(1, UnitImpl.SECOND));
    }

    SamplingImpl sampling;
}
