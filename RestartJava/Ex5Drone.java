package RestartJava;
//MouseControlled 2

public class Ex5Drone extends Ex1Drone{
	private double targetx, targety;

	/**
	 * Set up the paddle controlled by the user
	 */
	public Ex5Drone() {
		// TODO Auto-generated constructor stub
	}

	/**Set paddle Drone size ir at ix,iy
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	public Ex5Drone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir, ia, is );
		
		col = 'o';
	}

	public Ex5Drone(double ix, double iy, double ia) {
		x = ix;
		y = iy;
		rad = 15;
		bAngle = ia;
		bSpeed = 2.5;
		col = 'b';
	}

	public double getAngle(){
		return bAngle;
	}

	public void setSpeed(double is){
		bSpeed = is;
	}
@Override
	public void setAngle(double ix, double iy){
		double calcx, calcy;

		targetx = Math.round(ix);
		targety = Math.round(iy);

		calcx = ix-x;
		calcy = iy-y;

		bAngle = Math.toDegrees(Math.atan2(calcy, calcx));
	}

	
	@Override
	protected void adjustDrone() {
		double xTol, yTol;

		double radAngle = bAngle*Math.PI/180; // put angle in radians
		x += bSpeed * Math.cos(radAngle); // new X position
		y += bSpeed * Math.sin(radAngle); // new Y position
		if (x<targetx){
			xTol = (Math.abs(x/targetx));
		}else{
			xTol = (Math.abs(targetx/x));
		}
		if (y<targety){
			yTol = (Math.abs(y/targety));
		}else{
			yTol = (Math.abs(targety/y));
		}
		if (xTol > .98 && yTol > .98){
			bSpeed = 0;
		}
	}
	/**
	 *  return string description as paddle
	 */
	protected String getStrType() {
		return "RMouseControlled Drone ";
	}
}
