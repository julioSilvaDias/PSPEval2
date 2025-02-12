package encriptacion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class Hash {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Introduce la contraseña...");
		String password = teclado.nextLine();
		
		try {
			String hashedPassword = hash(password);
			System.out.println("Hash seguro: " + hashedPassword);
			
			boolean isValid = verifyPassword(password, hashedPassword);
			System.out.println("Contraseña valida?? " + isValid);
		}catch(NoSuchAlgorithmException nsae) {
			
		}
	}

	public static String hash(String password) throws NoSuchAlgorithmException {
		final String ALGORITHM = "SHA-512";
		final int SALT_LENGTH = 16;
		final int ITERATIONS = 100000;

		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_LENGTH];
		random.nextBytes(salt);

		MessageDigest md = MessageDigest.getInstance(ALGORITHM);
		md.update(salt);
		byte[] hashedBytes = md.digest(password.getBytes());

		for (int i = 0; i < ITERATIONS; i++) {
			hashedBytes = md.digest(hashedBytes);
		}

		return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedBytes);

	}
	
	public static boolean verifyPassword(String password, String storedHash) throws NoSuchAlgorithmException {
		String[] parts = storedHash.split(":");
		byte[] salt = Base64.getDecoder().decode(parts[0]);
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(salt);
		byte[] hashedBytes = md.digest(password.getBytes());
		
		for(int i=0; i<100000; i++) {
			hashedBytes = md.digest(hashedBytes);
		}
		
		return Base64.getEncoder().encodeToString(hashedBytes).equals(parts[1]);
	}
}
