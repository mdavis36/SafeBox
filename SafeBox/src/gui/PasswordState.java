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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import core.HintManager;

public class PasswordState extends BackgroundPanel{
	private static final long serialVersionUID = 1L;
	private static final String initTextField = "Enter Password";
	private final int buttonWidth = 270;
	private final int buttonHeight = 270 / 7;
	private final int textBoxWidth = 330;
	private StateManager sm;
	private final JPasswordField passWordField;
	
	private void validatePasswordAndMoveForward(JPasswordField passwordField){
		sm.getESM().setPassword(passwordField.getPassword());
		if (sm.getESM().fileSystemExists()){			
			if (sm.getESM().loadFileSystemHandler()){
				sm.setState(sm.MAIN_SCREEN_STATE);
				sm.setSuccessfullyDecrypted(true);
				sm.init();
				sm.update();
			} else {
				JOptionPane.showMessageDialog(sm.window, "Incorrect password, please try again.", null, JOptionPane.PLAIN_MESSAGE);
				passwordField.setText("");
			}
			
		} else {
			if (passwordField.getPassword().length > 1){
				sm.setSuccessfullyDecrypted(true);
				sm.setState(sm.MAIN_SCREEN_STATE);
			}
		}
	}
	
	protected PasswordState(final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, sm.window.getWidth(), sm.window.getHeight(), true));
		
		this.sm = sm;
		setTransparentAdd(true);
		JLabel titleLabel = new JLabel("SafeBox");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titleLabel.setBounds(sm.window.getWidth()/2-50, 75, 100, 50);
		add(titleLabel);
		
		
		
		JLabel lblSafeboxLogo = new JLabel("//RUNNING FROM MAIN");
		int logoWidth = 160;
		lblSafeboxLogo.setSize(new Dimension(logoWidth, 20));
		BufferedImage logo = MiscUtils.getBufferedImageFromFile("res/logos/largeLogo.png", lblSafeboxLogo.getWidth());
		lblSafeboxLogo.setIcon(new ImageIcon(logo));
		lblSafeboxLogo.setSize(new Dimension(logo.getWidth(), logo.getHeight()));
		lblSafeboxLogo.setLocation((sm.window.getWidth() / 2) - (logo.getWidth() / 2), (sm.window.getHeight() / 2) - (logo.getHeight() / 2) - 100);
		add(lblSafeboxLogo);
		setTransparentAdd(false);
		passWordField = new JPasswordField();
		passWordField.setBounds((sm.window.getWidth() / 2) - (textBoxWidth / 2), 
								(sm.window.getHeight() / 2) + 15, 
								textBoxWidth,
								40);
		init();
		passWordField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(isInitTextField(passWordField.getPassword())){
					passWordField.setText("");
					passWordField.setEchoChar(MiscUtils.ECHO_CHAR);
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
		passWordField.setVisible(true);
		add(passWordField);
		setTransparentAdd(true);
		
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
				init();
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
				JOptionPane.showMessageDialog(sm.window, HintManager.getHint(), null, JOptionPane.PLAIN_MESSAGE);
				init();
			}
		});
		add(forgotPWButton);		
		
		setTransparentAdd(false);
		setTransparentAdd(true);
		
		
		setBackground(new Color(102, 153, 255));
		setLayout(null);
		addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				System.out.println("mouse click on screen");
				if(passWordField.getPassword().length == 0){
					init();
				}
			}
		});
		
	}

	protected void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void init(){
		passWordField.setText(initTextField);
		passWordField.setEchoChar((char)0);
	}
	
	private boolean isInitTextField(char[] cs){
		if(initTextField.equals(new String(cs))){
			return true;
		}
		else{
			return false;
		}
	}
}
