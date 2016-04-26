package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	
	private JPanel leftPanel = new JPanel(new FlowLayout(0));
	private JPanel centerPanel = new JPanel(new FlowLayout(0));
	private JPanel rightPanel = new JPanel(new FlowLayout(0));
	
	private static final String SEARCH_BAR_STARTING_VALUE = "SearchBar";
	
	ChangePasswordBox changePasswordDialogBox;
	RenameFolderBox renameFolderDialog;//TODO: REMOVE ME LATER

	
	protected SearchBar(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, BAR_WIDTH, BAR_HEIGHT, true));
		setSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		setLayout(new BorderLayout(20, 0));
		setBounds(0, 0, BAR_WIDTH, BAR_HEIGHT);	
		renameFolderDialog = new RenameFolderBox(sm);
		renameFolderDialog.setVisible(false);//TODO: REMOVE ME AFTER TESTING
		
		
		//----------------------Border---------------------
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, MiscUtils.BLUE_PANEL_COLOUR_DARK);
		setBorder(border);
		setOpaque(true);
		changePasswordDialogBox = new ChangePasswordBox(sm);
		changePasswordDialogBox.setVisible(false);
		
		
		//-----------------------------------------
		//------------- LEFT PANEL ----------------
		//-----------------------------------------
		
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//---------------------Settings button---------------------
		int buttonWidth = BAR_HEIGHT - 10;
		int buttonHeight = BAR_HEIGHT - 10;
		CustomButton settingsButton = new CustomButton("", 0, 0, buttonWidth, buttonHeight);
		settingsButton.setImageFromFile("gear.png", true);
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(sm.window, "//This is your settings", null, JOptionPane.PLAIN_MESSAGE);
				changePasswordDialogBox.setVisible(true);
			}
		});
		leftPanel.add(settingsButton);
		
		
		//-------------------------------------------
		//------------- CENTER PANEL ----------------
		//-------------------------------------------
		
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//----------------------Search Box---------------------
		final JTextField searchBox = new JTextField(SEARCH_BAR_STARTING_VALUE);
		searchBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		searchBox.setForeground(Color.BLACK);
		searchBox.setBackground(Color.WHITE);
		searchBox.setPreferredSize(new Dimension(400, (int)(BAR_HEIGHT * 0.6)));
		searchBox.setOpaque(true);
		searchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.PASSWORD_STATE);
			}
		});
		searchBox.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (searchBox.getText().equals(SEARCH_BAR_STARTING_VALUE)){
					searchBox.setText("");
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		);
		
		CustomButton searchButton = new CustomButton("Search", 0, 0, 80, (int)(BAR_HEIGHT * 0.6));
		searchButton.setGradientBackground(new Color(255, 205, 40), new Color(255, 165, 0), true);
		searchButton.setBoarderDetails(new Color(215, 155, 0), 2);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(sm.window, "You", null, JOptionPane.PLAIN_MESSAGE);
				renameFolderDialog.setVisible(true);
			}
		});
		
		centerPanel.add(searchBox);
		centerPanel.add(searchButton);
		
		
		//------------------------------------------
		//------------- RIGHT PANEL ----------------
		//------------------------------------------
		
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//----------------------Title---------------------
		JLabel titleLabel = new JLabel("SafeBox");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		titleLabel.setLocation(700, 20);
		
		
		
		rightPanel.add(titleLabel);
		
		
		setTransparentAdd(true);
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
	}
	
}
