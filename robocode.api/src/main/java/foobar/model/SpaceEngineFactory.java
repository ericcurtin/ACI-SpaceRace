package foobar.model;

/**
 * SpaceEngineFactory creates SpaceEngine instances.
 * 
 * @author Eric Curtin (original)
 * @author Pablo Rodriguez (contributor)
 */
public final class SpaceEngineFactory {
	public static SpaceEngine getChallengerSpaceShip() {
		return new Challenger();
	}

	public static SpaceEngine getBuranSpaceShip() {
		return new Buran();
	}

	public static SpaceEngine getAtlantisSpaceShip() {
		return new Atlantis();
	}
}
