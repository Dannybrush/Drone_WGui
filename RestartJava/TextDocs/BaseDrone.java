package RestartJava;

import java.io.Serializable;

public abstract class BaseDrone  implements Serializable {
	protected double x, y, rad; 			//(XY coordinates, rad = radius (and size))
	protected char col;						// (Character used to represent colour )
	static int DroneCounter = 0;			//used to give each drone a unique identifying number
	protected int DroneID; 					// The Unique Identifying number
	
	/**
	 * default constructor for BaseDrone, calling the overloaded constructor to initialise with defaults of
	 * x = 100; 
	 * y = 100;
	 * r = 10;
	 */
	BaseDrone(){
		this(100,100,10);
	}

	/**OverLoaded Constructor 
	 * @param ix - X Coordinate 
	 * @param iy - Y Coordinate 
	 * @param ir - Radius 
	 */
	public BaseDrone(double ix, double iy, double ir) {
		// TODO Auto-generated constructor stub
		x = ix;																// X Coordinate
		y = iy;																// Y Coordinate
		rad = ir;															// Radius 
		DroneID = DroneCounter++;											// set the identifier and increment class static
		col = 'i';															// Colour of drone 
	}
	
	////GETTERS & SETTERS 
	
	/**Getter Method for X
	 * @return X coordinate
	 */
	public double getX() { return x; }
	/**Getter Method for Y
	 * @return Y coordinate
	 */
	public double getY() { return y; }
	/**Getter Method for Radius
	 * @return Radius 
	 */
	public double getRad() { return rad; }
	
	/** Setter for X & Y Coordinates 
	 * @param nx -  New X Coordinate 
	 * @param ny - New Y Coordinate 
	 */
	public void setXY(double nx, double ny) {
		x = nx;
		y = ny;
	}
	
	/** Getter Method for DroneID                          
	 * @return DroneID 
	 */
	public int getID() {return DroneID; }
	/**Method to Draw the Drone / Circele on the canvas 
	 * @param mc - said Canvas 
	 */
	public void drawDrone(myCanvas mc) {
		// TODO Auto-generated method stub
		mc.showCircle(x, y, rad, col);
	}
	/**Method to set/Change the radius of a drone 
	 * @param ir - Amount to increase the Radius by 
	 */
	public void updateSize(double ir){
		this.rad = this.rad + ir;
		
	}
	
	/**Abstract class to be inherited by all of the child classes
	 * used to check the drones accordingly
	 * @param b - Arena 
	 */
	protected abstract void checkDrone(DroneColosseum b);
	
	/**Abstract class to be inherited and extended by the child classes 
	 * will be used to adjust the drones accordingly 
	 */
	protected abstract void adjustDrone();
	/**Abstract class to be inherited and extended by the child classes 
	 * Will be used to adjust the angle 
	 * @param ix - input x 
	 * @param iy - input y
	 */
	protected void setAngle(double ix, double iy){}
	
/**boolean to determine whether a drone would be hitting a object or wall if it moved to this location
 * @param ox - XCoordinate
 * @param oy - YCoordinate
 * @param or - Radius
 * @return True if hitting, False if not
 */
public boolean hitting(double ox, double oy, double or) {
		return (ox-x)*(ox-x) + (oy-y)*(oy-y) <= (or+rad)*(or+rad);
	}		// hitting if dist between Drone and ox,oy < ist rad + or
	/**boolean to determine whether a drone would be hitting another drone if it moved to this location
	 * @param oDrone - Possible Drone to be colliding with 
	 * @return True if hitting /  false if missing 
	 */
	public boolean hitting (BaseDrone oDrone) {
		return hitting(oDrone.getX(), oDrone.getY(), oDrone.getRad());
	}
	/**Get the string for the type of drone
	 * @return string containing the type of drone
	 */
	protected String getStrType() {
		return "Drone";
	}
	
	
	/** Method to show string
	 * Returns string with coordinates
	 */
	public String toString() {
	return (getStrType()+" at "+Math.round(x)+", "+Math.round(y));
}
}

