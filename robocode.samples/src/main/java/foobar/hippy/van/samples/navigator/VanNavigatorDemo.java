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
	 * BEARING HELPER METHODS: -180 <= getBearing() < 180
	 */

	/**
	 * Returns if an object is forward ( wide range ) (-90 < bearing) &&
	 * (bearing < 90)
	 */
	public static boolean isBearingForward(double bearing) {
		return (-90.0d < bearing && bearing < 90.0d);
	}

	/**
	 * Returns if an object is at the right (wide range) (bearing > 0)
	 */
	public static boolean isBearingRight(double bearing) {
		return (bearing > 0.0d);
	}

}
