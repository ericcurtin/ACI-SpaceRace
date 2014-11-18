package foobar.hippy.van.fuel;

/**
 * Hydrogen is a fuel.
 * 
 * @author Eric Curtin (original)
 */
public final class Hydrogen extends FuelDecorator {
	public final static String name = "Hydrogen";

	public Hydrogen(IFuel fuel, double volume) {
		super(fuel, volume);
		individualFuelPower = 2;
		individualTemperatureIncrease = 10;
		mixFuel();
	}
}
