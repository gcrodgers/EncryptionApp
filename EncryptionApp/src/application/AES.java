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

public class AES {
	
	@FXML
	Label error_label;
	@FXML
	TextField result_textfield;
	@FXML
	TextField aesText_textfield;
	@FXML
	TextField passwordText_textfield;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
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
	
	public SecretKey generatePasswordKey(String password) {
			try {
				SecretKeyFactory create_key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
				KeySpec spec = new PBEKeySpec(password.toCharArray(), salt_val.getBytes(), 65536, 256);
		        SecretKey key = new SecretKeySpec(create_key.generateSecret(spec).getEncoded(), "AES");
		        return key;
			} catch (NoSuchAlgorithmException e) {
				error_label.setText("Error creating key from password");
			} catch (InvalidKeySpecException e) {
				error_label.setText("Error creating key from password");
			}
			return null;
	    }
	
	public void encrypt(ActionEvent e) {
		String text = aesText_textfield.getText();
		String password = passwordText_textfield.getText();
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
		
		SecretKey generated_key = generatePasswordKey(password);
		
		try {
			Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			IvParameterSpec iv = new IvParameterSpec(initV.getBytes("UTF-8"));
			encrypt.init(Cipher.ENCRYPT_MODE, generated_key, iv);
			byte[] encrypted_message = encrypt.doFinal(text.getBytes("UTF-8"));
			String result = Base64.getEncoder().encodeToString(encrypted_message);
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
	
	
	public void decrypt(ActionEvent e) {
		String text = aesText_textfield.getText();
		String password = passwordText_textfield.getText();
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
		
		SecretKey generated_key = generatePasswordKey(password);
		
		try {
			Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			IvParameterSpec iv = new IvParameterSpec(initV.getBytes("UTF-8"));
			decrypt.init(Cipher.DECRYPT_MODE, generated_key, iv);
			byte[] original = decrypt.doFinal(Base64.getDecoder().decode(text));
			String result = new String(original, "UTF-8");
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
			error_label.setText("Passwords do not match");
		}
	}

}
