package net.sf.robocode.ui.battleview.spaceship;

import java.awt.Color;

public class ColorMovingObject extends Object {

	protected static final Color COLOR_VAN1 = new Color(0, 0, 1);
	protected static final Color COLOR_VAN2 = new Color(0, 0, 2);
	protected static final Color COLOR_STONE = new Color(0, 0, 3);
	protected static final Color COLOR_ANIMAL = new Color(0, 0, 4);
	protected static final Color COLOR_TREASURE = new Color(0, 0, 5);

	public static boolean isVan(Color c) {
		return c.equals(COLOR_VAN1) || c.equals(COLOR_VAN2);
	}

	public static boolean isStone(Color c) {
		return c.equals(COLOR_STONE);
	}

	public static boolean isAnimal(Color c) {
		return c.equals(COLOR_ANIMAL);
	}

	public static boolean isTreasure(Color c) {
		return c.equals(COLOR_TREASURE);
	}

}
