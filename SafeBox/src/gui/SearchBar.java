package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SearchBar extends JPanel{
	
	private static final int BAR_HEIGHT = 50;
	private static final int BAR_WIDTH = 800;
	
	
	protected SearchBar(final StateManager sm){
		
		setLayout(new FlowLayout());
		
		int buttonWidth = 40;
		int buttonHeight = 40;
		CustomButton settingsButton = new CustomButton("",
				"gear.png",
				0,//(BAR_HEIGHT / 2) - (buttonHeight / 2), 
				0,//(BAR_HEIGHT / 2) - (buttonHeight / 2), 
				buttonWidth,
				0,
				true);
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(sm.window, "//This is your settings", null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(settingsButton);
		
		JTextField searchBox = new JTextField("SearchBar");
		searchBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchBox.setForeground(Color.BLACK);
		searchBox.setBackground(Color.WHITE);
		searchBox.setPreferredSize(new Dimension(600, (int)(BAR_HEIGHT * 0.6)));
		add(searchBox);
		searchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.PASSWORD_STATE);
			}
		});
		
		JLabel titleLabel = new JLabel("SafeBox");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titleLabel.setLocation(700, 20);
		add(titleLabel);
		
	}
	
	
	
	
}
