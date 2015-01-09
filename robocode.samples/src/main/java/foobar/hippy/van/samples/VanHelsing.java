package foobar.hippy.van.samples;

import java.awt.Color;
import java.util.Random;

import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.Fan;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.navigator.VanNavigator;

/**
 * This is a Van that has some basic logic:
 * <p/>
 * - It has a very basic Fuel system (it only uses CheapPetrol).
 * <p/>
 * - It uses a Fan as a Cooling system.
 * <p/>
 * - It overrides the basic scanning events and takes wise decisions on each of
 * them ( onScannedAnimal, onScannedStone, onScannedTreasure, onScannedVan).
 * <p/>
 * - It overrides the optional method onHitTreasure, so it does something when
 * it collides with a Treasure (the same could be done for Animal / Stone /
 * Van).
 * <p/>
 * - It shows real-time information in the Van's console using the variable
 * 'out'.
 * <p/>
 * - It uses the helper class VanNavigator that give information about the
 * position of the enemies. Using VanNavigator class is absolutely optional,
 * there is no need to use it although if it is well implemented it can help to
 * create accurate Vans.
 * 
 * @author rodriguezp
 *
 */
public class VanHelsing extends AbstractVan {
	//
	// Class variables.
	//
	int amazingMovementCount;
	boolean victoryDanceDone = false;;

	public VanHelsing() {
		//
		// Set the Cooling System of the Van.
		//
		setCoolingSystem(new Fan());

		//
		// Initialise class members.
		//
		amazingMovementCount = 0;
	}

	@Override
	public void runACI() {
		//
		// Set Van colors (optional).
		//
		setBodyColor(Color.BLACK);
		setRadarColor(Color.WHITE);
		setScanColor(Color.RED);

		//
		// Main loop.
		//
		while (true) {
			//
			// Move forward 100 turns.
			//
			for (int i = 0; i < 100; i++) {
				accelerate();
			}
			//
			// Scan for other obstacles.
			//
			turnRadarRight(360);
		}
	}

	@Override
	public void onScannedAnimal(ScannedRobotEvent event) {
		//
		// Print some of the event properties on the Van's console.
		//
		out.println("VanHelsing.onScannedAnimal()");
		out.println("Animal name:" + event.getName());
		out.println("Animal heading:" + event.getHeading());
		out.println("Animal bearing:" + event.getBearing());
		out.println("Animal distance:" + event.getDistance());
		out.println("");
	}

	@Override
	public void onScannedStone(ScannedRobotEvent event) {
		//
		// Do something if it is close.
		//
		if (event.getDistance() < 150) {
			//
			// If there is a stone in front we will turn to avoid it.
			//
			if (VanNavigator.isBearingForward(event.getBearing())) {
				//
				// The Van will turn at the right or at the left randomly.
				//
				if (new Random().nextBoolean()) {
					//
					// Turn Right 90 degrees (Heading Down).
					// Accelerate 118 turns.
					// Turn Left 90 degrees (Heading Right).
					//
					turnRight(90);
					for (int i = 0; i < 118; i++) {
						accelerate();
					}
					turnLeft(90);
				} else {
					//
					// Turn Left 90 degrees (Heading Up).
					// Accelerate 118 turns.
					// Turn Right 90 degrees (Heading Right).
					//
					turnLeft(90);
					for (int i = 0; i < 118; i++) {
						accelerate();
					}
					turnRight(90);
				}
			}
		}
	}

	@Override
	public void onScannedTreasure(ScannedRobotEvent event) {
		//
		// Do something if it is reasonably close.
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
		// If there is a Van at the right.
		//
		if (VanNavigator.isBearingRight(event.getBearing())) {

			//
			// Do an amazing movement once to impress the Van.
			//
			if (amazingMovementCount == 0) {
				doAmazingMovement();
				amazingMovementCount++;
			}
		}
	}

	//
	// This method override onHitTreasure so the Van will do a Winning Dance.
	//
	// There are four optional methods that can be overriden:
	// - onHitAnimal
	// - onHitStone
	// - onHitTreasure
	// - onHitVan
	//
	// so the Van can execute some action when it hits something.
	//
	@Override
	public void onHitTreasure(HitRobotEvent event) {
		//
		// If we hit the Treasure first then we win the race.
		//
		if (!victoryDanceDone) {
			doVictoryDance();
			victoryDanceDone = true;
		}
	}

	/**
	 * Victory dance
	 */
	private void doVictoryDance() {
		//
		// Go back 22 movements.
		//
		turnLeft(180);

		for (int i = 0; i < 22; i++) {
			accelerate();
		}

		//
		// Spin Radar 5 times.
		//
		turnRadarLeft(360.0 * 5);

		//
		// Head the Treasure again.
		//
		turnRight(180);
	}

	/**
	 * Spin 360 degrees
	 */
	private void doAmazingMovement() {

		double totalTurnedDegrees = 0.0;
		double turnDegrees = 10.0;

		while (totalTurnedDegrees < 360.0) {
			turnLeft(turnDegrees);
			totalTurnedDegrees += turnDegrees;
			for (int i = 0; i < 5; i++) {
				accelerate();
			}
		}
	}

	@Override
	protected void setFuel() {
		addFuel(CheapPetrol.name, 5000);
	}

	@Override
	public void onHitAnimal(HitRobotEvent event) {
	}

	@Override
	public void onHitStone(HitRobotEvent event) {
	}

	@Override
	public void onHitVan(HitRobotEvent event) {
	}

}
