package space.asteroid;

import java.awt.Color;

/**
 * This is a type of Asteroid that doesn't move.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class DummyAsteroid extends AbstractAsteroid {
	public DummyAsteroid() {
	}

	@Override
	public void run() {
		setGunColor(new Color(0, 0, 4));
	}
}
