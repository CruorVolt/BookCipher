package cipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/* Parent class for EncryptPanel and DecryptPanel to allow sharing of key-files and clipboard data between the two */

public class FileExchange extends JPanel implements ClipboardOwner {
	
	protected File file = null;
	protected ArrayList<String> lines;
	protected int fileLength = 0;
	protected String filename;
	
	protected Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	public String chooseFile(File sentfile) {
		file = sentfile;
		String filename = file.getName();
		readFile();
		return filename;
	}
	
	public void readFile() {
		try {
			lines = FileReader.read(file);
			fileLength = lines.size();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Please choose an existing file", "Invalid File Name", JOptionPane.WARNING_MESSAGE);
		} finally{}
	}
	
	public void setClip(String contents) {
		clipboard.setContents(new StringSelection(contents), this);
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		// TODO Auto-generated method stub
		
	}

}
