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

import core.EncryptedStorageManager;
import core.HintManager;
import core.LockManager;

public class StartUpDialog extends CustomDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 9098577891844549546L;
	private static final int TITLE_TEXT_SIZE = 28;
	private static final int MESSAGE_TEXT_SIZE = 16;

	private JLabel title;
	private JLabel message;

	private JPasswordField password;
	private JPasswordField confirmPassword;
	private JTextField hint;

	private CustomButton done;
	private CustomButton exit;

	private void configureNewPasswordAndClose(char[] password, char[] confirmPassword, String hint) {
		EncryptedStorageManager esm = sm.getESM();

		if (!esm.passwordMeetsRequirements(password)) {
			// too short
			sm.showPlainMessage(English.PASSWORD_DID_NOT_MEET_REQUIREMENTS_ERROR);
			return;
		}

		if (!Arrays.equals(password, confirmPassword)) {
			// not equal
			sm.showPlainMessage(English.PASSWORDS_DID_NOT_MATCH_ERROR);
			return;
		}

		esm.setPassword(password);
		esm.saveFileSystemHandler();
		HintManager.setHint(hint);
		close();
	}

	public StartUpDialog(StateManager sm, Color c1, Color c2, int w, int h) {
		super(sm, c1, c2, w, h);

		//--------------------north panel----------------------------------
		northPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		title = new JLabel(English.WELCOME_TO_SAFEBOX);
		title.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, TITLE_TEXT_SIZE));

		message = new JLabel(English.CREATE_PASSWORD_HINT);
		message.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, MESSAGE_TEXT_SIZE));

		c.gridy = 0;
		northPanel.add(title, c);
		c.gridy = 1;
		northPanel.add(message, c);

		//--------------------Center panel----------------------------------
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		password = new JPasswordField(English.PASSWORD);
		initPasswordField(password, English.PASSWORD);
		password.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				resetPasswordField(password, English.PASSWORD);
			}

			@Override
			public void focusGained(FocusEvent e) {
				passwordFieldClick(password);
			}
		});
		c.gridy = 0;
		centerPanel.add(password, c);

		confirmPassword = new JPasswordField(English.CONFIRM_PASSWORD);
		initPasswordField(confirmPassword, English.CONFIRM_PASSWORD);
		confirmPassword.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				resetPasswordField(confirmPassword, English.CONFIRM_PASSWORD);
			}

			@Override
			public void focusGained(FocusEvent e) {
				passwordFieldClick(confirmPassword);
			}
		});
		c.gridy = 1;
		centerPanel.add(confirmPassword, c);

		hint = new JTextField(English.HINT);
		hint.setPreferredSize(new Dimension(350, 30));
		hint.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if ("".equals(hint.getText())) {
					hint.setText(English.HINT);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (hint.getText().equals(English.HINT)) {
					hint.setText("");
				}
			}
		});
		hint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		c.gridy = 2;
		centerPanel.add(hint, c);

		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

		done = setupButton(English.DONE, 120, 36);
		done.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				configureNewPasswordAndClose(password.getPassword(), confirmPassword.getPassword(), hint.getText());
			}
		});

		exit = setupButton(English.EXIT, 120, 36);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				LockManager.releaseLock();
				System.exit(0);
			}
		});

		southPanel.add(done);
		southPanel.add(exit);
	}

	@Override
	protected void init() {
		title.setFocusable(true);
		title.requestFocus();
	}

}
