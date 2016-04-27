package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import core.HintManager;

/**
 * @author micha
 *
 */
/**
 * @author micha
 *
 */
public class PasswordState extends BackgroundPanel{
	private static final long serialVersionUID = 1L;
	private static final String PASSWORD_TITLE = "           Enter Password           ";
	
	private static final int BUTTON_WIDTH = 270;
	private static final int BUTTON_HEIGHT = BUTTON_WIDTH / 7;
	private static final int PASSWORD_WIDTH = 330;
	
	private static final String TITLE = "SafeBox";
	private static final String FORGOT_PASSWORD_TITLE = "ForgotPassword";
	private static final String ENTER_SAFEBOX_TITLE = "Enter SafeBox";
	
	private StateManager sm;
	
	private JPanel centerPanel;
	
	private final JPasswordField passWordField;
	
	
	protected PasswordState(final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, sm.window.getWidth(), sm.window.getHeight(), true));
		this.sm = sm;
	
		setLayout(new BorderLayout());
		centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		
		
		setTransparentAdd(true);
		JLabel titleLabel = new JLabel(TITLE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font(MiscUtils.FONT_STYLE, Font.BOLD, 24));
		titleLabel.setBounds(sm.window.getWidth()/2-50, 75, 100, 50);
		centerPanel.add(titleLabel, c);
		
		
		c.gridy += 1;
		JLabel lblSafeboxLogo = new JLabel();
		int logoWidth = 160;
		lblSafeboxLogo.setSize(new Dimension(logoWidth, 20));
		BufferedImage logo = MiscUtils.getBufferedImageFromFile("res/logos/largeLogo.png", lblSafeboxLogo.getWidth());
		lblSafeboxLogo.setIcon(new ImageIcon(logo));
		lblSafeboxLogo.setSize(new Dimension(logo.getWidth(), logo.getHeight()));
		lblSafeboxLogo.setLocation((sm.window.getWidth() / 2) - (logo.getWidth() / 2), (sm.window.getHeight() / 2) - (logo.getHeight() / 2) - 100);
		centerPanel.add(lblSafeboxLogo, c);
		
		
		c.gridy += 1;
		setTransparentAdd(false);
		passWordField = new JPasswordField();
		passWordField.setLocation(0, 0);
		passWordField.setSize(new Dimension (PASSWORD_WIDTH, 40));
		passWordField.setFont(new Font(MiscUtils.FONT_STYLE, Font.PLAIN, 24));
		resetPasswordField(passWordField);
		passWordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(isInitTextField(passWordField.getPassword())){
					passWordField.setText("");
					passWordField.setEchoChar(MiscUtils.ECHO_CHAR);
					passWordField.setEditable(true);
				}
			}
		});
		passWordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == e.VK_ENTER){
					validatePasswordAndMoveForward(passWordField);
				}
			}
		});
		centerPanel.add(passWordField, c);
		
		
		c.gridy += 1;
		setTransparentAdd(true);
		CustomButton enterSBButton = setupButton(ENTER_SAFEBOX_TITLE);
		enterSBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validatePasswordAndMoveForward(passWordField);
				init();
			}
				
		});
		centerPanel.add(enterSBButton, c);
		
		
		c.gridy += 1;
		CustomButton forgotPWButton = setupButton(FORGOT_PASSWORD_TITLE);
		forgotPWButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(sm.window, HintManager.getHint(), null, JOptionPane.PLAIN_MESSAGE);
				init();
			}
		});
		centerPanel.add(forgotPWButton, c);
		
		
		add(centerPanel, BorderLayout.CENTER);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(passWordField.getPassword().length == 0){
					resetPasswordField(passWordField);
				}
			}
		});		
	}

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
	
	protected void update() {
		
	}
	
	public void init(){
		resetPasswordField(passWordField);
	}
	
	private void resetPasswordField(JPasswordField p){
		p.setText(PASSWORD_TITLE);
		p.setEchoChar((char)0);
		p.setEditable(false);
	}
	
	/**
	 * @param text
	 * @return
	 */
	private CustomButton setupButton(String text){
		CustomButton b = new CustomButton(text,
											0, 
											0, 
											BUTTON_WIDTH,
											BUTTON_HEIGHT);
		b.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		return b;
	}
	
	private boolean isInitTextField(char[] cs){
		if(PASSWORD_TITLE.equals(new String(cs))){
			return true;
		}
		else{
			return false;
		}
	}
}
