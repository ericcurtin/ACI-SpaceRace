package foobar.enemy;

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
			// Van is in front and the distance is <300
			//
			if (isForward(event.getBearing()) && event.getDistance() < 300) {
				//
				// Select the prey
				//
				preyName = event.getName();

				//
				// Wake up movement
				//
				doWakeUpMovement();
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
			ahead(3);

			//
			// Fire
			//
			fire(2.5);
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

	private boolean isForward(double bearing) {
		return (bearing > (-2) && bearing < (2));
	}

	private void doWakeUpMovement() {
		turnLeft(20);
		turnRight(20);
		turnLeft(20);
		turnRight(20);
		turnLeft(20);
		turnRight(20);
		turnLeft(20);
		turnRight(20);
	}

}
