import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.io.IOException;

public class ChargenClient {
	public static int DEFAULT_PORT =8888;

	public static void main(String[] args) {
		
		int port=DEFAULT_PORT;
		try {
			port = Integer.parseInt(args[1]);
		} catch (RuntimeException ex) {
			port = DEFAULT_PORT;
		}
		//byte버퍼를 read,write를 같이 사용하기때문에 단점
		try {//바이트를 읽는다. 
			SocketAddress address = new InetSocketAddress("localhost", port);
			SocketChannel client = SocketChannel.open(address);
			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);
			while (client.read(buffer) != -1) {
				buffer.flip();//버퍼에 쌓인 데이터를 넘겨줌 즉 , 포이션을 맨앞으로땡김
				out.write(buffer);
				buffer.clear();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}