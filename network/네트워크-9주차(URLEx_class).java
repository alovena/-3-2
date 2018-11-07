import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URLEx_class {
	static String url="http://www.adc.org";
	public static void main(String[] args) {
		try {
			URL u=new URL(url);
			System.out.println(u.getProtocol());
			URL u1 = new URL("http://www.ibiblio.org/javafaq/index.html");
			URL u2 = new URL (u1, "mailinglists.html");
			//index.html -> mailinglists.html 로 변경
			System.out.println(u2);
			
			URL u3=new URL("http://www.lolcats.com");
			InputStream in=u3.openStream();
			int c;
			while((c=in.read())!=-1) {
				System.out.print((char)c);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			String protocol=url.substring(0,url.indexOf(':'));
			System.out.println(protocol+"은 지원이 안됩니다.");
			e.printStackTrace();
		}
		catch(IOException e2) {
			e2.printStackTrace();
		}
		

	}

}
