package foobar.hippy.van.samples;

import java.awt.Color;
import java.util.Random;

import robocode.ScannedRobotEvent;
import foobar.hippy.van.AbstractVan;
import foobar.hippy.van.coolingsystem.Fan;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.navigator.VanNavigator;

public class VanHelsing extends AbstractVan {
	//
	// Class members.
	//
	int amazingMovementCount;

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

		//
		//
		// Main loop.
		while (true) {
			//
			// Move forward 100 turns
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
		System.out.println("VanHelsing.onScannedAnimal()");
		System.out.println("Animal name:" + event.getName());
		System.out.println("Animal heading:" + event.getHeading());
		System.out.println("Animal bearing:" + event.getBearing());
		System.out.println("Animal distance:" + event.getDistance());
		System.out.println("");
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
			if (VanNavigator.isInFront(event.getBearing())) {
				//
				// The Van will turn at the right or at the left randomly.
				//
				if (new Random().nextBoolean()) {
					//
					// Turn Right 90 degrees (Heading Down)
					// Accelerate 118 turns
					// Turn Left 90 degrees (Heading Right)
					//
					turnRight(90);
					for (int i = 0; i < 118; i++) {
						accelerate();
					}
					turnLeft(90);
				} else {
					//
					// Turn Left 90 degrees (Heading Up)
					// Accelerate 118 turns
					// Turn Right 90 degrees (Heading Right)
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
		// Do something if it is reasonably close
		//
		if (event.getDistance() < 300) {
			//
			// Turn the Van to face the Treasure
			//
			turnRight(event.getBearing());

			//
			// Move forward 111 turns
			//
			for (int i = 0; i < 111; i++) {
				accelerate();
			}
		}
	}

	@Override
	public void onScannedVan(ScannedRobotEvent event) {
		//
		// If there is a Van heading at the right of the screen.
		//
		if (VanNavigator.isHeadingRight(event.getHeading())) {

			//
			// Do an amazing movement once to impress the Van.
			//
			if (amazingMovementCount == 0) {
				doAmazingMovement();
				amazingMovementCount++;
			}
		}
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

}
