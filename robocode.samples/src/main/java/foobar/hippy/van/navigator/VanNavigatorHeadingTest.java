package foobar.hippy.van.navigator;

import static org.junit.Assert.*;

import org.junit.Test;

public class VanNavigatorHeadingTest {

	private static final double HEADING_UP = 0.0d;
	private static final double HEADING_DOWN = 180.0d;
	private static final double HEADING_RIGHT = 90.0d;
	private static final double HEADING_LEFT = 270.0d;

	private static final double HEADING_UP_LEFT = 315.0d;
	private static final double HEADING_UP_RIGHT = 45.0d;
	private static final double HEADING_DOWN_LEFT = 225.0d;
	private static final double HEADING_DOWN_RIGHT = 135.0d;

	@Test
	public void test() {

		//
		// Test isHeadingUp method
		//
		assertTrue(VanNavigator.isHeadingUp(HEADING_UP));
		assertFalse(VanNavigator.isHeadingUp(HEADING_DOWN));
		assertFalse(VanNavigator.isHeadingUp(HEADING_LEFT));
		assertFalse(VanNavigator.isHeadingUp(HEADING_RIGHT));
		assertTrue(VanNavigator.isHeadingUp(HEADING_UP_LEFT));
		assertTrue(VanNavigator.isHeadingUp(HEADING_UP_RIGHT));
		assertFalse(VanNavigator.isHeadingUp(HEADING_DOWN_LEFT));
		assertFalse(VanNavigator.isHeadingUp(HEADING_DOWN_RIGHT));

		//
		// Test isHeadingDown method
		//
		assertFalse(VanNavigator.isHeadingDown(HEADING_UP));
		assertTrue(VanNavigator.isHeadingDown(HEADING_DOWN));
		assertFalse(VanNavigator.isHeadingDown(HEADING_LEFT));
		assertFalse(VanNavigator.isHeadingDown(HEADING_RIGHT));
		assertFalse(VanNavigator.isHeadingDown(HEADING_UP_LEFT));
		assertFalse(VanNavigator.isHeadingDown(HEADING_UP_RIGHT));
		assertTrue(VanNavigator.isHeadingDown(HEADING_DOWN_LEFT));
		assertTrue(VanNavigator.isHeadingDown(HEADING_DOWN_RIGHT));

		//
		// Test isHeadingLeft method
		//
		assertFalse(VanNavigator.isHeadingLeft(HEADING_UP));
		assertFalse(VanNavigator.isHeadingLeft(HEADING_DOWN));
		assertTrue(VanNavigator.isHeadingLeft(HEADING_LEFT));
		assertFalse(VanNavigator.isHeadingLeft(HEADING_RIGHT));
		assertTrue(VanNavigator.isHeadingLeft(HEADING_UP_LEFT));
		assertFalse(VanNavigator.isHeadingLeft(HEADING_UP_RIGHT));
		assertTrue(VanNavigator.isHeadingLeft(HEADING_DOWN_LEFT));
		assertFalse(VanNavigator.isHeadingLeft(HEADING_DOWN_RIGHT));

		//
		// Test isHeadingRight method
		//
		assertFalse(VanNavigator.isHeadingRight(HEADING_UP));
		assertFalse(VanNavigator.isHeadingRight(HEADING_DOWN));
		assertFalse(VanNavigator.isHeadingRight(HEADING_LEFT));
		assertTrue(VanNavigator.isHeadingRight(HEADING_RIGHT));
		assertFalse(VanNavigator.isHeadingRight(HEADING_UP_LEFT));
		assertTrue(VanNavigator.isHeadingRight(HEADING_UP_RIGHT));
		assertFalse(VanNavigator.isHeadingRight(HEADING_DOWN_LEFT));
		assertTrue(VanNavigator.isHeadingRight(HEADING_DOWN_RIGHT));
	}

}
