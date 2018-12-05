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
		//byte���۸� read,write�� ���� ����ϱ⶧���� ����
		try {//����Ʈ�� �д´�. 
			SocketAddress address = new InetSocketAddress("localhost", port);
			SocketChannel client = SocketChannel.open(address);
			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);
			while (client.read(buffer) != -1) {
				buffer.flip();//���ۿ� ���� �����͸� �Ѱ��� �� , ���̼��� �Ǿ����ζ���
				out.write(buffer);
				buffer.clear();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}