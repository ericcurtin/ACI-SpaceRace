package foobar.race.samples;

import foobar.coolingsystem.Fan;
import foobar.fuel.Coal;
import foobar.fuel.Hydrogen;
import foobar.fuel.Petrol;
import foobar.hippy.van.AbstractVan;
import foobar.model.Engine;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship cooling system example. Technora2c uses a mix of fuels
 * and a fan as a cooling system.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora2d extends AbstractVan {

	// Constructor
	public Technora2d() {
		super();

		// Set the cooling System
		setCoolingSystem(new Fan());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runACI() {

		// Main loop
		while (true) {

			if (getTemperature() < Engine.OVERHEAT_TEMPERATURE) {
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
		addFuel(Coal.name, 3950);
		addFuel(Petrol.name, 1000);
		addFuel(Hydrogen.name, 50);
	}

	@Override
	public void onScannedAnimal(ScannedRobotEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScannedStone(ScannedRobotEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScannedTreasure(ScannedRobotEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScannedVan(ScannedRobotEvent event) {
		// TODO Auto-generated method stub
		
	}

}