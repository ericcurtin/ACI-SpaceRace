package foobar.race.samples;

import foobar.coolingsystem.LiquidHydrogen;
import foobar.fuel.Coal;
import foobar.fuel.Hydrogen;
import foobar.fuel.Petrol;
import foobar.hippy.van.AbstractVan;
import foobar.model.SpaceEngine;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship cooling system example. Technora2c uses only hydrogen and
 * liquid hydrogen as a cooling system.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora2c extends AbstractVan {

	// Constructor
	public Technora2c() {
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
		while (true) {
			if (getTemperature() < SpaceEngine.overheatTemperature) {
				accelerate();
			} else { // OVERHEAT
				// Stop till 90% of maximum temperature
				while (getTemperature() > (0.9 * SpaceEngine.overheatTemperature)) {
					System.out.println("Cooling: " + getTemperature());
					stop();
				}
				System.out.println("Cooled: " + getTemperature());
			}
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