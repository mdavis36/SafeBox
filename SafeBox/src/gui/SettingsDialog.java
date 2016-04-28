package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.HintManager;

public class SettingsDialog extends CustomDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5922831387720313076L;
	
	private static final String save = "Save";
	private static final String cancel = "Cancel";
	private static final String settings = "Settings";
	
	private static final String initCurrentPasswordField = "Current Password";
	private static final String initNewPasswordField = "New Password";
	private static final String initConfirmPasswordField = "Retype New Password";
	private static final String initHintField = "New password hint";
	
	static final String PASSWORD_MEETS = "Password must meet requirements";
	static final String PASSWORDS_DONT_MATCH = "New password and confirm password didn't match.";
	static final String CURRENT_DIDNT_MATCH = "Current password didn't match.";
	
	
	private static JLabel titleLabel;
	private static CustomButton saveButton;
	private static CustomButton cancelButton;
	private JPasswordField currentPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	private JTextField hintField;
	private PlainMessageDialog passwordFailure;
	
	public SettingsDialog(final StateManager sm, Color c1, Color c2, int w, int h) {
		super(sm, c1, c2, w, h);
		passwordFailure = new PlainMessageDialog(sm, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, 450, 200,null);
		passwordFailure.setVisible(false);
		//--------------------north panel----------------------------------
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titleLabel = new JLabel(settings);
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, 24));
		northPanel.add(titleLabel);
		
		//--------------------Center panel----------------------------------
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridy = 0;
		currentPasswordField = new JPasswordField(initCurrentPasswordField);
		currentPasswordField.setPreferredSize(new Dimension(350,30));
		currentPasswordField.setEchoChar((char)0);
		centerPanel.add(currentPasswordField,c);
		c.gridy++;
		newPasswordField = new JPasswordField(initNewPasswordField);
		newPasswordField.setPreferredSize(new Dimension(350,30));
		newPasswordField.setEchoChar((char)0);
		centerPanel.add(newPasswordField,c);
		c.gridy++;
		confirmPasswordField = new JPasswordField(initConfirmPasswordField);
		confirmPasswordField.setPreferredSize(new Dimension(350,30));
		confirmPasswordField.setEchoChar((char)0);
		centerPanel.add(confirmPasswordField,c);
		c.gridy++;
		hintField = new JTextField(initHintField);
		hintField.setPreferredSize(new Dimension(350,30));
		centerPanel.add(hintField,c);
		
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		cancelButton = setupButton(cancel, 120, 36);
		saveButton = setupButton(save, 120, 36);
		southPanel.add(cancelButton);
		southPanel.add(saveButton);
		
		//--------------------Listeners----------------------------------
		titleLabel.setFocusable(true);
		titleLabel.requestFocus();
		currentPasswordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (currentPasswordField.getEchoChar() != Consts.ECHO_CHAR){
					currentPasswordField.setText("");
					currentPasswordField.setEchoChar(Consts.ECHO_CHAR);
				}
				
			}
		});
		newPasswordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (newPasswordField.getEchoChar() != Consts.ECHO_CHAR){
					newPasswordField.setText("");
					newPasswordField.setEchoChar(Consts.ECHO_CHAR);
				}
			}
		});
		confirmPasswordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (confirmPasswordField.getEchoChar() != Consts.ECHO_CHAR){
					confirmPasswordField.setText("");
					confirmPasswordField.setEchoChar(Consts.ECHO_CHAR);
				}
			}
		});
		hintField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (hintField.getText() != initHintField){
					hintField.setText("");
				}
			}
		});
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
		
		cancelButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		
		saveButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {				
				if (sm.getESM().isCurrentPassword(currentPasswordField.getPassword())){
					if (!sm.getESM().passwordMeetsRequirements(newPasswordField.getPassword())){
						passwordFailure.setMessage(PASSWORD_MEETS);
						passwordFailure.setVisible(true);
						return;
					}
					
					if (Arrays.equals(newPasswordField.getPassword(), confirmPasswordField.getPassword())){
						sm.getESM().setPassword(newPasswordField.getPassword());
						HintManager.setHint(hintField.getText());
						close();
					} else {
						passwordFailure.setMessage(PASSWORDS_DONT_MATCH);
						passwordFailure.setVisible(true);
					}
				} else {
					passwordFailure.setMessage(CURRENT_DIDNT_MATCH);
					passwordFailure.setVisible(true);
				}
			}			
		});
		
	}

	private void resetPasswordField(JPasswordField p, String text){
		if(p.getPassword().length == 0){
			p.setEchoChar((char)0);
			p.setText(text);
		}
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}


}
