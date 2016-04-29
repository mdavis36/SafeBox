package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
	
	private static JLabel titleLabel;
	private static CustomButton saveButton;
	private static CustomButton cancelButton;
	private JPasswordField currentPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	private JTextField hintField;
	
	public SettingsDialog(final StateManager sm, Color c1, Color c2, int w, int h) {
		super(sm, c1, c2, w, h);
		//--------------------north panel----------------------------------
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titleLabel = new JLabel(English.SETTINGS);
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, 24));
		northPanel.add(titleLabel);
		
		//--------------------Center panel----------------------------------
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridy = 0;
		currentPasswordField = new JPasswordField();
		initPasswordField(currentPasswordField, English.CURRENT_PASSWORD);
		centerPanel.add(currentPasswordField,c);
		
		c.gridy++;
		newPasswordField = new JPasswordField();
		initPasswordField(newPasswordField, English.NEW_PASSWORD);
		centerPanel.add(newPasswordField,c);
		
		c.gridy++;
		confirmPasswordField = new JPasswordField();
		initPasswordField(confirmPasswordField, English.RETYPE_NEW_PASSWORD);
		centerPanel.add(confirmPasswordField,c);
		
		c.gridy++;
		hintField = new JTextField(English.NEW_PASSWORD_HINT);
		hintField.setPreferredSize(new Dimension(350,30));
		centerPanel.add(hintField,c);
		
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		cancelButton = setupButton(English.CANCEL, 120, 36);
		saveButton = setupButton(English.SAVE, 120, 36);
		southPanel.add(cancelButton);
		southPanel.add(saveButton);
		
		//--------------------Listeners----------------------------------
		titleLabel.setFocusable(true);
		titleLabel.requestFocus();
		
		currentPasswordField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				resetPasswordField(currentPasswordField, English.CURRENT_PASSWORD);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				passwordFieldClick(currentPasswordField);
			}
		});
		
		newPasswordField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				resetPasswordField(newPasswordField, English.NEW_PASSWORD);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				passwordFieldClick(newPasswordField);
			}
		});
		
		confirmPasswordField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				resetPasswordField(confirmPasswordField, English.RETYPE_NEW_PASSWORD);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				passwordFieldClick(confirmPasswordField);
			}
		});
		
		hintField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if ("".equals(hintField.getText())){
					hintField.setText(English.NEW_PASSWORD_HINT);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (hintField.getText() != English.NEW_PASSWORD_HINT){
					hintField.setText("");
				}
			}
		});
	
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				resetPasswordField(currentPasswordField, English.CURRENT_PASSWORD);
				resetPasswordField(newPasswordField, English.NEW_PASSWORD);
				resetPasswordField(confirmPasswordField, English.RETYPE_NEW_PASSWORD);
				if ("".equals(hintField.getText())){
					hintField.setText(English.NEW_PASSWORD_HINT);
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
						sm.showPlainMessage(English.PASSWORD_MEETS);
						return;
					}
					
					if (Arrays.equals(newPasswordField.getPassword(), confirmPasswordField.getPassword())){
						sm.getESM().setPassword(newPasswordField.getPassword());
						HintManager.setHint(hintField.getText());
						close();
					} else {
						sm.showPlainMessage(English.PASSWORDS_DONT_MATCH);
					}
				} else {
					sm.showPlainMessage(English.CURRENT_DIDNT_MATCH);
				}
			}			
		});
		
	}
	

	@Override
	protected void init() {
		initPasswordField(currentPasswordField, English.CURRENT_PASSWORD);
		initPasswordField(newPasswordField, English.NEW_PASSWORD);
		initPasswordField(confirmPasswordField, English.RETYPE_NEW_PASSWORD);
		hintField.setText(English.NEW_PASSWORD_HINT);
		
	}


}
