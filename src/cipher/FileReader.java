package cipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	
	public static ArrayList<String> read(File file) throws FileNotFoundException {
		
		Scanner reader = new Scanner(file);
		ArrayList<String> lines = new ArrayList<String>();
		
		while ( reader.hasNext() ) {
			lines.add(reader.nextLine() + "\r\n");
		}
		reader.close();
		return lines;
	}
}