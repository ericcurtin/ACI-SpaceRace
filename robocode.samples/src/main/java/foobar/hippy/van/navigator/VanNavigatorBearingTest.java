package foobar.hippy.van.navigator;

import static org.junit.Assert.*;

import org.junit.Test;

public class VanNavigatorBearingTest {

	private static final double BEARING_AHEAD = 0.0d;
	private static final double BEARING_BACK = -180.0d;
	private static final double BEARING_LEFT = -90.0d;
	private static final double BEARING_RIGHT = 90.0d;

	private static final double BEARING_AHEAD_LEFT = -45.0d;
	private static final double BEARING_AHEAD_RIGHT = 45.0d;
	private static final double BEARING_BACK_LEFT = -135.0d;
	private static final double BEARING_BACK_RIGHT = 135.0d;

	@Test
	public void test() {
		//
		// Test isInFront method
		//
		assertTrue(VanNavigator.isInFront(BEARING_AHEAD));
		assertFalse(VanNavigator.isInFront(BEARING_BACK));
		assertFalse(VanNavigator.isInFront(BEARING_LEFT));
		assertFalse(VanNavigator.isInFront(BEARING_RIGHT));
		assertFalse(VanNavigator.isInFront(BEARING_AHEAD_LEFT));
		assertFalse(VanNavigator.isInFront(BEARING_AHEAD_RIGHT));
		assertFalse(VanNavigator.isInFront(BEARING_BACK_LEFT));
		assertFalse(VanNavigator.isInFront(BEARING_BACK_RIGHT));

		//
		// Test isAtTheBack method
		//
		assertFalse(VanNavigator.isAtTheBack(BEARING_AHEAD));
		assertTrue(VanNavigator.isAtTheBack(BEARING_BACK));
		assertFalse(VanNavigator.isAtTheBack(BEARING_LEFT));
		assertFalse(VanNavigator.isAtTheBack(BEARING_RIGHT));
		assertFalse(VanNavigator.isAtTheBack(BEARING_AHEAD_LEFT));
		assertFalse(VanNavigator.isAtTheBack(BEARING_AHEAD_RIGHT));
		assertFalse(VanNavigator.isAtTheBack(BEARING_BACK_LEFT));
		assertFalse(VanNavigator.isAtTheBack(BEARING_BACK_RIGHT));

		//
		// Test isAhead method
		//
		assertTrue(VanNavigator.isAhead(BEARING_AHEAD));
		assertFalse(VanNavigator.isAhead(BEARING_BACK));
		assertFalse(VanNavigator.isAhead(BEARING_LEFT));
		assertFalse(VanNavigator.isAhead(BEARING_RIGHT));
		assertTrue(VanNavigator.isAhead(BEARING_AHEAD_LEFT));
		assertTrue(VanNavigator.isAhead(BEARING_AHEAD_RIGHT));
		assertFalse(VanNavigator.isAhead(BEARING_BACK_LEFT));
		assertFalse(VanNavigator.isAhead(BEARING_BACK_RIGHT));

		//
		// Test isBack method
		//
		assertFalse(VanNavigator.isBack(BEARING_AHEAD));
		assertTrue(VanNavigator.isBack(BEARING_BACK));
		assertFalse(VanNavigator.isBack(BEARING_LEFT));
		assertFalse(VanNavigator.isBack(BEARING_RIGHT));
		assertFalse(VanNavigator.isBack(BEARING_AHEAD_LEFT));
		assertFalse(VanNavigator.isBack(BEARING_AHEAD_RIGHT));
		assertTrue(VanNavigator.isBack(BEARING_BACK_LEFT));
		assertTrue(VanNavigator.isBack(BEARING_BACK_RIGHT));

		//
		// Test isLeft method
		//
		assertFalse(VanNavigator.isLeft(BEARING_AHEAD));
		assertFalse(VanNavigator.isLeft(BEARING_BACK));
		assertTrue(VanNavigator.isLeft(BEARING_LEFT));
		assertFalse(VanNavigator.isLeft(BEARING_RIGHT));
		assertTrue(VanNavigator.isLeft(BEARING_AHEAD_LEFT));
		assertFalse(VanNavigator.isLeft(BEARING_AHEAD_RIGHT));
		assertTrue(VanNavigator.isLeft(BEARING_BACK_LEFT));
		assertFalse(VanNavigator.isLeft(BEARING_BACK_RIGHT));

		//
		// Test isRight method
		//
		assertFalse(VanNavigator.isRight(BEARING_AHEAD));
		assertFalse(VanNavigator.isRight(BEARING_BACK));
		assertFalse(VanNavigator.isRight(BEARING_LEFT));
		assertTrue(VanNavigator.isRight(BEARING_RIGHT));
		assertFalse(VanNavigator.isRight(BEARING_AHEAD_LEFT));
		assertTrue(VanNavigator.isRight(BEARING_AHEAD_RIGHT));
		assertFalse(VanNavigator.isRight(BEARING_BACK_LEFT));
		assertTrue(VanNavigator.isRight(BEARING_BACK_RIGHT));
	}

}
