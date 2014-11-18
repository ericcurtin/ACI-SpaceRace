package foobar.hippy.van.fuel;

/**
 * Petrol is a fuel.
 * 
 * @author Eric Curtin (original)
 */
public final class Petrol extends FuelDecorator {
	public final static String name = "Petrol";

	public Petrol(IFuel fuel, double volume) {
		super(fuel, volume);
		individualFuelPower = 1.5;
		individualTemperatureIncrease = 5;
		mixFuel();
	}
}
