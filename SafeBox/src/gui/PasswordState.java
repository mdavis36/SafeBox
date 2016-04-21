package gui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PasswordState extends State{
	private static final long serialVersionUID = 1L;
	private final int buttonWidth = 270;
	private final int buttonHeight = 270 / 7;
	private final int textBoxWidth = 330;
	
	private void validatePasswordAndMoveForward(JPasswordField passwordField){
		if (sm.getEFSM().fileSystemExists()){
			sm.getEFSM().setPassword(passwordField.getPassword());
			
			if (sm.getEFSM().loadFileSystemHandler()){
				sm.cl.show(sm.cards, sm.MAIN_SCREEN_STATE);
			} else {
				JOptionPane.showMessageDialog(sm.window, "Incorrect password, please try again.", null, JOptionPane.PLAIN_MESSAGE);
				passwordField.setText("");
			}
			
		} else {
			sm.cl.show(sm.cards, sm.MAIN_SCREEN_STATE);
		}
	}
	
	protected PasswordState(final StateManager sm) {
		this.sm = sm;
		
		
		JLabel titleLabel = new JLabel("SafeBox");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titleLabel.setBounds(321, 75, 100, 50);
		add(titleLabel);
		
		
		
		JLabel lblSafeboxLogo = new JLabel("//RUNNING FROM MAIN");
		int logoWidth = 160;
		lblSafeboxLogo.setSize(new Dimension(logoWidth, 20));
		BufferedImage logo = MiscUtils.getBufferedImageFromFile("res/logos/largeLogo.png", lblSafeboxLogo.getWidth());
		lblSafeboxLogo.setIcon(new ImageIcon(logo));
		lblSafeboxLogo.setSize(new Dimension(logo.getWidth(), logo.getHeight()));
		lblSafeboxLogo.setLocation((sm.window.getWidth() / 2) - (logo.getWidth() / 2), (sm.window.getHeight() / 2) - (logo.getHeight() / 2) - 100);
		add(lblSafeboxLogo);
		
		final JPasswordField passWordField = new JPasswordField();
		passWordField.setBounds((sm.window.getWidth() / 2) - (textBoxWidth / 2), 
								(sm.window.getHeight() / 2) + 15, 
								textBoxWidth,
								40);
		passWordField.setVisible(false);
		add(passWordField);
		
		CustomButton enterSBButton = new CustomButton("Enter SafeBox",
														(sm.window.getWidth() / 2) - (buttonWidth / 2), 
														(sm.window.getHeight() / 2) + 65, 
														buttonWidth,
														buttonHeight);
		enterSBButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		enterSBButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		enterSBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validatePasswordAndMoveForward(passWordField);
			}
				
		});
		add(enterSBButton);
		
		
		
		CustomButton forgotPWButton = new CustomButton("Forgot Password",
														(sm.window.getWidth() / 2) - (buttonWidth / 2), 
														(sm.window.getHeight() / 2) + 110, 
														buttonWidth,
														buttonHeight);
		forgotPWButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		forgotPWButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		forgotPWButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(sm.window, "//This is your hint", null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(forgotPWButton);		
		
		
		final JTextField textField = new JTextField("Enter Password");
		textField.setFont(new Font("Arial", Font.PLAIN, 25));
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setBounds((sm.window.getWidth() / 2) - (textBoxWidth / 2), 
							(sm.window.getHeight() / 2) + 15, 
							textBoxWidth,
							40);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setEditable(false);
		textField.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				passWordField.setVisible(true);
				textField.setVisible(false);
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		add(textField);
		
		
		
		setBackground(new Color(102, 153, 255));
		setLayout(null);
		addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouse click on screen");
				if(new String(passWordField.getPassword()).equals("")){
					System.out.println("field empty");
					textField.setVisible(true);
					passWordField.setVisible(false);
				}
			}
		});
		
	}

}
