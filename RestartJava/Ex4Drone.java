package RestartJava;

public class Ex4Drone extends Ex1Drone {

	public Ex4Drone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir, ia, is);
		col = 'p';
		// TODO Auto-generated constructor stub
		
			}
	protected String getStrType() {
		return "StealthDrone";
	}

}
