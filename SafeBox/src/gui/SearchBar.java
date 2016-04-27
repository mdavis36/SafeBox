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
	
	private static final String SEARCH_BAR_STARTING_VALUE = "Search";

	private static final int SEARCH_FONT_SIZE = 11;
	private static final int SEARCH_BOX_WIDTH = 400;
	private static final int SEARCH_BUTTON_WIDTH = 80;
	private static final String SEARCH_BUTTON_TEXT = "Search";
	private static final String TITLE = "SafeBox";
	private static final String GEAR_IMAGE = "gear.png";
	private static final double HEIGHT_RATIO = .6;
	private static final String LARGE_LOGO_IMAGE = "res/logos/largeLogo.png";
	private static final int TITLE_FONT_SIZE = 32;
	private static final int TITLE_LOCATION_X = 700;
	private static final int TITLE_LOCATION_Y = 20;
	private static final int LOGOUT_BUTTON_WIDTH_HEIGHT = 40;
	
	private static final Color SEARCH_BUTTON_COLOR_1 = new Color(255, 205, 40);
	private static final Color SEARCH_BUTTON_COLOR_2 = new Color(255, 165, 0);
	private static final Color SEARCH_BUTTON_COLOR_BORDER = new Color(215, 155, 0);
	private static final int SEARCH_BUTTON_BORDER_WIDTH = 2;
	
	
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
		super(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, BAR_WIDTH, BAR_HEIGHT, true));
		//setSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		//setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		setLayout(new BorderLayout(20, 0));
		setBounds(0, 0, BAR_WIDTH, BAR_HEIGHT);	
		changePasswordDialogBox = new ChangePasswordBox(sm);
		changePasswordDialogBox.setVisible(false);
		
		//----------------------Border---------------------
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, Consts.BLUE_PANEL_COLOUR_DARK);
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
		settingsButton.setImageFromFile(GEAR_IMAGE, true);
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
		searchBox.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, SEARCH_FONT_SIZE));

		searchBox.setForeground(Color.BLACK);
		searchBox.setBackground(Color.WHITE);
		searchBox.setPreferredSize(new Dimension(SEARCH_BOX_WIDTH, (int)(BAR_HEIGHT * HEIGHT_RATIO)));
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
		CustomButton searchButton = new CustomButton(SEARCH_BUTTON_TEXT, 0, 0, SEARCH_BUTTON_WIDTH, (int)(BAR_HEIGHT * HEIGHT_RATIO));
		searchButton.setGradientBackground(SEARCH_BUTTON_COLOR_1, SEARCH_BUTTON_COLOR_2, true);
		searchButton.setBoarderDetails(SEARCH_BUTTON_COLOR_BORDER, SEARCH_BUTTON_BORDER_WIDTH);
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
		JLabel titleLabel = new JLabel(TITLE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, TITLE_FONT_SIZE));
		titleLabel.setLocation(TITLE_LOCATION_X, TITLE_LOCATION_Y);
		rightPanel.add(titleLabel);
		
		
		//-----------------------Log Out------------------------
		CustomButton logOutButton = new CustomButton("", 0,0,LOGOUT_BUTTON_WIDTH_HEIGHT,LOGOUT_BUTTON_WIDTH_HEIGHT);
		logOutButton.setImageIcon(MiscUtils.getBufferedImageFromFile(LARGE_LOGO_IMAGE, logOutButton.getWidth()), false);
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
