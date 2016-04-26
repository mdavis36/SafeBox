package gui;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import core.*;
public class RenameFolderBox extends MessageBoxState {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5787629861094186120L;
	//Strings Start//
	private static final String title = "Rename:";
	private static final String renameText = "Rename";
	private String currentName;
	private static final String save = "Save";
	private static final String initRenameField = "Rename here";
	//Strings End//
	
	//JObjects Start//
	private JLabel nameOfFolder;
	private JPanel userInput = new JPanel(new BorderLayout());
	private static CustomButton saveButton = new CustomButton(save, 0, 0, 80, (int) (BAR_HEIGHT * 0.6));
	private JTextField renameField = new JTextField(initRenameField);
	//JObjects End//
	
	public RenameFolderBox(StateManager sm){
		final StateManager state = sm;
		currentName = state.getESM().getFileSystemHandler().getCurrent().getData().getName();//Gets name of folder you are in
		//Buttons Start//
		buttons.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		buttons.setLayout(new FlowLayout());
		cancelButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT,
				MiscUtils.BUTTON_COLOUR_DARK, true);
		cancelButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		saveButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT,
				MiscUtils.BUTTON_COLOUR_DARK, true);
		saveButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		cancelButton.addMouseListener(new MouseListener(){

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
		saveButton.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: Change depending on how implementing renaming button
				if(checkForValidText(renameField.getText())){
					state.getESM().getFileSystemHandler().getCurrent().getData().setName(renameField.getText());
					resetBox();
				}
				else{
					notValidText(state);
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
		cancelButton.setBorder(new EmptyBorder(0,50,0,50));
		saveButton.setBorder(new EmptyBorder(0,50,0,50));
		buttons.setBorder( new EmptyBorder(0,0,20,0));
		buttons.add(cancelButton, BorderLayout.WEST);
		buttons.add(saveButton,BorderLayout.EAST);
		//Buttons End//
		
		//Title Start//
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = 4;
		c.gridy = 0;
		nameOfFolder = new JLabel(currentName);
		titleLabel = new JLabel(title);
		titlePanel = new JPanel(new GridBagLayout());
		titleLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titlePanel.add(titleLabel,c);
		c.gridy = 1;
		titlePanel.add(nameOfFolder,c);
		titlePanel.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		//Title End//
		
		// User Input Start//
		renameField.setForeground(Color.LIGHT_GRAY);
		renameField.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(renameField.getText() == initRenameField){
					renameField.setText("");
					renameField.setForeground(Color.BLACK);
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
		renameField.setPreferredSize(new Dimension(300, 60));
		userInput.setBorder(new EmptyBorder(0, 50, 10, 50));
		userInput.setLayout(new BorderLayout(0, 50));
		userInput.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		userInput.add(renameField, BorderLayout.CENTER);
		
		//Frame Start//
		panel.setLayout(new BorderLayout());
		panel.add(buttons, BorderLayout.SOUTH);
		panel.add(titlePanel, BorderLayout.NORTH);
		panel.add(userInput,BorderLayout.CENTER);
		add(panel);
		setSize(new Dimension(450, 200));
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		//Frame End//
		
	}
	public void setName(String name){
		
	}
	@Override
	protected void resetBox() {
		this.setVisible(false);
		this.renameField.setText(initRenameField);
	}
	
	private boolean checkForValidText(String text){
		if(text.equals(initRenameField) && text.equals("") && text.equals(" ")){
			return false;
		}
		else{
			return true;
		}
	}
	
	private void notValidText(StateManager sm){
		JOptionPane.showMessageDialog(sm.window, "Not a valid name.", null, JOptionPane.PLAIN_MESSAGE);
	}
}
