package space.race.samples;

import java.util.HashSet;
import java.util.Set;

import robocode.ScannedRobotEvent;
import space.spaceship.AbstractSpaceship;
import space.spaceship.coolingsystem.LiquidHydrogen;
import space.spaceship.fuel.Coal;
import space.spaceship.fuel.Hydrogen;
import space.spaceship.fuel.Petrol;

/**
 * This is an advanced spaceship event example. When Technora5b detects a dummy
 * asteroid then it changes its direction to go to the asteroid and fire to it.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora5b extends AbstractSpaceship {

	Set<String> scannedAsteroids = new HashSet<String>();

	// Constructor
	public Technora5b() {
		// Set the type of Spaceship (Atlantis, Buran, Challenger)
		setAsBuranModel();

		// Set the cooling System
		setCoolingSystem(new LiquidHydrogen());
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
	public void onScannedSpaceship(ScannedRobotEvent e) {

		// Move the spaceship to point the enemy
		if (!isForward(e.getBearing())) {
			turnRight(e.getBearing());
		}
		// shoot!
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
	 * Returns if an object is in front, (-2, -2) degrees
	 */
	private boolean isForward(double bearing) {
		return (bearing > (-2) && bearing < (2));
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
	public void onScannedAsteroid(ScannedRobotEvent event) {
	}

}