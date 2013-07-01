package cipher;
//Test comment

import java.util.ArrayList;

public class IndexTracking {
	
	private ArrayList<ArrayList<Object>> found = new ArrayList<ArrayList<Object>>();
	
	public void put(int lineindex, int charindex, char c) {
		ArrayList<Object> currententry = new ArrayList<Object>();
		currententry.add(c);
		currententry.add(lineindex);
		currententry.add(charindex);
		found.add(currententry);
	}
	
	// returns { line, char } index values
	public int[] find(char c) {
		ArrayList<Object> currentlist;
		char current;
		int lineindex = -1;
		int charindex = -1;
		int searchindex = found.size() - 1;
		boolean match = false;
		while ( (!match) && (searchindex >= 0) ) {
			currentlist = found.get(searchindex);
			current = (char) currentlist.get(0);
			if (current == c) {
				match = true;
				lineindex = (int) currentlist.get(1);
				charindex = (int) currentlist.get(2);
			}
			searchindex --;
		}
		int[] location = {lineindex, charindex};
		return location;
	}
}
