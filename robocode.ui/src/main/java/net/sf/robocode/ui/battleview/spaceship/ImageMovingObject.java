package net.sf.robocode.ui.battleview.spaceship;

import foobar.hippy.HippyName;

public class ImageMovingObject {

	private static final String bodyPath = "/net/sf/robocode/ui/images/image";

	private static final String defaultImage = "00";

	private static final String PATH_VAN1 = "01";
	private static final String PATH_STONE = "03";
	private static final String PATH_ANIMAL = "04";
	private static final String PATH_TREASURE = "05";

	public static String getBodyImage(String s) {
		if (HippyName.isVan(s)) {
			return bodyPath + PATH_VAN1 + ".png";
		}
		if (HippyName.isStone(s)) {
			return bodyPath + PATH_STONE + ".png";
		}
		if (HippyName.isAnimal(s)) {
			return bodyPath + PATH_ANIMAL + ".png";
		}
		if (HippyName.isTreasure(s)) {
			return bodyPath + PATH_TREASURE + ".png";
		}

		return bodyPath + defaultImage + ".png";
	}
}
