package foobar.enemy;

import java.awt.Color;

import robocode.HitRobotEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import foobar.hippy.AbstractHippyRobot;

/**
 * An Animal does not do anything till a Van is in front of its line of vision
 * and the distance is less than 300. Once its awake it chases & fire the Van.
 * It will only hunt one Van.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class Animal extends AbstractHippyRobot {

	String preyName = "";
	boolean hasHunted = false;

	@Override
	public void run() {

		setAllColors(Color.BLUE);

		turnRadarLeft(45);

		while (true) {
			turnRadarRight(90);
			turnRadarLeft(90);
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {

		//
		// If the Animal has hunted before, do nothing, it's enough
		//
		if (hasHunted) {
			return;
		}

		//
		// There is no prey and a Van is scanned
		//
		if (preyName.isEmpty() && isVan(event.getName())) {
			out.println("preyName:" + preyName);
			//
			// Van is in front and the distance is <= 300
			//
			out.println("distance:" + event.getDistance());
			if ((event.getDistance() <= 300) && isForward(event)) {
				//
				// Select the prey
				//
				preyName = event.getName();
			}
		}
		//
		// There is a prey and it has been scanned, so we'll chase & fire it
		//
		if (!preyName.isEmpty() && event.getName().compareTo(preyName) == 0) {
			out.println("preyName:" + preyName);
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
			hasHunted = true;
		}
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {

		//
		// If the Animal has hunted before, do nothing, it's enough
		//
		if (hasHunted) {
			return;
		}

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

	private boolean isForward(ScannedRobotEvent e) {

		double angleToEnemy = e.getBearing();
		out.println("angleToEnemy:" + angleToEnemy);

		//
		// Calculate the angle to the scanned robot
		//
		double angle = Math.toRadians((getHeading() + angleToEnemy % 360));

		//
		// Calculate the x-coordinate of the scanned robot
		//
		double enemyX = (getX() + Math.sin(angle) * e.getDistance());
		out.println("enemyX:" + enemyX);

		//
		// If the scanned robot is forward ( same code as
		// VanNavigator.isBearingForward )
		//
		if (-90.0d < angleToEnemy && angleToEnemy < 90.0d) {
			out.println("isForward(180degrees):" + true);
			//
			// If it is in front of us (its x-coordinate is close to our
			// x-coordinate)
			//
			out.println("Math.abs(enemyX - getX()):"
					+ Math.abs(enemyX - getX()));
			//
			// Van is up or down of the Animal. 4 pixels are taken so there will
			// be a small space in the x-coordinate that the Van and the Animal
			// will			// share
			//
			if (Math.abs(enemyX - getX()) <= (getWidth() - 4)) {
				out.println("isForward(width):" + true);
				return true;
			} else {
				out.println("isForward(width):" + false);
			}
		} else {
			out.println("isForward(180degrees):" + false);
		}

		return false;
	}
}
