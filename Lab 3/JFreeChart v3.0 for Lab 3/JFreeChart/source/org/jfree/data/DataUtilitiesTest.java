package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
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
		DefaultKeyedValues2D values = new DefaultKeyedValues2D();
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
		DefaultKeyedValues2D values = new DefaultKeyedValues2D();
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
		DefaultKeyedValues2D values = new DefaultKeyedValues2D();
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

	@Test
	public void testCalculateRowTotalValid() {
		DefaultKeyedValues2D values = new DefaultKeyedValues2D();
		values.addValue(5.7, 0, 0);
		values.addValue(8.5, 0, 1);
		values.addValue(7.9, 0, 2);
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("The row total is not correct", 22.1, result, 0.0000001d);
	}

	@Test
	public void testCalculateRowTotalWithOutOfBoundsRow() {
		DefaultKeyedValues2D values = new DefaultKeyedValues2D();
		values.addValue(5.7, 0, 0);
		values.addValue(8.5, 0, 1);
		values.addValue(7.9, 0, 2);
		try {
			DataUtilities.calculateRowTotal(values, 5);
			fail("Should have thrown IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Expected exception, test passes
		}
	}

	@Test
	public void testCalculateRowTotalWithNullData() {
		try {
			DataUtilities.calculateRowTotal(null, 0);
			fail("Should have thrown NullPointerException");
		} catch (NullPointerException e) {
			// Expected exception, test passes
		}
	}

	@Test
	public void testCalculateRowTotalWithEmptyData() {
		Values2D values = new DefaultKeyedValues2D();
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("The row total of an empty data set should be zero", 0.0, result, 0.0000001d);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateRowTotalWithEmptyDataNegativeRow() {
		Values2D values = new DefaultKeyedValues2D();
		DataUtilities.calculateRowTotal(values, -1);
	}

    /* 
	 * 2.1.3 Testing: createNumberArray(double[] data)
	 */
    
    @Test
    public void testCreateNumberArrayWithValidInput() {
        double[] input = {3.0, 23.0, 321.0, 91.0, 15.0};
        double[] expectedOutput = {3.0, 23.0, 321.0, 91.0, 15.0};
        
        Number[] result = DataUtilities.createNumberArray(input);

        double[] actualOutput = new double[result.length];
        for (int i = 0; i < result.length; i++) {
            // Check if the individual elements are not null
            assertNotNull("Array element at index " + i + " should not be null", result[i]);
            actualOutput[i] = result[i].doubleValue();
        }
        
        // Using Assert with fully qualified name in case of import issues
        assertArrayEquals(expectedOutput, actualOutput, 0.001);
    }
    
    @Test
    public void testCreateNumberArrayWithNullInput() {
    	try {
    		DataUtilities.createNumberArray(null);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            /// Expected exception, test passes
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArrayWithEmptyArray() {
        double[] input = {};
        DataUtilities.createNumberArray(input);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArrayWithNonDoublePrimitives() {
        double[] input = {23, 5454.39, -39, -42};
        DataUtilities.createNumberArray(input);
    }
    
    /* 
	 * 2.1.4 Testing: createNumberArray2D(double[][] data)
	 */
    @Test
    public void testCreateNumberArray2DWithValidInput() {
        double[][] input = {
            {78.0, 99.0, 32.0, 55.0, 15.0},
            {14.0, 25.0, 45.0, 59.0, 74.0}
        };
        double[][] expectedOutput = {
            {78.0, 99.0, 32.0, 55.0, 15.0},
            {14.0, 25.0, 45.0, 59.0, 74.0}
        };
        assertArrayEquals(expectedOutput, DataUtilities.createNumberArray2D(input));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DWithEmptyArray() {
        double[][] input = new double[0][];
        DataUtilities.createNumberArray2D(input);
    }
    
    @Test
    public void testCreateNumberArray2DWithNullInput() {
    	try {
    		DataUtilities.createNumberArray2D(null);
    	}catch(IllegalArgumentException e) {
    		/// Expected exception, test passes
    	}
    	
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DWithMixedTypes() {
    	try {
    		double[][] input = new double[][] {
                {78.0, 99.0, 32, 55.0f, 15.0},
                {14.0, 25.0, -45, 59.0, 74.0}
            };
            DataUtilities.createNumberArray2D(input);
    	}catch(IllegalArgumentException e) {
    		/// Expected exception, test passes
    	}
         // This method would need to accept Object[][] and handle casting internally.
    }
    
    /*
	 * 2.1.5 Testing: getCumulativePercentages(KeyedValues data)
	 */

	@Test
	public void testGetCumulativePercentagesWithValidData() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		data.addValue((Comparable<Integer>) 0, 2);
		data.addValue((Comparable<Integer>) 1, 6);
		data.addValue((Comparable<Integer>) 2, 3);
		data.addValue((Comparable<Integer>) 3, 6);
		data.addValue((Comparable<Integer>) 4, 2);
		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		assertEquals(0.1053, result.getValue(0).doubleValue(), 0.0001d);
		assertEquals(0.4211, result.getValue(1).doubleValue(), 0.0001d);
		assertEquals(0.5789, result.getValue(2).doubleValue(), 0.0001d);
		assertEquals(0.8947, result.getValue(3).doubleValue(), 0.0001d);
		assertEquals(1.0, result.getValue(4).doubleValue(), 0.0001d);
	}

	@Test
	public void testGetCumulativePercentagesWithNullData() {
		try {
			DataUtilities.getCumulativePercentages(null);
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected exception, test passes
		}

	}

	@Test
	public void testGetCumulativePercentagesWithEmptyData() {
		DefaultKeyedValues data = new DefaultKeyedValues();

		KeyedValues result = DataUtilities.getCumulativePercentages(data);

		assertTrue(result.getItemCount() == 0);
	}
	

    @Test
    public void testGetCumulativePercentagesWithValidDataDoublePrimatives() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable<?>) 0, 2.0);
        data.addValue((Comparable<?>) 1, 6.0);
        data.addValue((Comparable<?>) 2, 3.0);
        data.addValue((Comparable<?>) 3, 6.0);
        data.addValue((Comparable<?>) 4, 2.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.1053, result.getValue(0).doubleValue(), 0.0001d);
        assertEquals(0.4211, result.getValue(1).doubleValue(), 0.0001d);
        assertEquals(0.5789, result.getValue(2).doubleValue(), 0.0001d);
        assertEquals(0.8947, result.getValue(3).doubleValue(), 0.0001d);
        assertEquals(1.0, result.getValue(4).doubleValue(), 0.0001d);
    }
   
    
     //Lab 3: Additional Test Cases:
     
    /*
     * getCumulativePercentages(KeyedValues data) 
     */
    @Test
    public void testGetCumulativePercentagesWithFewNullValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable<Integer>) 0, 2);
        data.addValue((Comparable<Integer>) 1, null);
        data.addValue((Comparable<Integer>) 2, 3);
        data.addValue((Comparable<Integer>) 3, 6);
        data.addValue((Comparable<Integer>) 4, null);
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(0.1818, result.getValue(0).doubleValue(), 0.0001d);
        assertEquals(0.1818, result.getValue(1).doubleValue(), 0.0001d); 
        assertEquals(0.4545, result.getValue(2).doubleValue(), 0.0001d);
        assertEquals(1.0, result.getValue(3).doubleValue(), 0.0001d);
        assertEquals(1.0, result.getValue(4).doubleValue(), 0.0001d); 
    }
   
    /*
     * calculateRowTotal(Values2D data, int row) 
     */
    
    @Test
    public void testCalculateRowTotalWithFewNullValues() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(5.7, 0, 0);
        values.addValue(null, 0, 1); 
        values.addValue(7.9, 0, 2);
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("The row total should register any nulls as zero", 13.6, result, 0.0000001d);
    }
    
    /*
     * calculateColumnTotal(Values2D data, int column) 
     */
    
    @Test
    public void testCalculateColumnTotalWithFewNullValues() {
        DefaultKeyedValues2D values = new DefaultKeyedValues2D();
        values.addValue(1.0, 0, 0);
        values.addValue(null, 1, 0); 
        values.addValue(3.0, 2, 0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("The column total should register any nulls as zero", 4.0, result, 0.0000001d);
    }

}

