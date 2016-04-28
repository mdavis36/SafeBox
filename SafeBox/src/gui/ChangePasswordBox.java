package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.HintManager;

public class ChangePasswordBox extends MessageBoxState {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4219722141628883742L;
	
	//numbers Start//
	private static final double BUTTON_HEIGHT_RATIO = .6;
	private static final int BUTTON_WIDTH = 80;
	private static final int BUTTON_BORDER_WIDTH = 2;
	//numbers End//
	
	//Strings Start//
	private static final String save = "Save";
	private static final String initCurrentPasswordField = "Current Password";
	static final String initNewPasswordField = "New Password";
	static final String initConfirmPasswordField = "Retype New Password";
	static final String initHintField = "New password hint";
	static final String title = "Change Password/Hint";
	static final String PASSWORD_MEETS = "Password must meet requirements";
	static final String PASSWORDS_DONT_MATCH = "Passwords and confirm password didn't match.";
	static final String CURRENT_DIDNT_MATCH = "Current password didn't match.";
	//Strings End//
	
	//JObjects Start//
	private static JLabel titleLabel = new JLabel(title);
	private static CustomButton saveButton = new CustomButton(save, 0, 0, BUTTON_WIDTH, (int) (BAR_HEIGHT * BUTTON_HEIGHT_RATIO));
	private JPanel userInput = new JPanel(new GridBagLayout());
	private JPasswordField currentPasswordField = new JPasswordField(initCurrentPasswordField);
	private JPasswordField newPasswordField = new JPasswordField(initNewPasswordField);
	private JPasswordField confirmPasswordField = new JPasswordField(initConfirmPasswordField);
	private JTextField hintField = new JTextField(initHintField);
	
	private PlainMessageDialog passwordFailure;
	
	public ChangePasswordBox(final StateManager sm){
		//Buttons Start//
		//passwordFailure = new PlainMessageDialog(sm, PASSWORD_MEETS);
		buttons.setBackground(Consts.BLUE_PANEL_COLOUR_DARK);
		buttons.setLayout(new FlowLayout());
		cancelButton.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT, Consts.BUTTON_COLOUR_DARK, true);
		cancelButton.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, BUTTON_BORDER_WIDTH);
		saveButton.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT, Consts.BUTTON_COLOUR_DARK, true);
		saveButton.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, BUTTON_BORDER_WIDTH);
		cancelButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				resetBox();
			}
		});
		
		
		saveButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {				
				if (sm.getESM().isCurrentPassword(currentPasswordField.getPassword())){
					if (!sm.getESM().passwordMeetsRequirements(newPasswordField.getPassword())){
						//passwordFailure.setText(PASSWORD_MEETS);
						//passwordFailure.setVisible(true);
						return;
					}
					
					if (Arrays.equals(newPasswordField.getPassword(), confirmPasswordField.getPassword())){
						sm.getESM().setPassword(newPasswordField.getPassword());
						HintManager.setHint(hintField.getText());
						resetBox();
					} else {
						//passwordFailure.setText(PASSWORDS_DONT_MATCH);
						//passwordFailure.setVisible(true);
					}
				} else {
					//passwordFailure.setText(CURRENT_DIDNT_MATCH);
					//passwordFailure.setVisible(true);
				}
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
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, 24));
		titlePanel.add(titleLabel);
		titlePanel.setBackground(Consts.BLUE_PANEL_COLOUR_DARK);

		//Title End//
		
		//User Input Start//
		userInput.setBackground(Consts.BLUE_PANEL_COLOUR_DARK);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = 4;
		c.gridy = 0;
		currentPasswordField.setPreferredSize(new Dimension(350,40));
		currentPasswordField.setEchoChar((char)0);
		currentPasswordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (currentPasswordField.getEchoChar() != Consts.ECHO_CHAR){
					currentPasswordField.setText("");
					currentPasswordField.setEchoChar(Consts.ECHO_CHAR);
				}
				
			}
		});
		userInput.add(currentPasswordField, c);
		
		c.gridy = 1;
		newPasswordField.setPreferredSize(new Dimension(350,40));
		newPasswordField.setEchoChar((char)0);
		newPasswordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (newPasswordField.getEchoChar() != Consts.ECHO_CHAR){
					newPasswordField.setText("");
					newPasswordField.setEchoChar(Consts.ECHO_CHAR);
				}
			}
		});
		userInput.add(newPasswordField,c);
		
		c.gridy = 2;
		confirmPasswordField.setPreferredSize(new Dimension(350,40));
		confirmPasswordField.setEchoChar((char)0);
		confirmPasswordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (confirmPasswordField.getEchoChar() != Consts.ECHO_CHAR){
					confirmPasswordField.setText("");
					confirmPasswordField.setEchoChar(Consts.ECHO_CHAR);
				}
			}
		});
		userInput.add(confirmPasswordField,c);
		
		c.gridy = 3;
		hintField.setPreferredSize(new Dimension(350,40));
		hintField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (hintField.getText() != initHintField){
					hintField.setText("");
				}
			}
		});
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
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				resetPasswordField(currentPasswordField, initCurrentPasswordField);
				resetPasswordField(newPasswordField, initNewPasswordField);
				resetPasswordField(confirmPasswordField, initConfirmPasswordField);
				if ("".equals(hintField.getText())){
					hintField.setText(initHintField);
				}
			}
		});
		//Frame End//
	}
	
	private void resetPasswordField(JPasswordField p, String text){
		if(p.getPassword().length == 0){
			p.setEchoChar((char)0);
			p.setText(text);
		}
	}

	@Override
	protected void resetBox() {
		this.setVisible(false);
		this.currentPasswordField.setText(initCurrentPasswordField);
		this.newPasswordField.setText(initNewPasswordField);
		this.confirmPasswordField.setText(initConfirmPasswordField);
		this.hintField.setText(initHintField);
		
	}
	
	
}
