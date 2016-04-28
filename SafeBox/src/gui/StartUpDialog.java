package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StartUpDialog extends CustomDialog{
	
	private static final String TITLE = "Welcome to SafeBox!";
	private static final String MESSAGE = "Please create a password and a hint in case you forget.";
	private static final String PASSWORD_TEXT = "Password";
	private static final String CON_PASSWORD_TEXT = "Confirm Password";
	private static final String HINT_TEXT = "Hint";
	private static final String DONE_TEXT = "Done";
	
	private static final int TITLE_TEXT_SIZE = 28;
	private static final int MESSAGE_TEXT_SIZE = 16;
	
	private JLabel title;
	private JLabel message;
	
	private JPasswordField  password;
	private JPasswordField  confirmPassword;
	private JTextField hint;
	
	private CustomButton done;

	public StartUpDialog(StateManager sm, Color c1, Color c2, int w, int h) {
		super(sm, c1, c2, w, h);

		//--------------------north panel----------------------------------
		northPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		title = new JLabel(TITLE);
		title.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, TITLE_TEXT_SIZE));
		
		message = new JLabel(MESSAGE);
		message.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, MESSAGE_TEXT_SIZE));
		
		c.gridy = 0;
		northPanel.add(title, c);
		c.gridy = 1;
		northPanel.add(message, c);
				
				
		//--------------------Center panel----------------------------------
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		
		password = new JPasswordField(PASSWORD_TEXT);
		password.setEchoChar((char)0);
		password.setPreferredSize(new Dimension(350,30));
		password.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				password.setEchoChar(Consts.ECHO_CHAR);
			}
		});
		c.gridy = 0;
		centerPanel.add(password, c);
		
		confirmPassword = new JPasswordField(CON_PASSWORD_TEXT);
		confirmPassword.setEchoChar((char)0);
		confirmPassword.setPreferredSize(new Dimension(350,30));
		confirmPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				confirmPassword.setEchoChar(Consts.ECHO_CHAR);
			}
		});
		c.gridy = 1;
		centerPanel.add(confirmPassword, c);
		
		hint = new JTextField(HINT_TEXT);
		hint.setPreferredSize(new Dimension(350,30));
		hint.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				hint.setText("");
			}
		});
		c.gridy = 2;
		centerPanel.add(hint, c);	
	
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		
		done = setupButton(DONE_TEXT, 120, 36);
		done.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				close();
			}
		});
		
		southPanel.add(done);
		
	}

}