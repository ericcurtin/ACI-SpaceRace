package space.race.samples;

import foobar.fuel.Coal;
import foobar.fuel.Hydrogen;
import foobar.fuel.Petrol;
import foobar.van.AbstractVan;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship fuel using example. Technora1b uses only fuel, it has
 * overheating.
 * 
 * @author Pablo Rodriguez (original)
 */

public class Technora1b extends AbstractVan {

	// Constructor
	public Technora1b() {
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
			accelerate();
			// }
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFuel() {
		addFuel(Coal.name, 0);
		addFuel(Petrol.name, 5000);
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