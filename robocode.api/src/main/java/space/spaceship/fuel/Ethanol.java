package space.spaceship.fuel;

/**
 * Ethanol is a fuel.
 * 
 * @author Eric Curtin (original)
 */
public final class Ethanol extends FuelDecorator {
	public final static String name = "Ethanol";

	public Ethanol(IFuel fuel, double volume) {
		super(fuel, volume);
		individualFuelPower = 2;
		individualTemperatureIncrease = 9;
		mixFuel();
	}
}
