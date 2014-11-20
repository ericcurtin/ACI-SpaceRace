package foobar.hippy.van.engine;

import foobar.hippy.van.booster.SpeedBooster;
import foobar.hippy.van.coolingsystem.AbstractCoolingSystem;
import foobar.hippy.van.fuel.CheapPetrol;
import foobar.hippy.van.fuel.FuelTank;
import foobar.hippy.van.fuel.RacingPetrol;
import foobar.hippy.van.fuel.IFuel;
import foobar.hippy.van.fuel.ExpensivePetrol;

/**
 * SpaceEngine consists of many different parts.
 * 
 * @author Eric Curtin (original)
 * @author Pablo Rodriguez (contributor)
 */
public class Engine {

	/**
	 * Class members.
	 */
	private double speed, temperature;
	private IFuel fuel;
	private AbstractCoolingSystem coolingSystem;
	private SpeedBooster booster;

	/**
	 * The temperature at which the space engine overheats.
	 */
	public final static double OVERHEAT_TEMPERATURE = 1500.0;

	public Engine() {
		speed = 0;
		temperature = 0;
		fuel = new FuelTank();
		coolingSystem = null;
		booster = null;
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
			speed *= booster.getBoost();
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
	 * Attach the engine's cooling system.
	 */
	public void setCoolingSystem(AbstractCoolingSystem coolingSystem) {
		this.coolingSystem = coolingSystem;
	}

	/**
	 * Attach the engine's booster.
	 */
	public void setBooster(SpeedBooster booster) {
		this.booster = booster;
	}

	/**
	 * Add fuel to the space engine.
	 */
	public void addFuel(String fuelName, double volume) {
		try {
			if (fuelName.equalsIgnoreCase(CheapPetrol.name)) {
				fuel = new CheapPetrol(fuel, volume);
			} else if (fuelName.equalsIgnoreCase(ExpensivePetrol.name)) {
				fuel = new ExpensivePetrol(fuel, volume);
			} else if (fuelName.equalsIgnoreCase(RacingPetrol.name)) {
				fuel = new RacingPetrol(fuel, volume);
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
		return temperature >= OVERHEAT_TEMPERATURE;
	}
}
