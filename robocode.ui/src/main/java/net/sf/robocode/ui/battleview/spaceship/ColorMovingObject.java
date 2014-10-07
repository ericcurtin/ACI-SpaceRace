package net.sf.robocode.ui.battleview.spaceship;

import java.awt.Color;

public class ColorMovingObject extends Object {

	protected static final Color COLOR_SPACESHIP1 = new Color(0, 0, 1);
	protected static final Color COLOR_SPACESHIP2 = new Color(0, 0, 2);
	protected static final Color COLOR_SPACESHIP3 = new Color(0, 0, 3);
	protected static final Color COLOR_ASTEROID_SMALL = new Color(0, 0, 4);
	protected static final Color COLOR_ASTEROID_BIG = new Color(0, 0, 5);

	public static boolean isSpaceShip(Color c) {
		return c.equals(COLOR_SPACESHIP1) || c.equals(COLOR_SPACESHIP2)
				|| c.equals(COLOR_SPACESHIP3);
	}

	public static boolean isAsteroid(Color c) {
		return c.equals(COLOR_ASTEROID_SMALL) || c.equals(COLOR_ASTEROID_BIG);
	}

}
