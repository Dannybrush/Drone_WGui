package RestartJava;
//the nature of code
/*
 * https://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
 * https://stackoverflow.com/questions/25468882/change-color-of-background-in-javafx-canvas
 *https://docs.oracle.com/javafx/2/canvas/jfxpub-canvas.htm
 *https://www.genuinecoder.com/save-files-javafx-filechooser/
 *https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
 *https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.Button&method=setOnAction
 *https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm#CCHGCCEG
 *https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
 * *https://beginnersbook.com/2013/12/how-to-empty-an-arraylist-in-java//
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Br016005
 *
 */

public class DroneColosseum implements Serializable {

  //  private Desktop desktop = Desktop.getDesktop();
 

	private double xSize, ySize;															// size of arena
	private ArrayList<BaseDrone> allDrones;													// array list of all Drones in arena
	public int M1,M2;																		//Used To Limit the amount of MouseControlledDrones

	/**
	 * default constructor with default size
	 */
	DroneColosseum() {this(500, 400);}														// default size

	/**
	 * Overloaded Constructor with X&Y Dimensions
	 * 
	 * @param xS - Horizontal Dimension of Drone Arena
	 * @param yS - Vertical Dimension of Drone Arena 
	 */
	public DroneColosseum(double xS, double yS) {
		// TODO Auto-generated constructor stub
		xSize = xS;																			//	Variable to store the XDim
		ySize = yS;																			//  Variable to store the YDim
			M1 =0;																			//  Current # of MouseDrones 
			M2 =0;																			//  Current # of RClickDrones 

		setAllDrones(new ArrayList<BaseDrone>());
		//allDrones = new ArrayList<DroneObj>();											// list of all Drones, initially empty
		allDrones.add(new Ex1Drone(xS/2, yS/2, 10, 45, 10));								// add game Drone
		allDrones.add(new Ex2Drone(xS/2, yS-20, 20));										// add ScoreDrone
		allDrones.add(new Ex2Drone(2*xS/3, 3*yS/4, 15));									// add ScoreDrone
		allDrones.add(new Ex3Drone(xS/2, yS/6, 10));										// add MouseDrone
		M1++;																					// increase MouseDroneCount 
		allDrones.add(new Ex4Drone(xS/3, 3*yS/4, 15, 30, 6));								// add Stealth
		allDrones.add(new Ex5Drone(50, 50, 15, 30, 6));										// add RClickDrone
		M2++;																		        	//increase RCDC 
	}

	/**
	 * getter for Xsize of DroneArena 
	 * @return xSize - The horizontal 
	 */
	public double getXSize() {return xSize;	}
	/**
	 * getter for Ysize of DroneArena 
	 * @return YSize - The Vertical 
	 */
	public double getYSize() {return ySize;}

	/**
	 * Method to iterate through the alldrones arraylist and checking through them
	 */
	public void checkDrones() {
		// TODO Auto-generated method stub
		for (BaseDrone b : allDrones) b.checkDrone(this);	// check all Drones

	}

	/**Return array of 3 random numbers which will is used for creating a random spawn and size of drone
	 * also checks to make sure no drone/obstacle in the suggested location
	 * @return RndCo[] - array of 3 random numbers (0-xSize, 0-ySize, 5 - 20)  
	 */
	private double[] getRndCo(){
		boolean ans = false;													//checkflag to make sure unique location

		double RndCo[] = {this.getXSize()/2, this.ySize/2, 30};					//default values to prevent null pointer ex,
		Random Rnd = new Random();												//randomiser 

		do{																		//Do this until uniquelocation found 
			
			System.out.println("Stuck here");													//Debugging PrintStatement 
			RndCo[0] = Rnd.nextDouble()*this.getXSize();										// generates Random X coordinate within the bounds 
			RndCo[1] = Rnd.nextDouble()*this.getYSize();										// generates Random Y coordinate within the bounds 
			RndCo[2]  = (Rnd.nextDouble() *15) +5;												// generates Random Radius within 5-20 

			for (BaseDrone b : allDrones)										// checking spawn location is not currently within use  
				if (b instanceof Ex1Drone && b.hitting(RndCo[0], RndCo[1], RndCo[2])) {ans = false;}
			// try all Drones, if GameDrone, check if hitting the target
			

		}while(ans);
		return RndCo;															// array of the 3 values

	}


	/**
	 * Method to iterate all drones in alldrones and adjust them 
	 */
	public void adjustDrones() {
		for (BaseDrone b : allDrones) b.adjustDrone();
	}
	/** method to move the drone controlled by the mouse (Left Click)
	 * @param x - New X Coordinate
	 * @param y - New Y Coordinate
	 */
	public void setPaddle(double x, double y) {							
		for (BaseDrone b : allDrones)												//iterate through list of alldrones 
			if (b instanceof Ex3Drone) b.setXY(x, y);								//check if instance of MouseControlled Drone
		System.out.println("3");																//debugging aid 
	}
	
/**getter method to return arraylist of all the drones 
 * @return ArrayList<BaseDrone> allDrones
 */
public ArrayList<BaseDrone> getAllDrones() {
		return allDrones;															//Arraylist of all drones in arena 
	}

	/**
	 * method to set arena arraylist to "allDrones"
	 * @param allDrones - array list all drone in arena
	 */
	public void setAllDrones(ArrayList<BaseDrone> allDrones) {
		this.allDrones = allDrones;
	}
	/**Method to add drones to arena using an identifying integer and a case statement to determine the type of drone, 
	 * spawning it in a random location 
	 * @param i - identifying integer representing the type of drone to add
	 */
	public void addDrone(int i ) {															//i = identifier of dronetype 
		double RndC[] = this.getRndCo();													//generates a random X Coordinate, Y Coordinate and Radius such that no collisions occur
		switch (i){																				//switch on drone identifier 
		case 1:																								// Add Game/MainDrone // 
			//	allDrones.add(new Ex1Drone(xSize/2, ySize/2, 10, 60, 5));
			allDrones.add(new Ex1Drone(RndC[0], RndC[1], RndC[2]));
			break;
		case 3:																								// Add Score Drone //
			allDrones.add(new Ex2Drone(RndC[0], RndC[1], RndC[2]));
			break;
		case 2:																								// Add Stealth Drone //
			allDrones.add(new Ex4Drone(RndC[0], RndC[1], RndC[2]));
			break;
		case 4:																								// Add Mouse Controlled Drone //
			allDrones.add(new Ex3Drone(RndC[0], RndC[1], RndC[2]));
			M1++;
			break;
		case 5:																								// Add RMouse Controlled Drone // 
			allDrones.add(new Ex5Drone(RndC[0], RndC[1], RndC[2]));
			M2++;
			break;

		default: 																							// Catch in case of error. //
			allDrones.add(new Ex1Drone(RndC[0], RndC[1], RndC[2]));}

		// TODO Auto-generated method stub

	}
	/**Method to Draw All Drones onto the canvas
	 * @param mc -  the given canvas to which the drones must be drawn upon
	 */
	public void drawArena(myCanvas mc) {
		// TODO Auto-generated method stub
		for (BaseDrone b : allDrones) b.drawDrone(mc);

	}
	
	/**
	 * Method to Check Drone Angle 
	 * @param x - X Coordinate
	 * @param y - Y Coordinate 
	 * @param rad - Radius
	 * @param ang - Current Angle 
	 * @param notID - ID of the currentdrone, used to check every other drone 
	 * @return Ans - the new angle the given drone should travel at 
	 */
	public double CheckDroneAngle(double x, double y, double rad, double ang, int notID) {
		double ans = ang;
		if (x < rad || x > xSize - rad) ans = 180 - ans;
																					// if Drone hit (tried to go through) left or right walls, set mirror angle, being 180-angle
		if (y < rad || y > ySize - rad) ans = - ans;
																					// if try to go off top or bottom, set mirror angle

		for (BaseDrone b : allDrones) 
			if (b.getID() != notID && b.hitting(x, y, rad)) ans = 180*Math.atan2(y-b.getY(), x-b.getX())/Math.PI;
																					// check all Drones except one with given id
																					// if hitting, return angle between the other Drone and this one.

		return ans;																	// return the angle
	}
	/* check if the target Drone has been hit by another Drone
	 * @param target	the target Drone
	 * @return 	true if hit
	 */
	public boolean checkHit( BaseDrone target){
		boolean ans = false;
		for (BaseDrone b : allDrones)
			if (b instanceof Ex1Drone && b.hitting(target)) ans = true;
																					// try all Drones, if GameDrone, check if hitting the target
		return ans;
	}

	/**Method to set the new position of the mouse controlled drone
	 * If drone is clicked again in the same position: increase the size of the drone 
	 * @param nx - New X Coordinate
	 * @param ny - New Y Coordinate
	 */
	public void setMCDrone(double nx, double ny){
		for (BaseDrone b: allDrones)
										
			if (b instanceof Ex3Drone) {											//only check instances involving mouse drone
				if (nx == b.getX() && ny == b.getY()){									//  if repeat occurrence of clicked position
					b.updateSize(3);												//increase the radius by 3 
				}
				b.setXY(nx, ny);}													// set Position to XY 
	}
	/**Method to alter angle of the RMouse Controlled Drone 
	 * so that it moves towards the location of the Right click of the mouse 
	 * @param nx - New X Coordinate
	 * @param ny - New Y Coordinate
	 */
	public void setMC2Drone(double nx, double ny){
		for (BaseDrone b: allDrones)

			if (b instanceof Ex5Drone) {												// Only Instances of RClick Drone 
				b.setAngle(nx, ny);														// Adjust Angle  
				((Ex5Drone) b).setSpeed(2.5);}
	}
	/**Method used to Cloak the Stealth drones
	 * used in conjunction with the animation timer; And UnCloak(); 
	 * 
	 */
	public void Cloak(){				
		for (BaseDrone b: allDrones)
			if (b instanceof Ex4Drone) {
				b.col = 'T'; }																//Set Colour To transparent  
	}

	/**
	 * Method to clear the arraylist 
	 *  also resets the Counter for both mouse controlled Drones  
	 */
	public void clearArrayList(){
		allDrones.clear();
		M1 =0;
		M2 = 0;
		}
	
	/**Method used to UnCloak the Stealth drones
	 * used in conjunction with the animation timer; And Cloak(); 
	 */
	public void UnCloak(){
		for (BaseDrone b: allDrones)
			if (b instanceof Ex4Drone) {
				b.col = 'p'; } 																//Return colour back to pink
	}

	
	/**QUICK SAVE 
	 * opens fileOutputStream & ObjectOutputStream 
	 * serializes data and writes arena to a file
	 * @param p - Arena
	 */
	public void QSaveObjectToFile(Object p){
		// save the object to file

		String filename = ("N:\\Workspace\\Part2Java\\src\\RestartJava\\TextDocs\\ConfigFile.SER");     			//Serialized File, & FilePath 
		FileOutputStream fos = null;																	//Initialised FOS 
		ObjectOutputStream out = null;																	//Initialised OOS 					
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);																	//Write	
			System.out.println("T1Passed");																	//Debugging & Testing Purposes	
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("You done fucked up");														//Debugging & Testing Purposes	

		}
		}
	
	/**Long Save 
	 * @param p -  Arena 
	 * @param fileName -  FilePath passed from filechooser 
	 * 
	 */
	public void SaveObjectToFile(Object p, String fileName ){
	
		//String filename = fileName; //"N:\\Workspace\\Part2Java\\src\\RestartJava\\TextDocs\\NewTest.ser";
	     FileOutputStream fos = null;
	     ObjectOutputStream out = null;
	    
	     try {
	         fos = new FileOutputStream(fileName);
	         out = new ObjectOutputStream(fos);
	         out.writeObject(this);

	         out.close();
	     } catch (Exception ex) {
	         ex.printStackTrace();
	         
	     }
	     // read the object from file
	     // save the object to file
	     FileInputStream fis = null;
	     ObjectInputStream in = null;
	     try {
	         fis = new FileInputStream(fileName);
	         in = new ObjectInputStream(fis);
	         p = (Object) in.readObject();
	         in.close();
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
	     System.out.println(p);}
	
	/*public void openFile(File file) {
	        try {
	            desktop.open(file);
	        } catch (IOException ex) {
	            Logger.getLogger(
	                Load.class.getName()).log(
	                    Level.SEVERE, null, ex
	                );
	        }
	    }*/
	
/**
 * Long Load 
 * @param p - Arena 
 * @param f - FilePath 
 * @return
 */
public Object LoadObjectToFile(Object p, File f ){
	//String filename = f;

	// read the object from file
	// save the object to file
	FileInputStream fis = null;
	ObjectInputStream in = null;
	try {
		fis = new FileInputStream(f);
		in = new ObjectInputStream(fis);
		p = (Object) in.readObject();
		in.close();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	System.out.println(p);
	return p;

}



/**QUICK LOAD 
 * @param p - Arena 
 * @return - Read File ->  Arena 
 */
public Object QLoadObjectToFile(Object p){
	  
		String filename = "N:\\Workspace\\Part2Java\\src\\RestartJava\\TextDocs\\ConfigFile.SER";		//ConfogFile to Load 
		//Object p = null;
																										// read the object from file
																										// save the object to file
		FileInputStream fis = null;																	// Initialise FIS 
		ObjectInputStream in = null;																// Initialise OIS
		try {
			System.out.println(filename);																	// Debugging & Testing Purposes 
			fis = new FileInputStream(filename);													//Read the File  
			in = new ObjectInputStream(fis);
			p = (Object) in.readObject();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(p);
		return p;																					//Return the arena 

	}
	/**Describes all of the drones in the arraylist in the arena using their own built in function 
	 * @return arraylist of strings which can be outputted
	 */
	public ArrayList<String> describeAll() {
		// TODO Auto-generated method stub
		ArrayList<String> ans = new ArrayList<String>();								// set up empty arraylist
		for (BaseDrone b : allDrones) ans.add(b.toString());							// add string defining each Drone
		return ans;																		// return string list

	}

	/**Setter for arena xSize
	 * @param i - xSize
	 */
	public void SetxSize(int i) {
		// TODO Auto-generated method stub
		xSize = i;
	}
	
	/**Setter for arena ySize
	 * @param i - ySize
	 */
	public void SetySize(int i) {
		// TODO Auto-generated method stub
		ySize = i;
	}
}
