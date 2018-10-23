
import java.io.*;
import java.security.*;
import javax.xml.bind.*;

public class DigestTread2 extends Thread {
	private String filename;
	byte[] digest;
	public DigestTread2(String filename) {
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
		String [] temp= {"data.txt","data.bin","tttt.txt"};
		for (String filename : temp) {
			DigestTread2 t = new DigestTread2(filename);
			t.start();
			
			StringBuilder result = new StringBuilder(filename);
			result.append(": ");
			result.append(DatatypeConverter.printHexBinary(t.getDigest()));
			
			System.out.println(result);
			
		}
	}

}
