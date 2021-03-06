package space.spaceship.model;

import space.spaceship.booster.AbstractBooster;
import space.spaceship.coolingsystem.AbstractCoolingSystem;
import space.spaceship.fuel.Coal;
import space.spaceship.fuel.Ethanol;
import space.spaceship.fuel.IFuel;
import space.spaceship.fuel.FuelTank;
import space.spaceship.fuel.Hydrogen;
import space.spaceship.fuel.Petrol;
import space.spaceship.fuel.Wood;

/**
 * SpaceEngine consists of many different parts.
 * 
 * @author Eric Curtin (original)
 * @author Pablo Rodriguez (contributor)
 */
public abstract class SpaceEngine {

	/**
	 * The temperature at which the space engine overheats.
	 */
	public final static double overheatTemperature = 5000.0;
	protected double speed, temperature;
	protected String nationality, name, message;
	private int bodySize;
	protected AbstractBooster booster;
	protected IFuel fuel;
	private AbstractCoolingSystem coolingSystem;

	public SpaceEngine(int bodySize) {
		speed = 0;
		temperature = 0;
		this.bodySize = bodySize;
		fuel = new FuelTank();
	}

	public double accelerate() {
		temperature += fuel.getTemperatureIncrease();
		if (coolingSystem != null) {
			if (temperature > coolingSystem.getCoolingEffectMovement()) {
				temperature -= coolingSystem.getCoolingEffectMovement();
			} else {
				temperature = 0;
			}
		}

		speed = fuel.getPower();
		fuel.decrementVolume();

		if (booster != null) {
			speed += booster.getBoost();
		}
		System.out.println(currentDetails());
		return speed;
	}

	public double stop() {
		if (coolingSystem != null) {
			if (temperature > coolingSystem.getCoolingEffectStopped()) {
				temperature -= coolingSystem.getCoolingEffectStopped();
			} else {
				temperature = 0;
			}
		}

		speed = 0;
		System.out.println(currentDetails());
		return speed;
	}

	public boolean canAccelerate() {
		System.out.println(currentDetails());
		return !(getVolume() <= 0 || isOverheated());
	}

	/**
	 * @return the current speed of the space engine.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @return the current volume of the space engine.
	 */
	public double getVolume() {
		return fuel.getVolume();
	}

	/**
	 * @return the current temperature of the space engine.
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @return the body size of the space engine.
	 */
	public int getBodySize() {
		return bodySize;
	}

	/**
	 * Attach the engine's cooling system.
	 */
	public void setCoolingSystem(AbstractCoolingSystem coolingSystem) {
		this.coolingSystem = coolingSystem;
	}

	/**
	 * Attach the engine's booster.
	 */
	public void setBooster(AbstractBooster booster) {
		this.booster = booster;
	}

	/**
	 * Add fuel to the space engine.
	 */
	public void addFuel(String fuelName, double volume) {
		try {
			if (fuelName.equalsIgnoreCase(Coal.name)) {
				fuel = new Coal(fuel, volume);
			} else if (fuelName.equalsIgnoreCase(Petrol.name)) {
				fuel = new Petrol(fuel, volume);
			} else if (fuelName.equalsIgnoreCase(Hydrogen.name)) {
				fuel = new Hydrogen(fuel, volume);
			} else if (fuelName.equalsIgnoreCase(Wood.name)) {
				fuel = new Wood(fuel, volume);
			} else if (fuelName.equalsIgnoreCase(Ethanol.name)) {
				fuel = new Ethanol(fuel, volume);
			} else {
				System.out.println("Incorrect string entered, no fuel added");
			}
		} catch (NoClassDefFoundError e) {
			System.out.println("You do not have the .class file to add "
					+ fuelName);
		}
	}

	/**
	 * @return details of the spaceship's current status
	 */
	public String currentDetails() {
		String currentDetails = "Volume is " + getVolume()
				+ "\nTemperature is " + temperature;
		if (isOverheated()) {
			currentDetails += " (overheated)";
		}
		return currentDetails + "\nSpeed is " + speed + "\n";
	}

	/**
	 * Check's if the space engine is currently overheated.
	 * 
	 * @return true if the space engine if overheated, false if it isn't
	 */
	public boolean isOverheated() {
		return temperature >= overheatTemperature;
	}
}
