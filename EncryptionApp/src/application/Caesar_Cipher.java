package application;
import java.io.IOException;

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
 * 
 * @author Garrett Rodgers
 *
 ** A simple class that will allow a user to encrypt or decrypt their very own Caesar Cipher in 
 ** English text. It requires a key to be input, making the encryption and decryption process 
 ** rather simple.
 */
public class Caesar_Cipher {
	
	
	//JavaFX components
	@FXML
	TextField caesarText_textfield;
	@FXML
	TextField shift_textfield;
	@FXML
	Label resultLabel;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	//there are 26 letters in the English alphabet
	final static int LETTER_AMOUNT = 26;
	
	
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
		//retrieve both the entered texts
		String text = caesarText_textfield.getText();
		String keyText = shift_textfield.getText();
		
		//error checking
		if(text.isEmpty()) {
			resultLabel.setText("Please enter some text");
			return;
		}
		else if(keyText.isEmpty() ) {
			resultLabel.setText("Please enter a shift amount");
			return;
		}
		else if(!text.matches("[a-zA-Z ]*")) {
			resultLabel.setText("Please enter only text in English");
			return;
		}
		else if(!keyText.matches("\\d+")) {
			resultLabel.setText("Please enter a valid integer");
			return;
		}
		else if(Integer.parseInt(keyText) < 0 || Integer.parseInt(keyText) > 25) {
			resultLabel.setText("Please enter a valid integer between 0 and 25");
			return;
		}
		
		//remove whitespace
		text = text.trim();
		
		//cast the entered string for shift to int
		int keyVal = Integer.parseInt(keyText);
		
		//call cipher method and display result to user
		StringBuffer result = caesar_cipher(text, keyVal);
		resultLabel.setText("Your encrypted cipher is: " + result.toString());
	}
	
	/**
	 * Decrypts given the inputs in the text fields
	 * 
	 * @param e - event
	 */
	public void decrypt(ActionEvent e) {
		//retrieve both the entered texts
		String text = caesarText_textfield.getText();
		String keyText = shift_textfield.getText();
		
		//error checking
		if(text.isEmpty()) {
			resultLabel.setText("Please enter some text");
			return;
		}
		else if(keyText.isEmpty() ) {
			resultLabel.setText("Please enter a shift amount");
			return;
		}
		else if(!text.matches("[a-zA-Z ]*")) {
			resultLabel.setText("Please enter only text in English");
			return;
		}
		else if(!keyText.matches("\\d+")) {
			resultLabel.setText("Please enter a valid integer");
			return;
		}
		else if(Integer.parseInt(keyText) < 0 || Integer.parseInt(keyText) > 25) {
			resultLabel.setText("Please enter a valid integer between 0 and 25");
			return;
		}
		
		//remove whitespace
		text = text.trim();
		
		//cast the entered string for shift to int
		int keyVal = Integer.parseInt(keyText);
		
		//call cipher method and display result to user
		StringBuffer result = caesar_cipher(text, LETTER_AMOUNT - keyVal);
		resultLabel.setText("Your decrypted cipher is: " + result.toString());
	}
	
	
	/**
	 * This method will return an encrypted or decrypted Caesar Cipher given
	 * a text message and a key shift value.
	 * 
	 * @param text - the text to be encrypted/decrypted
	 * @param key - the amount to shift
	 * 
	 * @return the encrypted or decrypted cipher
	 */
	public static StringBuffer caesar_cipher(String text, int key) {
		//hold the encrypted or decrpyted Caesar Cipher
		StringBuffer cipher = new StringBuffer();
		//hold the current character
		char curr;
		//character ASCII value
		int charValue;
		
		//loop through the entire text
		for (int i = 0; i < text.length(); i++) {
			//space to readd to the cipher
			if(Character.isSpaceChar(text.charAt(i))) {
				curr = ' ';
			}
			//lowercase letters
			else if(Character.isLowerCase(text.charAt(i))) {
				charValue = (int)text.charAt(i);
				
				charValue = (charValue + key - 97) % LETTER_AMOUNT + 97;
				
				curr = (char)(charValue + ' ' - 32);
			//uppercase letters
			}
			else {
                charValue = (int)text.charAt(i);
				
				charValue = (charValue + key - 65) % LETTER_AMOUNT + 65;
				
				curr = (char)(charValue + ' ' - 32);
			}
			
			//append current character to final cipher
			cipher.append(curr);
		}
		
		return cipher;
	}

}
