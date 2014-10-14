package foobar.fuel;

/**
 * Coal is a fuel.
 * 
 * @author Eric Curtin (original)
 */
public final class Coal extends FuelDecorator {
	public final static String name = "Coal";
	
	public Coal(IFuel fuel, double volume) {
		super(fuel, volume);
		individualFuelPower = 1;
		individualTemperatureIncrease = 2;
		mixFuel();
	}
}
