package foobar.hippy.van;

import java.awt.Color;
import java.awt.Graphics2D;

import foobar.booster.AbstractBooster;
import foobar.coolingsystem.AbstractCoolingSystem;
import foobar.hippy.AbstractHippyRobot;
import foobar.model.SpaceEngine;
import foobar.model.SpaceEngineFactory;
import robocode.Bullet;
import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * The is the spaceship base class that ACI FooBar challenge competitors should
 * extend from.
 * 
 * @author Eric Curtin (original)
 * @author Pablo Rodriguez (contributor)
 * @author Alan O'Dea (contributor)
 */
public abstract class AbstractVan extends AbstractHippyRobot {
	private SpaceEngine spaceEngine;
	private Color color;
	private static final String warning = "The ACI Foobar hacking"
			+ " challenge is over, please code ethically";
	private boolean isFuelFilled;

	protected AbstractVan() {
		isFuelFilled = false;
	}

	/**
	 * Accelerate and move your spaceship forward.
	 */
	public void accelerate() {
		if (spaceEngine.canAccelerate()) {
			super.ahead(spaceEngine.accelerate());
		} else {
			// Skip the turn, don't use cooling system
			doNothing();
		}
	}

	/**
	 * Stop your spaceship.
	 */
	@Override
	public void stop() {
		// Use cooling system
		spaceEngine.stop();

		// Stop
		super.stop();
	}

	@Override
	@Deprecated
	public final void run() {
		// If you delete this line, the spaceship image is not loaded
		// correctly
		setColor();

		// Check it does not have an asteroid name
		getName();

		try {
			super.setGunColor(new Color(0, 0, spaceEngine.getBodySize()));

		} catch (NullPointerException nullPointerException) {
			throw new NullPointerException("Please select model using "
					+ "one of the setAs() methods in the "
					+ "constructor of your Space Ship.");
		}

		setFuel();
		isFuelFilled = true;
		runACI();
	}

	/**
	 * This function sets the color of the spaceship, scanner and bullets.
	 */
	public final void setColor(Color color) {
		this.color = color;
	}

	/**
	 * This function sets the color of the spaceship, if no color is selected
	 * then the default one is used.
	 */
	private final void setColor() {
		if (color == null) {
			// Use default color
			color = Color.ORANGE;
		}

		// Set colors
		super.setBodyColor(color);
		super.setBulletColor(color);
		super.setRadarColor(color);
		super.setScanColor(color);
	}

	/**
	 * The setFuel abstract method. This should be overridden to ensure fuel is
	 * added at the start of the race.
	 */
	protected abstract void setFuel();

	/**
	 * Run this method if you want to use the Challenger Model.
	 */
	protected void setAsChallengerModel() {
		spaceEngine = SpaceEngineFactory.getChallengerSpaceShip();
	}

	/**
	 * Run this method if you want to use the Buran Model.
	 */
	protected void setAsBuranModel() {
		spaceEngine = SpaceEngineFactory.getBuranSpaceShip();
	}

	/**
	 * Run this method if you want to use the Atlantis Model.
	 */
	protected void setAsAtlantisModel() {
		spaceEngine = SpaceEngineFactory.getAtlantisSpaceShip();
	}

	/**
	 * Use this method to add fuel to your spaceship.
	 * 
	 * @param fuelName
	 *            the name of the fuel you are going to add
	 * @param value
	 *            the amount of fuel you are going to add
	 */
	protected void addFuel(String fuelName, double value) {
		if (!isFuelFilled) {
			spaceEngine.addFuel(fuelName, value);
			return;
		}
		System.out.println("You can only add fuel at the start of the race");
	}

	/**
	 * This method attaches the booster that you want to use to the spaceship.
	 * You may only use one booster.
	 * 
	 * @param booster
	 *            the booster you are going to attach to the Spaceship
	 */
	protected void setBooster(AbstractBooster booster) {
		spaceEngine.setBooster(booster);
	}

	/**
	 * This method attaches the cooling system that you want to use to the
	 * spaceship. You may only use one cooling system.
	 * 
	 * @param coolingSystem
	 *            the cooling system you are going to attach to the the
	 *            Spaceship
	 */
	protected void setCoolingSystem(AbstractCoolingSystem coolingSystem) {
		spaceEngine.setCoolingSystem(coolingSystem);
	}

	/**
	 * The main method in every spaceship. You must override this to set up your
	 * spaceship's basic behavior.
	 */
	public abstract void runACI();

	/**
	 * @return the spaceship's name
	 */
	@Override
	public final String getName() {
		if (super.getName() != null
				&& super.getName().toLowerCase().contains("asteroid")) {

			throw new UnsupportedOperationException("You are not an"
					+ "asteroid, please change your spaceship class name");
		}

		return super.getName();
	}

	@Override
	@Deprecated
	public final void onScannedRobot(ScannedRobotEvent event) {
		String name = event.getName();

		if (isDummyAsteroid(name) || isCircularAsteroid(name)
				|| isLargeAsteroid(name)) {
			onScannedAsteroid(event);
		} else {
			onScannedSpaceship(event);
		}
	}

	/**
	 * Checks if asteroid is of type DummyAsteroid.
	 * 
	 * @param name
	 *            the name of the MovingObject you have detected
	 * @return true if it is of type DummyAsteroid, false if it isn't
	 */
	protected final boolean isDummyAsteroid(String name) {
		return name.toLowerCase().contains("dummyasteroid");
	}

	/**
	 * Checks if asteroid is of type CircularAsteroid.
	 * 
	 * @param name
	 *            the name of the MovingObject you have detected
	 * @return true if it is of type CircularAsteroid, false if it isn't
	 */
	protected final boolean isCircularAsteroid(String name) {
		return name.toLowerCase().contains("circularasteroid");
	}

	/**
	 * Checks if asteroid is of type LargeAsteroid.
	 * 
	 * @param name
	 *            the name of the MovingObject you have detected
	 * @return true if it is of type LargeAsteroid, false if it isn't
	 */
	protected final boolean isLargeAsteroid(String name) {
		return name.toLowerCase().contains("largeasteroid");
	}

	/**
	 * This method is called when your spaceship sees an asteroid, i.e. when the
	 * spaceship's radar scan "hits" an asteroid. You should override it in your
	 * spaceship if you want to be informed of this event. (Almost all
	 * spaceships should override this!) This event is automatically called if
	 * there is a spaceship in range of your radar.
	 * 
	 * @param event
	 *            the scanned-asteroid event set by the game
	 */
	public abstract void onScannedAsteroid(ScannedRobotEvent event);

	/**
	 * This method is called when your spaceship sees another spaceship, i.e.
	 * when the spaceship's radar scan "hits" another spaceship. You should
	 * override it in your spaceship if you want to be informed of this event.
	 * (Almost all spaceships should override this!) This event is automatically
	 * called if there is a spaceship in range of your radar.
	 * 
	 * @param event
	 *            the scanned-spaceship event set by the game
	 */
	public abstract void onScannedSpaceship(ScannedRobotEvent event);

	/**
	 * Checks the current temperature of this spaceship.
	 * 
	 * @return the temperature of this spaceship
	 */
	public double getTemperature() {
		return spaceEngine.getTemperature();
	}

	/**
	 * Checks if the spaceship is currently overheated.
	 * 
	 * @return true if it is overheated, false if it isn't
	 */
	protected boolean isOverheated() {
		return getTemperature() >= SpaceEngine.overheatTemperature;
	}

	/**
	 * Immediately turns the spaceship's gun to the left by degrees. This call
	 * executes immediately, and does not return until it is complete, i.e. when
	 * the angle remaining in the gun's turn is 0. Note that both positive and
	 * negative values can be given as input, where negative values means that
	 * the spaceship's gun is set to turn right instead of left.
	 * 
	 * @param degrees
	 *            the amount of degrees to turn the spaceship's gun to the left.
	 *            If degrees > 0 the spaceship's gun will turn left. If degrees
	 *            < 0 the spaceship's gun will turn right. If degrees = 0 the
	 *            spaceship's gun will not turn, but execute.
	 */
	@Override
	public void turnGunLeft(double degrees) {
		while (degrees > 20.0) {
			spaceEngine.stop();
			super.turnGunLeft(20.0);
			degrees -= 20.0;
		}
		spaceEngine.stop();
		super.turnGunLeft(degrees);
	}

	/**
	 * Immediately turns the spaceship's gun to the right by degrees. This call
	 * executes immediately, and does not return until it is complete, i.e. when
	 * the angle remaining in the gun's turn is 0. Note that both positive and
	 * negative values can be given as input, where negative values means that
	 * the spaceship's gun is set to turn left instead of right.
	 * 
	 * @param degrees
	 *            the amount of degrees to turn the spaceship's gun to the
	 *            right. If degrees > 0 the spaceship's gun will turn right. If
	 *            degrees < 0 the spaceship's gun will turn left. If degrees = 0
	 *            the spaceship's gun will not turn, but execute.
	 */
	@Override
	public void turnGunRight(double degrees) {
		while (degrees > 20.0) {
			spaceEngine.stop();
			super.turnGunRight(20.0);
			degrees -= 20.0;
		}
		spaceEngine.stop();
		super.turnGunRight(degrees);
	}

	/**
	 * Immediately turns the spaceship's radar to the left by degrees. This call
	 * executes immediately, and does not return until it is complete, i.e. when
	 * the angle remaining in the spaceship's turn is 0. Note that both positive
	 * and negative values can be given as input, where negative values means
	 * that the robot's radar is set to turn right instead of left.
	 * 
	 * @param degrees
	 *            the amount of degrees to turn the spaceship's radar to the
	 *            left. If degrees > 0 the spaceship's radar will turn left. If
	 *            degrees < 0 the spaceship's radar will turn right. If degrees
	 *            = 0 the spaceship's radar will not turn, but execute.
	 */
	@Override
	public void turnRadarLeft(double degrees) {
		while (degrees > 45.0) {
			spaceEngine.stop();
			super.turnRadarLeft(45.0);
			degrees -= 45.0;
		}
		spaceEngine.stop();
		super.turnRadarLeft(degrees);
	}

	/**
	 * Immediately turns the spaceship's radar to the right by degrees. This
	 * call executes immediately, and does not return until it is complete, i.e.
	 * when the angle remaining in the spaceship's turn is 0. Note that both
	 * positive and negative values can be given as input, where negative values
	 * means that the spaceship's radar is set to turn left instead of right.
	 * 
	 * @param degrees
	 *            the amount of degrees to turn the spaceship's radar to the
	 *            right. If degrees > 0 the spaceship's radar will turn right.
	 *            If degrees < 0 the spaceship's radar will turn left. If
	 *            degrees = 0 the spaceship's radar will not turn, but execute.
	 */
	@Override
	public void turnRadarRight(double degrees) {
		while (degrees > 45.0) {
			spaceEngine.stop();
			super.turnRadarRight(45.0);
			degrees -= 45.0;
		}
		spaceEngine.stop();
		super.turnRadarRight(degrees);
	}

	/**
	 * Immediately fires a bullet. The bullet will travel in the direction the
	 * gun is pointing. The specified bullet power is an amount of energy that
	 * will be taken from the spaceship's energy. Hence, the more power you want
	 * to spend on the bullet, the more energy is taken from your spaceship. The
	 * bullet will do (4 * power) damage if it hits another spaceship. You will
	 * get (3 * power) back if you hit the other spaceship. You can call
	 * Rules.getBulletDamage(double) for getting the damage that a bullet with a
	 * specific bullet power will do. The maximum bullet power is .15. The
	 * minimum bullet power is .1.
	 */
	@Override
	public void fire(double power) {
		final double maxPower = .15;
		if (power < maxPower) {
			super.fire(power);
		} else {
			super.fire(maxPower);
		}
	}

	@Override
	@Deprecated
	public final void ahead(double distance) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void back(double distance) {
		throw new UnsupportedOperationException(warning);
	}

	/*
	 * @Override
	 * 
	 * @Deprecated public final void doNothing() { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void fire(double power) { throw new
	 * UnsupportedOperationException(warning); }
	 */

	@Override
	@Deprecated
	public final Bullet fireBullet(double power) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getBattleFieldHeight() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getBattleFieldWidth() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getEnergy() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final Graphics2D getGraphics() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getGunCoolingRate() {
		throw new UnsupportedOperationException(warning);
	}

	// @Override
	// @Deprecated
	// public final double getGunHeading() {
	// throw new UnsupportedOperationException(warning);
	// }

	@Override
	@Deprecated
	public final double getGunHeat() {
		throw new UnsupportedOperationException(warning);
	}

	// @Override
	// @Deprecated
	// public final double getHeading() {
	// throw new UnsupportedOperationException(warning);
	// }

	@Override
	@Deprecated
	public final double getHeight() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final int getNumRounds() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final int getOthers() {
		throw new UnsupportedOperationException(warning);
	}

	// @Override
	// @Deprecated
	// public final double getRadarHeading() {
	// throw new UnsupportedOperationException(warning);
	// }

	@Override
	@Deprecated
	public final int getRoundNum() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final long getTime() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getVelocity() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getWidth() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getX() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getY() {
		throw new UnsupportedOperationException(warning);
	}

	/*
	 * @Override
	 * 
	 * @Deprecated public final void onBattleEnded(BattleEndedEvent event) {
	 * throw new UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onBulletHit(BulletHitEvent event) { throw
	 * new UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onBulletHitBullet(BulletHitBulletEvent
	 * event) { throw new UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onBulletMissed(BulletMissedEvent event) {
	 * throw new UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onDeath(DeathEvent event) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onHitByBullet(HitByBulletEvent event) {
	 * throw new UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onHitRobot(HitRobotEvent event) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onHitWall(HitWallEvent event) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onKeyPressed(KeyEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onKeyReleased(KeyEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onKeyTyped(KeyEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onMouseClicked(MouseEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onMouseDragged(MouseEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onMouseEntered(MouseEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onMouseExited(MouseEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onMouseMoved(MouseEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onMousePressed(MouseEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onMouseReleased(MouseEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onMouseWheelMoved(MouseWheelEvent e) {
	 * throw new UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onPaint(Graphics2D g) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onRobotDeath(RobotDeathEvent event) { throw
	 * new UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onRoundEnded(RoundEndedEvent event) { throw
	 * new UnsupportedOperationException(warning); }
	 * 
	 * public final void onStatus(StatusEvent e) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void onWin(WinEvent event) { throw new
	 * UnsupportedOperationException(warning); }
	 */

	@Override
	@Deprecated
	public final void resume() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void scan() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setAdjustGunForRobotTurn(boolean independent) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setAdjustRadarForGunTurn(boolean independent) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setAdjustRadarForRobotTurn(boolean independent) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setAllColors(Color color) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setBodyColor(Color color) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setBulletColor(Color color) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setColors(Color bodyColor, Color gunColor,
			Color radarColor) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setColors(Color bodyColor, Color gunColor,
			Color radarColor, Color bulletColor, Color scanArcColor) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setDebugProperty(String key, String value) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setGunColor(Color color) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setRadarColor(Color color) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setScanColor(Color color) {
		throw new UnsupportedOperationException(warning);
	}

	/*
	 * @Override
	 * 
	 * @Deprecated public final void turnGunLeft(double degrees) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void turnGunRight(double degrees) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void turnLeft(double degrees) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void turnRadarLeft(double degrees) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void turnRadarRight(double degrees) { throw new
	 * UnsupportedOperationException(warning); }
	 * 
	 * @Override
	 * 
	 * @Deprecated public final void turnRight(double degrees) { throw new
	 * UnsupportedOperationException(warning); }
	 */

	@Override
	@Deprecated
	public final int getBattleNum() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getGunCharge() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final String getGunImageName() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final double getLife() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final int getNumBattles() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final String getRadarImageName() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final String getRobotImageName() {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setGunImageName(String newGunImageName) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setInterruptible(boolean interruptible) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setRadarImageName(String newRadarImageName) {
		throw new UnsupportedOperationException(warning);
	}

	@Override
	@Deprecated
	public final void setRobotImageName(String newRobotImageName) {
		throw new UnsupportedOperationException(warning);
	}
}
