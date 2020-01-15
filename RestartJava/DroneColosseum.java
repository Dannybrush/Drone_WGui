package RestartJava;

import java.util.ArrayList;



public class DroneColosseum {
	
		double xSize, ySize;						// size of arena
		private ArrayList<BaseDrone> allDrones;			// array list of all Drones in arena
		
		DroneColosseum() {
			this(500, 400);			// default size
		}

		public DroneColosseum(double xS, double yS) {
			// TODO Auto-generated constructor stub
			xSize = xS;
			ySize = yS;
			setAllDrones(new ArrayList<BaseDrone>());
		}
		public double getXSize() {return xSize;	}
		public double getYSize() {return ySize;}

		public void checkDrones() {
			// TODO Auto-generated method stub
			
		}

		public void adjustDrones() {
			// TODO Auto-generated method stub
			
		}

		public ArrayList<BaseDrone> getAllDrones() {
			return allDrones;
		}
		public void setAllDrones(ArrayList<BaseDrone> allDrones) {
			this.allDrones = allDrones;
		}
		public void addDrone() {
			// TODO Auto-generated method stub
			
		}
		public void drawArena(myCanvas mc) {
			// TODO Auto-generated method stub
			for (BaseDrone b : allDrones) b.drawDrone(mc);
			
		}

		public ArrayList<String> describeAll() {
			// TODO Auto-generated method stub
			ArrayList<String> ans = new ArrayList<String>();		// set up empty arraylist
			for (BaseDrone b : allDrones) ans.add(b.toString());			// add string defining each Drone
			return ans;												// return string list
		
		}
		
}
