import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.io.IOException;

public class IntgenClient {
	public static int DEFAULT_PORT = 8888;

	public static void main(String[] args) {
		
		int port;
		try {
			//port = Integer.parseInt(args[1]);
			port=8888;
		} catch (RuntimeException ex) {
			port = DEFAULT_PORT;
		}
		try {
			SocketAddress address = new InetSocketAddress("localhost", port);
			SocketChannel client = SocketChannel.open(address);
			ByteBuffer buffer = ByteBuffer.allocate(4);
			IntBuffer view = buffer.asIntBuffer(); 
			for (int expected = 0;expected<20; expected++) {
				client.read(buffer);
				int actual = view.get();
				System.out.println("Æ÷Áö¼Ç"+actual);
				
				
				buffer.clear();
				view.rewind();
				if (actual != expected) {
					System.err.println("Expected " + expected + "; was " + actual);
					break;
				}
				System.out.println(actual);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}