package foobar.hippy.van.fuel;

/**
 * Coal is a fuel.
 * 
 * @author Eric Curtin (original)
 */
public final class CheapPetrol extends FuelDecorator {
	public final static String name = "CheapPetrol";

	public CheapPetrol(IFuel fuel, double volume) {
		super(fuel, volume);
		individualFuelPower = 1;
		individualTemperatureIncrease = 2;
		mixFuel();
	}
}
