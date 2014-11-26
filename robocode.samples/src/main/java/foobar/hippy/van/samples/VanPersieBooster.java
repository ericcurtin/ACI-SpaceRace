package foobar.hippy.van.samples;

import java.awt.Color;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.Fan;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.fuel.RacingPetrol;
import foobar.hippy.van.fuel.ExpensivePetrol;
import robocode.ScannedRobotEvent;

/**
 * This is a Van using an advanced Fuel & Cooling system & Booster.
 *
 * This example uses the same Fuel & Cooling combination than VanNistelrooy
 * example.
 * 
 * Every Van can use a Booster once (and only once) that makes the Van move
 * twice faster for the next 50 accelerate movements.
 * 
 * This example uses the Booster at the very beginning.
 * 
 * @author Pablo Rodriguez (original)
 */

public class VanPersieBooster extends AbstractVan {

	@Override
	public void runACI() {
		//
		// Set Van colors (optional).
		//
		setBodyColor(Color.RED);
		setRadarColor(Color.WHITE);
		setScanColor(Color.WHITE);

		//
		// Set the Cooling System of the Van.
		//
		setCoolingSystem(new Fan());

		//
		// Use the booster before the main loop.
		//
		if (!isVanBoosterUsed()) {
			useBooster();
		}

		//
		// Main loop.
		//
		while (true) {
			//
			// Accelerate the Van.
			//
			accelerate();
		}
	}

	@Override
	public void setFuel() {
		//
		// I use a mix of fuels because it's cool.
		//
		addFuel(CheapPetrol.name, 3000);
		addFuel(ExpensivePetrol.name, 1000);
		addFuel(RacingPetrol.name, 1000);
	}

	@Override
	public void onScannedAnimal(ScannedRobotEvent event) {
		//
		// I wish this was onScannedReferee. I don't know how to manage this
		// method.
		//
	}

	@Override
	public void onScannedStone(ScannedRobotEvent event) {
		//
		// I wish this was onScannedGoal. I don't know how to manage this
		// method.
		//
	}

	@Override
	public void onScannedTreasure(ScannedRobotEvent event) {
		//
		// I wish this was onScannedBall. I don't know how to manage this
		// method.
		//
	}

	@Override
	public void onScannedVan(ScannedRobotEvent event) {
		//
		// I wish this was onScannedTeamMate. I don't know how to manage this
		// method.
		//
	}

}