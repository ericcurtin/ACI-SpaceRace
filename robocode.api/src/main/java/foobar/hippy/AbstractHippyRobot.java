package foobar.hippy;

import robocode.Robot;

/**
 * Abstract class that identifies the Robot class based on the name.
 * 
 * @author rodriguezp
 */
abstract public class AbstractHippyRobot extends Robot {

	/**
	 * String that defines if a Robot is an Animal.
	 */
	private static final String ANIMAL = "Animal";

	/**
	 * String that defines if a Robot is a Stone.
	 */
	private static final String STONE = "Stone";

	/**
	 * String that defines if a Robot is a Treasure.
	 */
	private static final String TREASURE = "Treasure";

	/**
	 * Checks if a Robot is an Animal using its name.
	 * 
	 * @param name
	 *            name of the Robot
	 * @return true if the Robot is an Animal, false otherwise.
	 */
	public static boolean isAnimal(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		return name.toLowerCase().contains(ANIMAL.toLowerCase());
	}

	/**
	 * Checks if a Robot is a Stone using its name.
	 * 
	 * @param name
	 *            name of the Robot
	 * @return true if the Robot is a Stone, false otherwise.
	 */
	public static boolean isStone(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		return name.toLowerCase().contains(STONE.toLowerCase());
	}

	/**
	 * Checks if a Robot is a Treasure using its name.
	 * 
	 * @param name
	 *            name of the Robot
	 * @return true if the Robot is a Treasure, false otherwise.
	 */
	public static boolean isTreasure(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		return name.toLowerCase().contains(TREASURE.toLowerCase());
	}

	/**
	 * Checks if a Robot is a Van using its name.
	 * 
	 * @param name
	 *            name of the Robot
	 * @return true if the Robot is a Van, false otherwise.
	 */
	public static boolean isVan(String name) {
		return !isStone(name) && !isAnimal(name) && !isTreasure(name);
	}

}
