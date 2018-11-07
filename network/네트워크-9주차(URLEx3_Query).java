import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class QueryString {
	private StringBuilder query = new StringBuilder();
	public synchronized void add(String name, String value) {
		query.append('&');
		encode(name, value);
	}

	private synchronized void encode(String name, String value) {
		try {
			query.append(URLEncoder.encode(name, "UTF-8"));
			query.append('=');
			query.append(URLEncoder.encode(value, "UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("Broken VM does not support UTF-8");
		}
	}

	public synchronized String getQuery() {
		return query.toString();
	}

	@Override
	public String toString() {
		return getQuery();
	}
}

public class URLEx3_Query {
	// URLSplitter
	public static void main(String[] args) {
		try {
			URL u = new URL("https://" + "www.hanbat.ac.kr");
			System.out.println("The URL is " + u);
			System.out.println("The scheme is " + u.getProtocol());
			System.out.println("The user info is " + u.getUserInfo());
			String host = u.getHost();
			if (host != null) {
				int atSign = host.indexOf('@');
				if (atSign != -1)
					host = host.substring(atSign + 1);
				System.out.println("The host is " + host);
			} else {
				System.out.println("The host is null.");
			}
			System.out.println("The port is " + u.getPort());
			System.out.println("The path is " + u.getPath());
			System.out.println("The ref is " + u.getRef());
			System.out.println("The query string is " + u.getQuery());
			System.out.println(URLEncoder.encode("This string has spaces", "UTF-8"));
			System.out.println(URLEncoder.encode("This*string*has*asterisks", "UTF-8"));
			System.out.println(URLEncoder.encode("This%string%has%percent%signs", "UTF-8"));
			System.out.println(URLEncoder.encode("This+string+has+pluses", "UTF-8"));
			System.out.println(URLEncoder.encode("This/string/has/slashes", "UTF-8"));
			System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks", "UTF-8"));
			System.out.println(URLEncoder.encode("This:string:has:colons", "UTF-8"));
			System.out.println(URLEncoder.encode("This~string~has~tildes", "UTF-8"));
			System.out.println(URLEncoder.encode("This(string)has(parentheses)", "UTF-8"));
			System.out.println(URLEncoder.encode("This.string.has.periods", "UTF-8"));
			System.out.println(URLEncoder.encode("This=string=has=equals=signs", "UTF-8"));
			System.out.println(URLEncoder.encode("This&string&has&ampersands", "UTF-8"));

			QueryString qs = new QueryString();
			qs.add("hl", "en");
			qs.add("as_q", "Java");
			qs.add("as_epq", "I/O");
			String url = "http://www.google.com/search?" + qs; // 문자열 +객체를 한 결과 : str+qs는 qs인스턴스에서 toString를 부름 
			System.out.println(url);
			String str="http://www.google.com/search?&hl=en&as_q=Java&as_epq=I%2FO";
			System.out.println(URLDecoder.decode(str));
			
			// /는 %2F0로 인식
		} catch (MalformedURLException ex) {
			System.err.println(" is not a URL I understand.");
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException("Broken VM does not support UTF-8");
		}
		System.out.println();

	}

}
