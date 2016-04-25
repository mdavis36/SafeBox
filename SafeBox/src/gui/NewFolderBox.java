package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewFolderBox extends MessageBoxState{
	static final String createRecord = "Create Record";
	static final String createFolder = "Create Folder";
	static final String initTextField = "Name of Record/Folder";
	private JPanel userInput = new JPanel();
	private JPanel buttons = new JPanel();
	private JPanel label = new JPanel();
	private static CustomButton newFolderButton = new CustomButton("Create Folder", 0, 0, 150, (int)(BAR_HEIGHT * 0.6));	
	private static CustomButton newRecordButton = new CustomButton("Create Record", 0, 0, 150, (int)(BAR_HEIGHT * 0.6));
	private JTextField nameField = new JTextField("Name of Record/Folder");
	private JLabel nameLabel = new JLabel("Name your folder/record");
	
	public NewFolderBox(StateManager sm){
		setLayout(new BorderLayout());
		//pane = new JOptionPane(frame);
		//Buttons Start//
		buttons.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		buttons.setLayout(new FlowLayout());
		newFolderButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		newFolderButton.setBoarderDetails(MiscUtils.ORANGE_PANEL_COLOUR_BORDER, 2);
		newRecordButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		newRecordButton.setBoarderDetails(MiscUtils.ORANGE_PANEL_COLOUR_BORDER, 2);
		cancelButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		cancelButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		buttons.add(cancelButton, BorderLayout.WEST);
		buttons.add(newFolderButton);
		buttons.add(newRecordButton, BorderLayout.EAST);
		//Buttons End//
		
		//User Input Start//
		nameField.setSize(300,50);
		userInput.setBorder(new EmptyBorder(0,50,20,50));
		userInput.setLayout(new BorderLayout(0,50));
		userInput.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		userInput.add(nameField, BorderLayout.CENTER);
		//User Input End//
		
		//Label Start//
		nameLabel.setSize(300,200);
		nameLabel.setBorder(new EmptyBorder(10,0,0,0));
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setLayout(new FlowLayout());
		label.add(nameLabel);
		label.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		//Label End//
		
		//Setting Size Start//
		//frame.setSize(450,200);
		buttons.setPreferredSize(new Dimension(450,60));
		userInput.setPreferredSize(new Dimension(450,60));
		label.setPreferredSize(new Dimension(450,60));
		//Settting Size End//
		
		//Lock Main Window Start//
		sm.loseFocus();
		//Lock Main Window End//
		
		//Setting Frame Start//
		add(label, BorderLayout.NORTH);
		add(userInput, BorderLayout.CENTER);
		add(buttons,BorderLayout.SOUTH);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//		frame.setResizable(false);
//		frame.setAlwaysOnTop(true);
		//pane.setModal(true);
		//pane.setAlwaysOnTop(true);
		//pane.setModalityType(ModalityType.APPLICATION_MODAL);
		//Setting Frame End//
		
		nameField.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (nameField.getText().equals(initTextField)){
					nameField.setText("");
				}
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		);
	}
	
	public static void main(String[] args){
		JFrame main = new JFrame();
		//StateManager sm = new StateManager(main);
		//NewFolderBox test = new NewFolderBox();
	}
}
