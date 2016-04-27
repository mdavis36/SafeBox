package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class ModifyFolderBox extends MessageBoxState {

	public ModifyFolderBox(){
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetBox();
			}
		});
		button1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				button1Action();
			}
		});
		button2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				button2Action();
			}
		});
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textFieldAction();
			}
		});
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2774570685172705815L;
	//Strings Start//
	protected String button1Text;
	protected String button2Text;
	protected String initTextField;
	//Strings Ends//
	
	//JObjects Start//
	protected  final CustomButton button1 = new CustomButton(null, 0, 0, LONG_BUTTON_WIDTH, BUTTON_HEIGHT);
	protected  final CustomButton button2 = new CustomButton(null, 0, 0, LONG_BUTTON_WIDTH, BUTTON_HEIGHT);
	protected final JTextField textField = new JTextField(null);
	protected JPanel userInput = new JPanel();	
	//JObjects End//
	

	@Override
	protected void resetBox() {		
	}
	//Setup Process: setStrings -> init
	protected void setStrings(String button1String, String button2String, String textFieldString, String titleString){
		button1.setText(button1String);
		button2.setText(button2String);
		cancelButton.setText(cancel);
		initTextField = textFieldString;
		titleLabel.setText(titleString);
	}
	
	protected void init(){
		titleLabel.setFont(new Font(MiscUtils.FONT_STYLE, Font.BOLD, 24));
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(450,180));
		userInput.setPreferredSize(new Dimension(450, 60));
		textField.setPreferredSize(new Dimension(300, 40));
		textField.setBorder(new EmptyBorder(0,0,20,0));
		buttons.setPreferredSize(new Dimension(450, 60));
		title.setPreferredSize(new Dimension(450, 40));
		title.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		buttons.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		buttons.setBorder(new EmptyBorder(0,10,20,10));
		title.add(titleLabel, BorderLayout.CENTER);
		textField.setText(initTextField);
		userInput.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		userInput.add(textField, BorderLayout.CENTER);
		drawButton(cancelButton);
		drawButton(button1);
		drawButton(button2);
		buttons.add(cancelButton, BorderLayout.WEST);
		buttons.add(button1, BorderLayout.CENTER);
		buttons.add(button2, BorderLayout.EAST);
		panel.add(buttons,BorderLayout.SOUTH);
		panel.add(userInput, BorderLayout.CENTER);
		panel.add(title, BorderLayout.NORTH);
		panel.setVisible(true);
		setSize(new Dimension(450, 180));
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		add(panel);
		setVisible(false);
	}

	protected abstract void button1Action();
	protected abstract void button2Action();
	protected abstract void textFieldAction();
}
