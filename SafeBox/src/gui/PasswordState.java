package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PasswordState extends State{
	private static final long serialVersionUID = 1L;
	private final int buttonWidth = 270;
	private final int textBoxWidth = 330;
	
	protected PasswordState(final StateManager sm) {
		this.sm = sm;
		
		
		JLabel lblPasscard = new JLabel("SafeBox");
		lblPasscard.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasscard.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblPasscard.setBounds(321, 75, 100, 50);
		add(lblPasscard);
		
		
		
		JLabel lblSafeboxLogo = new JLabel("//RUNNING FROM MAIN");
		lblSafeboxLogo.setIcon(new ImageIcon("res/logos/largeLogo.png"));
		lblSafeboxLogo.setBounds(321, 170, 162, 14);
		add(lblSafeboxLogo);
		
		
		
		CustomButton enterSBButton = new CustomButton("Enter SafeBox",
														"buttonBackground1.png",
														(sm.window.getWidth() / 2) - (buttonWidth / 2), 
														(sm.window.getHeight() / 2) + 65, 
														buttonWidth,
														0,
														true);
		enterSBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.MAIN_SCREEN_STATE);
			}
		});
		add(enterSBButton);
		
		
		
		CustomButton forgotPWButton = new CustomButton("Forgot Password",
														"buttonBackground1.png", 
														(sm.window.getWidth() / 2) - (buttonWidth / 2), 
														(sm.window.getHeight() / 2) + 110, 
														buttonWidth,
														0,
														true);
		forgotPWButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JOptionPane.showMessageDialog(sm.window, "//This is your hint", null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(forgotPWButton);
		
		
		
		final JPasswordField passWordField = new JPasswordField();
		passWordField.setBounds((sm.window.getWidth() / 2) - (textBoxWidth / 2), 
								(sm.window.getHeight() / 2) + 15, 
								textBoxWidth,
								40);
		passWordField.setVisible(false);
		add(passWordField);
		
		
		
		final JTextField textField = new JTextField("Enter Password");
		textField.setFont(new Font("Arial", Font.PLAIN, 25));
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setBounds((sm.window.getWidth() / 2) - (textBoxWidth / 2), 
							(sm.window.getHeight() / 2) + 15, 
							textBoxWidth,
							40);
		textField.setHorizontalAlignment(JTextField.CENTER);
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
				if(passWordField.getPassword() == null){
					textField.setVisible(true);
					passWordField.setVisible(false);
				}
			}
		});
		
	}

}
