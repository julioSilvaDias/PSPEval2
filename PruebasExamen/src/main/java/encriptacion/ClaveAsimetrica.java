package encriptacion;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class ClaveAsimetrica {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		try {
			KeyPair keyPair = generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			
			System.out.print("Mensaje que quieres encriptar.. ");
			String original = teclado.nextLine();
			String encrypted = encrypt(original, publicKey);
			System.out.println("Encriptado: " + encrypted);
			
			String decrypted = decrypt(encrypted, privateKey);
			System.out.println("Desencriptado: " + decrypted);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static KeyPair generateKeyPair() throws Exception{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		return keyGen.generateKeyPair();
	}
	
	public static String encrypt(String plainText, PublicKey publicKey) throws Exception{
		
		String ret = null;
		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] cipherText = cipher.doFinal(plainText.getBytes());
		ret = Base64.getEncoder().encodeToString(cipherText);
		
		
		return ret;
	}
	
	public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
		String ret = null;
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
		
		ret = new String(decryptedBytes);
		
		return ret;
	}
}
