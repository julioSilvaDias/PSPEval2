package encriptacion;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class ClaveSimetrica {
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final int KEY_SIZE = 256;
	private static final int IV_LENGTH = 16;

	public static void main(String[] args) {
		try {
			Scanner teclado = new Scanner(System.in);
			
			SecretKey key = generateKey();
			System.out.print("Introduce el mensaje que deseas encriptar... ");
			String original = teclado.nextLine();
			
			String encrypted = encrypt(original, key);
			System.out.println("Encriptado. " + encrypted);
			
			String decrypted = decrypt(encrypted, key);
			System.out.println("Desencriptado: " + decrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SecretKey generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(KEY_SIZE);
		return keyGen.generateKey();
	}

	public static String encrypt(String plainText, SecretKey key) throws Exception {
		byte[] iv = new byte[IV_LENGTH];
		SecureRandom random = new SecureRandom();
		random.nextBytes(iv);

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

		byte[] cipherText = cipher.doFinal(plainText.getBytes());

		byte[] ivAndCipherText = new byte[IV_LENGTH + cipherText.length];
		System.arraycopy(iv, 0, ivAndCipherText, 0, IV_LENGTH);
		System.arraycopy(cipherText, 0, ivAndCipherText, IV_LENGTH, cipherText.length);

		return Base64.getEncoder().encodeToString(ivAndCipherText);
	}

	public static String decrypt(String encryptedText, SecretKey key) throws Exception {
		String ret = null;

		byte[] ivAndCipherText = Base64.getDecoder().decode(encryptedText);

		byte[] iv = new byte[IV_LENGTH];
		System.arraycopy(ivAndCipherText, 0, iv, 0, IV_LENGTH);

		byte[] cipherText = new byte[ivAndCipherText.length - IV_LENGTH];
		System.arraycopy(ivAndCipherText, IV_LENGTH, cipherText, 0, cipherText.length);

		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

		ret = new String(cipher.doFinal(cipherText));
		return ret;
	}
}
