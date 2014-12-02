package foobar.hippy.van.navigator;

/**
 * Helper class to manage bearing / heading values.
 * <p/>
 * Heading: Absolute angle in degrees with 0 facing up the screen, positive
 * clockwise. 0 <= heading < 360.
 * <p/>
 * Bearing: Relative angle to some object from your Van's heading, positive
 * clockwise. -180 < bearing <= 180.
 * <p/>
 * Using this class is absolutely optional, good Vans can be created without
 * using VanNavigator's methods.
 * 
 * @author rodriguezp
 *
 */
public final class VanNavigator {

	/**
	 * No VanNavigator instances can be created
	 */
	private VanNavigator() {
	}

	/*
	 * HEADING HELPER METHODS: 0 <= getHeading() < 360
	 */
	/**
	 * Returns if an enemy is heading up ( heading = 0 )
	 * */
	public static boolean isHeadingUpPrecision(double heading) {
		return (heading == 0.0d);
	}

	/**
	 * Returns if an enemy is heading down ( ??? )
	 */
	public static boolean isHeadingDownPrecision(double heading) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an enemy is heading left ( ??? )
	 */
	public static boolean isHeadingLeftPrecision(double heading) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an enemy is heading right ( heading = 90 )
	 */
	public static boolean isHeadingRightPrecision(double heading) {
		return (heading == 90.0d);
	}

	/**
	 * Returns if an enemy is heading up (wide range) ( ??? )
	 * */
	public static boolean isHeadingUp(double heading) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an enemy is heading down (wide range) ( ??? )
	 */
	public static boolean isHeadingDown(double heading) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an enemy is heading left (wide range) ( ??? )
	 */
	public static boolean isHeadingLeft(double heading) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an enemy is heading right (wide range) ( 0 < heading < 180 )
	 */
	public static boolean isHeadingRight(double heading) {
		return (0.0d < heading && heading < 180.0d);
	}

	/*
	 * BEARING HELPER METHODS: -180 <= getBearing() < 180
	 */

	/**
	 * Returns if an object is ahead ( bearing = 0 )
	 */
	public static boolean isAheadPrecision(double bearing) {
		return (bearing == 0.0d);
	}

	/**
	 * Returns if an object is at the back ( ??? )
	 */
	public static boolean isBackPrecision(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is at the left ( ??? )
	 */
	public static boolean isLeftPrecision(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is at the right ( bearing = 90 )
	 */
	public static boolean isRightPrecision(double bearing) {
		return (bearing == 90.d);
	}

	/**
	 * Returns if an object is ahead (wide range) ( ??? )
	 */
	public static boolean isAhead(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is at the back (wide range) ( ??? )
	 */
	public static boolean isBack(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is at the left (wide range) ( ???? )
	 */
	public static boolean isLeft(double bearing) {
		// TODO - Implement if desired
		return false;
	}

	/**
	 * Returns if an object is at the right (wide range) ( bearing > 0 )
	 */
	public static boolean isRight(double bearing) {
		return (bearing > 0.0d);
	}

}
