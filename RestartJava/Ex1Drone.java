package RestartJava;

//GameDrone - MAIN DRONE
public class Ex1Drone extends BaseDrone{
	double bAngle, bSpeed ;
	 
	public Ex1Drone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir);
		bAngle = ia;
		bSpeed = is;
	}
	@Override
	protected void checkDrone(DroneColosseum b) {
		bAngle = b.CheckDroneAngle(x, y, rad, bAngle, DroneID);
	}
	@Override
	protected void adjustDrone() {
		double radAngle = bAngle*Math.PI/180;		// put angle in radians
		x += bSpeed * Math.cos(radAngle);		// new X position
		y += bSpeed * Math.sin(radAngle);		// new Y position
	}
	protected String getStrType() {
		return "Main Drone";
	}
}
