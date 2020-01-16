package RestartJava;

import java.util.ArrayList;
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
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DroneGUI extends Application{ 
	private myCanvas mc;
	private AnimationTimer timer;								// timer used for animation
	private VBox rtPane;										// vertical box for putting info
	private DroneColosseum arena;
	private long startTime = System.currentTimeMillis();
	
	private  void showAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);				// define what box is
		alert.setTitle("About");									// say is About
		alert.setHeaderText(null);
		alert.setContentText("Brush's JavaFX Demonstrator");			// give text
		alert.showAndWait();										// show box and wait for user to close
	}
	void setMouseEvents(Canvas canvas){
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent e) {
	        	   	arena.setMCDrone(e.getX(), e.getY());
	            	drawWorld();							// redraw world
	            	drawStatus();
	           }

		
	       });
	}
	MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();						// create main menu

		Menu mFile = new Menu("File");						// add File main menu
		MenuItem mExit = new MenuItem("Exit");					// whose sub menu has Exit
		 mExit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
		 mExit.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) {					// action on exit is
				timer.stop();									// stop timer
				System.exit(0);									// exit program
			}
		});
		mFile.getItems().addAll(mExit);							// add exit to File menu
		
		MenuItem mSave = new MenuItem("Save");					// whose sub menu has Exit
		 mSave.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		 mSave.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {					// action on exit is
	        	timer.stop();									// stop timer
		        System.exit(0);									// exit program
		    }
		});
		mFile.getItems().addAll(mSave);	
		MenuItem mLoad = new MenuItem("Load");		// whose sub menu has Exit
		
		mLoad.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
		mLoad.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {					// action on exit is
	        	timer.stop();									// stop timer
		        
	        	System.exit(0);									// exit program
		    }
		});
		mFile.getItems().addAll(mLoad);	
		
		MenuItem mClear = new MenuItem("Clear");
	    mClear.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
	    mClear.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent t) {
	        	arena.clearArrayList();
	            ;
	        }
	});
	    mFile.getItems().addAll(mClear);	
		
		Menu mHelp = new Menu("Help");							// create Help menu
		MenuItem mAbout = new MenuItem("About");				// add About sub men item
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showAbout();									// and its action to print about
			}	
		});
		mHelp.getItems().addAll(mAbout);						// add About to Help main item

		menuBar.getMenus().addAll(mFile, mHelp);				// set main menu with File, Help
		return menuBar;}											// return the menu
	public void drawWorld(){
		mc.clearCanvas();						// set beige colour
	 	arena.drawArena(mc);
			}
	public void drawStatus(){
		rtPane.getChildren().clear();					// clear rtpane
		ArrayList<String> allBs = arena.describeAll();
		for (String s : allBs) {
			Label l = new Label(s); 		// turn description into a label
			rtPane.getChildren().add(l);	// add label	
		}	
	}
	public void showScore (double x, double y, int score) {
		mc.showText(x, y, Integer.toString(score));
	}
	private HBox setButtons() {
		Button btnStart = new Button("Start");					// create button for starting
		btnStart.setOnAction(new EventHandler<ActionEvent>() {	// now define event when it is pressed
			@Override
			public void handle(ActionEvent event) {
				timer.start();									// its action is to start the timer
			}
		});
		Button btnStop = new Button("Pause");					// now button for stop
		btnStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.stop();									// and its action to stop the timer
			}
		});
		 Button btnAdd = new Button("Another Drone");				// now button for stop
		    btnAdd.setOnAction(new EventHandler<ActionEvent>() {
		        @Override
		        public void handle(ActionEvent event) {
		           	arena.addDrone();								// and its action to stop the timer
		           	drawWorld();
		       }
		    });
		    														// now add these buttons + labels to a HBox
		// now add these buttons + labels to a HBox
		return new HBox(new Label("Run: "), btnStart, btnStop, new Label("Add: "), btnAdd);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle(" This is my GUI Demo");
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 20, 10, 20));

		bp.setTop(setMenu());											// put menu at the top

		Group root = new Group();										// create group with canvas
		Canvas canvas = new Canvas( 400, 500 );
		root.getChildren().add( canvas );
		bp.setLeft(root);												// load canvas to left area

		mc = new myCanvas(canvas.getGraphicsContext2D(), 400, 500);

		setMouseEvents(canvas);											// set up mouse events

		arena = new DroneColosseum(400, 500);								// set up arena
		drawWorld();

		timer = new AnimationTimer() {									// set up timer
			public void handle(long currentNanoTime) {					// and its action when on
				long ellapsedMillis = System.currentTimeMillis() - startTime;
				 
				arena.checkDrones();									// check the angle of all Drones
				arena.adjustDrones();								// move all Drones
				drawWorld();										// redraw the world
				drawStatus();										// indicate where Drones are
			if (Math.round(ellapsedMillis / 1000)% 3 == 0){
				arena.UnCloak();
			}else if((Math.round(ellapsedMillis / 1000)% 2 == 0)){
				arena.Cloak();
			}
			
			}
		};

		rtPane = new VBox();		 									// set vBox on right to list items
		rtPane.setAlignment(Pos.TOP_LEFT);								// set alignment
		rtPane.setPadding(new Insets(5, 75, 75, 5));					// padding
		bp.setRight(rtPane);											// add rtPane to borderpane right

		bp.setBottom(setButtons());										// set bottom pane with buttons

		Scene scene = new Scene(bp, 700, 600);							// set overall scene
		bp.prefHeightProperty().bind(scene.heightProperty());
		bp.prefWidthProperty().bind(scene.widthProperty());

		primaryStage.setScene(scene);
		primaryStage.show();


	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);			// launch the GUI

	}
}
