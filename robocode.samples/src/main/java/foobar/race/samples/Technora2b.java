package foobar.race.samples;

import java.awt.Color;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.LiquidHydrogen;
import foobar.hippy.van.engine.Engine;
import foobar.hippy.van.fuel.Coal;
import foobar.hippy.van.fuel.Hydrogen;
import foobar.hippy.van.fuel.Petrol;
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

		// Set the cooling System
		setCoolingSystem(new LiquidHydrogen());

		// Set the colour of the spaceship
		setBodyColor(Color.GREEN);
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
		addFuel(Coal.name, 0);
		addFuel(Petrol.name, 5000);
		addFuel(Hydrogen.name, 0);
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