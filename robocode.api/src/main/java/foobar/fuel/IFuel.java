package foobar.fuel;

/**
 * Fuel interface.
 * 
 * @author Eric Curtin (original)
 */
public interface IFuel {

	/**
	 * @return the max speed a spaceship can travel at currently
	 */
	double getPower();

	void decrementVolume();

	/**
	 * @return the volume of a fueltank currently
	 */
	double getVolume();

	/**
	 * @return the amount a fuel will increase the temperature per accelerate()
	 *         call currently
	 */
	double getTemperatureIncrease();
}
