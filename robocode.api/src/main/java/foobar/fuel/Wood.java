package foobar.fuel;

/**
 * Wood is a Fuel.
 * 
 * @author Eric Curtin (original)
 */
public final class Wood extends FuelDecorator {
	public final static String name = "Wood";

	public Wood(IFuel fuel, double volume) {
		super(fuel, volume);
		individualFuelPower = 1.1;
		individualTemperatureIncrease = 2;
		mixFuel();
	}
}
