package DroneSimulationGui;

/**
 * 
 */

import java.util.ArrayList;

/**
 * @author 27016005 (_G56_)
 * Class for Arena of Drones
 */
public class DroneArena {	
	double xSize, ySize;						// size of arena
	private ArrayList<DroneObj> allDrones;			// array list of all Drones in arena
	/**
	 * construct an arena
	 */
	DroneArena() {
		this(500, 400);			// default size
	}
	/**
	 * construct arena of size xS by yS
	 * @param xS
	 * @param yS
	 */
	DroneArena(double xS, double yS){
		xSize = xS;
		ySize = yS;
		allDrones = new ArrayList<DroneObj>();					// list of all Drones, initially empty
		allDrones.add(new GameDrone(xS/2, yS/2, 10, 45, 10));	// add game Drone
		allDrones.add(new TargetDrone(xS/2, 30, 15));			// add target Drone
		allDrones.add(new PaddleDrone(xS/2, yS-20, 20));		// add paddle
		allDrones.add(new BlockerDrone(xS/3, yS/4, 15));		// add blocker
		allDrones.add(new BlockerDrone(2*xS/3, yS/4, 15));	// add blocker
	}
	/**
	 * return arena size in x direction
	 * @return
	 */
	public double getXSize() {
		return xSize;
	}
	/**
	 * return arena size in y direction
	 * @return
	 */
	public double getYSize() {
		return ySize;
	}
	/**
	 * draw all Drones in the arena into interface bi
	 * @param bi
	 */
	public void drawArena(MyCanvas mc) {
		for (DroneObj b : allDrones) b.drawDrone(mc);		// draw all Drones
	}
	/**
	 * check all Drones .. see if need to change angle of moving Drones, etc 
	 */
	public void checkDrones() {
		for (DroneObj b : allDrones) b.checkDrone(this);	// check all Drones
	}
	/**
	 * adjust all Drones .. move any moving ones
	 */
	public void adjustDrones() {
		for (DroneObj b : allDrones) b.adjustDrone();
	}
	/** 
	 * set the paddle Drone at x,y
	 * @param x
	 * @param y
	 */
	public void setPaddle(double x, double y) {
		for (DroneObj b : allDrones)
			if (b instanceof PaddleDrone) b.setXY(x, y);
	}
	/**
	 * return list of strings defining each Drone
	 * @return
	 */
	public ArrayList<String> describeAll() {
		ArrayList<String> ans = new ArrayList<String>();		// set up empty arraylist
		for (DroneObj b : allDrones) ans.add(b.toString());			// add string defining each Drone
		return ans;												// return string list
	}
	/** 
	 * Check angle of Drone ... if hitting wall, rebound; if hitting Drone, change angle
	 * @param x				Drone x position
	 * @param y				y
	 * @param rad			radius
	 * @param ang			current angle
	 * @param notID			identify of Drone not to be checked
	 * @return				new angle 
	 */
	public double CheckDroneAngle(double x, double y, double rad, double ang, int notID) {
		double ans = ang;
		if (x < rad || x > xSize - rad) ans = 180 - ans;
			// if Drone hit (tried to go through) left or right walls, set mirror angle, being 180-angle
		if (y < rad || y > ySize - rad) ans = - ans;
			// if try to go off top or bottom, set mirror angle
		
		for (DroneObj b : allDrones) 
			if (b.getID() != notID && b.hitting(x, y, rad)) ans = 180*Math.atan2(y-b.getY(), x-b.getX())/Math.PI;
				// check all Drones except one with given id
				// if hitting, return angle between the other Drone and this one.
		
		return ans;		// return the angle
	}

	/**
	 * check if the target Drone has been hit by another Drone
	 * @param target	the target Drone
	 * @return 	true if hit
	 */
	public boolean checkHit(DroneObj target) {
		boolean ans = false;
		for (DroneObj b : allDrones)
			if (b instanceof GameDrone && b.hitting(target)) ans = true;
				// try all Drones, if GameDrone, check if hitting the target
		return ans;
	}
	
	public void addDrone() {
		allDrones.add(new GameDrone(xSize/2, ySize/2, 10, 60, 5));
	}
}