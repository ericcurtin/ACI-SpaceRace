package foobar.hippy;

import robocode.Robot;

abstract public class AbstractHippyRobot extends Robot {

	final protected boolean isVan(AbstractHippyRobot r) {
		return !isStone(r) && !isAnimal(r) && !isTreasure(r);
	}

	final protected boolean isStone(AbstractHippyRobot r) {
		return HippyName.isStone(r.getName());
	}

	final protected boolean isAnimal(AbstractHippyRobot r) {
		return HippyName.isAnimal(r.getName());
	}

	final protected boolean isTreasure(AbstractHippyRobot r) {
		return HippyName.isTreasure(r.getName());
	}

}
