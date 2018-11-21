
import java.io.*;
import java.net.*;
import java.util.*;

public class LastModified {
	public static void main(String[] args) {

			try {
				URL u = new URL("https://www.oreilly.com");
				HttpURLConnection http = (HttpURLConnection) u.openConnection();
				http.setRequestMethod("OPTIONS");
				//Head ,OPtion으로도 바꿔볼것
				System.out.println(u + " was last modified at " + new Date(http.getLastModified()));
				for(int j=0;;j++) {
					String hedaer=http.getHeaderField(j);
					if(hedaer ==null)
						break;
					System.out.println(http.getHeaderFieldKey(j));
				}
				//for 문 제작
			} catch (MalformedURLException ex) {
				System.err.println( " is not a URL I understand");
			} catch (IOException ex) {
				System.err.println(ex);
			}
			System.out.println();
		
	}
}