package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
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

public class TripleDES {

	//JavaFX components
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	//FXML fields we need
	@FXML
	TextField tripledes_textfield;
	@FXML
	Label error_label;
	@FXML
	TextField tripledeskey_textfield;
	@FXML
	TextField result_textfield;
	@FXML
	TextField resultkey_textfield;
	
	private final String initV = "G90FJKS1";
	
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
	
	public String generateRandomKey() {
		Random random = ThreadLocalRandom.current();
		byte[] random_bytes = new byte[16];
		random.nextBytes(random_bytes);
		String string_key = Base64.getEncoder().encodeToString(random_bytes);
		
		return string_key;
	}
	
	public void encrypt(ActionEvent e) {
		error_label.setText("");
		tripledeskey_textfield.setText("");
		String string_key = generateRandomKey();
		byte[] secretKey = string_key.getBytes();
        byte[] iv_bytes = initV.getBytes();
        
        String text = tripledes_textfield.getText();
        //error checking
      	if(text.isEmpty()) {
            error_label.setText("Please enter some text");
      		return;
      	}
        
        SecretKeySpec keySpec = new SecretKeySpec(secretKey, "DESede");
        IvParameterSpec iv = new IvParameterSpec(iv_bytes);
        try {
			Cipher encrypt = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			encrypt.init(Cipher.ENCRYPT_MODE, keySpec, iv);
			byte[] encrypted_message = encrypt.doFinal(text.getBytes("UTF-8"));
			String result = Base64.getEncoder().encodeToString(encrypted_message);
			
			result_textfield.setText(result);
			resultkey_textfield.setText(string_key);
		} catch (NoSuchAlgorithmException e1) {
			
		} catch (NoSuchPaddingException e1) {
			
		} catch (InvalidKeyException e1) {
			
		} catch (InvalidAlgorithmParameterException e1) {
			
		} catch (IllegalBlockSizeException e1) {
			
		} catch (BadPaddingException e1) {
			
		} catch (UnsupportedEncodingException e1) {
			
		}
	}
	
	public void decrypt(ActionEvent e) {
		
	}
	
}
