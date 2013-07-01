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
import javax.swing.border.TitledBorder;

public class EncryptPanel extends FileExchange implements ActionListener {
	
    //static field for sharing file between panels
    public static JTextField textFieldFile;
    public static JLabel lblFileLengthNum;
    
	private JTextField textFieldLineStart, textFieldCharStart;
    private JEditorPane editorPaneCipherText, editorPaneEncryptText;
    private JLabel lblTextToEncrypt, lblCipherText, lblChar, lblLine, lblFileLength;
    private JButton btnFileButton;
    private JCheckBox chckbxIgnoreCase, chckbxEncryptSpaces;
    private JScrollPane scrollPaneEncrypt, scrollPaneCipher;
    private JButton btnCopyText, btnEncrypt;
    private JPanel panelFile;
    private JPanel panelIndex;

	public EncryptPanel() {

        FormLayout fl_panel = new FormLayout(new ColumnSpec[] {
        		FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
        		ColumnSpec.decode("250px:grow"),
        		FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),
        		FormFactory.UNRELATED_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),},
        	new RowSpec[] {
        		FormFactory.LINE_GAP_ROWSPEC,
        		RowSpec.decode("14px"),
        		FormFactory.LINE_GAP_ROWSPEC,
        		RowSpec.decode("min:grow"),
        		FormFactory.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("min:grow"),
        		FormFactory.LINE_GAP_ROWSPEC,
        		RowSpec.decode("25px"),
        		FormFactory.RELATED_GAP_ROWSPEC,
        		FormFactory.DEFAULT_ROWSPEC,
        		FormFactory.RELATED_GAP_ROWSPEC,
        		FormFactory.DEFAULT_ROWSPEC,
        		FormFactory.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("100dlu:grow"),
        		FormFactory.RELATED_GAP_ROWSPEC,
        		FormFactory.DEFAULT_ROWSPEC,});
        setLayout(fl_panel);
        
        lblTextToEncrypt = new JLabel("Text to Encrypt:");
        add(lblTextToEncrypt, "2, 2, center, center");
        
        btnCopyText = new JButton("Copy Text");
        btnCopyText.setMargin(new Insets(0,0,0,0));
        btnCopyText.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnCopyText.addActionListener(this);
        
        panelFile = new JPanel();
        panelFile.setBorder(new TitledBorder(null, "Key File", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 11), null));
        add(panelFile, "4, 4, 3, 1, fill, fill");
        panelFile.setLayout(new FormLayout(new ColumnSpec[] {
        		FormFactory.RELATED_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),
        		FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
        		FormFactory.DEFAULT_COLSPEC,
        		FormFactory.UNRELATED_GAP_COLSPEC,
        		FormFactory.DEFAULT_COLSPEC,
        		FormFactory.RELATED_GAP_COLSPEC,},
        	new RowSpec[] {
        		FormFactory.RELATED_GAP_ROWSPEC,
        		RowSpec.decode("25px:grow"),
        		FormFactory.LINE_GAP_ROWSPEC,
        		RowSpec.decode("25px"),}));
        
        textFieldFile = new JTextField();
        panelFile.add(textFieldFile, "2, 2, 3, 1, fill, center");
        textFieldFile.setFont(new Font("Tahoma", Font.ITALIC, 11));
        textFieldFile.setText("select a file....");
        textFieldFile.setEditable(false);
        textFieldFile.setColumns(10);
        
        btnFileButton = new JButton("Browse");
        panelFile.add(btnFileButton, "6, 2, left, center");
        btnFileButton.addActionListener(this);
        btnFileButton.setHorizontalAlignment(SwingConstants.RIGHT);
        
        lblFileLength = new JLabel("File Length:");
        panelFile.add(lblFileLength, "2, 4, right, center");
        lblFileLength.setFont(new Font("Tahoma", Font.PLAIN, 11));
        
        lblFileLengthNum = new JLabel("...");
        panelFile.add(lblFileLengthNum, "4, 4, 3, 1, left, center");
        lblFileLengthNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblFileLengthNum.setHorizontalAlignment(SwingConstants.LEFT);
        
        panelIndex = new JPanel();
        panelIndex.setBorder(new TitledBorder(null, "Key Start Index", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 11), null));
        add(panelIndex, "4, 6, 3, 1, fill, fill");
        panelIndex.setLayout(new FormLayout(new ColumnSpec[] {
        		FormFactory.RELATED_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),
        		FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
        		FormFactory.DEFAULT_COLSPEC,
        		FormFactory.UNRELATED_GAP_COLSPEC,
        		ColumnSpec.decode("default:grow"),
        		FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
        		FormFactory.DEFAULT_COLSPEC,
        		FormFactory.RELATED_GAP_COLSPEC,},
        	new RowSpec[] {
        		RowSpec.decode("25px:grow"),}));
        
        lblLine = new JLabel("Line:");
        panelIndex.add(lblLine, "2, 1, right, center");
        
        textFieldLineStart = new JTextField();
        panelIndex.add(textFieldLineStart, "4, 1, fill, center");
        textFieldLineStart.setColumns(5);
        textFieldLineStart.setText("1");
        
        lblChar = new JLabel("Char:");
        panelIndex.add(lblChar, "6, 1, right, center");
        
        textFieldCharStart = new JTextField();
        panelIndex.add(textFieldCharStart, "8, 1, fill, center");
        textFieldCharStart.setColumns(5);
        textFieldCharStart.setText("1");
        
        btnEncrypt = new JButton("ENCRYPT");
        btnEncrypt.addActionListener(this);
        btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(btnEncrypt, "4, 10, 3, 1, center, center");
        add(btnCopyText, "2, 16, 5, 1, center, center");
        
        scrollPaneEncrypt = new JScrollPane();
        scrollPaneEncrypt.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneEncrypt.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPaneEncrypt, "2, 3, 1, 8, fill, fill");
        
        editorPaneEncryptText = new JEditorPane();
        scrollPaneEncrypt.setViewportView(editorPaneEncryptText);
        editorPaneEncryptText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        chckbxEncryptSpaces = new JCheckBox("Encrypt Spaces");
        chckbxEncryptSpaces.setSelected(true);
        chckbxEncryptSpaces.setToolTipText("preserve space-formatting");
        add(chckbxEncryptSpaces, "4, 8, center, center");
        
        chckbxIgnoreCase = new JCheckBox("Ignore Case");
        chckbxIgnoreCase.setToolTipText("match char index regardless of case");
        add(chckbxIgnoreCase, "6, 8");
        
        lblCipherText = new JLabel("Ciphertext:");
        lblCipherText.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblCipherText, "2, 12, 5, 1");
        
        scrollPaneCipher = new JScrollPane();
        scrollPaneCipher.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneCipher.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPaneCipher, "2, 14, 5, 1, fill, fill");
        
        editorPaneCipherText = new JEditorPane();
        scrollPaneCipher.setViewportView(editorPaneCipherText);
        editorPaneCipherText.setBackground(new Color(220, 220, 220));
        editorPaneCipherText.setEditable(false);
        editorPaneCipherText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
         
	}

	public void actionPerformed(ActionEvent arg0) {

		// 'Encrypt' Button/
		if (arg0.getSource() == btnEncrypt) {
			if ( ( super.file != null ) && ( super.file.canRead() ) ){
				try {
					//Get encryption args
					String plaintext = editorPaneEncryptText.getText();
					int keyStartLine = Integer.valueOf(textFieldLineStart.getText());
					int keyStartChar = Integer.valueOf(textFieldCharStart.getText());
					boolean encryptSpaces = chckbxEncryptSpaces.isSelected();
					boolean ignoreCase = chckbxIgnoreCase.isSelected();
					
					//Cancel if index higher than file-length
					if (keyStartLine > fileLength) {
					JOptionPane.showMessageDialog(this, "Line index cannot be larger than the key file length", "Index out of bounds", JOptionPane.WARNING_MESSAGE);
					
					//Send for encryption 
					} else {
						try {
							editorPaneCipherText.setText(BookCipher.encrypt(plaintext, super.file, keyStartLine, keyStartChar, encryptSpaces, ignoreCase));
						} catch( FileNotFoundException e) {
							JOptionPane.showMessageDialog(this, "Please choose an existing file", "Invalid File Name", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch ( NumberFormatException e) {
					//Index is in wrong format
					JOptionPane.showMessageDialog(this, "Key indices must be integer values", "Key Index Error", JOptionPane.WARNING_MESSAGE);
				} finally{}
			} else {
				//file not initialized
				JOptionPane.showMessageDialog(this, "Choose an existing key file for encryption", "No Key File", JOptionPane.WARNING_MESSAGE);
			}

		// 'Browse' Button/
		} else if (arg0.getSource() == btnFileButton) {

			final JFileChooser chooser = new JFileChooser();

			int selected = chooser.showOpenDialog(this);
			if (selected == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				Tabs.keyFile = file;
				textFieldFile.setText(file.getName());

				//Send file to FileReader
				readFile();
        		lblFileLengthNum.setText(super.fileLength + " lines");

			} else {
				//No file selected
			}

		// 'Copy Text' Button/
		} else if (arg0.getSource() == btnCopyText) {
			super.setClip(editorPaneCipherText.getText());
		}
	}
	
}