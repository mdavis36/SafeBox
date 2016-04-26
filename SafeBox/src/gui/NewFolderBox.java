package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewFolderBox extends MessageBoxState {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7873243537744491713L;
	static final String createRecord = "Create Record";
	static final String createFolder = "Create Folder";
	static final String initTextField = "Name of Record/Folder";
	private JPanel userInput = new JPanel();
	private JPanel label = new JPanel();
	private static CustomButton newFolderButton = new CustomButton(
			createFolder, 0, 0, 150, (int) (BAR_HEIGHT * 0.6));
	private static CustomButton newRecordButton = new CustomButton(
			createRecord, 0, 0, 150, (int) (BAR_HEIGHT * 0.6));
	private JTextField nameField = new JTextField("Name of Record/Folder");
	private JLabel nameLabel = new JLabel("Name your folder/record");

	public NewFolderBox(final StateManager sm) {
		final StateManager state = sm;
		// Buttons Start//
		buttons.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		buttons.setLayout(new FlowLayout());
		newFolderButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT,
				MiscUtils.BUTTON_COLOUR_DARK, true);
		newFolderButton.setBoarderDetails(MiscUtils.ORANGE_PANEL_COLOUR_BORDER,
				2);
		newRecordButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT,
				MiscUtils.BUTTON_COLOUR_DARK, true);
		newRecordButton.setBoarderDetails(MiscUtils.ORANGE_PANEL_COLOUR_BORDER,
				2);
		cancelButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT,
				MiscUtils.BUTTON_COLOUR_DARK, true);
		cancelButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		cancelButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				resetBox();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});
		newFolderButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkForValidText(nameField.getText())){
					createFolder(nameField.getText(), sm);
					resetBox();
				}
				else{
					notValidText(state);
					return;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});
		newRecordButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkForValidText(nameField.getText())){
					createRecord(nameField.getText(), state);
					resetBox();
				}
				else{
					notValidText(state);
					return;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

		});
		buttons.add(cancelButton, BorderLayout.WEST);
		buttons.add(newFolderButton, BorderLayout.CENTER);
		buttons.add(newRecordButton, BorderLayout.EAST);
		// Buttons End//

		// User Input Start//
		nameField.setPreferredSize(new Dimension(300, 60));
		userInput.setBorder(new EmptyBorder(0, 50, 10, 50));
		userInput.setLayout(new BorderLayout(0, 50));
		userInput.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		userInput.add(nameField, BorderLayout.CENTER);
		// User Input End//

		// Label Start//
		nameLabel.setSize(300, 200);
		nameLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setLayout(new FlowLayout());
		label.add(nameLabel);
		label.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		// Label End//

		// Setting Size Start//
		buttons.setPreferredSize(new Dimension(450, 60));
		userInput.setPreferredSize(new Dimension(450, 60));
		label.setPreferredSize(new Dimension(450, 60));
		// Settting Size End//

		// Setting Frame Start//
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.add(label, BorderLayout.NORTH);
		panel.add(userInput, BorderLayout.CENTER);
		panel.add(buttons, BorderLayout.SOUTH);
		setSize(new Dimension(450, 180));
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		// Setting Frame End//

		nameField.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (nameField.getText().equals(initTextField)) {
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

		});

	}
	
	@Override
	protected void resetBox(){
		this.setVisible(false);
		this.nameField.setText(initTextField);
	}

	private boolean checkForValidText(String text){
		if(text.equals(initTextField) || "".equals(text) || text.equals(" ")){
			return false;
		}
		else{
			return true;
		}
	}
	
	private void notValidText(StateManager sm){
		JOptionPane.showMessageDialog(sm.window, "Not a valid name.", null, JOptionPane.PLAIN_MESSAGE);
	}

	private void createRecord(String name, StateManager sm){
		sm.getESM().getFileSystemHandler().createRecord(sm.getESM().getFileSystemHandler().getCurrent(), name);
		sm.update();
	}
	
	private void createFolder(String name, StateManager sm){
		sm.getESM().getFileSystemHandler().createFolder(sm.getESM().getFileSystemHandler().getCurrent(), name);
		sm.update();
	}
}
