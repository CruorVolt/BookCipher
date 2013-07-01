package cipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.security.SecureRandom;

public class BookCipher {

	public static String encrypt(String plaintext, File key, int keyStartLine, 
			int keyStartChar, boolean encryptSpaces, boolean ignoreCase) throws FileNotFoundException {
		
		String ciphertext = "";
		
		ArrayList<String> lines = FileReader.read(key);
		char[] plainarray = plaintext.toCharArray();
		int lineindex, charindex, location;
		boolean charmatch = false;
		IndexTracking found = new IndexTracking();
		SecureRandom randomIndex = new SecureRandom();
		
		for (char currentchar: plainarray) {
			lineindex = Math.abs(keyStartLine - 1);
			charindex = Math.abs(keyStartChar - 1);
			int[] foundindex = found.find(currentchar);
			if ( foundindex[1] >= 0 ) {
				lineindex = foundindex[0];
				charindex = foundindex[1] + 1;
			}
			if ( (encryptSpaces) || (!Character.isWhitespace(currentchar)) ) {
				do {
					location = findChar(lines.get(lineindex), currentchar, ignoreCase, charindex);
					if  (location < 0) {
						lineindex += 1;
						charindex = 0;
					} else {
						charmatch = true;
						found.put(lineindex, location, currentchar);
						ciphertext = ciphertext + (lineindex +1) + ":" + (location + 1) + " ";
					}
				} while ( ( lineindex < (lines.size() - 1 ) ) && (!charmatch) );
			
				//File end reached without match
				if (!charmatch) {
					int randomline = randomIndex.nextInt( (int) Math.ceil(lines.size() / 4) ) + 1;
					int randomchar = lines.get(randomline - 1).length() + randomIndex.nextInt(10) + 1;
					ciphertext = ciphertext + randomline + ":" + randomchar + " ";
				}
			}
			charmatch = false;
		}
		return ciphertext;
	}
	
	public static String decrypt(String ciphertext, File key) throws FileNotFoundException {
		
		String plaintext = "";
		String formaterrormessage = " (ERROR: The ciphertext looks incorrectly formatted. " +
				"Correctly formatted ciphertext looks like numerical tuples (XX:XXX) seperated by spaces.";
		boolean formaterror = false;
		ArrayList<String> lines = FileReader.read(key);
		String[] encryptedtuples = ciphertext.split(" ");
		int lineindex;
		int charindex;
		
		for ( String tuple : encryptedtuples ) {
			String[] indices = tuple.split(":", 2);
			try {
				lineindex = Integer.valueOf(indices[0]);
				charindex = Integer.valueOf(indices[1]);
				try {
					//Encrypted lines/chars have natural numbering (begin with 1) 
					if ( ( lineindex == 0 ) || ( charindex == 0 ) ) {
						plaintext = addErrorChar(plaintext);
					}
					else {
						char currentchar = lines.get(lineindex - 1).charAt(charindex - 1);
						plaintext = plaintext + currentchar;
					}
				} catch (IndexOutOfBoundsException i) {
					plaintext = addErrorChar(plaintext);
				}
			} catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
				plaintext = addErrorChar(plaintext);
				formaterror = true;
			}
		}
		if (formaterror) { plaintext = plaintext + formaterrormessage; }
		return plaintext;
	}
	
	public static String addErrorChar(String current) {
		char errorchar = '#';
		return current + errorchar; 
	}
	
	public static int findChar(String line, char c, boolean ignoreCase, int startIndex) {
		int index = -1;
		String character = Character.toString(c);
		if (ignoreCase) {
			character = character.toLowerCase();
			line = line.toLowerCase();
		}
		index = line.indexOf(character, startIndex);
		return index;
	}
}
