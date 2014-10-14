package net.sf.robocode.ui.battleview.spaceship;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ImageMovingObject {

	private static final String bodyPath = "/net/sf/robocode/ui/images/image";

	private static final String defaultImage = "00";

	private static final String PATH_VAN1 = "01";
	private static final String PATH_VAN2 = "02";
	private static final String PATH_STONE = "03";
	private static final String PATH_ANIMAL = "04";
	private static final String PATH_TREASURE = "05";

	private static final Map<Color, String> colorsAndImages = createMap();

	private static Map<Color, String> createMap() {
		Map<Color, String> result = new HashMap<Color, String>();
		result.put(ColorMovingObject.COLOR_VAN1, PATH_VAN1);
		result.put(ColorMovingObject.COLOR_VAN2, PATH_VAN2);
		result.put(ColorMovingObject.COLOR_STONE, PATH_STONE);
		result.put(ColorMovingObject.COLOR_ANIMAL, PATH_ANIMAL);
		result.put(ColorMovingObject.COLOR_TREASURE, PATH_TREASURE);

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
