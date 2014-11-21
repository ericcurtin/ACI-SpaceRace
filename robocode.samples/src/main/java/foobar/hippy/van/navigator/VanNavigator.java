package foobar.hippy.van.navigator;

/**
 * Helper class to manage bearing / heading values.
 */
public final class VanNavigator {

	/**
	 * No VanNavigator instances can be created
	 */
	private VanNavigator() {
	}

	/*
	 * BEARING HELPER METHODS: -180 <= getBearing() < 180
	 */

	/**
	 * Returns if an object is in front (0.0) degrees
	 */
	public static boolean isInFront(double bearing) {
		return (bearing == 0.0);
	}

	/**
	 * Returns if an object is in front ??? degrees
	 */
	public static boolean isAtTheBack(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is ahead ??? degrees
	 */
	public static boolean isAhead(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is at the back ??? degrees
	 */
	public static boolean isBack(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is at the left ??? degrees
	 */
	public static boolean isLeft(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is at the right ??? degrees
	 */
	public static boolean isRight(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/*
	 * HEADING HELPER METHODS: 0 <= getHeading() < 360
	 */

	/**
	 * Returns if an enemy is heading up ??? degrees
	 * */
	public static boolean isHeadingUp(double heading) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an enemy is heading down ??? degrees
	 */
	public static boolean isHeadingDown(double heading) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an enemy is heading left ??? degrees
	 */
	public static boolean isHeadingLeft(double heading) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an enemy is heading right ( 0 < heading < 180 )
	 */
	public static boolean isHeadingRight(double heading) {
		return (0 < heading && heading < 180);
	}

}
