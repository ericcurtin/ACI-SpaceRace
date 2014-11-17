package foobar.race.samples;

import java.awt.Color;

import foobar.coolingsystem.LiquidHydrogen;
import foobar.fuel.Coal;
import foobar.fuel.Hydrogen;
import foobar.fuel.Petrol;
import foobar.hippy.van.AbstractVan;
import foobar.model.SpaceEngine;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship cooling system example. Technora2b uses only fuel and
 * liquid hydrogen as a cooling system.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora2b extends AbstractVan {

	// Constructor
	public Technora2b() {
		super();

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

			if (getTemperature() < SpaceEngine.overheatTemperature) {
				accelerate();
			} else { // OVERHEAT
				// Stop 10 turns to cool the system
				for (int i = 1; i <= 10; i++) {
					System.out
							.println("Cooling " + i + ": " + getTemperature());
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