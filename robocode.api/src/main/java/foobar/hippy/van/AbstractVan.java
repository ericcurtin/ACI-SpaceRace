package foobar.hippy.van;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import foobar.hippy.AbstractHippyRobot;
import foobar.hippy.van.booster.SpeedBooster;
import foobar.hippy.van.coolingsystem.AbstractCoolingSystem;
import foobar.hippy.van.engine.Engine;
import robocode.Bullet;
import robocode.HitRobotEvent;
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

	/**
	 * Class members.
	 */
	private Engine engine = new Engine();
	private boolean isFuelFilled = false;
	private boolean isBoosterUsed = false;

	private Map<Integer, String> warningMessages = new HashMap<Integer, String>();
	{
		warningMessages
				.put(0,
						"The ACI Foobar hacking challenge is over, please code ethically");
		warningMessages
				.put(1,
						"Do you think you can win using deprecated methods? Now you know that you can't");
		warningMessages
				.put(2,
						"So I said to the judge: I swear I didn't try to cheat, it's just that I used deprecated methods");
		warningMessages
				.put(3,
						"You are banned from the ACI FooBar Challenge... Well... You're not but next time don't use deprecated methods");
	}

	protected AbstractVan() {

	}

	/**
	 * Accelerate and move your spaceship forward.
	 */
	public void accelerate() {
		if (engine.canAccelerate()) {
			super.ahead(engine.accelerate());
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
		engine.stop();

		// Stop
		super.stop();
	}

	@Override
	@Deprecated
	public final void run() {
		// Check if the name is valid
		checkVanName();

		setFuel();
		isFuelFilled = true;
		runACI();
	}

	/**
	 * This method checks if the name of the Van is valid. If it contains
	 * 'stone', 'animal' or 'treasure' then it is not valid.
	 */
	private final void checkVanName() {
		if (super.isStone(getName())) {
			throw new UnsupportedOperationException(
					"You are not a stone, please change your spaceship class name");
		} else if (super.isAnimal(getName())) {
			throw new UnsupportedOperationException(
					"You are not an animal, please change your spaceship class name");
		} else if (super.isTreasure(getName())) {
			throw new UnsupportedOperationException(
					"You are not a treasure, please change your spaceship class name");
		}
	}

	/**
	 * The setFuel abstract method. This should be overridden to ensure fuel is
	 * added at the start of the race.
	 */
	protected abstract void setFuel();

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
			engine.addFuel(fuelName, value);
			return;
		}
		System.out.println("You can only add fuel at the start of the race");
	}

	@Override
	@Deprecated
	public void onHitRobot(HitRobotEvent event) {
		String scannedRobotName = event.getName();

		if (super.isAnimal(scannedRobotName)) {
			onHitAnimal(event);
		} else if (super.isStone(scannedRobotName)) {
			onHitStone(event);
		} else if (super.isTreasure(scannedRobotName)) {
			onHitTreasure(event);
		} else {
			onHitVan(event);
		}
	}

	/**
	 * This method is called when your robot collides with an Animal. You should
	 * override it in your robot if you want to be informed of this event.
	 */
	public void onHitAnimal(HitRobotEvent event) {
		// Do nothing. It's up to the developer to override this method.
	}

	/**
	 * This method is called when your robot collides with a Stone. You should
	 * override it in your robot if you want to be informed of this event.
	 */
	public void onHitStone(HitRobotEvent event) {
		// Do nothing. It's up to the developer to override this method.
	}

	/**
	 * This method is called when your robot collides with a Treasure. You
	 * should override it in your robot if you want to be informed of this
	 * event.
	 */
	public void onHitTreasure(HitRobotEvent event) {
		// Do nothing. It's up to the developer to override this method.
	}

	/**
	 * This method is called when your robot collides with a Van. You should
	 * override it in your robot if you want to be informed of this event.
	 */
	public void onHitVan(HitRobotEvent event) {
		// Do nothing. It's up to the developer to override this method.
	}

	/**
	 * The main method in every spaceship. You must override this to set up your
	 * spaceship's basic behavior.
	 */
	public abstract void runACI();

	@Override
	@Deprecated
	public final void onScannedRobot(ScannedRobotEvent event) {
		String scannedRobotName = event.getName();

		if (super.isAnimal(scannedRobotName)) {
			onScannedAnimal(event);
		} else if (super.isStone(scannedRobotName)) {
			onScannedStone(event);
		} else if (super.isTreasure(scannedRobotName)) {
			onScannedTreasure(event);
		} else {
			onScannedVan(event);
		}
	}

	/**
	 * This method is automatically called when you scan with the radar and
	 * there is an animal in its range. You should override it in your van if
	 * you want to be informed of this event.
	 * 
	 * @param event
	 *            the scanned-animal event set by the game
	 */
	public abstract void onScannedAnimal(ScannedRobotEvent event);

	/**
	 * This method is automatically called when you scan with the radar and
	 * there is a stone in its range. You should override it in your van if you
	 * want to be informed of this event.
	 * 
	 * @param event
	 *            the scanned-stone event set by the game
	 */
	public abstract void onScannedStone(ScannedRobotEvent event);

	/**
	 * This method is automatically called when you scan with the radar and
	 * there is a treasure in its range. You should override it in your van if
	 * you want to be informed of this event.
	 * 
	 * @param event
	 *            the scanned-treasure event set by the game
	 */
	public abstract void onScannedTreasure(ScannedRobotEvent event);

	/**
	 * This method is automatically called when you scan with the radar and
	 * there is a van in its range. You should override it in your van if you
	 * want to be informed of this event.
	 * 
	 * @param event
	 *            the scanned-van event set by the game
	 */
	public abstract void onScannedVan(ScannedRobotEvent event);

	/**
	 * Get a random warning message from the 'warningMessages' class member.
	 * 
	 * @return random warning message from the 'warningMessages' class member.
	 */
	private String getRandomWarningMessage() {
		Random r = new Random();
		return warningMessages.get(r.nextInt(4));
	}

	/**
	 * Checks the current temperature of this spaceship.
	 * 
	 * @return the temperature of this spaceship
	 */
	public double getTemperature() {
		return engine.getTemperature();
	}

	/**
	 * This method checks if the Van SpeedBooster has been used.
	 */
	protected boolean isVanBoosterUsed() {
		return isBoosterUsed;
	}

	/**
	 * Checks if the spaceship is currently overheated.
	 * 
	 * @return true if it is overheated, false if it isn't
	 */
	protected boolean isVanOverheated() {
		return getTemperature() >= Engine.OVERHEAT_TEMPERATURE;
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
		engine.setCoolingSystem(coolingSystem);
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
			engine.stop();
			super.turnRadarLeft(45.0);
			degrees -= 45.0;
		}
		engine.stop();
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
			engine.stop();
			super.turnRadarRight(45.0);
			degrees -= 45.0;
		}
		engine.stop();
		super.turnRadarRight(degrees);
	}

	/**
	 * This method uses the Van SpeedBooster. It doubles its speed for the next
	 * 50 accelerate calls.
	 */
	protected void useBooster() {
		if (!isVanBoosterUsed()) {
			engine.setBooster(new SpeedBooster());
			isBoosterUsed = true;
		} else {
			throw new UnsupportedOperationException(
					"So you wanted to use a booster twice right? You can not do it.");
		}
	}

	@Override
	@Deprecated
	public final void ahead(double distance) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void back(double distance) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not fire.
	 */
	@Override
	@Deprecated
	public final void fire(double power) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final Bullet fireBullet(double power) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getBattleFieldHeight() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getBattleFieldWidth() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final int getBattleNum() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getEnergy() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final Graphics2D getGraphics() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getGunCharge() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getGunCoolingRate() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have gun. It can not get the gun heading.
	 */
	@Override
	@Deprecated
	public final double getGunHeading() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getGunHeat() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final String getGunImageName() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getHeight() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getLife() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final int getNumBattles() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final int getNumRounds() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final int getOthers() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final String getRadarImageName() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final String getRobotImageName() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final int getRoundNum() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final long getTime() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final double getWidth() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void resume() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void scan() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setAdjustGunForRobotTurn(boolean independent) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setAdjustRadarForGunTurn(boolean independent) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setAdjustRadarForRobotTurn(boolean independent) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setAllColors(Color color) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setBulletColor(Color color) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have gun. It can not set the gun color.
	 */
	@Override
	@Deprecated
	public final void setColors(Color bodyColor, Color gunColor,
			Color radarColor) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setDebugProperty(String key, String value) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have gun. It can not set the gun color.
	 */
	@Override
	@Deprecated
	public final void setGunColor(Color color) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setGunImageName(String newGunImageName) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setInterruptible(boolean interruptible) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setRadarImageName(String newRadarImageName) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	@Override
	@Deprecated
	public final void setRobotImageName(String newRobotImageName) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have gun. It can not turn the gun left.
	 */
	@Override
	public final void turnGunLeft(double degrees) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have gun. It can not turn the gun right.
	 */
	@Override
	public final void turnGunRight(double degrees) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

}
