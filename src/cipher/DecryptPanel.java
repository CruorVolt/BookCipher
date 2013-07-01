package cipher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DecryptPanel extends FileExchange implements ActionListener {
	
	//static field for filename shared between panels
	public static JTextField textFieldFile;
	
    private JEditorPane editorPanePlainText, editorPaneCipherText;
    private JButton btnDecrypt, btnFileButton;
    private JLabel lblCiphertext;
    private JLabel lblPlaintext;
    private JScrollPane scrollPaneCipher;
    private JScrollPane scrollPanePlain;
    private JButton btnCopyText;
    private JPanel borderPanel;
    private JCheckBox chckbxSinglecase;

	public DecryptPanel() {

        FormLayout fl_panel = new FormLayout(new ColumnSpec[] {
        		ColumnSpec.decode("default:grow"),
        		FormFactory.RELATED_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),},
        	new RowSpec[] {
        		FormFactory.LINE_GAP_ROWSPEC,
        		FormFactory.DEFAULT_ROWSPEC,
        		FormFactory.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("14px"),
        		FormFactory.LINE_GAP_ROWSPEC,
        		RowSpec.decode("min:grow"),
        		FormFactory.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("25px"),
        		FormFactory.LINE_GAP_ROWSPEC,
        		FormFactory.DEFAULT_ROWSPEC,
        		FormFactory.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("min:grow"),
        		FormFactory.RELATED_GAP_ROWSPEC,
        		FormFactory.DEFAULT_ROWSPEC,});
        setLayout(fl_panel);
        
        borderPanel = new JPanel();
        borderPanel.setBorder(new TitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Key File", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), null));
        add(borderPanel, "1, 2, right, fill");
        borderPanel.setLayout(new FormLayout(new ColumnSpec[] {
        		FormFactory.RELATED_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),
        		FormFactory.RELATED_GAP_COLSPEC,
        		FormFactory.DEFAULT_COLSPEC,
        		FormFactory.RELATED_GAP_COLSPEC,},
        	new RowSpec[] {
        		FormFactory.RELATED_GAP_ROWSPEC,
        		FormFactory.DEFAULT_ROWSPEC,
        		FormFactory.RELATED_GAP_ROWSPEC,}));
        
        textFieldFile = new JTextField();
        borderPanel.add(textFieldFile, "2, 2");
        textFieldFile.setFont(new Font("Tahoma", Font.ITALIC, 11));
        textFieldFile.setText("select a file....");
        textFieldFile.setEditable(false);
        textFieldFile.setColumns(20);
        
        btnFileButton = new JButton("Browse");
        borderPanel.add(btnFileButton, "4, 2, left, center");
        btnFileButton.addActionListener(this);
        btnFileButton.setHorizontalAlignment(SwingConstants.RIGHT);
        
        chckbxSinglecase = new JCheckBox("Single-Case");
        chckbxSinglecase.setToolTipText("Improves readability of case-neutral ciphertext");
        chckbxSinglecase.setHorizontalAlignment(SwingConstants.LEFT);
        add(chckbxSinglecase, "3, 2, left, default");
        
        lblCiphertext = new JLabel("Ciphertext:");
        lblCiphertext.setVerticalAlignment(SwingConstants.BOTTOM);
        add(lblCiphertext, "1, 4, 3, 1, left, bottom");
        
        scrollPaneCipher = new JScrollPane();
        scrollPaneCipher.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneCipher.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPaneCipher, "1, 6, 3, 1, fill, fill");
        
        editorPaneCipherText = new JEditorPane();
        scrollPaneCipher.setViewportView(editorPaneCipherText);
        editorPaneCipherText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        btnDecrypt = new JButton("DECRYPT");
        btnDecrypt.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDecrypt.addActionListener(this);
        add(btnDecrypt, "1, 8, 3, 1, center, center");
        
        lblPlaintext = new JLabel("Plaintext:");
        lblPlaintext.setVerticalAlignment(SwingConstants.BOTTOM);
        add(lblPlaintext, "1, 10, 3, 1, left, bottom");
        
        scrollPanePlain = new JScrollPane();
        scrollPanePlain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePlain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPanePlain, "1, 12, 3, 1, fill, fill");
        
        editorPanePlainText = new JEditorPane();
        scrollPanePlain.setViewportView(editorPanePlainText);
        editorPanePlainText.setBackground(new Color(220, 220, 220));
        editorPanePlainText.setEditable(false);
        editorPanePlainText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        btnCopyText = new JButton("Copy Text");
        btnCopyText.setMargin(new Insets(0,0,0,0));
        btnCopyText.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnCopyText.addActionListener(this);
        add(btnCopyText, "1, 14, 3, 1, center, center");
         
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		// 'Decrypt' Button/
		if (arg0.getSource() == btnDecrypt) {
	
			if ( (file != null) && (file.canRead()) ) {
				try {
					//Send for  decryption
					String ciphertext = editorPaneCipherText.getText();
					String plaintext = BookCipher.decrypt(ciphertext, file);
					if (chckbxSinglecase.isSelected()) { plaintext = plaintext.toUpperCase(); }
					editorPanePlainText.setText(plaintext);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(this, "Please select the message's key file", "No Key File", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Choose an existing key file for decryption", "No Key File", JOptionPane.WARNING_MESSAGE);
			}
					
		// 'Browse' Button/
		} else if (arg0.getSource() == btnFileButton) {
			
			filename = "";
			final JFileChooser chooser = new JFileChooser();
			
			int selected = chooser.showOpenDialog(this);
			if (selected == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				Tabs.keyFile = file;
				textFieldFile.setText(file.getName());
				chooseFile(file);
				
				//Send file to FileReader
				readFile();
				
			} else {
				//No file selected 
			}
			
		// 'Copy Text' Button/
		} else if (arg0.getSource() == btnCopyText) {
			super.setClip(editorPanePlainText.getText());
		}
	}
}