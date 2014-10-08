package space.race.samples;

import robocode.ScannedRobotEvent;
import space.spaceship.AbstractSpaceship;
import space.spaceship.fuel.Coal;
import space.spaceship.fuel.Hydrogen;
import space.spaceship.fuel.Petrol;

/**
 * This is a spaceship fuel using example. Technora1c uses only hydrogen, it has
 * overheating.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora1c extends AbstractSpaceship {

	// Constructor
	public Technora1c() {
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
			accelerate();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFuel() {
		addFuel(Coal.name, 0);
		addFuel(Petrol.name, 0);
		addFuel(Hydrogen.name, 5000);
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