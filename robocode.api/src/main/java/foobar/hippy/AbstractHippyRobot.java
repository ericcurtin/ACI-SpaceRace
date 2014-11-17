package foobar.hippy;

import robocode.Robot;

abstract public class AbstractHippyRobot extends Robot {

	public boolean isVan(AbstractHippyRobot r) {
		return !isStone(r) && !isAnimal(r) && !isTreasure(r);
	}

	public boolean isStone(AbstractHippyRobot r) {
		return HippyName.isStone(r.getName());
	}

	public boolean isAnimal(AbstractHippyRobot r) {
		return HippyName.isAnimal(r.getName());
	}

	public boolean isTreasure(AbstractHippyRobot r) {
		return HippyName.isTreasure(r.getName());
	}

}
