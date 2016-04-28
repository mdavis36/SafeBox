package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class PlainMessageDialog extends CustomDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795604581523451482L;

	private static final int BUTTON_BORDER_WIDTH = 2;
	private static final int FONT_SIZE = 18;
	private static final int GRID_WIDTH = 4;
	private static final int GRID_Y_1 = 0;
	private static final int EMPTY_BORDER_TOP = 10;
	private static final int DELETE_BOX_WIDTH = 450;
	private static final int DELETE_BOX_HEIGHT = 180;
	private static final String OK_TEXT = "Ok";
	//Strings Start//

	//Strings End//
	
	//JObjects Start//
	private CustomButton okButton;
	private JLabel nameOfFolder;
	JTextArea messageArea;
	//JObjects End//
	
	public PlainMessageDialog(StateManager sm, Color c1, Color c2, int w, int h, String message){
		super(sm, c1, c2, w, h);

		//--------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		messageArea = new JTextArea(message);
		messageArea.setBorder(new EmptyBorder(EMPTY_BORDER_TOP, 0, 0, 0));
		messageArea.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, FONT_SIZE));
		messageArea.setSize(new Dimension(350, 100));
		messageArea.setWrapStyleWord(true);
		messageArea.setLineWrap(true);
		messageArea.setOpaque(false);
		messageArea.setEditable(false);
		centerPanel.add(messageArea);
		
		
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		
		okButton = setupButton(OK_TEXT, 120, 36);
		okButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				close();
			}
		});
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		southPanel.add(okButton);
		
	}
	
	public void setMessage(String message){
		messageArea.setText(message);
	}
	
}
