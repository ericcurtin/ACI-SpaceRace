package net.sf.robocode.ui.battleview.van;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import foobar.hippy.AbstractHippyRobot;

/**
 * This class gets the robot's image from the disk, based on the robot's name it
 * can be: </br> - An Animal </br> - A Stone </br> - A Treasure </br> - A Van
 * 
 * 
 * @author rodriguezp
 *
 */
public class ImageMovingObject {

	private static final String bodyPath = "/net/sf/robocode/ui/images/";

	private static final String PATH_ANIMAL_01 = "animal01";
	private static final String PATH_ANIMAL_02 = "animal02";
	private static final String PATH_STONE = "stone";
	private static final String PATH_TREASURE = "treasure";
	private static final String PATH_VAN = "van";

	private static Map<String, String> mapAnimalPath = new HashMap<String, String>();

	public static String getBodyImage(String robotName) {

		//
		// If it's an animal then its image.
		//
		if (AbstractHippyRobot.isAnimal(robotName)) {

			//
			// If it has an associate image then load it again.
			//
			if (mapAnimalPath.containsKey(robotName)) {
				return bodyPath + mapAnimalPath.get(robotName) + ".png";
			}
			//
			// Otherwise find a random animal image.
			//
			int randomAnimal = new Random().nextInt(2);

			switch (randomAnimal) {
			case 0:
				mapAnimalPath.put(robotName, PATH_ANIMAL_01);
				break;

			default:
				mapAnimalPath.put(robotName, PATH_ANIMAL_02);
			}

			return bodyPath + mapAnimalPath.get(robotName) + ".png";

			//
			// If it's a stone then get the stone image.
			//
		} else if (AbstractHippyRobot.isStone(robotName)) {
			return bodyPath + PATH_STONE + ".png";

			//
			// If it's a treasure then get the treasure image.
			//
		} else if (AbstractHippyRobot.isTreasure(robotName)) {
			return bodyPath + PATH_TREASURE + ".png";

			//
			// Otherwise get the Van image.
			//
		} else {
			return bodyPath + PATH_VAN + ".png";
		}
	}
}
