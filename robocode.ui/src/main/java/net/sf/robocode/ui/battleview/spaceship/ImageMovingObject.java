package net.sf.robocode.ui.battleview.spaceship;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ImageMovingObject {

	private static final String bodyPath = "/net/sf/robocode/ui/images/image";

	private static final String defaultImage = "00";

	private static final String PATH_SPACESHIP1 = "01";
	private static final String PATH_SPACESHIP2 = "02";
	private static final String PATH_SPACESHIP3 = "03";
	private static final String PATH_ASTEROID_SMALL = "04";
	private static final String PATH_ASTEROID_BIG = "05";

	private static final Map<Color, String> colorsAndImages = createMap();

	private static Map<Color, String> createMap() {
		Map<Color, String> result = new HashMap<Color, String>();
		result.put(ColorMovingObject.COLOR_SPACESHIP1, PATH_SPACESHIP1);
		result.put(ColorMovingObject.COLOR_SPACESHIP2, PATH_SPACESHIP2);
		result.put(ColorMovingObject.COLOR_SPACESHIP3, PATH_SPACESHIP3);
		result.put(ColorMovingObject.COLOR_ASTEROID_SMALL, PATH_ASTEROID_SMALL);
		result.put(ColorMovingObject.COLOR_ASTEROID_BIG, PATH_ASTEROID_BIG);

		return Collections.unmodifiableMap(result);
	}

	public static String getBodyImage(Color c) {
		Set<Color> movingObjects = colorsAndImages.keySet();

		for (Color movingObject : movingObjects) {
			if (c.equals(movingObject)) {
				return bodyPath + colorsAndImages.get(movingObject) + ".png";
			}
		}

		return bodyPath + defaultImage + ".png";
	}
}
