package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MainScreenState extends State{
	private static final long serialVersionUID = 1L;

	protected MainScreenState(final StateManager sm) {
		this.sm = sm;
		
		
		setLayout(null);
		
		JLabel lblMaincard = new JLabel("MainCard");
		lblMaincard.setBounds(325, 9, 96, 14);
		add(lblMaincard);
				
		JButton btnNewButton_1 = new JButton("Go to pass");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 153, 102));
		btnNewButton_1.setBounds(271, 104, 179, 124);
		add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.PASSWORD_STATE);
			}
		});
	}
}
	
