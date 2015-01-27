package foobar.hippy.van;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import foobar.hippy.AbstractHippyRobot;
import foobar.hippy.van.coolingsystem.AbstractCoolingSystem;
import foobar.hippy.van.engine.Engine;
import robocode.Bullet;
import robocode.Event;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

/**
 * The AbstractVan is the Van base class that ACI FooBar Challenge 2015
 * competitors should extend from.
 * 
 * @author Eric Curtin (original)
 * @author Pablo Rodriguez (contributor)
 * @author Alan O'Dea (contributor)
 */
public abstract class AbstractVan extends AbstractHippyRobot {

	/**
	 * It defines the Van's engine.
	 */
	private Engine engine = new Engine();

	/**
	 * It defines if the Van's fuel has been filled.
	 */
	private boolean isFuelFilled = false;

	/**
	 * It defines a Map< Integer id, String message > with funny messages that
	 * will be displayed when developers try to use methods that they shouldn't.
	 */
	private Map<Integer, String> warningMessages = new HashMap<Integer, String>();
	{
		warningMessages
				.put(0,
						"The ACI Foobar hacking challenge is over, please code ethically.");
		warningMessages
				.put(1,
						"Do you think you can win using deprecated methods? Now you know that you can't.");
		warningMessages
				.put(2,
						"So I said to the judge: I swear I didn't try to cheat, it's just that I used deprecated methods.");
		warningMessages
				.put(3,
						"You are banned from the ACI FooBar Challenge... Well... You're not but next time don't use deprecated methods.");
		warningMessages
				.put(4,
						"Please write one zillion times on a paper sheet: 'I won't use deprecated methods again'.");
		warningMessages
				.put(5,
						"Hello! I am Mr. Annoying: If you don't use deprecated methods I swear I won't visit you again.");
		warningMessages
				.put(6,
						"I am sexy and (using deprecated methods is not cool and) I know it.");
	}

	/**
	 * Immediately accelerates the Van (if possible) so it will move forward.
	 * Van won't move if it runs out of fuel or if it is overheated. If the Van
	 * can not move then the temperature will remain as you're still trying to
	 * accelerate. When a Van is moving:
	 * <p/>
	 * - The fuel volume will decrease by 1.
	 * <p/>
	 * - The fuel mix will increase the engine temperature.
	 * <p/>
	 * - If a cooling system is defined it will decrease the engine temperature.
	 */
	public void accelerate() {
		if (engine.canAccelerate()) {
			super.ahead(engine.accelerate());
		} else {
			//
			// Skip the turn, don't use cooling system.
			//
			doNothing();
		}
	}

	/**
	 * Immediately stops your Van. When a Van is stopped:
	 * <p/>
	 * - If a cooling system is defined it will decrease the engine temperature.
	 */
	@Override
	public void stop() {
		//
		// Use cooling system.
		//
		engine.stop();

		//
		// Stop moving.
		//
		super.stop();
	}

	/**
	 * The old main method in every Van.
	 * 
	 * @deprecated Use {@link AbstractVan#runACI() runACI()} instead.
	 */
	@Override
	@Deprecated
	public final void run() {
		//
		// Check if the name is valid.
		//
		checkVanName();

		//
		// Sets the Van's fuel mix.
		//
		setFuel();
		isFuelFilled = true;

		//
		// Call the main method.
		//
		runACI();
	}

	/**
	 * Checks if the Van's name is valid. All names are valid as long as they
	 * don't contain the words 'stone', 'animal' or 'treasure'.
	 * 
	 * @throws UnsupportedOperationException
	 *             When the Van does not have a valid Van name.
	 */
	private final void checkVanName() {
		if (super.isStone(getName())) {
			throw new UnsupportedOperationException(
					"You are not a stone, please change your Van class name.");
		} else if (super.isAnimal(getName())) {
			throw new UnsupportedOperationException(
					"You are not an animal, please change your Van class name.");
		} else if (super.isTreasure(getName())) {
			throw new UnsupportedOperationException(
					"You are not a treasure, please change your Van class name.");
		}
	}

	/**
	 * It defines the fuel mix. This method should be overridden to ensure fuel
	 * is added at the start of the race.
	 * <p/>
	 * Example:
	 * 
	 * <pre>
	 * // A basic fuel mix that only contains 5000 litres of CheapPetrol.
	 * protected void setFuel() {
	 * 	addFuel(CheapPetrol.name, 5000);
	 * }
	 * </pre>
	 */
	protected abstract void setFuel();

	/**
	 * Use this method to add fuel to your Van.
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

	/**
	 * Old method that is called when your Van collides with a robot.
	 * 
	 * @deprecated Use one of the following methods instead:
	 *             <p/>
	 *             {@link AbstractVan#onHitAnimal(HitRobotEvent) onHitAnimal()}
	 *             <p/>
	 *             {@link AbstractVan#onHitStone(HitRobotEvent) onHitStone()}
	 *             <p/>
	 *             {@link AbstractVan#onHitTreasure(HitRobotEvent)
	 *             onHitTreasure()}
	 *             <p/>
	 *             {@link AbstractVan#onHitVan(HitRobotEvent) onHitVan()}
	 */
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
	 * This method is called when your Van collides with an Animal. You should
	 * override it in your Van if you want to be informed of this event.
	 * <p/>
	 * Example:
	 * 
	 * <pre>
	 * void onHitAnimal(HitRobotEvent event) {
	 * 	if (event.getBearing() &gt; -90 &amp;&amp; event.getBearing() &lt;= 90) {
	 * 		//
	 * 		// Move back.
	 * 		//
	 * 		turnLeft(180);
	 * 		accelerate();
	 * 	} else {
	 * 		accelerate();
	 * 	}
	 * }
	 * </pre>
	 * <p/>
	 * The angle is relative to your Van's facing. So 0 is straight ahead of
	 * you.
	 * <p/>
	 * This event can be generated if another Animal hits you, in which case
	 * {@link HitRobotEvent#isMyFault() event.isMyFault()} will return
	 * {@code false}. In this case, you will not be automatically stopped by the
	 * game -- but if you continue moving toward the Animal you will hit it (and
	 * generate another event). If you are moving away, then you won't hit it.
	 *
	 * @param event
	 *            the hit-Animal event set by the game
	 * @see HitRobotEvent
	 * @see Event
	 */
	 public abstract void onHitAnimal(HitRobotEvent event);

	/**
	 * This method is called when your Van collides with a Stone. You should
	 * override it in your Van if you want to be informed of this event.
	 * <p/>
	 * Example:
	 * 
	 * <pre>
	 * void onHitStone(HitRobotEvent event) {
	 * 	if (event.getBearing() &gt; -90 &amp;&amp; event.getBearing() &lt;= 90) {
	 * 		//
	 * 		// Move back.
	 * 		//
	 * 		turnLeft(180);
	 * 		accelerate();
	 * 	} else {
	 * 		accelerate();
	 * 	}
	 * }
	 * </pre>
	 * 
	 * The angle is relative to your Van's facing. So 0 is straight ahead of
	 * you.
	 * 
	 * @param event
	 *            the hit-Stone event set by the game
	 * @see HitRobotEvent
	 * @see Event
	 */
	 public abstract void onHitStone(HitRobotEvent event);

	/**
	 * This method is called when your Van collides with a Treasure. You should
	 * override it in your Van if you want to be informed of this event.
	 * <p/>
	 * Example:
	 * 
	 * <pre>
	 * void onHitTreasure(HitRobotEvent event) {
	 * 	if (event.getBearing() &gt; -90 &amp;&amp; event.getBearing() &lt;= 90) {
	 * 		//
	 * 		// Move back.
	 * 		//
	 * 		turnLeft(180);
	 * 		accelerate();
	 * 	} else {
	 * 		accelerate();
	 * 	}
	 * }
	 * </pre>
	 * <p/>
	 * The angle is relative to your Van's facing. So 0 is straight ahead of
	 * you.
	 *
	 * @param event
	 *            the hit-Treasure event set by the game
	 * @see HitRobotEvent
	 * @see Event
	 */
	public abstract void onHitTreasure(HitRobotEvent event);

	/**
	 * This method is called when your Van collides with a Van. You should
	 * override it in your Van if you want to be informed of this event.
	 * <p/>
	 * Example:
	 * 
	 * <pre>
	 * void onHitVan(HitRobotEvent event) {
	 * 	if (event.getBearing() &gt; -90 &amp;&amp; event.getBearing() &lt;= 90) {
	 * 		//
	 * 		// Move back.
	 * 		//
	 * 		turnLeft(180);
	 * 		accelerate();
	 * 	} else {
	 * 		accelerate();
	 * 	}
	 * }
	 * </pre>
	 * <p/>
	 * The angle is relative to your Van's facing. So 0 is straight ahead of
	 * you.
	 * <p/>
	 * This event can be generated if another Van hits you, in which case
	 * {@link HitRobotEvent#isMyFault() event.isMyFault()} will return
	 * {@code false}. In this case, you will not be automatically stopped by the
	 * game -- but if you continue moving toward the Van you will hit it (and
	 * generate another event). If you are moving away, then you won't hit it.
	 *
	 * @param event
	 *            the hit-Van event set by the game
	 * @see HitRobotEvent
	 * @see Event
	 */
	public abstract void onHitVan(HitRobotEvent event);

	/**
	 * The main method in every Van. You must override this to set up your Van's
	 * basic behavior.
	 * <p/>
	 * Example:
	 * 
	 * <pre>
	 * // A basic Van that moves around in a square
	 * public void runACI() {
	 * 	while (true) {
	 * 		for (int i = 0; i &lt; 50; i++) {
	 * 			accelerate();
	 * 		}
	 * 		turnRight(90);
	 * 	}
	 * }
	 * </pre>
	 */
	public abstract void runACI();

	/**
	 * Old method that is called when your Van sees another robot.
	 * 
	 * @deprecated Use one of the following methods instead:
	 *             <p/>
	 *             {@link AbstractVan#onScannedAnimal(ScannedRobotEvent)
	 *             onScannedAnimal()}
	 *             <p/>
	 *             {@link AbstractVan#onScannedStone(ScannedRobotEvent)
	 *             onScannedStone()}
	 *             <p/>
	 *             {@link AbstractVan#onScannedTreasure(ScannedRobotEvent)
	 *             onScannedTreasure()}
	 *             <p/>
	 *             {@link AbstractVan#onScannedVan(ScannedRobotEvent)
	 *             onScannedVan()}
	 */
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
	 * there is an Animal in its range. You should override it in your Van if
	 * you want to be informed of this event.
	 * 
	 * @param event
	 *            the scanned-Animal event set by the game
	 */
	public abstract void onScannedAnimal(ScannedRobotEvent event);

	/**
	 * This method is automatically called when you scan with the radar and
	 * there is a Stone in its range. You should override it in your Van if you
	 * want to be informed of this event.
	 * 
	 * @param event
	 *            the scanned-Stone event set by the game
	 */
	public abstract void onScannedStone(ScannedRobotEvent event);

	/**
	 * This method is automatically called when you scan with the radar and
	 * there is a Treasure in its range. You should override it in your Van if
	 * you want to be informed of this event.
	 * 
	 * @param event
	 *            the scanned-Treasure event set by the game
	 */
	public abstract void onScannedTreasure(ScannedRobotEvent event);

	/**
	 * This method is automatically called when you scan with the radar and
	 * there is a Van in its range. You should override it in your Van if you
	 * want to be informed of this event.
	 * 
	 * @param event
	 *            the scanned-Van event set by the game
	 */
	public abstract void onScannedVan(ScannedRobotEvent event);

	/**
	 * Get a random warning message from the 'warningMessages' class member.
	 * 
	 * @return random warning message from 'warningMessages'.
	 */
	private String getRandomWarningMessage() {
		Random r = new Random();
		return warningMessages.get(r.nextInt(4));
	}

	/**
	 * Checks the current temperature of the Van's engine.
	 * 
	 * @return the temperature of the engine.
	 */
	public double getTemperature() {
		return engine.getTemperature();
	}

	/**
	 * Checks if the Van's engine is currently overheated.
	 * 
	 * @return true if the engine is overheated, false otherwise.
	 */
	protected boolean isVanOverheated() {
		return getTemperature() >= Engine.OVERHEAT_TEMPERATURE;
	}

	/**
	 * This method attaches the cooling system that you want to use to the Van.
	 * You may only use one cooling system.
	 * 
	 * @param coolingSystem
	 *            the cooling system you are going to attach to the the Van.
	 */
	protected void setCoolingSystem(AbstractCoolingSystem coolingSystem) {
		engine.setCoolingSystem(coolingSystem);
	}

	/**
	 * Immediately turns the Van's radar to the left by degrees. This call
	 * executes immediately, and does not return until it is complete, i.e. when
	 * the angle remaining in the Van's turn is 0. Note that both positive and
	 * negative values can be given as input, where negative values means that
	 * the Van's radar is set to turn right instead of left.
	 * 
	 * @param degrees
	 *            the amount of degrees to turn the Van's radar to the left. If
	 *            degrees > 0 the Van's radar will turn left. If degrees < 0 the
	 *            Van's radar will turn right. If degrees = 0 the Van's radar
	 *            will not turn, but execute.
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
	 * Immediately turns the Van's radar to the right by degrees. This call
	 * executes immediately, and does not return until it is complete, i.e. when
	 * the angle remaining in the Van's turn is 0. Note that both positive and
	 * negative values can be given as input, where negative values means that
	 * the Van's radar is set to turn left instead of right.
	 * 
	 * @param degrees
	 *            the amount of degrees to turn the Van's radar to the right. If
	 *            degrees > 0 the Van's radar will turn right. If degrees < 0
	 *            the Van's radar will turn left. If degrees = 0 the Van's radar
	 *            will not turn, but execute.
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
	 * Immediately moves your Van ahead.
	 * 
	 * @deprecated Use {@link AbstractVan#accelerate() accelerate()} instead.
	 */
	@Override
	@Deprecated
	public final void ahead(double distance) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Immediately moves your Van back.
	 * 
	 * @deprecated Turn the Van 180 degrees and use
	 *             {@link AbstractVan#accelerate() accelerate()} instead.
	 */
	@Override
	@Deprecated
	public final void back(double distance) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not fire bullets.
	 */
	@Override
	@Deprecated
	public final void fire(double power) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not fire bullets.
	 */
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

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final double getGunCharge() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final double getGunCoolingRate() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final double getGunHeading() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final double getGunHeat() {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final String getGunImageName() {
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

	/**
	 * Sets all the robot's color to the same color in the same time, i.e. the
	 * color of the body, radar and scan arc.
	 * <p/>
	 * You may only call this method one time per battle. A {@code null}
	 * indicates the default (blue) color for the body, radar and scan arc.
	 * <p/>
	 * 
	 * <pre>
	 * Example:
	 *   // Don't forget to import java.awt.Color at the top...
	 *   import java.awt.Color;
	 *   ...
	 * 
	 *   public void runACI() {
	 *       setAllColors(Color.RED);
	 *       ...
	 *   }
	 * </pre>
	 *
	 * @param color
	 *            the new color for all the colors of the Van.
	 * @see #setBodyColor(Color)
	 * @see #setRadarColor(Color)
	 * @see #setScanColor(Color)
	 * @see Color
	 */
	@Override
	public final void setAllColors(Color color) {
		setBodyColor(color);
		setRadarColor(color);
		setScanColor(color);
	}

	/**
	 * Van does not fire bullets.
	 */
	@Override
	@Deprecated
	public final void setBulletColor(Color color) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final void setColors(Color bodyColor, Color gunColor,
			Color radarColor) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final void setColors(Color bodyColor, Color gunColor,
			Color radarColor, Color bulletColor, Color scanArcColor) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	};

	@Override
	@Deprecated
	public final void setDebugProperty(String key, String value) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final void setGunColor(Color color) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
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
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final void turnGunLeft(double degrees) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

	/**
	 * Van does not have a Gun.
	 */
	@Override
	@Deprecated
	public final void turnGunRight(double degrees) {
		throw new UnsupportedOperationException(getRandomWarningMessage());
	}

}
