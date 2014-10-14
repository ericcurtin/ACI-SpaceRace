package foobar.enemy;

import java.awt.Color;

import robocode.Robot;

/**
 * This is a type of Asteroid that doesn't move.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class Stone extends Robot {
	public Stone() {
	}

	@Override
	public void run() {
		setGunColor(new Color(0, 0, 4));
	}
}
