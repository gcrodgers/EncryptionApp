package application;
import java.util.Scanner;

/**
 * 
 * @author Garrett Rodgers
 *
 ** A simple class that will allow a user to encrypt or decrypt their very own Caesar Cipher in 
 ** English text. It requires a key to be input, making the encryption and decryption process 
 ** rather simple.
 */
public class Caesar_Cipher {
	
	//there are 26 letters in the English alphabet
	final static int LETTER_AMOUNT = 26;
	
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

	public static void main(String[] args) {
		//Scanner object for user input
		Scanner s = new Scanner(System.in);
		
		//String input for a encryption or decryption 
		System.out.print("Please enter text for the Caesar Cipher: ");
		String text = s.nextLine();
		
		//accept only English letters, allow spaces
		while(!text.matches("[a-zA-Z ]*")) {
			System.out.print("Please enter only text in English: ");
			text = s.nextLine();
		}
		
		//remove any leading or trailing whitespace
		text = text.trim();
		
		//accept a key shift value between 0 and 25
		System.out.print("Please enter a shift between 0-25 for the Cipher: ");
		while(!s.hasNextInt()) {
			s.next();
			System.out.println("Please enter a number: ");
		}
		
		//key input 
		int key = s.nextInt();
		//verify key input is valid
		while(key < 0 || key > 25) {
			System.out.print("Please enter a number between 0 and 25 for the shift: ");
			while(!s.hasNextInt()) {
				s.next();
				System.out.print("Please enter a number: ");
			}
			key = s.nextInt();
		}
		
		//Allow the user to encrypt or decrypt the text they input
		System.out.print("Please enter 0 if you wish to encrypt, or 1 to decrypt: ");
		while(!s.hasNextInt()) {
			s.next();
			System.out.print("Please enter a number: ");
		}
		
		//allow only the choice of 0 or 1 to call the respective method
		int choice = s.nextInt();
		while(choice < 0 || choice > 1) {
			System.out.print("Please enter 0 or 1: ");
			while(!s.hasNextInt()) {
				s.next();
				System.out.print("Please enter a number: ");
			}
			choice = s.nextInt();
		}
		
		if(choice == 0) {
			System.out.println("Your encrypted Caesar Cipher is: " + caesar_cipher(text, key));
		}
		else if(choice == 1) {
			System.out.println("Your decrypted Caesar Cipher is: " + caesar_cipher(text, LETTER_AMOUNT - key));
		}
		//close Scanner object
		s.close();
		
	}

}
