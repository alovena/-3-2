import java.io.*;
import java.net.*;
import java.util.*;

public class HeaderViewer {

	public static void main(String[] args) {
			String kk="http://cyber.hanbat.ac.kr";

			try {
				URL u = new URL(kk);
				URLConnection uc = u.openConnection();
				System.out.println("Content-type: " + uc.getContentType());
				if (uc.getContentEncoding() != null) {
					System.out.println("Content-encoding: " + uc.getContentEncoding());
				}
				if (uc.getDate() != 0) {
					System.out.println("Date: " + new Date(uc.getDate()));
					System.out.println("Date: " +(uc.getDate())); //1900년부터 흘렀던 millSecond 를 반환해서 현재시간에 맞게끔 설정해줌
				}
				if (uc.getLastModified() != 0) {
					System.out.println("Last modified: " + new Date(uc.getLastModified()));
				}
				if (uc.getExpiration() != 0) {
					System.out.println("Expiration date: " + new Date(uc.getExpiration()));
				}
				if (uc.getContentLength() != -1) {
					System.out.println("Content-length: " + uc.getContentLength());
				}
				if(uc.getLastModified() !=-1) {
					System.out.println("Content-length: " + uc.getLastModified());
				}
				if(uc.getExpiration() !=-1) {
					System.out.println("Content-length: " + uc.getExpiration());
				}
			} catch (MalformedURLException ex) {
				System.err.println( " is not a URL I understand");
			} catch (IOException ex) {
				System.err.println(ex);
			}
			System.out.println();
		}
}
