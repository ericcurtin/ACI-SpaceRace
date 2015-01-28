package foobar.hippy.van.samples;

import java.awt.Color;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.Aircon;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.fuel.RacingPetrol;
import foobar.hippy.van.fuel.ExpensivePetrol;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

/**
 * This is a Van using a basic Fuel & Cooling system.
 * 
 * The Fuel defines the number of pixels that the Van will move per turn and
 * also the number of degrees that the engine temperature will increase per
 * turn.
 * 
 * This example uses CheapFuel, which has: - A fuel power of 1 (1 pixel / turn).
 * - A fuel temperature increase of 1 (1 degree / turn).
 * 
 * The Cooling System defines the number of degrees that the engine temperature
 * will decrease per turn.
 * 
 * This example uses Aircon, which has: - A Cooling Effect of 1 when moving (-1
 * degree / turn). - A Cooling Effect of 1 when stopped (-1 degree / turn).
 * 
 * As the CheapFuel increases the temperature by 1 per turn and the Aircon
 * decreases it by 1 per turn, then temperature will always 0 so overheating
 * will never happen.
 * 
 * This is a slow but safe Van.
 * 
 * @author Pablo Rodriguez (original)
 */

public class VanBasten extends AbstractVan {

	public VanBasten() {
		//
		// Set the Cooling System of the Van.
		//
		setCoolingSystem(new Aircon());
	}

	@Override
	public void runACI() {
		//
		// Set Van colors (optional).
		// Note: Do not set the colors in the constructor.
		//
		setBodyColor(Color.BLACK);
		setRadarColor(Color.RED);
		setScanColor(Color.BLACK);

		//
		// Main loop.
		//
		while (true) {

			//
			// Accelerate the Van 200 turns.
			//
			for (int i = 0; i < 200; i++) {
				accelerate();
			}

			//
			// Scan for the treasure.
			//
			turnRadarLeft(360);
		}
	}

	@Override
	public void setFuel() {
		//
		// I use CheapPetrol because my glory days are over.
		//
		addFuel(CheapPetrol.name, 5000);
		addFuel(ExpensivePetrol.name, 0);
		addFuel(RacingPetrol.name, 0);
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
		// I love treasures, let's go for it.
		// Do something if the treasure is reasonably close.
		//
		if (event.getDistance() < 300) {
			//
			// Turn the Van to face the Treasure.
			//
			turnRight(event.getBearing());

			//
			// Move forward 111 turns.
			//
			for (int i = 0; i < 111; i++) {
				accelerate();
			}
		}
	}

	@Override
	public void onScannedVan(ScannedRobotEvent event) {
		//
		// I wish this was onScannedTeamMate. I don't know how to manage this
		// method.
		//
	}

	@Override
	public void onHitAnimal(HitRobotEvent event) {
	}

	@Override
	public void onHitStone(HitRobotEvent event) {
	}

	@Override
	public void onHitTreasure(HitRobotEvent event) {
	}

	@Override
	public void onHitVan(HitRobotEvent event) {
	}

}