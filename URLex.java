import java.net.MalformedURLException;
import java.net.URL;

public class URLex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try {
				URL url=new URL(" http://www.codechobo.com:80/sample/hello.html?referer=codechobo#index1");
				System.out.println(url.getProtocol());
				System.out.println(url.getHost());
				System.out.println(url.getPath());
				System.out.println(url.getPort());
				System.out.println(url.getFile());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
