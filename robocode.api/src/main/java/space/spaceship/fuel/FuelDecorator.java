package space.spaceship.fuel;

/**
 * FuelDecorator acts as abstract decorator in decorator pattern.
 * 
 * @author Eric Curtin (original)
 */
public abstract class FuelDecorator implements IFuel {
	private IFuel fuel;
	private double mixedFuelPower, mixedFuelTemperatureIncrease = 0,
			totalTemperature = 0;
	protected double individualFuelPower, individualTemperatureIncrease;
	private double volume = 0;

	public FuelDecorator(IFuel fuel, double volume) {
		this.fuel = fuel;
		if (volume >= 0 && volume + fuel.getVolume() <= 5000) {
			this.volume = volume;
		} else {
			System.out.println("Cannot add this volume of fuel, max "
					+ "capacity 5000 and you may not add negative values "
					+ "of fuel");
		}
	}

	protected void mixFuel() {
		if (volume <= 0) {
			mixedFuelPower = fuel.getPower();
			mixedFuelTemperatureIncrease = fuel.getTemperatureIncrease();
		} else {
			double lastFuelVolume = fuel.getVolume();
			double lastFuelPower = fuel.getPower();
			mixedFuelPower = ((lastFuelPower * lastFuelVolume) + (individualFuelPower * volume))
					/ (volume + lastFuelVolume);

			double lastFuelTemperatureIncrease = fuel
					.getTemperatureIncrease();
			mixedFuelTemperatureIncrease = ((lastFuelTemperatureIncrease * lastFuelVolume) + (individualTemperatureIncrease * volume))
					/ (volume + lastFuelVolume);
		}
		volume += fuel.getVolume();
	}

	/**
	 * {@inheritDoc}
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getPower() {
		if (volume >= 1 || volume <= 0) {
			return mixedFuelPower;
		} else {
			return mixedFuelPower * volume;
		}
	}

	public void decrementVolume() {
		if (volume >= 1) {
			--volume;
		} else {
			volume = 0;
		}
	}

	/**
	 * @return the current temperature of the SpaceShip
	 */
	public double getTemperature() {
		return totalTemperature;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getTemperatureIncrease() {
		return mixedFuelTemperatureIncrease;
	}
}
