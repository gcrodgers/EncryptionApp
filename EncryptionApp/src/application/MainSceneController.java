package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSceneController {

	private Parent root;
	private Stage stage;
	private Scene scene;
	
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
	
	public void caesarScene(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("CaesarScene.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
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
	
	public void exit(ActionEvent e) throws IOException {
		Platform.exit();
	}
}
