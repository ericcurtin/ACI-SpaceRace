package foobar.race.samples;

import java.awt.Color;

import foobar.coolingsystem.LiquidHydrogen;
import foobar.fuel.Coal;
import foobar.fuel.Hydrogen;
import foobar.fuel.Petrol;
import foobar.hippy.van.AbstractVan;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship scanning example. Technora3a uses only coal
 * (=Technora1a). No scan is done.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora3a extends AbstractVan {

	// Constructor
	public Technora3a() {

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

			// for (int i = 0; i < 100; i++) {
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