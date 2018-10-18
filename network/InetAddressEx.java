import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try { //getByname
				InetAddress ip=InetAddress.getByName("www.google.com");
				System.out.println("getByname : "+ip.getHostAddress());
				System.out.println("getByname : "+ip.getHostName());
				
				
				//getLocalHost
				InetAddress ip2=InetAddress.getLocalHost();
				System.out.println("getLocalHost : "+ip2.getHostAddress());
				System.out.println("getLocalHost : "+ip2.getHostName());
				
				
				//getAllByname
				InetAddress[] ip3=InetAddress.getAllByName("www.google.com");
				String result="";
				for(int i=0;i<ip3.length;i++) {
					System.out.println("getAllByname : "+ip3[i].getHostAddress());
					System.out.println("getAllByname : "+ip3[i].getHostName());
				}
				
				
				//getAddress
				byte bytes[] = ip.getAddress();
		        for (int j = 0; j < bytes.length; j++) {
		            //System.out.print(bytes[j] + 256);
		          result +=(bytes[j]<0)?(bytes[j]+256):(bytes[j]);
		          result +=".";
		        }
		        System.out.println("getAddress : "+result);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
