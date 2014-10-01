package space.spaceship.coolingsystem;

/**
 * A cooling system may be used to cool down the space engine.
 * 
 * @author Eric Curtin (original)
 * @author Pablo Rodriguez (contributor)
 */
public abstract class AbstractCoolingSystem {
	private double coolingEffectMovement, coolingEffectStopped;

	protected AbstractCoolingSystem(double coolingEffectMovement,
			double coolingEffectStopped) {
		this.coolingEffectMovement = coolingEffectMovement;
		this.coolingEffectStopped = coolingEffectStopped;
	}

	public double getCoolingEffectMovement() {
		return coolingEffectMovement;
	}

	public double getCoolingEffectStopped() {
		return coolingEffectStopped;
	}
}
