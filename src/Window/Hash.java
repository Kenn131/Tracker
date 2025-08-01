package Window;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	public static String hashPassword(String password) {
		
		try {
			MessageDigest random = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = random.digest(password.getBytes());
			
			StringBuilder sb = new StringBuilder();
			for (byte b : hashBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Hashing algorithm not available.", e);
		}
		
	}
	
}
