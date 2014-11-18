package foobar.hippy;

import robocode.Robot;

abstract public class AbstractHippyRobot extends Robot {

	private static final String STONE = "Stone";
	private static final String ANIMAL = "Animal";
	private static final String TREASURE = "Treasure";

	public static boolean isVan(String s) {
		return !isStone(s) && !isAnimal(s) && !isTreasure(s);
	}

	public static boolean isStone(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		return s.toLowerCase().contains(STONE.toLowerCase());
	}

	public static boolean isAnimal(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		return s.toLowerCase().contains(ANIMAL.toLowerCase());
	}

	public static boolean isTreasure(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		return s.toLowerCase().contains(TREASURE.toLowerCase());
	}

}
