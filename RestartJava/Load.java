package RestartJava;
//https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
/*import java.io.File;

import javafx.stage.FileChooser;

public class Load extends Object{
	 FileChooser fileChooser = new FileChooser();
	 fileChooser.setTitle("Open Resource File");
	 fileChooser.getExtensionFilters().addAll(
	         new ExtensionFilter("Text Files", "*.txt"),
	         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
	         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
	         new ExtensionFilter("All Files", "*.*"));
	 File selectedFile = fileChooser.showOpenDialog(mainStage);
	 if (selectedFile != null) {
	    mainStage.display(selectedFile);
	 }
	 
}
*/
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public final class Load extends Application {
 
    private Desktop desktop = Desktop.getDesktop();
 
    @Override
    public void start(final Stage stage) {
        stage.setTitle("File Chooser Sample");
 
        final FileChooser fileChooser = new FileChooser();
 
        final Button openButton = new Button("Open a Picture...");
        final Button openMultipleButton = new Button("Open Pictures...");
 
        openButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                	configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        openFile(file);
                    }
                }
            });
 
        openMultipleButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    List<File> list =
                        fileChooser.showOpenMultipleDialog(stage);
                    if (list != null) {
                        for (File file : list) {
                            openFile(file);
                        }
                    }
                }
            });
 
 
        final GridPane inputGridPane = new GridPane();
 
        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(openMultipleButton, 1, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, openMultipleButton);
 
        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));
 
        stage.setScene(new Scene(rootGroup));
        stage.show();
    }
  private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );} 
  
    public static void main(String[] args) {
        Application.launch(args);
    }
         
       
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                Load.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}