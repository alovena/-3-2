
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
			//why error?  계산 도중 실행 => 실행중 결과를 출력 
			//sol : 쓰레드가 끝남을 알려주고 => 결과를 출력
			//sol 1. Polling을 사용 == getter methods 를 생성한다.==flag value를 리턴한다.
			//
			StringBuilder result = new StringBuilder(filename);
			result.append(": ");
			byte[] digest = dr.getDigest();
			
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
		}

	}

}
