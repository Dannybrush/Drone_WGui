package RestartJava;

public class Ex4Drone extends Ex1Drone {

	public Ex4Drone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir, ia, is);
		col = 'p';
		// TODO Auto-generated constructor stub
		
			}
	public Ex4Drone(double ix, double iy, double ir){
		this(ix, iy, ir, 45, 10);
	}
	public Ex4Drone(){
		this(20.0, 20.0, 10.0, 45.0, 10.0);
	}	
protected String getStrType() {
		return "StealthDrone";
	}

}
