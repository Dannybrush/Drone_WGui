package DroneSimulationGui;

/**
 * 
 */

/**
 * @author shsmchlr
 * The Target Drone which you are aiming at
 */
public class TargetDrone extends DroneObj {
	private int score;
	/**
	 * 
	 */
	public TargetDrone() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	public TargetDrone(double ix, double iy, double ir) {
		super(ix, iy, ir);
		score = 0;
		col = 'g';
	}

	/** 
	 * checkDrone in arena 
	 * @param b DroneArena
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		if (b.checkHit(this)) score++;			// if been hit, then increase score
	}
	/**
	 * draw Drone and display score
	 */
	public void drawDrone(MyCanvas mc) {
		super.drawDrone(mc);
		mc.showInt(x, y, score);
	}

	/**
	 * adjustDrone
	 * for moving the Drone - not needed here
	 */
	@Override
	protected void adjustDrone() {
				// nothing to do at the moment...
	}
	/**
	 * return string defining Drone ... here as target
	 */
	protected String getStrType() {
		return "Target";
	}	
}