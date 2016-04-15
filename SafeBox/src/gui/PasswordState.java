package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.sun.glass.events.KeyEvent;

public class PasswordState extends State{
	private static final long serialVersionUID = 1L;

	protected PasswordState(final StateManager sm) {
		this.sm = sm;
		
		setLayout(null);
		JLabel lblPasscard = new JLabel("SafeBox");
		lblPasscard.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasscard.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblPasscard.setBounds(321, 75, 100, 50);
		add(lblPasscard);
		
		JLabel lblSafeboxLogo = new JLabel("//RUNNING FROM MAIN");
		lblSafeboxLogo.setBounds(321, 170, 162, 14);
		add(lblSafeboxLogo);
		
		CustomButton enterSBButton = new CustomButton("Enter SafeBox",
														"buttonBackground1.png",
														(sm.window.getWidth() / 2) - (270 / 2), 
														(sm.window.getHeight() / 2) - (60 / 2) + 40, 
														270,
														200,
														true);
		enterSBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.MAIN_SCREEN_STATE);
			}
		});
		
		add(enterSBButton);
		
		CustomButton forgotPWButton = new CustomButton("Forgot Password",
														"buttonBackground1.png", 
														(sm.window.getWidth() / 2) - (270 / 2), 
														(sm.window.getHeight() / 2) - (60 / 2) + 100, 
														270,
														0,
														true);
		forgotPWButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JOptionPane.showMessageDialog(sm.window, "//This is your hint", null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(forgotPWButton);
	}

}
