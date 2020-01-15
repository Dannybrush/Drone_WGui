package RestartJava;

//GameDrone 
public class Ex1Drone extends BaseDrone{
	double bAngle, bSpeed ;
	 
	public Ex1Drone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir);
		bAngle = ia;
		bSpeed = is;
	}
}
