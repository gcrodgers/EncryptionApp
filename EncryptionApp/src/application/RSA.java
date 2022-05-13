package application;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RSA {
	
	@FXML
	TextField rsa_textfield;
	@FXML
	Label error_label;
	@FXML
	Label encrypt_label;
	@FXML
	Label publickey_label;
	@FXML
	Label privatekey_label;
	
	private Parent root;
	private Stage stage;
	private Scene scene;

	/**
	 * Takes you back to the main screen
	 * 
	 * @param e - event
	 * @throws IOException
	 */
	public void back(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("builder.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Encrypts given the inputs in the text fields
	 * 
	 * @param e - event
	 */
	public void encrypt(ActionEvent e) {
		
	}

}
