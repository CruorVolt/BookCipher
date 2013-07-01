package cipher;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setTitle("Book Cipher");
		getContentPane().add(new Tabs(), BorderLayout.CENTER);
		pack();
		setVisible(true);
		
	}

}
