import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLEx2_InputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream in = null;
		try {
		URL u = new URL("http://hanbat.ac.kr/images/kr/common/logo.gif");
		in = u.openStream();
		Reader r = new InputStreamReader(new  BufferedInputStream(in));
		int c;
		while ((c = r.read()) != -1) {
		System.out.print((char) c);
		}
		} catch (MalformedURLException ex) {
		System.err.println(args[0] + " is not a parseable URL");
		} catch (IOException ex) {
		System.err.println(ex);
		} finally {
		if (in != null) {
		try {
		in.close();
		} catch (IOException e) {
		// ignore
		}
	}
		}
	}
	

}
