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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import core.FileSystemHandler;
import core.Folder;
import core.Node;

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
	
	private void executeSearch(StateManager sm, String query){
		FileSystemHandler fsh = sm.getESM().getFileSystemHandler();
		ArrayList<Node> results = fsh.search(query, fsh.getCurrentRecord());
		Node searchResults = new Node(new Folder("Search Results"), results);
		searchResults.setParent(fsh.getRoot());
		fsh.setCurrentNode(searchResults);
		sm.update();
	}

	
	protected SearchBar(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, BAR_WIDTH, BAR_HEIGHT, true));
		//setSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		//setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		setLayout(new BorderLayout(20, 0));
		setBounds(0, 0, BAR_WIDTH, BAR_HEIGHT);	
		changePasswordDialogBox = new ChangePasswordBox(sm);
		changePasswordDialogBox.setVisible(false);
		
		//----------------------Border---------------------
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, MiscUtils.BLUE_PANEL_COLOUR_DARK);
		setBorder(border);
		setOpaque(true);
		
		
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
				executeSearch(sm, searchBox.getText());
			}
		});
		searchBox.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				if (searchBox.getText().equals(SEARCH_BAR_STARTING_VALUE)){
					searchBox.setText("");
				}
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		
		
		//------------------------Search Button -------------------------
		CustomButton searchButton = new CustomButton("Search", 0, 0, 80, (int)(BAR_HEIGHT * 0.6));
		searchButton.setGradientBackground(new Color(255, 205, 40), new Color(255, 165, 0), true);
		searchButton.setBoarderDetails(new Color(215, 155, 0), 2);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeSearch(sm, searchBox.getText());
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
		
		
		//-----------------------Log Out------------------------
		CustomButton logOutButton = new CustomButton("", 0,0,40,40);
		logOutButton.setImageIcon(MiscUtils.getBufferedImageFromFile("res/logos/largeLogo.png", logOutButton.getWidth()), false);
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sm.isSuccessfullyDecrypted()){
					sm.getESM().saveFileSystemHandler();
				}
				sm.setState(sm.PASSWORD_STATE);
				sm.init();
			}
		});
		rightPanel.add(logOutButton);
		//---------------------------------------------
		//----------------- SEARCHBAR -----------------
		//---------------------------------------------
		setTransparentAdd(true);
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
	}
	
}
