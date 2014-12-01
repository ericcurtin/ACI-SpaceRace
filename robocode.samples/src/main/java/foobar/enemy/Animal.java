package foobar.enemy;

import java.awt.Color;

import robocode.HitRobotEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import foobar.hippy.AbstractHippyRobot;

/**
 * An Animal does not do anything till a Van is in front of its line of vision
 * and the distance is less than 300. Once its awake it chases & fire the Van.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class Animal extends AbstractHippyRobot {

	String preyName = "";

	@Override
	public void run() {

		setAllColors(Color.BLUE);

		while (true) {
			turnRadarLeft(360);
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {

		//
		// There is no prey and a Van is scanned
		//
		if (preyName.isEmpty() && isVan(event.getName())) {
			//
			// Van is in front and the distance is <200
			//
			if (isForward(event.getBearing()) && event.getDistance() <= 300) {
				//
				// Select the prey
				//
				preyName = event.getName();
			}
			//
			// There is a prey and it has been scanned, so we'll chase & fire it
			//
		} else if (event.getName().compareTo(preyName) == 0) {
			//
			// Turn body
			//
			turnRight(event.getBearing());

			//
			// Chase
			//
			ahead(8);

			//
			// Fire only if they are really close, so the Animal will always
			// kill the Van
			//
			if (event.getDistance() < 45) {
				fire(3.0);
			}
		}
	}

	@Override
	public void onRobotDeath(RobotDeathEvent event) {
		//
		// Prey is dead, reset
		//
		if ((event.getName().compareTo(preyName) == 0)) {
			preyName = "";
		}
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
		//
		// If a Van hits the Animal it will be the prey
		//
		if (isVan(event.getName())) {
			//
			// Select the prey
			//
			preyName = event.getName();
		}
	}

	private boolean isForward(double bearing) {
		return (bearing > (-2) && bearing < (2));
	}

}
