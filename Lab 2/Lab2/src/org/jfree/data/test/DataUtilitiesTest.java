package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class DataUtilitiesTest extends TestCase {
	private Values2D values2D;

	@Before
	public void setUp() {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(2, 1, 0);
		testValues.addValue(2, 4, 0);
	}

	@After
	public void tearDown() {
		values2D = null;
	}

	/* 
	 * 2.1.1 Testing: calculateColumnTotal(Values2D data, int column) 
	 */
	
	@Test
	public void testValidDataAndColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0),
				0.0000001d);
	}

	@Test
	public void testNullDataColumnTotal() {
	    try {
	        DataUtilities.calculateColumnTotal(null, 0);
	        fail("IllegalArgumentException expected");
	    } catch (IllegalArgumentException e) {
	        // Expected exception, test passes
	    } catch (Exception e) {
	        fail("Unexpected exception type thrown");
	    }
	}
	
	@Test
    public void testValidDataColumnTotal() {
		DefaultKeyedValues2D  values = new DefaultKeyedValues2D();
        values.addValue(1, 0, 0);
        values.addValue(5, 0, 1);
        values.addValue(6, 0, 2);
        values.addValue(4, 1, 0);
        values.addValue(8, 1, 1);
        values.addValue(23, 1, 2);
        assertEquals(13, DataUtilities.calculateColumnTotal(values, 1), 0.0000001d);
    }

	@Test
	public void testValidDataColumnTotalWithOutOfBoundsColumn() {
	    DefaultKeyedValues2D values = new DefaultKeyedValues2D();
	    values.addValue(15, 0, 0);
	    values.addValue(69, 0, 1);
	    values.addValue(-20, 0, 2);
	    try {
	        DataUtilities.calculateColumnTotal(values, 20);
	        fail("Should have thrown IndexOutOfBoundsException");
	    } catch (IndexOutOfBoundsException e) {
	        // Expected exception, test passes
	    }
	}
  
    @Test
    public void testEmptyArrayColumnTotal() {
        Values2D values = new DefaultKeyedValues2D();
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("The sum of an empty column should be 0.0", 0.0, result, 0.0000001d);
    }

    @Test
    public void testNegativeColumnIndexColumnTotal() {
    	DefaultKeyedValues2D  values = new DefaultKeyedValues2D();
        values.addValue(1, 0, 0);
        values.addValue(2, 0, 1);
        values.addValue(3, 0, 2);
        values.addValue(4, 1, 0);
        values.addValue(5, 1, 1);
        values.addValue(6, 1, 2);
        try {
            DataUtilities.calculateColumnTotal(values, -230); 
            fail("Should have thrown IndexOutOfBoundsException");
	    } catch (IndexOutOfBoundsException e) {
	        // Expected exception, test passes
	    }
    }

    @Test
    public void testColumnIndexOutOfBoundsColumnTotal() {
    	DefaultKeyedValues2D  values = new DefaultKeyedValues2D();
        values.addValue(1, 0, 0);
        values.addValue(2, 0, 1);
        values.addValue(3, 0, 2);
        values.addValue(4, 1, 0);
        values.addValue(5, 1, 1);
        values.addValue(6, 1, 2);
        try {
            DataUtilities.calculateColumnTotal(values, 3);
            fail("Should have thrown IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
	        // Expected exception, test passes
	    }
    }
    
    /* 
	 * 2.1.2 Testing: calculateColumnTotal(Values2D data, int column) 
	 */
}


