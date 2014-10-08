package space.race.samples;

import java.awt.Color;

import robocode.ScannedRobotEvent;
import space.spaceship.AbstractSpaceship;
import space.spaceship.fuel.Coal;
import space.spaceship.fuel.Hydrogen;
import space.spaceship.fuel.Petrol;

/**
 * This is a spaceship fuel using example. Technora1d uses a mix of fuels.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora1d extends AbstractSpaceship {

	// Constructor
	public Technora1d() {
		super();

		// Set the type of Spaceship (Atlantis, Buran, Challenger)
		setAsBuranModel();

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
			accelerate();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFuel() {
		addFuel(Coal.name, 3950);
		addFuel(Petrol.name, 1000);
		addFuel(Hydrogen.name, 50);
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