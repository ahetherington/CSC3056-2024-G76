package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeTest extends TestCase{

	private Range rangeObjectUnderTest;

	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest = new Range(-1, 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCentralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0", 0, rangeObjectUnderTest.getCentralValue(),
				0.000000001d);
	}

	/*
	 * 2.2.1 Testing: getLowerBound()
	 */

	@Test
	public void testGetLowerBoundWithPositiveValues() {
		Range range = new Range(50, 74);
		assertEquals(50.0, range.getLowerBound(), 0.0001);
	}

	@Test
	public void testGetLowerBoundWithNegativeValues() {
		Range range = new Range(-94, -78);
		assertEquals(-94.0, range.getLowerBound(), 0.0001);
	}

	@Test
	public void testGetLowerBoundWithNullRange() {
	    try {
	        Range range = null;
	        range.getLowerBound();
	        fail("Expected NullPointerException to be thrown when calling getLowerBound() on a null range.");
	    } catch (NullPointerException e) {
	        
	    } catch (Exception e) {
	        fail("Unexpected exception type thrown: " + e.getClass().getSimpleName());
	    }
	}

	@Test
	public void testGetLowerBoundCrossingZero() {
		Range range = new Range(-89, 20);
		assertEquals(-89.0, range.getLowerBound(), 0.0001);
	}

	@Test
	public void testGetLowerBoundSinglePoint() {
		Range range = new Range(10, 10);
		assertEquals(10.0, range.getLowerBound(), 0.0001);
	}

	/*
	 * 2.2.2 Testing: expandToInclude(Range range, double value)
	 */
	@Test
    public void testExpandToInclude_NullRange() {
        double value = 5.0;
        Range result = Range.expandToInclude(null, value);
        Range expected = new Range(value, value);
        assertEquals("Expanding a null range to include a value should create a new range from the value", expected, result);
    }

    @Test
    public void testExpandToInclude_ValueAlreadyInRange() {
        Range range = new Range(2.0, 8.0);
        double value = 5.0;
        Range result = Range.expandToInclude(range, value);
        Range expected = range; // should be unchanged
        assertEquals("Expanding a range to include a value already in the range should return the same range", expected, result);
    }

    @Test
    public void testExpandToInclude_ValueBelowRange() {
        Range range = new Range(2.0, 8.0);
        double value = 1.0;
        Range result = Range.expandToInclude(range, value);
        Range expected = new Range(value, range.getUpperBound());
        assertEquals("Expanding a range to include a value below the range should return a new range", expected, result);
    }

    @Test
    public void testExpandToInclude_ValueAboveRange() {
        Range range = new Range(2.0, 8.0);
        double value = 10.0;
        Range result = Range.expandToInclude(range, value);
        Range expected = new Range(range.getLowerBound(), value);
        assertEquals("Expanding a range to include a value above the range should return a new range", expected, result);
    }

    @Test
    public void testExpandToInclude_ValueEqualToLowerBound() {
        Range range = new Range(2.0, 8.0);
        double value = 2.0;
        Range result = Range.expandToInclude(range, value);
        Range expected = range; // should be unchanged
        assertEquals("Expanding a range to include a value equal to the lower bound should return the same range", expected, result);
    }

    @Test
    public void testExpandToInclude_ValueEqualToUpperBound() {
        Range range = new Range(2.0, 8.0);
        double value = 8.0;
        Range result = Range.expandToInclude(range, value);
        Range expected = range; // should be unchanged
        assertEquals("Expanding a range to include a value equal to the upper bound should return the same range", expected, result);
    }

	/*
	 * 2.2.3 Testing: combine(range range1, range range2)
	 */

    @Test
    public void testCombine_OverlappingRanges() {
        try {
            Range range1 = new Range(1, 5);
            Range range2 = new Range(3, 7);
            Range expected = new Range(1, 7);
            Range actual = Range.combine(range1, range2);
            assertEquals("The combined range should be from 1 to 7", expected, actual);
        } catch (IllegalArgumentException e) {
            fail("Combining overlapping ranges should not throw an IllegalArgumentException.");
        }
    }

    @Test
    public void testCombine_NonOverlappingRanges() {
        try {
            Range range1 = new Range(1, 5);
            Range range2 = new Range(6, 10);
            Range expected = new Range(1, 10);
            Range actual = Range.combine(range1, range2);
            assertEquals("The combined range should be from 1 to 10", expected, actual);
        } catch (IllegalArgumentException e) {
            fail("Combining non-overlapping ranges should not throw an IllegalArgumentException.");
        }
    }

    @Test
    public void testCombine_FirstNullSecondNonNull() {
        Range range1 = null;
        Range range2 = new Range(5, 10);
        Range expected = new Range(5, 10);
        Range actual = Range.combine(range1, range2);
        assertEquals(expected, actual);
    }

    @Test
    public void testCombine_FirstNonNullSecondNull() {
        Range range1 = new Range(1, 5);
        Range range2 = null;
        Range expected = new Range(1, 5);
        Range actual = Range.combine(range1, range2);
        assertEquals(expected, actual);
    }

    @Test
    public void testCombine_BothNull() {
        Range range1 = null;
        Range range2 = null;
        Range actual = Range.combine(range1, range2);
        assertNull(actual);
    }

	/*
	 * 2.2.4 Testing: expand(Range range, double lowerMargin, double upperMargin)
	 */
    @Test
    public void testExpand_NormalRangePositiveMargins() {
        Range range = new Range(5, 10);
        Range expected = new Range(4.5, 10.5);
        Range actual = Range.expand(range, 0.1, 0.1);
        assertEquals("Expanding a normal range with positive margins should yield the correct range", expected, actual);
    }

    @Test
    public void testExpand_ZeroLengthRangePositiveMargins() {
        Range range = new Range(10, 10);
        Range expected = new Range(9, 11);
        Range actual = Range.expand(range, 0.1, 0.1);
        assertEquals("Expanding a zero-length range with positive margins should yield the correct range", expected, actual);
    }

    @Test
    public void testExpand_NormalRangeNoMargins() {
        Range range = new Range(5, 10);
        Range expected = new Range(5, 10);
        Range actual = Range.expand(range, 0, 0);
        assertEquals("Expanding a normal range with zero margins should yield an unchanged range", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpand_NormalRangeNegativeMargins() {
        Range range = new Range(5, 10);
        Range.expand(range, -0.1, -0.1);
    }

    @Test
    public void testExpand_NormalRangeExpansionOneSideOnly() {
        Range range = new Range(5, 10);
        Range expected = new Range(5, 11);
        Range actual = Range.expand(range, 0, 0.2);
        assertEquals("Expanding a normal range on one side only should yield the correct range", expected, actual);
    }

    @Test
    public void testExpand_LargeExpansionFactors() {
        Range range = new Range(1, 2);
        double rangeLength = range.getUpperBound() - range.getLowerBound();
        Range expected = new Range(1 - 5 * rangeLength, 2 + 5 * rangeLength);
        Range actual = Range.expand(range, 5, 5);
        assertEquals("Expanding a range with large expansion factors should yield a significantly expanded range", expected, actual);
    }

    @Test
    public void testExpand_NullRange() {
        try {
            Range.expand(null, 0.1, 0.2);
            fail("Expected IllegalArgumentException to be thrown when expanding a null range.");
        } catch (IllegalArgumentException e) {
            // This is expected, so the test passes.
        } catch (Exception e) {
            // If any other exception is thrown, the test will fail.
            fail("Unexpected exception type thrown: " + e.getClass().getSimpleName());
        }
    }

	/*
	 * 2.2.5 Testing: intersects(double lower, double upper)
	 */

	@Test
	public void testIntersectsPositiveRangesLBRLessOrEqualUBR() {
		Range range1 = new Range(2.0, 7.0);
		Range range2 = new Range(5.0, 10.0);
		assertTrue("Ranges should intersect", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
	}

	@Test
	public void testIntersectsPositiveRangesLBRGreaterOrEqualUBR() {
	    try {
	        Range range1 = new Range(7.0, 2.0); 
	        Range range2 = new Range(3.0, 8.0);
	        range1.intersects(range2.getLowerBound(), range2.getUpperBound());
	        fail("Expected IllegalArgumentException to be thrown due to invalid range bounds.");
	    } catch (IllegalArgumentException e) {
	    } catch (Exception e) {
	        fail("Unexpected exception type thrown: " + e.getClass().getSimpleName());
	    }
	}

	@Test
	public void testIntersectsPositiveRangesLBREqualUBR() {
		Range range1 = new Range(4.0, 4.0);
		Range range2 = new Range(4.0, 4.0);
		assertTrue("Ranges should intersect", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
	}

	@Test
	public void testIntersectsNegativeRangesLBRLessOrEqualUBR() {
		Range range1 = new Range(-10.0, -5.0);
		Range range2 = new Range(-7.0, -3.0);
		assertTrue("Ranges should intersect", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
	}

	@Test
	public void testIntersectsNegativeRangesLBRGreaterOrEqualUBR() {
	    try {
	        Range range1 = new Range(-5.0, -10.0);
	        Range range2 = new Range(-8.0, -4.0);
	        range1.intersects(range2.getLowerBound(), range2.getUpperBound());
	        fail("Expected IllegalArgumentException to be thrown.");
	    } catch (IllegalArgumentException e) {
	        // Test passes if exception is caught
	    }
	}
	
	@Test
	public void testIntersectsNegativeRangesLBREqualUBR() {
		Range range1 = new Range(-6.0, -6.0);
		Range range2 = new Range(-7.0, -5.0);
		assertTrue("Ranges should intersect", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
	}

	@Test
	public void testIntersectsCrossingZeroRangesLBRLessOrEqualUBR() {
		Range range1 = new Range(-2.0, 2.0);
		Range range2 = new Range(-1.0, 1.0);
		assertTrue("Ranges should intersect", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
	}

	@Test
	public void testIntersectsCrossingZeroRangesLBRGreaterOrEqualUBR() {
		Range range1 = new Range(-3.0, 3.0);
		Range range2 = new Range(4.0, 10.0);
		assertFalse("Ranges should not intersect", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
	}

}
