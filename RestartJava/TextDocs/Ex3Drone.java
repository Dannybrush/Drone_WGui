package RestartJava;
//MOUSE-CONTROLLED
public class Ex3Drone extends BaseDrone{

	public Ex3Drone(){}
	public Ex3Drone(double ix, double iy, double ir) {
		super(ix, iy, ir);
		col = 'w';
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void checkDrone(DroneColosseum b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void adjustDrone() {
		// TODO Auto-generated method stub
		
	}
	protected String getStrType() {
		return "Mouse Controlled";
	}	
	
}
