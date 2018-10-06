package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class test8 {

	public static void main(String[] args) {
		long startTime=System.nanoTime();
		long endTime=System.nanoTime();
		try {
			//generateCharacters(System.out);
			//generateCharacters2(System.out);
			OutputStream out=new FileOutputStream("C:\\Users\\com418\\eclipse-workspace\\test\\src\\date_test.txt");
			generateCharacters(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void generateCharacters(OutputStream out) throws IOException {
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		int start = firstPrintableCharacter;
		for (int number = 0; number < 100; number++) {
			for (int i = start; i < start + numberOfCharactersPerLine; i++) {
				out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter);
			}
			out.write('\r');
			out.write('\n'); // linefeed
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;

		}

	}

	public static void generateCharacters2(OutputStream out) throws IOException {
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		int start = firstPrintableCharacter;
		byte[] line = new byte[numberOfCharactersPerLine + 2];
		// the +2 is for the carriage return and linefeed
		for(int j=0;j<100;j++) {
			for (int i = start; i < start + numberOfCharactersPerLine; i++) {
				line[i - start] = (byte) ((i - firstPrintableCharacter) % numberOfPrintableCharacters
						+ firstPrintableCharacter);
			}
			line[72] = (byte) '\r'; // carriage return
			line[73] = (byte) '\n'; // line feed
			out.write(line);
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
		}
			
		
	}

}
