package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class SearchBar extends BackgroundPanel{
	
	private static final int BAR_HEIGHT = 60;
	private static final int BAR_WIDTH = 800;
	private BufferedImage bImage;
	private Border border;
	private int boarderWidth = 3;
	
	private JPanel centerPanel = new JPanel(new FlowLayout());
	
	protected SearchBar(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(new Color(218, 232, 252), new Color(126, 166, 224), BAR_WIDTH, BAR_HEIGHT, true));
		
		setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		setLayout(new BorderLayout(0,0));
		setBounds(0, 0, BAR_WIDTH, BAR_HEIGHT);		
		
		
		//----------------------Border---------------------
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, new Color(108, 142, 191));
		setBorder(border);
		setOpaque(true);
		
		
		
		
		
		
		
		
		//---------------------Settings button---------------------
		int buttonWidth = BAR_HEIGHT - 10;
		int buttonHeight = BAR_HEIGHT - 10;
		CustomButton settingsButton = new CustomButton("", 0, 0, buttonWidth, buttonHeight);
		settingsButton.setImageFromFile("gear.png", true);
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(sm.window, "//This is your settings", null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(settingsButton, BorderLayout.WEST);
		
		
		//----------------------Search Box---------------------
		JTextField searchBox = new JTextField("SearchBar");
		searchBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchBox.setForeground(Color.BLACK);
		searchBox.setBackground(Color.WHITE);
		searchBox.setPreferredSize(new Dimension(500, (int)(BAR_HEIGHT * 0.6)));
		searchBox.setOpaque(true);
		centerPanel.add(searchBox, BorderLayout.CENTER);
		searchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.PASSWORD_STATE);
			}
		});
		
		
		
		
		
		//----------------------Title---------------------
		JLabel titleLabel = new JLabel("SafeBox");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titleLabel.setLocation(700, 20);
		add(titleLabel, BorderLayout.EAST);
		setTransparentAdd(true);
		add(centerPanel, BorderLayout.CENTER);
	}
	
}
