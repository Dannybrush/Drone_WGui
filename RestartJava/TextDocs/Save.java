package RestartJava;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
//https://www.genuinecoder.com/save-files-javafx-filechooser/
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
 //FileWithFileChooser
public class Save extends Application {
 
    @Override
    public void start(final Stage primaryStage) {
        final String sampleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut \n"
                + "labore et dolore magna aliqua.\n"
                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n"
                + "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n"
                + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
 
        Text sample = new Text(sampleText);
        sample.setFont(new Font(14));
 
        Button btnSave = new Button("Save");
 
        btnSave.setOnAction(click -> {
            FileChooser fileChooser = new FileChooser();
 
            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
 
            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);
 
            if (file != null) {
                saveTextToFile(sampleText, file);
            }
        });
        
        Button btnSerialize = new Button("Attempt5");
        
        btnSerialize.setOnAction(click -> {
            FileChooser fileChooser = new FileChooser();
 
            //Set extension filter for text files
           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER File (*.ser)","*SER");
           fileChooser.getExtensionFilters().add(extFilter);
 
            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);
 
            if (file != null) {
            	System.out.println("I WANT TO DIE");
               SaveObjectToFile(new Ex1Drone(15,15, 10, 45, 10));
            }
        });
        VBox vBox = new VBox(sample, btnSave, btnSerialize);
        
        vBox.setAlignment(Pos.CENTER);
 
        primaryStage.setScene(new Scene(vBox, 800, 300));
        primaryStage.setTitle("TestMyOG1SaveFunction");
        primaryStage.show();
 
    }
    /*
     * 
     *   FileChooser fileChooser = new FileChooser();
				    String pathOfTheCurrentClass = this.getClass().getResource(".").getPath();
						  File filePath = new File(pathOfTheCurrentClass+"/TextDocs/");
				           //  new File(File.getParentFile())
						  
				  fileChooser.setInitialDirectory(filePath); 
		            //Set extension filter for text files
		           FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER File (*.ser)","*SER");
		           fileChooser.getExtensionFilters().add(extFilter);
		           
		           */
 public void SaveObjectToFile(Object p ){
	 // save the object to file
	 
	 String filename = "N:\\Workspace\\Part2Java\\src\\RestartJava\\TextDocs\\NewTest.ser";
     FileOutputStream fos = null;
     ObjectOutputStream out = null;
     try {
         fos = new FileOutputStream(filename);
         out = new ObjectOutputStream(fos);
         out.writeObject(p);

         out.close();
     } catch (Exception ex) {
         ex.printStackTrace();
         
     }
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
 }
     
	 
 
 
    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
}