package foobar.race.samples;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.LiquidHydrogen;
import foobar.hippy.van.fuel.Coal;
import foobar.hippy.van.fuel.Hydrogen;
import foobar.hippy.van.fuel.Petrol;
import robocode.ScannedRobotEvent;

/**
 * This is a spaceship event example. When Technora4b detects a spaceship then
 * it turns the gun to fire to it.
 * 
 * @author Pablo Rodriguez (original)
 */
public class Technora4b extends AbstractVan {

	// Constructor
	public Technora4b() {

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

			// Move forward 30 turns
			for (int i = 0; i < 30; i++) {
				accelerate();
			}

			// Scan for other spaceships / asteroids.
			moveRadar();
		}
	}

/*
	@Override
	public void onScannedSpaceship(ScannedRobotEvent e) {

		// Lock the gun on to our target
		// turnGunRight(getHeading() - getRadarHeading() + e.getBearing());
		turnGunRight((getHeading() + e.getBearing()) - getGunHeading());

		// shoot!
		fire(0.15);
	}
*/
	/**
	 * Move the radar 360 degrees right.
	 */
	private void moveRadar() {
		turnRadarRight(360);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setFuel() {
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