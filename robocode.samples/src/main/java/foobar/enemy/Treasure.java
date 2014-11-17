package foobar.enemy;

import foobar.hippy.AbstractHippyRobot;
import foobar.hippy.HippyName;
import robocode.HitRobotEvent;

/**
 * This is a type of Asteroid that doesn't move.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class Treasure extends AbstractHippyRobot {

	private boolean winner = false;

	@Override
	public void onHitRobot(HitRobotEvent event) {

		//
		// There is no winner yet and a Van hit the treasure
		//
		if (!winner && HippyName.isVan(event.getName())) {
			//
			// Print the winner's name
			//
			System.out.println(event.getName() + " wins the race !!!");
			winner = true;

			//
			// Spin to know that somebody hit the treasure
			//
			turnLeft(360);

			//
			// Can not be used because of the security
			//
			// JOptionPane.showMessageDialog(null, event.getName()
			// + " wins the race");
		}

	}

}
