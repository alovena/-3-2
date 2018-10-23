
import java.io.*;
import java.security.*;

import javax.xml.bind.DatatypeConverter;

public class callback {
	public static void receiveDigest(byte[] digest, String name) {
		StringBuilder result = new StringBuilder(name);
		result.append(": ");
		result.append(DatatypeConverter.printHexBinary(digest));
		System.out.println(result);
	}

	public static void main(String[] args) {
		String [] temp= {"data.txt","data.bin","tttt.txt"};
		for (String filename : temp) {
// Calculate the digest
			CallbackDigest cb = new CallbackDigest(filename);
			Thread t = new Thread(cb);
			t.start();
		}
	}
}

class CallbackDigest implements Runnable {
	private String filename;

	public CallbackDigest(String filename) {
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
			byte[] digest = sha.digest();
			callback.receiveDigest(digest, filename);
		} catch (IOException ex) {
			System.err.println(ex);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
	}
}

