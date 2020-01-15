package DroneSimulationGui;

/**
 * 
 */


/**
 * @author shsmchlr
 * Drone which gets in way of game Drone
 */
public class BlockerDrone extends DroneObj {

	/**
	 * 
	 */
	public BlockerDrone() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	public BlockerDrone(double ix, double iy, double ir) {
		super(ix, iy, ir);
		col = 'o';
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
	protected String getStrType() {
		return "Blocker";
	}	

}