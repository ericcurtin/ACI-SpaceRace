package foobar.hippy.van.coolingsystem;

/**
 * An Aircon cooling system.
 * 
 * It reduces the temperature 1.0 degree / turn when moving.
 * 
 * It reduces the temperature 1.0 degree / turn when stopped.
 * 
 * @author Eric Curtin (original)
 * @author Pablo Rodriguez (contributor)
 */

public final class Aircon extends AbstractCoolingSystem {
	public Aircon() {
		super(1, 1);
	}
}
