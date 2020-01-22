package RestartJava;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Br016005
 *
 */
public class DroneGUI extends Application{ 

	private myCanvas mc;										//
	private AnimationTimer timer;								// timer used for animation
	private VBox rtPane;										// vertical box for putting info
	private DroneColosseum arena;
	private long startTime = System.currentTimeMillis();
	/**
	 * @return XY 
	 */
	private  int[]  showMsg() {
		int[] XY = {512,512};
		TextInputDialog alert = new TextInputDialog("test");				// define what box is
		TextInputDialog alert2 = new TextInputDialog("test2");	
		alert.setTitle("X dimensions");									// say is About
		alert.setHeaderText(null);
		alert.setContentText("What is the maximum horizontal dimension of the canvas");// give text
		alert2.setTitle("Y dimensions");									// say is About
		alert2.setHeaderText(null);
		alert2.setContentText("What is the maximum Vertical dimension of the canvas");// give text
		Optional<String> result = alert.showAndWait();
		Optional<String> result2 = alert2.showAndWait();


		if (result.isPresent()&&result2.isPresent()){ 

			//System.out.println("Ni Hao");
			System.out.println(Integer.valueOf(result.get()));
			XY[0] = Integer.valueOf(result.get());
			//System.out.println(XY[0]);
			XY[1] = Integer.valueOf(result2.get());
			return XY;
		} 
		alert.showAndWait();										// show box and wait for user to close
		return XY;
	}
	/**
	 * Method to display a msgbox containing information about the program
	 */
	private  void showAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);						// define what box is
		alert.setTitle("About");											// say is About
		alert.setHeaderText(null);
		alert.setContentText("Brush's JavaFX DRone Simulation - ");			// give text
		alert.showAndWait();												// show box and wait for user to close
	}

	/**Method for handling when the mouse is clicked, 
	 * including when the secondary button is pressed. 
	 * @param canvas
	 */
	void setMouseEvents(Canvas canvas){

		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,								//EventHandler 
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.isSecondaryButtonDown()){											//If Secondary button
					arena.setMC2Drone(e.getX(), e.getY());
				}else{																	// If Primary Button 
					arena.setMCDrone(e.getX(), e.getY());
				}

				drawWorld();															// redraw world
				drawStatus();
			}


		});

	}
	/**Creating the MenuBar with Menus, Submenus and Items 
	 * @return The Full Menu 
	 * menu 
	 * 	file
	 * 		exit
	 * 		save
	 * 		QSave
	 * 		QLoad
	 * 		Load	
	 * 	About
	 * 		Help
	 * 	New
	 * 		MainDrone
	 * 		StealthDrone
	 * 		ScoreDrone
	 * 		MouseControlled
	 * 		RMouseDrone
	 * 		
	 */
	MenuBar setMenu() {

		MenuBar menuBar = new MenuBar();		 													// create main menu

		Menu mFile = new Menu("File");																// add File main menu
		MenuItem mExit = new MenuItem("Exit");														// whose sub menu has Exit
		mExit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));								// Add CTRL + E Shortcut 
		mExit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {																// action on exit is
				timer.stop();																				// stop timer
				System.exit(0);																				// exit program
			}
		});
		mFile.getItems().addAll(mExit);																// add exit to File menu

		MenuItem mSave = new MenuItem("Save");														// whose sub menu has Save 
		mSave.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+S"));						// Add CTRL + SHIFT + S Shortcut 
		mSave.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {																// action on Save is
				timer.stop();																				// stop timer
				Stage SaveStage = null;																	// New Stage
				  FileChooser fileChooser = new FileChooser();											// Initialising new FileChooser 
				    String pathOfTheCurrentClass = this.getClass().getResource(".").getPath();			// Obtaining the FilePath of the current Program
						  File filePath = new File(pathOfTheCurrentClass+"/TextDocs/");					// navigating to the TextDocs file
				           //  new File(File.getParentFile())
						  
				  fileChooser.setInitialDirectory(filePath); 											// setting the initial directory for the fileChooser
		            //Set extension filter for text files
		           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER File (*.ser)","*SER"); // Saving as an SER (Serializable )
		           fileChooser.getExtensionFilters().add(extFilter);
		 
		            //Show save file dialog 
		            File file = fileChooser.showSaveDialog(SaveStage);									// standard save dialogbox
		  
		            if (file != null) {																	// error handling (-Making sure filename was created-)
		            	 String file_name = file.toString();
		            	 
				        	if (!file_name.endsWith(".SER")){											// if file extension not added - Then Add
					            file_name += ".SER";}
					        System.out.print(file_name);												// Testing & Debugging Purposes
		            	System.out.println("I WANT TO DIE");											// Testing & Debugging Purposes
		               arena.SaveObjectToFile(this, file_name);							// Save program						
		               System.out.println("SAVE SUCCESS#");												// Testing & Debugging Purposes
		            }else{
		            	System.out.println("CATASTOPHIC FAILURE");										// Testing & Debugging Purposes
		            }
		          /* SaveStage.setScene(new Scene(vBox, 800, 300));*/
		            //SaveStage.setTitle("TestMyOG1SaveFunction");
		           // SaveStage.show();
		     
				
			}
		});
		mFile.getItems().addAll(mSave);																	// AddSave to Menu 
		MenuItem mQSave = new MenuItem("QSave");														// QuickSave
		mQSave.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));									// Shortcut = CTRL + S
		mQSave.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {															// action on QSave 
				timer.stop();																			// stop timer
				 
		               arena.QSaveObjectToFile(this);								// Save program
		               System.out.println("QSAVE SUCCESS#");											// Testing & Debugging Purposes
		       
			}
		});
		mFile.getItems().addAll(mQSave);																// Add QSave to menu 

		MenuItem mQLoad = new MenuItem("QLoad");														// whose sub menu has QLoad

		mQLoad.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));									// Shortcut = CTRL + L
		mQLoad.setOnAction(new EventHandler<ActionEvent>() {									
			public void handle(ActionEvent t) {															// action on QLoad is
				arena = (DroneColosseum) arena.QLoadObjectToFile(arena);								// Load Saved program from Hardcoded file
			}
		});
		mFile.getItems().addAll(mQLoad);																// Add QLoad to menu 

		MenuItem mLoad = new MenuItem("Load");															// whose sub menu has Load

		mLoad.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+L"));							//Shortcut = CTRL + SHIFT + L
		mLoad.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent t) {															// action on Load is
									
				
				 FileChooser fileChooser = new FileChooser();											// Load FileChooser
				    String pathOfTheCurrentClass = this.getClass().getResource(".").getPath();			// Obtain Current FilePath 
						  File filePath = new File(pathOfTheCurrentClass+"/TextDocs/");					// Navigating to TextDocs
				           //  new File(File.getParentFile())
						  
				  fileChooser.setInitialDirectory(filePath); 											// Start FileChooser in TextDocs Dir 
				  								//Set extension filter for text files
		           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER File (*.ser)","*SER"); // Initially only show SER files
		           fileChooser.getExtensionFilters().add(extFilter);
		           FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("ANY (*.*)","*"); // Give Option to View All files 
		           fileChooser.getExtensionFilters().add(extFilter1);
		           Stage loadStage = null;
			        File file = fileChooser.showOpenDialog(loadStage);											// FileChooser Dialogbox
			      
			      
			        if (file != null) {																			// File Exists 
			        	System.out.println("T2Passed");															// Testing & Debugging 
			        	System.out.println(file);
			          arena = (DroneColosseum) arena.LoadObjectToFile(arena,file);								// Load From Retrieved Filename 
			        }else{System.out.println("You done fucked up3");											// Testing & Debugging 
			        }
			   	    }
			
			
		});
		mFile.getItems().addAll(mLoad);																			// Add Load to Menu 

		MenuItem mClear = new MenuItem("Clear");																// New Menu Item Clear 
		mClear.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));											// Shortcut = CTRL + X
		mClear.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				arena.clearArrayList(); 																		// On Press - Clear Arraylist & Canvas 
				//	M1 =0;
				//	M2=0;
				
			}
		});
		mFile.getItems().addAll(mClear);																		// add Clear to menu 

		Menu mHelp = new Menu("Help");																			// create Help menu
		MenuItem mAbout = new MenuItem("About");																// add About sub men item
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showAbout();																					// and its action to print about
			}	
		});
		mHelp.getItems().addAll(mAbout);																		// add About to Help main item

		Menu mNew = new Menu("New");																			// New Menu item -> Submenu NEW 

		//Sub-Menu
		Menu subMenu_s1 = new Menu("Add New Drone");															// Add new Drone
		MenuItem subMenuItem1 = new MenuItem("BasicDrone");														// Add Main Drone
		subMenuItem1.setAccelerator(KeyCombination.keyCombination("Ctrl+1"));										// Shortcut (CTRL + 1) 
		MenuItem subMenuItem2 = new MenuItem("StealthDrone");													// Add Stealth Drone  
		subMenuItem2.setAccelerator(KeyCombination.keyCombination("Ctrl+2"));										//  Shortcut (CTRL + 2)
		MenuItem subMenuItem3 = new MenuItem("ScoreDrone");														// Add StealthDrone
		subMenuItem3.setAccelerator(KeyCombination.keyCombination("Ctrl+3"));										//  Shortcut (CTRL + 3)
		MenuItem subMenuItem4 = new MenuItem("MouseDrone");														// Add MouseDrone (LB)
		
	/*	if (arena.M1 >= 0){subMenuItem4.setDisable(true);}
		else {subMenuItem4.setDisable(false);}*/
		
		subMenuItem4.setAccelerator(KeyCombination.keyCombination("Ctrl+4"));										// Shortcut (CTRL + 4)
		MenuItem subMenuItem5 = new MenuItem("M2Drone");														// Add RMouseDrone (RC)
		
		
		subMenuItem5.setAccelerator(KeyCombination.keyCombination("Ctrl+5"));											// Shortcut (CTRL + 5)
		
		/*if (arena.M2 < 0){subMenuItem5.setDisable(true);}
		else {subMenuItem5.setDisable(false);}*/
		arena.addDrone(5);																						// Add Drone to start with 
		
		MenuItem subMenuItem6 = new MenuItem("Canvas");																		
		subMenuItem6.setAccelerator(KeyCombination.keyCombination("Ctrl+6"));										
		
		subMenuItem1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				arena.addDrone(1);																				// and its action to print about
			}	
		});
		subMenuItem2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				arena.addDrone(2);																					// and its action to print about
			}	
		});
		subMenuItem3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				arena.addDrone(3);																					// and its action to print about
			}	
		});
		subMenuItem4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println(arena.M1);
				arena.addDrone(4);																					// and its action to print about
				//subMenuItem4.setDisable(true);																Limit amount of mouse drones?? 
			}	
		});
		subMenuItem5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				arena.addDrone(5);		
													// and its action to print about
			}	
		});
		

	/*	subMenuItem6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int[] Size = showMsg();// and its action to print about
				//timer.stop();
				mc.clearCanvas();
				//arena.SetxSize(Size[0]);
				//	arena.SetySize(Size[1]);
				//	mc.resize(Size[0], Size[1]);
				//System.out.println("SS");
				//	mc.clearCanvas();

				timer.start();
			}	
		});*/
		subMenu_s1.getItems().addAll(subMenuItem1, subMenuItem2, subMenuItem3,subMenuItem4,subMenuItem5, subMenuItem6 ); // Add New Submenu

		// Menu subMenu_s2 = new Menu("NewCanvas");

		mNew.getItems().add(subMenu_s1); 																					// add submenu to menu 
		
//Creating the mouse event handler 
	      EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() { 	//Disable mouse Button Spawn 
	    	  
	         @Override 
	         public void handle(javafx.scene.input.MouseEvent e) { 
	        	 System.out.println(arena.M1 + "THIS IS M!");
					if (arena.M1 >= 0){subMenuItem4.setDisable(true);}
				else {subMenuItem4.setDisable(false);} 
			}
			
	         }; 
	        
		mNew.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
		
		
		/*mNew.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println(arena.M1 + "THIS IS M!");
					if (arena.M1 > 0){subMenuItem4.setDisable(true);}
				else {subMenuItem4.setDisable(false);} 
			}
			
		});*/

		menuBar.getMenus().addAll(mFile, mHelp, mNew);															// set main menu with File, Help
		return menuBar;}																						// return the menu
	
	
	/**
	 * Method to draw the world 
	 */
	public void drawWorld(){
		mc.clearCanvas();						// set black colour
		arena.drawArena(mc);
	}
	/**
	 * Method to display details about all the drones
	 */
	public void drawStatus(){
		rtPane.getChildren().clear();					// clear rtpane
		ArrayList<String> allBs = arena.describeAll();
		for (String s : allBs) {
			Label l = new Label(s); 		// turn description into a label
			rtPane.getChildren().add(l);	// add label	
		}	
	}
	/** method to show score on the score drone 
	 * @param x - XCoordinates
	 * @param y - YCoordinates
	 * @param score - Score 
	 */
	public void showScore (double x, double y, int score) {
		mc.showText(x, y, Integer.toString(score));
	}
	/**Create ToolBarInterface With Start, Pause and Add Main Drone Button
	 * @return Hbox
	 */
	private HBox setButtons() {
		Button btnStart = new Button("Start");															// create button for starting
		btnStart.setOnAction(new EventHandler<ActionEvent>() {											// now define event when it is pressed
			@Override
			public void handle(ActionEvent event) {
				timer.start();																			// its action is to start the timer
			}
		});
		Button btnStop = new Button("Pause");															// now button for stop
		btnStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.stop();																			// and its action to stop the timer
			}
		});
		Button btnAdd = new Button("Another Drone");													// now button for another drone
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				arena.addDrone(1);																		// and its action to add and refresh 
				drawWorld();
			}
		});
		// now add these buttons + labels to a HBox
		// now add these buttons + labels to a HBox
		return new HBox(new Label("Run: "), btnStart, btnStop, new Label("Add: "), btnAdd);
	}
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle(" This is my GUI Demo");
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 20, 10, 20));
		arena = new DroneColosseum(800, 500);		
		bp.setTop(setMenu());																	// put menu at the top

		Group root = new Group();																// create group with canvas
		Canvas canvas = new Canvas( 800, 500 );
		root.getChildren().add( canvas );
		bp.setLeft(root);																		// load canvas to left area

		mc = new myCanvas(canvas.getGraphicsContext2D(), 800, 500);

		setMouseEvents(canvas);																	// set up mouse events

								// set up arena
		drawWorld();

		timer = new AnimationTimer() {															// set up timer
			public void handle(long currentNanoTime) {											// and its action when on
				long ellapsedMillis = System.currentTimeMillis() - startTime;

				arena.checkDrones();															// check the angle of all Drones
				arena.adjustDrones();															// move all Drones
				drawWorld();																	// redraw the world
				drawStatus();																	// indicate where Drones are
				if (Math.round(ellapsedMillis / 1000)% 3 == 0){
					arena.UnCloak();
				}else if((Math.round(ellapsedMillis / 1000)% 2 == 0)){
					arena.Cloak();
				}

			}
		};

		rtPane = new VBox();		 															// set vBox on right to list items
		rtPane.setAlignment(Pos.TOP_LEFT);														// set alignment
		rtPane.setPadding(new Insets(5, 75, 75, 5));											// padding
		bp.setRight(rtPane);																	// add rtPane to borderpane right

		bp.setBottom(setButtons());																// set bottom pane with buttons

		Scene scene = new Scene(bp, 1000, 600);													// set overall scene
		bp.prefHeightProperty().bind(scene.heightProperty());
		bp.prefWidthProperty().bind(scene.widthProperty());

		primaryStage.setScene(scene);
		primaryStage.show();


	}

	/**Method to set canvas Size
	 * @param xS - xSize
	 * @param yS - ySize
	 */
	public void SetCanvas(int xS, int yS){
		mc.xCanvasSize = xS;
		mc.yCanvasSize = yS;

	}

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		Application.launch(args);			// launch the GUI

	}
}
