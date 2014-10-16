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

	private boolean hit = false;

	public Treasure() {
		super();
	}

	@Override
	public void run() {
		setGunColor(new Color(0, 0, 5));
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {

		//
		// Print the winner's name
		//
		if (!hit) {
			System.out.println(event.getName() + " wins the race !!!");

			// JOptionPane.showMessageDialog(null, event.getName()
			// + " wins the race");

			//
			// Turn to notice that a robot hit the Treasure
			//
			turnLeft(360);

			hit = true;
		}

	}

}
