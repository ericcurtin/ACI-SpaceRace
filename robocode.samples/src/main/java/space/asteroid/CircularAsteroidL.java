package space.asteroid;

import java.awt.Color;

/**
 * This is a type of Asteroid that moves anti-clockwise.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class CircularAsteroidL extends AbstractAsteroid {
	public CircularAsteroidL() {
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
			turnLeft(8);
		}
	}
}
