package foobar.hippy.van.coolingsystem;

/**
 * An Fan cooling system.
 * 
 * It reduces the temperature 1.1 degree / turn when moving.
 * 
 * It reduces the temperature 0.1 degree / turn when stopped.
 * 
 * @author Eric Curtin (original)
 * @author Pablo Rodriguez (contributor)
 */
public final class Fan extends AbstractCoolingSystem {
	public Fan() {
		super(1.1, 0.1);
	}
}
