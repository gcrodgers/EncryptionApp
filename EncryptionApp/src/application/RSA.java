package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
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

/**
 * This class will represent the RSA algorithm for encrypting and decrypting. It will only be using a bit size of 512
 * because anything more makes the strings a bit too long.
 * @author garre
 *
 */
public class RSA {
	
	//FXML fields we need
	@FXML
	TextField rsa_textfield;
	@FXML
	Label error_label;
	@FXML
	TextField result_textfield;
	@FXML
	TextField publickey_textfield;
	@FXML
	TextField privatekey_textfield;
	@FXML
	TextField privatekey_enterfield;
	
	//JavaFX components
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
		//retrieve user text
		String text = rsa_textfield.getText();
		//reset to default
		error_label.setText("");
		
		//error checking
		if(text.isEmpty()) {
		    error_label.setText("Please enter some text");
			return;
		}
		
		try {
			//initiate the key pair generator since RSA uses two keys
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
			//set size to 512 bits
			gen.initialize(512);
			//generate the keys
			KeyPair p = gen.generateKeyPair();
			//get the public and private key
			PublicKey public_key = p.getPublic();
			PrivateKey private_key = p.getPrivate();
			//change these to text the user can see/copy
			String public_key_string = Base64.getEncoder().encodeToString(public_key.getEncoded());
			String private_key_string = Base64.getEncoder().encodeToString(private_key.getEncoded());
			
			//set the cipher to RSA mode
			Cipher encrypt = Cipher.getInstance("RSA");
			//initiate encrypting with the public key
			encrypt.init(Cipher.ENCRYPT_MODE, public_key);
			//store result of encryption in the byte array
			byte[] encrypted_message = encrypt.doFinal(text.getBytes(StandardCharsets.UTF_8));
			//convert to readable text
			String result = Base64.getEncoder().encodeToString(encrypted_message);
			
			//disaply the results to the user
			result_textfield.setText(result);
			publickey_textfield.setText(public_key_string);
			privatekey_textfield.setText(private_key_string);
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
	
	/**
	 * This method will decrypt a message for the user
	 * 
	 * @param e
	 */
	public void decrypt(ActionEvent e) {
		//retrieve the encrypted message and the private key
		String text = rsa_textfield.getText();
		String privateKey = privatekey_enterfield.getText();
		//reset to default
		error_label.setText("");
		publickey_textfield.setText("");
		
		//error checking
		if(text.isEmpty()) {
		    error_label.setText("Please enter some text");
			return;
		}
		else if(privateKey.isEmpty()) {
		    error_label.setText("Decrypt requires the private key");
			return;
		}
		
		//only reset if the user attempted to input a private key
		privatekey_textfield.setText("");
		
		try {
			//set up the cipher in RSA mode
			Cipher decrypt = Cipher.getInstance("RSA");
			//convert the provided encrypted message and key to byte array
			byte[] original = Base64.getDecoder().decode(text);
			byte[] encoded_key = Base64.getDecoder().decode(privateKey);
			
			//turn it back into a key
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded_key);
			
			//get the Private Key
			KeyFactory factory = KeyFactory.getInstance("RSA");
	        PrivateKey myKey = factory.generatePrivate(keySpec);
	        
	        //set up for decrypt mode with private key
	        decrypt.init(Cipher.DECRYPT_MODE, myKey);
	        //store result in byte array
	        byte[] decrypted_message = decrypt.doFinal(original);
	        //convert to readable text
	        String result = new String(decrypted_message, "UTF-8");
	        //display to user
	        result_textfield.setText(result);
		} catch (NoSuchAlgorithmException er) {
			error_label.setText("Fatal error1");
		} catch (NoSuchPaddingException er) {
			error_label.setText("Fatal error2"); 
		} catch (IllegalBlockSizeException e1) {
			error_label.setText("Fatal error3"); 
		} catch (BadPaddingException e1) {
			error_label.setText("Fatal error4"); 
		} catch (InvalidKeySpecException e1) {
			error_label.setText("Fatal error5"); 
		} catch (InvalidKeyException e1) {
			//this should be thrown if the incorrect key is used
			error_label.setText("Incorrect Key");  
		} catch (UnsupportedEncodingException e1) {
			error_label.setText("Fatal error6");  
		}
	}

}
