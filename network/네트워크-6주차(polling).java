
import java.io.*;
import java.security.*;
import javax.xml.bind.*;

public class polling extends Thread {
	private String filename;
	private byte[] digest;

	public polling(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		try {
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while (din.read() != -1)
				; // read entire file
			din.close();
			digest = sha.digest();
		} catch (IOException ex) {
			System.err.println(ex);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
	}

	public byte[] getDigest() {
		return digest;
	}

	public static void main(String[] args) {
		String[] temp = { "data.txt", "data.bin", "tttt.txt" };
		polling[] digests = new polling[temp.length];
		for (int i = 0; i < temp.length; i++) {
			// Calculate the digest
			digests[i] = new polling(temp[i]);
			digests[i].start();
		}
		for (int i = 0; i < temp.length; i++) {
			while (true) {
				// Now print the result
				byte[] digest = digests[i].getDigest();
				if (digest != null) {
					StringBuilder result = new StringBuilder(temp[i]);
					result.append(": ");
					result.append(DatatypeConverter.printHexBinary(digest));
					System.out.println(result);
					break;
				}
			}
		}
	}
}