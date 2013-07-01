package cipher;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AboutPanel extends JPanel {

	String descrip = "A character-by-character implementation of a book cipher algorithm, " +
					"\na process that encodes a message by associating text with its location " +
					"\nin a predetermined key file, usually a book or other large text document." +
					"\n\nProduces ciphertext of numerical tuples of the format \"<line>:<character>\", " +
					"\nwhich are index references to characters in the key file.";
					
			
	public AboutPanel() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(panel, "2, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JTextPane txtpnDescrip = new JTextPane();
		StyledDocument document = txtpnDescrip.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		document.setParagraphAttributes(0, document.getLength(), center, false);
		panel.add(txtpnDescrip, "1, 1, center, center");
		txtpnDescrip.setEditable(false);
		txtpnDescrip.setBorder(null);
		txtpnDescrip.setBackground(UIManager.getColor("Panel.background"));
		txtpnDescrip.setText(descrip);
		
		JLabel lblWrittenByAnders = new JLabel("  Written by Anders Lundgren  ");
		lblWrittenByAnders.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		lblWrittenByAnders.setFont(new Font("Courier New", Font.ITALIC, 12));
		add(lblWrittenByAnders, "2, 4, center, default");

	}

}
