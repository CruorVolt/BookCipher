package cipher;

import java.applet.Applet;
import java.awt.EventQueue;
import java.awt.Graphics;

public class CipherApplet extends Applet {

	Frame frame;
	
	@Override
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	@Override
	public void paint(Graphics g) {}
	
}
