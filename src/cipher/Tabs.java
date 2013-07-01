package cipher;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Tabs extends JPanel {
    private JTabbedPane tabbedPane;
    
    static File keyFile;
        
	public Tabs() {
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		final EncryptPanel encryptpanel = new EncryptPanel();
		final DecryptPanel decryptpanel = new DecryptPanel();
		final AboutPanel aboutpanel = new AboutPanel();
		encryptpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		decryptpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		aboutpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("Encrypt", encryptpanel);
        tabbedPane.addTab("Decrypt", decryptpanel);
        tabbedPane.addTab("About", aboutpanel);
        
        tabbedPane.addChangeListener(new ChangeListener() {
        	
        	@Override
        	public void stateChanged(ChangeEvent e) {
        		try {
					if ( ( keyFile != null ) && ( keyFile.canRead() ) ){
		        		decryptpanel.chooseFile(keyFile);
		        		decryptpanel.textFieldFile.setText(keyFile.getName());
		        		encryptpanel.chooseFile(keyFile);
		        		encryptpanel.textFieldFile.setText(keyFile.getName());
		        		encryptpanel.lblFileLengthNum.setText(FileReader.read(keyFile).size()+ " lines");
					}
        		} catch (FileNotFoundException f) {
        			encryptpanel.lblFileLengthNum.setText("No File Found");
        		}
        	}
        });
        
        add(tabbedPane);
         
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

}
