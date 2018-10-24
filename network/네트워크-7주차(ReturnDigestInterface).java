
import java.io.*;
import java.security.*;

import javax.xml.bind.DatatypeConverter;

class re extends Thread {
	private String filename;
	private byte[] digest;

	public re(String filename) {
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
}

public class ReturnDigestInterface {

	public static void main(String[] args) {
		
		String [] temp= {"data.txt","data.bin"};
		
		
		for (String filename : temp) {
			// Calculate the digest
			re dr = new re(filename);
			dr.start();
			// Now print the result
			//why error?  ��� ���� ���� => ������ ����� ��� 
			//sol : �����尡 ������ �˷��ְ� => ����� ���
			//sol 1. Polling�� ��� == getter methods �� �����Ѵ�.==flag value�� �����Ѵ�.
			//
			StringBuilder result = new StringBuilder(filename);
			result.append(": ");
			byte[] digest = dr.getDigest();
			
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
		}

	}

}
