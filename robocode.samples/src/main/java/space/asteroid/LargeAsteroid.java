package space.asteroid;

import java.awt.Color;
import java.util.Random;

/**
 * This is a Large Asteroid that moves very fast, it's speed varies.
 * 
 * @author Pablo Rodriguez (original)
 * @author Eric Curtin (contributor)
 */
public final class LargeAsteroid extends AbstractAsteroid {
	public LargeAsteroid() {
	}

	@Override
	public void run() {
		setAllColors(Color.MAGENTA);
		this.setGunColor(new Color(0, 0, 5));

		Random rn = new Random();

		for (int movement = rn.nextInt(5) + 4; true;) {
			ahead(movement);
			System.out.println(movement);
		}

	}
}