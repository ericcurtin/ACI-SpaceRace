package space.asteroid;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;

/**
 * This is a base class for all Asteroids in the space race, it defines an
 * Asteroids movement and how it interacts with walls.
 * 
 * @author Pablo Rodriguez (original)
 * @author Eric Curtin (contributor)
 */
public abstract class AbstractAsteroid extends Robot {
	protected void asteroidMovement() {
		while (true) {
			ahead(8);
		}
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		hitAndMove(event.getBearing());
	}

	private void hitAndMove(double bearing) {
		if ((bearing >= 0) && (bearing < 90)) {
			turnLeft(180 - bearing * 2);
		} else if ((bearing >= 90) && (bearing < 180)) {
			turnRight((180 - bearing));
		} else if ((bearing < 0) && (bearing >= -90)) {
			turnRight(180 - bearing * (-2));
		} else {
			turnLeft((180 - bearing));
		}
	}
}
