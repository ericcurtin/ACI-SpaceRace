package foobar.hippy.van.fuel;

/**
 * FuelTank is an empty fuel tank that the spaceship has to start with.
 * 
 * @author Eric Curtin (original)
 */
public final class FuelTank implements IFuel {

	/**
	 * {@inheritDoc}
	 */
	public double getPower() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getVolume() {
		return 0;
	}

	@Override
	public void decrementVolume() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getTemperatureIncrease() {
		return 0;
	}
}
