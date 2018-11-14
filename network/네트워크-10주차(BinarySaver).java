import java.io.*;
import java.net.*;

public class BinarySaver {

	public static void main(String[] args) {
			String u="http://www.hanbat.ac.kr/images/kr/common/logo.gif";
			try {
				URL root = new URL(u);
				saveBinaryFile(root);
			} catch (MalformedURLException ex) {
				System.err.println(u + " is not URL I understand.");
			} catch (IOException ex) {
				System.err.println(ex);
			}
		
	}

	public static void saveBinaryFile(URL u) throws IOException {
		URLConnection uc = u.openConnection();
		String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();
		System.out.print(contentType);
		System.out.print(contentLength);
		if (contentType.startsWith("text/") || contentLength == -1) {
			throw new IOException("This is not a binary file.");
		}
		try (InputStream raw = uc.getInputStream()) {
			InputStream in = new BufferedInputStream(raw);
			byte[] data = new byte[contentLength];
			int offset = 0;
			while (offset < contentLength) {
				int bytesRead = in.read(data, offset, data.length - offset);
				if (bytesRead == -1)
					break;
				offset += bytesRead;
			}
			if (offset != contentLength) {
				throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
			}
			String filename = u.getFile();
			//subString index 부터 끝까지 마지막 / 을 찾기위해 lastIndexOf 메서드를 사용한것!
			filename = filename.substring(filename.lastIndexOf('/') + 1);
			try (FileOutputStream fout = new FileOutputStream(filename)) {
				fout.write(data);
				fout.flush();
			}
		}
	}

}
