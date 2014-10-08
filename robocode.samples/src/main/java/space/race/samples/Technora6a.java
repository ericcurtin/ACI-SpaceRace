package space.race.samples;

import robocode.ScannedRobotEvent;
import space.spaceship.AbstractSpaceship;
import space.spaceship.fuel.Coal;
import space.spaceship.fuel.Hydrogen;
import space.spaceship.fuel.Petrol;

/**
 * This is a spaceship booster example. Technora6a uses only coal (=Technora1a).
 * No boosters are used.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora6a extends AbstractSpaceship {

	// Constructor
	public Technora6a() {
		super();

		// Set the type of Spaceship (Atlantis, Buran, Challenger)
		setAsBuranModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runACI() {

		// Main loop
		while (true) {

			// Move forward 30 turns
			// for (int i = 0; i < 30; i++) {
			System.out.println("Trying to accelerate");
			accelerate();
			// }
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

	@Override
	public void onScannedAsteroid(ScannedRobotEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScannedSpaceship(ScannedRobotEvent event) {
		// TODO Auto-generated method stub

	}

}