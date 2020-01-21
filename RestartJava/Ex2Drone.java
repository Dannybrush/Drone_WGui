package RestartJava;



//ScoreDrone
public class Ex2Drone extends BaseDrone {
	//double bAngle, bSpeed ;
	private int score;
		
	//ONLY NEEDED FOR MOVING DRONES
	/*public Ex2Drone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir);
		bAngle = ia;
		bSpeed = is;
	}*/
	
	public Ex2Drone(double ix, double iy, double ir) {
		super(ix, iy, ir);
		col = 'b';
		score = 0;
		
		// TODO Auto-generated constructor stub
	}
/*	public Ex2Drone(double ix, double iy ){
	new Ex2Drone(ix, iy, 10);}
	*/
	public Ex2Drone(){
	this(20.0, 20.0, 10.0);
	}
protected void checkDrone(DroneColosseum b) {
	if (b.checkHit(this)) score++;			// if been hit, then increase score
}
/**
 * draw Drone and display score
 */
public void drawDrone(myCanvas mc) {
	super.drawDrone(mc);
	mc.showInt(x, y, score);
}
	@Override
	protected void adjustDrone() {
		// TODO Auto-generated method stub
		
	}
	protected String getStrType() {
		return "ScoreDrone";
	}	
}