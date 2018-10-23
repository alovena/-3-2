
import java.io.*;
import java.security.*;
import javax.xml.bind.*;

public class DigestThread extends Thread {
	private String filename;

	public DigestThread(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		try {
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while (din.read() != -1);
			din.close();
			
			byte[] digest = sha.digest();
			
			StringBuilder result = new StringBuilder(filename);
			result.append(": ");
			result.append(DatatypeConverter.printHexBinary(digest));
			
			System.out.println(result);
		} catch (IOException ex) {
			System.err.println(ex);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
	}

	public static void main(String[] args) {
		String [] temp= {"data.txt","data.bin","tttt.txt"};
		for (String filename : temp) {
			Thread t = new DigestThread(filename);
			t.start();
		}
	}

}
