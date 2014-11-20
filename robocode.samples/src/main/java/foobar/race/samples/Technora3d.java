package foobar.race.samples;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.fuel.RacingPetrol;
import foobar.hippy.van.fuel.ExpensivePetrol;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship scanning example. Technora3d uses only coal
 * (=Technora1a). It spins the spaceship every 30 turns. Max.degrees/turn = 10.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora3d extends AbstractVan {

	// Constructor
	public Technora3d() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runACI() {

		// Main loop
		while (true) {

			// Move 30 turns
			for (int i = 0; i < 30; i++) {
				accelerate();
			}

			// Spin the Spaceship
			spinpaceship();
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

	/**
	 * Rotate the spaceship 360 degrees left.
	 */
	private void spinpaceship() {
		// turnRight(360);
		turnLeft(360);
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