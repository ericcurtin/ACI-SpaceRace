package foobar.hippy.van.fuel;

/**
 * ExpensivePetrol is a fuel.
 * 
 * @author Eric Curtin (original)
 */
public final class ExpensivePetrol extends FuelDecorator {
	public final static String name = "ExpensivePetrol";

	public ExpensivePetrol(IFuel fuel, double volume) {
		super(fuel, volume);
		individualFuelPower = 1.5;
		individualTemperatureIncrease = 2.5;
		mixFuel();
	}
}
