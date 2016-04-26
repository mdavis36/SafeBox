package gui;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import core.HintManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class ChangePasswordBox extends MessageBoxState {
	//Strings Start//
	private static final String save = "Save";
	private static final String initCurrentPasswordField = "Current Password";
	static final String initNewPasswordField = "New Password";
	static final String initConfirmPasswordField = "Retype New Password";
	static final String initHintField = "New password hint";
	static final String title = "Change Password/Hint";
	//Strings End//
	
	//JObjects Start//
	private static JLabel titleLabel = new JLabel(title);
	private static CustomButton saveButton = new CustomButton(
			save, 0, 0, 80, (int) (BAR_HEIGHT * 0.6));
	private JPanel userInput = new JPanel(new GridBagLayout());
	private JPasswordField currentPasswordField = new JPasswordField(initCurrentPasswordField);
	private JPasswordField newPasswordField = new JPasswordField(initNewPasswordField);
	private JPasswordField confirmPasswordField = new JPasswordField(initConfirmPasswordField);
	private JTextField hintField = new JTextField(initHintField);
	
	public ChangePasswordBox(final StateManager sm){
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
				setVisible(false);
				currentPasswordField.setText(initCurrentPasswordField);
				newPasswordField.setText(initNewPasswordField);
				confirmPasswordField.setText(initConfirmPasswordField);
				hintField.setText(initHintField);
			}

			@Override
			public void mousePressed(MouseEvent e) {
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
		saveButton.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {				
				if (sm.getESM().isCurrentPassword(currentPasswordField.getPassword())){
					if (!sm.getESM().passwordMeetsRequirements(newPasswordField.getPassword())){
						JOptionPane.showMessageDialog(sm.window, "Password must meet requirements", null, JOptionPane.PLAIN_MESSAGE);
						return;
					}
					
					if (Arrays.equals(newPasswordField.getPassword(), confirmPasswordField.getPassword())){
						sm.getESM().setPassword(newPasswordField.getPassword());
						
						HintManager.setHint(hintField.getText());
						
						setVisible(false);
						currentPasswordField.setText(initCurrentPasswordField);
						newPasswordField.setText(initNewPasswordField);
						confirmPasswordField.setText(initConfirmPasswordField);
						hintField.setText(initHintField);
					} else {
						JOptionPane.showMessageDialog(sm.window, "Passwords and confirm password didn't match.", null, JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(sm.window, "Current password didn't match.", null, JOptionPane.PLAIN_MESSAGE);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
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
		cancelButton.setBorder(new EmptyBorder(0,50,0,50));
		saveButton.setBorder(new EmptyBorder(0,50,0,50));
		buttons.setBorder( new EmptyBorder(0,0,20,0));
		buttons.add(cancelButton, BorderLayout.WEST);
		buttons.add(saveButton,BorderLayout.EAST);
		//Buttons End//
		
		//Title Start//
		titlePanel = new JPanel(new FlowLayout());
		titleLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titlePanel.add(titleLabel);
		titlePanel.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);

		//Title End//
		
		//User Input Start//
		userInput.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = 4;
		c.gridy = 0;
		currentPasswordField.setPreferredSize(new Dimension(350,40));
		userInput.add(currentPasswordField, c);
		c.gridy = 1;
		newPasswordField.setPreferredSize(new Dimension(350,40));
		userInput.add(newPasswordField,c);
		c.gridy = 2;
		confirmPasswordField.setPreferredSize(new Dimension(350,40));
		userInput.add(confirmPasswordField,c);
		c.gridy = 3;
		hintField.setPreferredSize(new Dimension(350,40));
		userInput.add(hintField,c);
		//User Input End//
		
		//Frame Start//
		panel.setLayout(new BorderLayout());
		panel.add(buttons, BorderLayout.SOUTH);
		panel.add(titlePanel, BorderLayout.NORTH);
		panel.add(userInput,BorderLayout.CENTER);
		add(panel);
		setSize(new Dimension(450, 400));
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		//Frame End//
	}
	
	
}
