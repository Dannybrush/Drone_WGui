package DroneSimulationGui;
/**
 * 
 */

/**
 * @author shsmchlr
 *
 */
public class GameDrone extends DroneObj {

	double bAngle, bSpeed;			// angle and speed of travel
	/**
	 * 
	 */
	public GameDrone() {
		// TODO Auto-generated constructor stub
	}

	/** Create game Drone, size ir ay ix,iy, moving at angle ia and speed is
	 * @param ix
	 * @param iy
	 * @param ir
	 * @param ia
	 * @param is
	 */
	public GameDrone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir);
		bAngle = ia;
		bSpeed = is;
	}

	/**
	 * checkDrone - change angle of travel if hitting wall or another Drone
	 * @param b   DroneArena
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		bAngle = b.CheckDroneAngle(x, y, rad, bAngle, DroneID);
	}

	/**
	 * adjustDrone
	 * Here, move Drone depending on speed and angle
	 */
	@Override
	protected void adjustDrone() {
		double radAngle = bAngle*Math.PI/180;		// put angle in radians
		x += bSpeed * Math.cos(radAngle);		// new X position
		y += bSpeed * Math.sin(radAngle);		// new Y position
	}
	/**
	 * return string defining Drone type
	 */
	protected String getStrType() {
		return "Game Drone";
	}

}