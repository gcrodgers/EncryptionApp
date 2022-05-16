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

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
		stage.setHeight(400);
		stage.setWidth(600);
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Encrypts given the inputs in the text fields
	 * 
	 * @param e - event
	 */
	public void encrypt(ActionEvent e) {
		String text = rsa_textfield.getText();
		error_label.setText("");
		
		//error checking
		if(text.isEmpty()) {
		    error_label.setText("Please enter some text");
			return;
		}
		else if(!text.matches("[a-zA-Z ]*")) {
			error_label.setText("Please enter only text in English");
			return;
		}
		
		try {
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
			gen.initialize(512);
			KeyPair p = gen.generateKeyPair();
			PublicKey public_key = p.getPublic();
			PrivateKey private_key = p.getPrivate();
			String public_key_string = Base64.getEncoder().encodeToString(public_key.getEncoded());
			String private_key_string = Base64.getEncoder().encodeToString(private_key.getEncoded());
			
			Cipher encrypt = Cipher.getInstance("RSA");
			encrypt.init(Cipher.ENCRYPT_MODE, public_key);
			byte[] encrypted_message = encrypt.doFinal(text.getBytes(StandardCharsets.UTF_8));
			String result = Base64.getEncoder().encodeToString(encrypted_message);
			
			encrypt_label.setText(result);
			publickey_label.setText(public_key_string);
			privatekey_label.setText(private_key_string);
		} catch (NoSuchAlgorithmException er) {
			error_label.setText("Fatal error generating keys");
		}
		  catch(NoSuchPaddingException er) {
			error_label.setText("Fatal error encrypting"); 
		} catch (InvalidKeyException er) {
			error_label.setText("Fatal error encrypting"); 
		} catch (IllegalBlockSizeException er) {
			error_label.setText("Fatal error. Text too long for 512 bits"); 
		} catch (BadPaddingException er) {
			error_label.setText("Fatal error encrypting"); 
		}
		
	}

}
