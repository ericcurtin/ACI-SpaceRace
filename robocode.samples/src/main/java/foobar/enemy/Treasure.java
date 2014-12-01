package foobar.enemy;

import java.awt.Color;

import foobar.hippy.AbstractHippyRobot;
import robocode.HitRobotEvent;

/**
 * A Treasure is the object that will define the winner of the race. Once a Van
 * hits it then the treasure will spin just to let users now that the race is
 * over, the name of the winner is printed in the Treasure's console.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class Treasure extends AbstractHippyRobot {

	private boolean winner = false;

	@Override
	public void run() {
		setAllColors(new Color(122, 82, 48));
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
		//
		// There is no winner yet and a Van hit the treasure
		//
		if (!winner && isVan(event.getName())) {
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
