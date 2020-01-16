package RestartJava;


public abstract class BaseDrone {
	protected double x, y, rad; 			//(XY coordinates, rad = radius (and size))
	protected char col;						// (Character used to represent colour )
	static int DroneCounter = 0;			//used to give each drone a unique identifying number
	protected int DroneID; 					// The Unique Identifying number
	
	BaseDrone(){
		this(100,100,10);
	}

	public BaseDrone(double ix, double iy, double ir) {
		// TODO Auto-generated constructor stub
		x = ix;
		y = iy;
		rad = ir;
		DroneID = DroneCounter++;			// set the identifier and increment class static
		col = 'i';
	}
	////GETTERS & SETTERS 
	public double getX() { return x; }
	public double getY() { return y; }
	public double getRad() { return rad; }
		public void setXY(double nx, double ny) {
		x = nx;
		y = ny;
	}
		public int getID() {return DroneID; }
	public void drawDrone(myCanvas mc) {
		// TODO Auto-generated method stub
		mc.showCircle(x, y, rad, col);
	}
	public void updateSize(double ir){
		this.rad = this.rad + ir;
		
	}
	
	protected abstract void checkDrone(DroneColosseum b);
	protected abstract void adjustDrone();
	
	public boolean hitting(double ox, double oy, double or) {
		return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or+rad)*(or+rad);
	}		// hitting if dist between Drone and ox,oy < ist rad + or
	public boolean hitting (BaseDrone oDrone) {
		return hitting(oDrone.getX(), oDrone.getY(), oDrone.getRad());
	}
	protected String getStrType() {
		return "Drone";
	}
	
	public String toString() {
	return getStrType()+" at "+Math.round(x)+", "+Math.round(y);
}
}

