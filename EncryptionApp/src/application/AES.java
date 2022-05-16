package application;

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

public class AES {
	
	private final String initV = "1234567891234567";
	
	public SecretKey getKeyFromPassword(String password, String salt) {
			try {
				SecretKeyFactory create_key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
				KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		        SecretKey key = new SecretKeySpec(create_key.generateSecret(spec).getEncoded(), "AES");
		        return key;
			} catch (NoSuchAlgorithmException e) {
				
			} catch (InvalidKeySpecException e) {
				
			}
			return null;
	    }
	
	public void encrypt(ActionEvent e) {
		String text;
		String password;
	}
	
	/**

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Garrettfasfafsafasfaffafasfdafcvffadfdsffsdfaff f    fafsaf";
		String key = "aesEncryptionKey";
		

		
		try {
			//SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			SecretKey skeySpec = getKeyFromPassword("Rodgerafdsfasdfasfasfas", "23");
			//SecretKey skeySpec2 = getKeyFromPassword("Rodgers", "23");
			
			Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			IvParameterSpec temp = new IvParameterSpec(initV.getBytes("UTF-8"));
			//IvParameterSpec temp = generateIv();
			encrypt.init(Cipher.ENCRYPT_MODE, skeySpec, temp);
			byte[] encrypted_message = encrypt.doFinal(text.getBytes("UTF-8"));
			String result = Base64.getEncoder().encodeToString(encrypted_message);
			
			
			System.out.println(result);
			
			Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			decrypt.init(Cipher.DECRYPT_MODE, skeySpec, temp);
			byte[] original = decrypt.doFinal(Base64.getDecoder().decode(result));
			String de = new String(original, "UTF-8");
			
			System.out.println(de);

		} catch (NoSuchAlgorithmException er) {
			
		}
		  catch(NoSuchPaddingException er) {
			 
		} catch (InvalidKeyException er) {
			System.out.println(er);
			
		} catch (IllegalBlockSizeException er) {
			
		} catch (BadPaddingException er) {
			
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	*/

}
