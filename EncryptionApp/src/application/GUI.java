package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

//This class launches the GUI application for the Encryption Sampler.
public class GUI extends Application {
	
	/**
	 * The start method sets up the GUI window's stage and initial scene.
	 */
	@Override
	public void start(Stage stage) {
		try {
			//retrieve fxml for main menu
			Parent root = FXMLLoader.load(getClass().getResource("builder.fxml"));
			//set the current scene to the main menu
			Scene scene = new Scene(root);
			//set up stage properties
			stage.setTitle("Ecryption Selecter Demo");
			stage.setHeight(400);
			stage.setWidth(600);
			stage.setResizable(false);
			//add an icon
			Image myIcon = new Image("encryption-icon.png");
			stage.getIcons().add(myIcon);
			//import css (right now only for color)
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			//set the scene and show
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			System.out.println("Fatal Error launching applicaiton");
		}
	}
	
	/**
	 * Main method. All it does is start the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//launch the GUI
		launch(args);
	}
}
