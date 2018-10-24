import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressEx {
	public static void main(String args[]) throws UnknownHostException {
		InetAddress id3=InetAddress.getByName("www.hanbat.ac.kr");
		System.out.println(id3);
		System.out.println(id3.getHostName());
		System.out.println(id3.getHostAddress());
		System.out.println(id3.getCanonicalHostName());
		InetAddress[] address=InetAddress.getAllByName("www.google.com");
		for(InetAddress address3:address) {
			System.out.println(address3);
		}
		
		String local=InetAddress.getLocalHost().getHostAddress();
		System.out.println(local);
		getVersin(id3);
				
	}
	public static int getVersin(InetAddress ia) {
		byte[] bbbb=ia.getAddress();
		System.out.println(bbbb.length);
		for(int i=0;i<4;i++) {
			System.out.print(bbbb[i]>0?bbbb[i]+".":bbbb[i]+256+".");
		}
			System.out.println(bbbb);
		
		return 0;
	}
}
