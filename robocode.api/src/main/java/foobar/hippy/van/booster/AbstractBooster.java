package foobar.hippy.van.booster;

/**
 * A booster may be used to give your space engine a speed boost.
 * 
 * @author Eric Curtin (original)
 */
public abstract class AbstractBooster {
	private double boost;

	protected AbstractBooster(double boost) {
		this.boost = boost;
	}

	/**
	 * Get speed boost that is added by booster.
	 * 
	 * @return speed boost
	 */
	public double getBoost() {
		return boost;
	}
}
