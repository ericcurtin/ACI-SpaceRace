package foobar.race.samples;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.booster.SuperBooster;
import foobar.hippy.van.fuel.Coal;
import foobar.hippy.van.fuel.Hydrogen;
import foobar.hippy.van.fuel.Petrol;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship booster example. Technora6b uses only coal (=Technora1a).
 * It uses SuperBooster.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora6b extends AbstractVan {

	// Constructor
	public Technora6b() {
		super();

		// Set SuperBooster
		setBooster(new SuperBooster());
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