package foobar.race.samples;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.fuel.Coal;
import foobar.hippy.van.fuel.Hydrogen;
import foobar.hippy.van.fuel.Petrol;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship fuel using example. Technora1c uses only hydrogen, it has
 * overheating.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora1c extends AbstractVan {

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