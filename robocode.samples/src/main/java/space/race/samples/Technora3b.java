package space.race.samples;

import robocode.ScannedRobotEvent;
import space.spaceship.AbstractSpaceship;
import space.spaceship.coolingsystem.LiquidHydrogen;
import space.spaceship.fuel.Coal;
import space.spaceship.fuel.Hydrogen;
import space.spaceship.fuel.Petrol;

/**
 * This is a spaceship scanning example. Technora3b uses only coal
 * (=Technora1a). It scans with the radar every 30 turns. Max.degrees/turn = 45.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora3b extends AbstractSpaceship {

	// Constructor
	public Technora3b() {
		super();

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

			// Move 30 turns
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
	public void setFuel() {
		addFuel(Coal.name, 5000);
		addFuel(Petrol.name, 0);
		addFuel(Hydrogen.name, 0);
	}

	/**
	 * Move the radar 360 degrees left.
	 */
	private void moveRadar() {
		// turnRadarRight(360);
		turnRadarLeft(360);
	}

	@Override
	public void onScannedAsteroid(ScannedRobotEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScannedSpaceship(ScannedRobotEvent event) {
		// TODO Auto-generated method stub

	}

}