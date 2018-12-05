import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.IOException;

public class ChargenServer {
	public static int DEFAULT_PORT = 8888
			;

	public static void main(String[] args) {
		int port;
		try {
			port = Integer.parseInt(args[0]);
		} catch (RuntimeException ex) {
			port = DEFAULT_PORT;
		}
		System.out.println("Listening for connections on port " + port);
		byte[] rotation = new byte[95 * 2];
		for (byte i = 'A'; i <= 'Z'; i++) {
			rotation[i - 'A'] = i;
			rotation[i + 26 - 'A'] = i;
		}
		ServerSocketChannel serverChannel;
		Selector selector;
		try {
			serverChannel = ServerSocketChannel.open();
			//open = 채널 생성
			ServerSocket ss = serverChannel.socket();
			//bind 과정 
			InetSocketAddress address = new InetSocketAddress(port);
			ss.bind(address);
			serverChannel.configureBlocking(false);
			selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			//Accept 에대한 키를 등록시키겠다.
			//서버에서 accpt 가 되면 알려달라..!
			//서버가 대기하지 않고 
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}//selector에서 이벤트발생시 뽑음
		while (true) {
			try {
				selector.select();
			} catch (IOException ex) {
				ex.printStackTrace();
				break;
			}
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			//키값 뽑음
			Iterator<SelectionKey> iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				try {
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel client = server.accept();
						System.out.println("Accepted connection from " + client);
//						key chaneel socket 이 한세트
						client.configureBlocking(false);
						SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
						//op Write를 등록!
						ByteBuffer buffer = ByteBuffer.allocate(74);
						buffer.put(rotation, 0, 26);
						//ABC~ZAB...Z를 만들어서 52개로 가겠다
						buffer.put((byte) '\r');
						buffer.put((byte) '\n');
						buffer.flip();
						key2.attach(buffer);
					} else if (key.isWritable()) {
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer buffer = (ByteBuffer) key.attachment();
						if (!buffer.hasRemaining()) {
							// Refill the buffer with the next line
							buffer.rewind();
							// Get the old first character
							int first = buffer.get();
							// Get ready to change the data in the buffer
							buffer.rewind();
							// Find the new first characters position in rotation
							int position = first - 'A' + 1;
							// copy the data from rotation into the buffer
							buffer.put(rotation, position, 26);
							// Store a line break at the end of the buffer
							buffer.put((byte) '\r');
							buffer.put((byte) '\n');
							// Prepare the buffer for writing
							buffer.flip();
						}
						client.write(buffer);
					}
				} catch (IOException ex) {
					key.cancel();
					try {
						key.channel().close();
					} catch (IOException cex) {
					}
				}
			}
		}
	}
}