package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class will demonstrate an AES Encryption using CBC mode. The user will provide text and a password
 * for the key. For this demonstration, the salt value for creating the password will be static as well as the 
 * Initization Value will be static. This is not normal for a real instance of AES being used, but will work for
 * this demo. Otherwise, it would be a bit tedious to show the decryption working. 
 * 
 * @author Garrett Rodgers
 *
 */
public class AES {
	
	//FXML fields we need
	@FXML
	Label error_label;
	@FXML
	TextField result_textfield;
	@FXML
	TextField aesText_textfield;
	@FXML
	TextField passwordText_textfield;
	
	//JavaFX components
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	//the salt value and IV that will not be changed
	private final String initV = "1234567891234567";
	private final String salt_val = "G4FZX892SLP184RP";
	
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
	 * This method will turn the user provided password into the key for encrypting and
	 * decrypting. 
	 * 
	 * @param password - user provided
	 * @return the key or null
	 */
	public SecretKey generatePasswordKey(String password) {
	    try {
	    	//using PBKDF2 to create the key
		    SecretKeyFactory create_key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		    //group key specifications
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt_val.getBytes(), 65536, 256);
			//create and return the key
		    SecretKey key = new SecretKeySpec(create_key.generateSecret(spec).getEncoded(), "AES");
		    return key;
			} catch (NoSuchAlgorithmException e) {
				error_label.setText("Error creating key from password");
			} catch (InvalidKeySpecException e) {
				error_label.setText("Error creating key from password");
			}
		return null;
    }
	
	/**
	 * This method will be used when the user wishes to encrypt
	 * 
	 * @param e
	 */
	public void encrypt(ActionEvent e) {
		//retrieve the user text and password
		String text = aesText_textfield.getText();
		String password = passwordText_textfield.getText();
		//reset these to default
		error_label.setText("");
		result_textfield.setText("");
		
		//error checking
		if(text.isEmpty()) {
		    error_label.setText("Please enter some text");
			return;
		}
		else if(password.isEmpty()) {
			error_label.setText("Please enter a password for the key");
			return;
		}
		
		//generate the key
		SecretKey generated_key = generatePasswordKey(password);
		
		try {
			//set up the Cipher for AES
			Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			//create the Initialization Vector
			IvParameterSpec iv = new IvParameterSpec(initV.getBytes("UTF-8"));
			//set to encrypt and provide key and iv
			encrypt.init(Cipher.ENCRYPT_MODE, generated_key, iv);
			//store the result of encryption in byte array
			byte[] encrypted_message = encrypt.doFinal(text.getBytes("UTF-8"));
			//convert to readable text
			String result = Base64.getEncoder().encodeToString(encrypted_message);
			//display to user
			result_textfield.setText(result);
		} catch (NoSuchAlgorithmException e1) {
			error_label.setText("Error generating AES algo");
		} catch (NoSuchPaddingException e1) {
			error_label.setText("Error generating AES algo");
		} catch (UnsupportedEncodingException e1) {
			error_label.setText("Error generating Initialization Vector");
		} catch (InvalidKeyException e1) {
			error_label.setText("Error encyrpting");
		} catch (InvalidAlgorithmParameterException e1) {
			error_label.setText("Error encyrpting");
		} catch (IllegalBlockSizeException e1) {
			error_label.setText("Error encyrpting");
		} catch (BadPaddingException e1) {
			error_label.setText("Error encyrpting");
		}
	}
	
	/**
	 * This method will be used when the user wishes to decrypt
	 * 
	 * @param e
	 */
	public void decrypt(ActionEvent e) {
		//retrieve the user text and password
		String text = aesText_textfield.getText();
		String password = passwordText_textfield.getText();
		//reset these to default
		error_label.setText("");
		result_textfield.setText("");
		
		//error checking
		if(text.isEmpty()) {
		    error_label.setText("Please enter some text");
			return;
		}
		else if(password.isEmpty()) {
			error_label.setText("Please enter a password for the key");
			return;
		}
		
		//retrieve the key
		SecretKey generated_key = generatePasswordKey(password);
		
		try {
			//set up the Cipher for AES
			Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			//get iv
			IvParameterSpec iv = new IvParameterSpec(initV.getBytes("UTF-8"));
			//set to decrypt mode
			decrypt.init(Cipher.DECRYPT_MODE, generated_key, iv);
			//decrypt into byte array
			byte[] original = decrypt.doFinal(Base64.getDecoder().decode(text));
			//convert to readable text
			String result = new String(original, "UTF-8");
			//display to user
			result_textfield.setText(result);
		} catch (NoSuchAlgorithmException e1) {
			error_label.setText("Error generating AES algo");
		} catch (NoSuchPaddingException e1) {
			error_label.setText("Error generating AES algo");
		} catch (UnsupportedEncodingException e1) {
			error_label.setText("Error generating Initialization Vector");
		} catch (InvalidKeyException e1) {
			error_label.setText("Error decrypting");
		} catch (InvalidAlgorithmParameterException e1) {
			error_label.setText("Error decrypting");
		} catch (IllegalBlockSizeException e1) {
			error_label.setText("Error decrypting");
		} catch (BadPaddingException e1) {
			//this should be thrown if the incorrect password is used
			error_label.setText("Passwords do not match");
		}
	}

}
