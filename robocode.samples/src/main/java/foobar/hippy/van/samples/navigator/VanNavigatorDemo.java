package foobar.hippy.van.samples.navigator;

/**
 * Demo helper class to manage bearing values.
 * <p/>
 * Bearing: Relative angle to some object from your spaceship's heading,
 * positive clockwise. -180 <= bearing < 180.
 * 
 * @author rodriguezp
 *
 */
public final class VanNavigatorDemo {

	/**
	 * No VanNavigator instances can be created
	 */
	private VanNavigatorDemo() {
	}

	/*
	 * HEADING HELPER METHODS: 0 <= getHeading() < 360
	 */
	/**
	 * Returns if an enemy is heading right ( heading = 90 )
	 */
	public static boolean isHeadingRightPrecision(double heading) {
		return (heading == 90.0d);
	}

	/*
	 * BEARING HELPER METHODS: -180 <= getBearing() < 180
	 */

	/**
	 * Returns if an object is forward ( bearing = 0 )
	 */
	public static boolean isBearingForwardPrecision(double bearing) {
		return (bearing == 0.0d);
	}

	/**
	 * Returns if an object is at the right (wide range) (bearing > 0)
	 */
	public static boolean isBearingRight(double bearing) {
		return (bearing > 0.0d);
	}

}
