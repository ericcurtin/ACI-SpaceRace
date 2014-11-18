package foobar.race.samples;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.LiquidHydrogen;
import foobar.hippy.van.fuel.Coal;
import foobar.hippy.van.fuel.Hydrogen;
import foobar.hippy.van.fuel.Petrol;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship scanning example. Technora3c uses only coal
 * (=Technora1a). It moves the gun every 30 turns. Max.degrees/turn = 20.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora3c extends AbstractVan {

	// Constructor
	public Technora3c() {
		super();

		// Set the cooling System
		setCoolingSystem(new LiquidHydrogen());
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

			// Spin the Gun
			moveGun();
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

	/**
	 * Move the gun 360 degrees left.
	 */
	private void moveGun() {
		// turnGunRight(360);
		turnGunLeft(360);
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