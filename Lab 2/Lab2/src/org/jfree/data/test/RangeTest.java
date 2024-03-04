package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {

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

	@Test(expected = NullPointerException.class)
	public void testGetLowerBoundWithNullRange() {
		Range range = null;
		range.getLowerBound();
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
	 * 2.2.2 Testing:
	 */

	/*
	 * 2.2.3 Testing:
	 */

	/*
	 * 2.2.4 Testing:
	 */

	/*
	 * 2.2.5 Testing: intersects(double lower, double upper)
	 */

	@Test
	public void testIntersectsPositiveRangesLBRLessOrEqualUBR() {
		Range range1 = new Range(2.0, 7.0);
		Range range2 = new Range(5.0, 10.0);
		assertTrue("Ranges should intersect", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIntersectsPositiveRangesLBRGreaterOrEqualUBR() {
		Range range1 = new Range(7.0, 2.0);
		Range range2 = new Range(3.0, 8.0);
		range1.intersects(range2.getLowerBound(), range2.getUpperBound());
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

	@Test(expected = IllegalArgumentException.class)
	public void testIntersectsNegativeRangesLBRGreaterOrEqualUBR() {
		Range range1 = new Range(-5.0, -10.0);
		Range range2 = new Range(-8.0, -4.0);
		range1.intersects(range2.getLowerBound(), range2.getUpperBound());

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
