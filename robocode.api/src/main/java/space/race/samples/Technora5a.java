package space.race.samples;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import space.spaceship.AbstractSpaceship;
import space.spaceship.coolingsystem.LiquidHydrogen;
import space.spaceship.fuel.Coal;
import space.spaceship.fuel.Hydrogen;
import space.spaceship.fuel.Petrol;

/**
 * This is an advanced spaceship event example. When Technora5a detects a dummy
 * asteroid then it is added to an asteroid list. If an asteroid is close then
 * the spaceship modifies its direction. If the spaceship is being fired then it
 * defends itself.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora5a extends AbstractSpaceship {
	Set<String> scannedAsteroids = new HashSet<String>();

	// Constructor
	public Technora5a() {
		// Set the type of Spaceship (Atlantis, Buran, Challenger)
		setAsBuranModel();

		// Set the Cooling System
		setCoolingSystem(new LiquidHydrogen());

		// Set the colour of the spaceship
		setColor(Color.GREEN);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runACI() {
		// Main loop
		while (true) {
			// Move forward 30 turns
			for (int i = 0; i < 30; i++) {
				accelerate();
			}

			// Scan for other spaceships / asteroids.
			moveRadar();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onScannedAsteroid(ScannedRobotEvent e) {
		// If the asteroid is ahead, add it to the asteroids set
		if (isAhead(e.getBearing())) {
			scannedAsteroids.add(e.getName());
			System.out.println(e.getName() + " added, total ("
					+ scannedAsteroids.size() + ")");
			// If the asteroid is back then remove it from the asteroids set
		} else {
			scannedAsteroids.remove(e.getName());
			System.out.println(e.getName() + " removed, total ("
					+ scannedAsteroids.size() + ")");
		}

		// If the asteroid is close and forward, then change the direction
		if (isForward(e.getBearing()) && e.getDistance() < 200) {

			turnRight(90);
			for (int i = 0; i < 50; i++) {
				accelerate();
			}

			turnLeft(90);
			for (int i = 0; i < 50; i++) {
				accelerate();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		double turnGunAmt = (getHeading() + e.getBearing()) - getGunHeading();

		if (turnGunAmt % 360 != 0) {
			turnGunRight(turnGunAmt);
		}
		fire(0.15);
	}

	/**
	 * Returns if an object is ahead
	 */
	private boolean isAhead(double bearing) {
		return (bearing > (-90) && bearing < (90));
	}

	/**
	 * Returns if an object is at the back
	 */
	private boolean isBack(double bearing) {
		return !isAhead(bearing);
	}

	/**
	 * Returns if an object is at the left
	 */
	private boolean isLeft(double bearing) {
		return (bearing < 0);
	}

	/**
	 * Returns if an object is at the right
	 */
	private boolean isRight(double bearing) {
		return (bearing > 0);
	}

	/**
	 * Returns if an object is in front, (-10, 10) degrees
	 */
	private boolean isForward(double bearing) {
		return (bearing > (-10) && bearing < (10));
	}

	/**
	 * Move the radar 360 degrees right.
	 */
	private void moveRadar() {
		turnRadarRight(360);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setFuel() {
		addFuel(Coal.name, 5000);
		addFuel(Petrol.name, 0);
		addFuel(Hydrogen.name, 0);
	}

	@Override
	public void onScannedSpaceship(ScannedRobotEvent event) {
	}
}
