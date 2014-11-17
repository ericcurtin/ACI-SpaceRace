package foobar.race.samples;

import java.awt.Color;

import foobar.coolingsystem.LiquidHydrogen;
import foobar.fuel.Coal;
import foobar.fuel.Hydrogen;
import foobar.fuel.Petrol;
import foobar.hippy.van.AbstractVan;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship event example. When Technora4a detects a dummy asteroid
 * then it changes its direction to go to the asteroid and/or fire at it.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora4a extends AbstractVan {

	// Constructor
	public Technora4a() {
		// Set the type of Spaceship (Atlantis, Buran, Challenger)
		setAsBuranModel();

		// Set the cooling System
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

		// Process dummy asteroids
		if (isDummyAsteroid(e.getName())) {
			System.out.println("Dummy Asteroid Detected");

			// If the asteroid is in front of the spaceship, then shoot at it to
			// earn life
			if (isForward(e.getBearing())) {
				fire(0.15);
			}
			// If it is not in front of the spaceship, the turn the spaceship to
			// the asteroid
			else {
				// Turn to the position of the dummy asteroid
				turnRight(e.getBearing());
			}
		}
		// Process circular asteroids
		if (isCircularAsteroid(e.getName())) {
			System.out.println("Circular Asteroid Detected");
		}
		// Process large asteroids
		if (isLargeAsteroid(e.getName())) {
			System.out.println("Large Asteroid Detected");
		}
	}

	/**
	 * Detects if the deviation of an angle is low.
	 */
	private boolean isForward(double bearing) {
		return (bearing > (-5) && bearing < (5));
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
		// TODO Auto-generated method stub

	}

}