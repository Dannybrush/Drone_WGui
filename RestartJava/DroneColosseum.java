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


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class DroneColosseum implements Serializable {

	private double xSize, ySize;						// size of arena
	private ArrayList<BaseDrone> allDrones;			// array list of all Drones in arena
	public int M1,M2;

	DroneColosseum() {this(500, 400);}			// default size

	public DroneColosseum(double xS, double yS) {
		// TODO Auto-generated constructor stub
		xSize = xS;
		ySize = yS;
			M1 =0;
			M2 =0;

		setAllDrones(new ArrayList<BaseDrone>());
		//allDrones = new ArrayList<DroneObj>();					// list of all Drones, initially empty
		allDrones.add(new Ex1Drone(xS/2, yS/2, 10, 45, 10));	// add game Drone
		allDrones.add(new Ex2Drone(xS/2, yS-20, 20));		// add ScoreDrone
		allDrones.add(new Ex2Drone(2*xS/3, 3*yS/4, 15));	// add ScoreDrone
		allDrones.add(new Ex3Drone(xS/2, yS/6, 10));			// add MouseDrone
		M1++;
		allDrones.add(new Ex4Drone(xS/3, 3*yS/4, 15, 30, 6));		// add Stealth
		allDrones.add(new Ex5Drone(50, 50, 15, 30, 6));
		M2++;
	}

	public double getXSize() {return xSize;	}
	public double getYSize() {return ySize;}

	public void checkDrones() {
		// TODO Auto-generated method stub
		for (BaseDrone b : allDrones) b.checkDrone(this);	// check all Drones

	}

	private double[] getRndCo(){
		boolean ans = false;

		double RndCo[] = {this.getXSize()/2, this.ySize/2, 30};
		Random Rnd = new Random();

		do{
			
			System.out.println("Stuck here");
			RndCo[0] = Rnd.nextDouble()*this.getXSize();
			RndCo[1] = Rnd.nextDouble()*this.getYSize();
			RndCo[2]  = (Rnd.nextDouble() *15) +5;

			for (BaseDrone b : allDrones)
				if (b instanceof Ex1Drone && b.hitting(RndCo[0], RndCo[1], RndCo[2])) {ans = false;}
			// try all Drones, if GameDrone, check if hitting the target
			

		}while(ans);
		return RndCo;

	}


	public void adjustDrones() {
		for (BaseDrone b : allDrones) b.adjustDrone();
	}
	public void setPaddle(double x, double y) {
		for (BaseDrone b : allDrones)
			if (b instanceof Ex3Drone) b.setXY(x, y);
		System.out.println("3");
	}
	public ArrayList<BaseDrone> getAllDrones() {
		return allDrones;
	}
	public void setAllDrones(ArrayList<BaseDrone> allDrones) {
		this.allDrones = allDrones;
	}
	public void addDrone(int i ) {
		double RndC[] = this.getRndCo();
		switch (i){
		case 1:
			//	allDrones.add(new Ex1Drone(xSize/2, ySize/2, 10, 60, 5));
			allDrones.add(new Ex1Drone(RndC[0], RndC[1], RndC[2]));
			break;
		case 3:
			allDrones.add(new Ex2Drone(RndC[0], RndC[1], RndC[2]));
			break;
		case 2:
			allDrones.add(new Ex4Drone(RndC[0], RndC[1], RndC[2]));
			break;
		case 4:
			allDrones.add(new Ex3Drone(RndC[0], RndC[1], RndC[2]));
			M1++;
			break;
		case 5:
			allDrones.add(new Ex5Drone(RndC[0], RndC[1], RndC[2]));
			M2++;
			break;

		default:
			allDrones.add(new Ex1Drone(RndC[0], RndC[1], RndC[2]));}

		// TODO Auto-generated method stub

	}
	public void drawArena(myCanvas mc) {
		// TODO Auto-generated method stub
		for (BaseDrone b : allDrones) b.drawDrone(mc);

	}
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

		return ans;		// return the angle
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

	public void setMCDrone(double nx, double ny){
		for (BaseDrone b: allDrones)

			if (b instanceof Ex3Drone) {
				if (nx == b.getX() && ny == b.getY()){
					b.updateSize(3);
				}
				b.setXY(nx, ny);}
	}
	public void setMC2Drone(double nx, double ny){
		for (BaseDrone b: allDrones)

			if (b instanceof Ex5Drone) {
				b.setAngle(nx, ny);
				((Ex5Drone) b).setSpeed(2.5);}
	}
	public void Cloak(){
		for (BaseDrone b: allDrones)
			if (b instanceof Ex4Drone) {
				b.col = 'T'; }
	}

	public void clearArrayList(){
		allDrones.clear();
		M1 =0;
		M2 = 0;
		}
	public void UnCloak(){
		for (BaseDrone b: allDrones)
			if (b instanceof Ex4Drone) {
				b.col = 'p'; }
	}
	public void SaveObjectToFile(Object p ){
		// save the object to file

		String filename = "N:\\Workspace\\Part2Java\\src\\RestartJava\\TextDocs\\nextTest.ser";
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);

			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}}
	public Object LoadObjectToFile(Object p ){
		String filename = "N:\\Workspace\\Part2Java\\src\\RestartJava\\TextDocs\\nextTest.ser";

		// read the object from file
		// save the object to file
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			p = (Object) in.readObject();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(p);
		return p;

	}
	public ArrayList<String> describeAll() {
		// TODO Auto-generated method stub
		ArrayList<String> ans = new ArrayList<String>();		// set up empty arraylist
		for (BaseDrone b : allDrones) ans.add(b.toString());			// add string defining each Drone
		return ans;												// return string list

	}

	public void SetxSize(int i) {
		// TODO Auto-generated method stub
		xSize = i;
	}
	public void SetySize(int i) {
		// TODO Auto-generated method stub
		ySize = i;
	}
}
