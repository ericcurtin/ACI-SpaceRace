package foobar.enemy;

import java.awt.Color;

import foobar.hippy.AbstractHippyRobot;

/**
 * A Stone just stands there, it does nothing. If a Van hits a Stone both get
 * damaged.
 * 
 * @author Pablo Rodriguez (original)
 */
public final class Stone extends AbstractHippyRobot {

	@Override
	public void run() {
		setAllColors(Color.DARK_GRAY);
	}

}
