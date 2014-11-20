package foobar.race.samples;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.Fan;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.fuel.RacingPetrol;
import foobar.hippy.van.fuel.ExpensivePetrol;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship cooling system example. Technora2a uses only coal and a
 * fan as a cooling system.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora2a extends AbstractVan {

	// Constructor
	public Technora2a() {
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
		addFuel(CheapPetrol.name, 5000);
		addFuel(ExpensivePetrol.name, 0);
		addFuel(RacingPetrol.name, 0);
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