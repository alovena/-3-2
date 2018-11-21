import java.io.*;
import java.net.*;

public class AllHeaders {

	public static void main(String[] args) {
		String str = "https://www.naver.com";
		try {
			URL u = new URL(str);
			URLConnection uc = u.openConnection();
			for (int j = 0;; j++) {
				String header = uc.getHeaderField(j);
				if (header == null)
					break;
				System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
			}
		} catch (MalformedURLException ex) {
			System.err.println(str + " is not a URL I understand.");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		System.out.println();
	}

}

