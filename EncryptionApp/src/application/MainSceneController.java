package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class will be responsible for the main menu scene of the app. All it does
 * is navigates to the different options.
 * 
 * @author Garrett Rodgers
 *
 */

public class MainSceneController {

	//JavaFX components
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	/**
	public void mainScene(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("builder.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setHeight(400);
		stage.setWidth(600);
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	*/

	/**
	 * This will redirect the user to the scene for a Caesar Cipher
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void caesarScene(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("CaesarScene.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * This will redirect the user to the scene for a RSA encryption
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void RSAScene(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("RSAScene.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setHeight(520);
		stage.setWidth(628);
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * This will redirect the user to the scene for a Triple DES Encryption
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void TripleDESScene(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TripleDESScene.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setHeight(520);
		stage.setWidth(600);
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * This will redirect the user to the scene for a AES Encryption
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void AESScene(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("AESScene.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setHeight(550);
		stage.setWidth(650);
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * This will exit the application
	 * 
	 * @param e
	 * @throws IOException
	 */
	public void exit(ActionEvent e) throws IOException {
		Platform.exit();
	}
}
