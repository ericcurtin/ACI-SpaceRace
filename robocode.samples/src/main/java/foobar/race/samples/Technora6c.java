package foobar.race.samples;

import foobar.booster.UltraBooster;
import foobar.fuel.Coal;
import foobar.fuel.Hydrogen;
import foobar.fuel.Petrol;
import foobar.van.AbstractVan;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship booster example. Technora6c uses only coal (=Technora1a).
 * It uses UltraBooster.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora6c extends AbstractVan {

	// Constructor
	public Technora6c() {
		super();

		// Set the type of Spaceship (Atlantis, Buran, Challenger)
		setAsBuranModel();

		// Set UltraBooster
		setBooster(new UltraBooster());
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