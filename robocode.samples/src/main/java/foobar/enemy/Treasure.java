package foobar.enemy;

import java.awt.Color;

import robocode.HitRobotEvent;
import robocode.Robot;

/**
 * This is a type of Asteroid that doesn't move.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class Treasure extends Robot {
	public Treasure() {
	}

	@Override
	public void run() {
		setGunColor(new Color(0, 0, 5));
	}
}
