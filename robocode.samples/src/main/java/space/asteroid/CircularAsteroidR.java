package space.asteroid;

import java.awt.Color;

/**
 * This is a type of Asteroid that moves clockwise.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class CircularAsteroidR extends AbstractAsteroid {
	public CircularAsteroidR() {
	}

	@Override
	public void run() {
		this.setBodyColor(Color.YELLOW);
		this.setGunColor(new Color(0, 0, 4));
		asteroidCircularMovement();
	}

	private void asteroidCircularMovement() {
		while (true) {
			ahead(4);
			turnRight(8);
		}
	}
}
