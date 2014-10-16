package foobar.race.samples;

import foobar.fuel.Coal;
import foobar.fuel.Hydrogen;
import foobar.fuel.Petrol;
import foobar.van.AbstractVan;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship fuel using example. Technora1a uses only coal.
 * 
 * @author Pablo Rodriguez (original)
 */
public class HappyVan1 extends AbstractVan {

	// Constructor
	public HappyVan1() {
		// Set the type of Spaceship (Atlantis, Buran, Challenger)
		setAsBuranModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runACI() {

		//
		// Hardcode the route so the Van will go to the center ( aprox ) of the
		// screen so it will hit the Treasure
		//
		turnRight(90);
		for (int i = 0; i < 150; i++) {
			accelerate();
		}
		turnLeft(90);
		for (int i = 0; i < 150; i++) {
			accelerate();
		}

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