package foobar.hippy.van.samples;

import java.awt.Color;

import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.Fan;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.fuel.RacingPetrol;
import foobar.hippy.van.fuel.ExpensivePetrol;
import robocode.ScannedRobotEvent;

/**
 * This is a Van using an advanced Fuel & Cooling system.
 *
 * 
 * The Fuel defines the number of pixels that the Van will move per turn and
 * also the number of degrees that the engine temperature will increase per
 * turn.
 *
 * This example uses a mix of Fuels: 3000 Litres of CheapFuel. 1000 Litres of
 * ExpensiveFuel. 1000 Litres of RacingFuel.
 * 
 * Fuel Power = ( 3000 * 1.0 ) + ( 1000 * 1.5 ) + ( 1000 * 2.0) / ( 3000 + 1000
 * + 1000 ) = 1.3 pixels / turn.
 * 
 * Engine Temperature Increase = ( 3000 * 1.0 ) + ( 1000 * 2.5 ) + ( 1000 * 5.0)
 * / ( 3000 + 1000 + 1000 ) = 2.1 degrees / turn.
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
 * As temperature will increase 2.1 degrees per turn and the Fan can only reduce
 * the temperature by 1.1 per turn then the Van temperature will increase by 1.0
 * degrees per turn. When the engine temperature reaches 1500 degrees the engine
 * will be overheated so the Van won't move.
 * 
 * 
 * Mixing the fuels to find one with the better performance than your rival is
 * an advantage to win the race.
 * 
 * A Fan has a better performance when the Van is moving than the Aircon. On the
 * other hand, if the Van is overheated (stopped) the Aircon works way better
 * than the Fan. Pick carefully your Cooling System. *
 * 
 * This is a faster Van which might overheat.
 * 
 * @author Pablo Rodriguez (original)
 */

public class VanNistelrooy extends AbstractVan {

	public VanNistelrooy() {
		//
		// Set the Cooling System of the Van.
		//
		setCoolingSystem(new Fan());
	}

	@Override
	public void runACI() {
		//
		// Set Van colors (optional).
		//
		setAllColors(Color.WHITE);

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