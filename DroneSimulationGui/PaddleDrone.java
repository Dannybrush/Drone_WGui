package DroneSimulationGui;

/**
 * 
 */

/**
 * @author shsmchlr
 *
 */
public class PaddleDrone extends DroneObj {

	/**
	 * Set up the paddle controlled by the user
	 */
	public PaddleDrone() {
		// TODO Auto-generated constructor stub
	}

	/**Set paddle Drone size ir at ix,iy
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	public PaddleDrone(double ix, double iy, double ir) {
		super(ix, iy, ir);
		col = 'b';
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see uk.ac.reading.profrichardmitchell83.Drone#checkDrone(uk.ac.reading.profrichardmitchell83.DroneArena)
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		// nowt to do
	}

	/* (non-Javadoc)
	 * @see uk.ac.reading.profrichardmitchell83.Drone#adjustDrone()
	 */
	@Override
	protected void adjustDrone() {
		// nowt to do 
	}
	/**
	 *  return string description as paddle
	 */
	protected String getStrType() {
		return "Paddle";
	}	
}