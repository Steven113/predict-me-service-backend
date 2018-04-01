import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class RandomStringGenerator {
	String seed = "0";
	
	MessageDigest digest = null;
	
	public RandomStringGenerator() {
		generateSHA256Digest();
	}
	
	public RandomStringGenerator(String seed) {
		generateSHA256Digest();
		this.seed = seed;
		
	}
	
	private void generateSHA256Digest() {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Faild to locate SHA-256 hash digest.");
			e.printStackTrace();
		}
	}
	
	public String getRandomString() {
		byte[] encodedhash = digest.digest(
				  seed.getBytes(StandardCharsets.UTF_8));
		String randomString = new String(encodedhash);
		
		seed = new String(randomString);
		
		return randomString;
	}
	
}
