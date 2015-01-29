package foobar.hippy.van.samples;

import java.awt.Color;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.Fan;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.fuel.RacingPetrol;
import foobar.hippy.van.fuel.ExpensivePetrol;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

/**
 * This is a Van using an ambitious Fuel mix & Cooling system & Overheating.
 *
 * The Fuel defines the number of pixels that the Van will move per turn and
 * also the number of degrees that the engine temperature will increase per
 * turn.
 *
 * This example uses a mix of Fuels: 1000 Litres of CheapFuel. 1000 Litres of
 * ExpensiveFuel. 3000 Litres of RacingFuel.
 * 
 * Fuel Power = ( 1000 * 1.0 ) + ( 1000 * 1.5 ) + ( 3000 * 2.0) / ( 3000 + 1000
 * + 1000 ) = 1.7 pixels / turn.
 * 
 * Engine Temperature Increase = ( 1000 * 1.0 ) + ( 1000 * 2.5 ) + ( 3000 * 5.0)
 * / ( 3000 + 1000 + 1000 ) = 3.7 degrees / turn.
 * 
 * 
 * The Cooling System defines the number of degrees that the engine temperature
 * will decrease per turn.
 * 
 * This example uses a Fan, which has: - A Cooling Effect of 1.1 when moving
 * (-1.1 degree / turn). - A Cooling Effect of 0.1 when stopped (-1 degree /
 * turn).
 * 
 * 
 * As temperature will increase 3.7 degrees per turn and the Fan can only reduce
 * the temperature by 1.1 per turn then the Van temperature will increase by 2.6
 * degrees per turn.
 * 
 * 1500 / 2.6 = 416.667
 * 
 * 416.667 * 1.7 pixels =
 * 
 * This Van will overheat at the turn number 417, before reaching the finish
 * line.
 * 
 * Mixing the fuels to find one with the better performance than your rival is
 * an advantage to win the race.
 * 
 * A Fan has a better performance when the Van is moving than the Aircon. On the
 * other hand, if the Van is overheated (stopped) the Aircon works way better
 * than the Fan. Pick carefully your Cooling System.
 * 
 * This is a faster Van which overheats.
 * 
 * @author Pablo Rodriguez (original)
 */

public class VanPersie extends AbstractVan {

	public VanPersie() {
		//
		// Set the Cooling System of the Van.
		//
		setCoolingSystem(new Fan());
	}

	@Override
	public void runACI() {
		//
		// Set Van colors (optional).
		// Note: Do not set the colors in the constructor.
		//
		setBodyColor(Color.RED);
		setRadarColor(Color.WHITE);
		setScanColor(Color.RED);

		//
		// Main loop.
		//
		while (true) {

			//
			// Accelerate the Van 200 turns.
			//
			for (int i = 0; i < 200; i++) {

				//
				// Accelerate the Van if it not overheated.
				//
				if (!isVanOverheated()) {
					accelerate();

					//
					// Stop the Van if it is overheated, so it can cool down.
					//
				} else {
					stop();
				}
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
		// I use a mix of fuels because it's cool.
		//
		addFuel(CheapPetrol.name, 1000);
		addFuel(ExpensivePetrol.name, 1000);
		addFuel(RacingPetrol.name, 3000);
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