package foobar.hippy.van.booster;

/**
 * SpeedBooster doubles the speed fot 50 times.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class SpeedBooster {

	private double booster = 2.0;
	private int nTurns = 50;

	public SpeedBooster() {
		booster = 2.0;
		nTurns = 50;
	}

	public double getBoost() {
		if (nTurns > 0) {
			nTurns--;
			return booster;
		} else {
			// It multiplies the Van Speed so it can not be 0.
			return 1.0;
		}
	}

}
