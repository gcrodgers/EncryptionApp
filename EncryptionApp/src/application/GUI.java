package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GUI extends Application {
	
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("builder.fxml"));
			//Group root = new Group();
			Scene scene = new Scene(root, Color.LIGHTSKYBLUE);
			stage.setTitle("Ecryption Selecter Demo");
			stage.setHeight(600);
			stage.setWidth(600);
			stage.setResizable(false);
			Image myIcon = new Image("encryption-icon.png");
			stage.getIcons().add(myIcon);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
