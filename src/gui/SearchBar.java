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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import core.FileSystemHandler;
import core.Folder;
import core.Node;

public class SearchBar extends BackgroundPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 6350678714653901296L;
	private static final int BAR_HEIGHT = 60;
	private static final int BAR_WIDTH = 800;
	private Border border;
	private int boarderWidth = 3;

	private JPanel leftPanel = new JPanel(new FlowLayout(0));
	private JPanel centerPanel = new JPanel(new FlowLayout(0));
	private JPanel rightPanel = new JPanel(new FlowLayout(0));

	private static final int SEARCH_FONT_SIZE = 11;
	private static final int SEARCH_BOX_WIDTH = 400;
	private static final int SEARCH_BUTTON_WIDTH = 80;

	private static final double HEIGHT_RATIO = .6;
	private static final String LARGE_LOGO_IMAGE = "res/logos/smallButtonLogo.png";
	private static final int TITLE_FONT_SIZE = 32;
	private static final int LOGOUT_BUTTON_WIDTH = 30;
	private static final int LOGOUT_BUTTON_HEIGHT = 40;
	private static final int BUTTON_MINUS = 15;
	private static final int LAYOUT_HGAP = 20;

	private static final int SEARCH_BUTTON_BORDER_WIDTH = 2;
	private static final int SETTINGS_WIDTH = 450;
	private static final int SETTINGS_HEIGHT = 300;

	SettingsDialog settingsBox;

	private void executeSearch(StateManager sm, String query) {
		FileSystemHandler fsh = sm.getESM().getFileSystemHandler();
		ArrayList<Node> results = fsh.search(query, fsh.getCurrentRecord());
		Node searchResults = new Node(new Folder(English.SEARCH_RESULTS), results);
		searchResults.setGlobalIndex(-1);
		searchResults.setParent(fsh.getRoot());
		fsh.setCurrentNode(searchResults);
		sm.update();
	}

	protected SearchBar(final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, BAR_WIDTH, BAR_HEIGHT, true));
		//setSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		//setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
		setLayout(new BorderLayout(LAYOUT_HGAP, 0));
		setBounds(0, 0, BAR_WIDTH, BAR_HEIGHT);
		settingsBox = new SettingsDialog(sm, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, SETTINGS_WIDTH, SETTINGS_HEIGHT);
		settingsBox.setVisible(false);

		//----------------------Border---------------------
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, Consts.BLUE_PANEL_COLOUR_DARK);
		setBorder(border);
		setOpaque(true);

		//-----------------------------------------
		//------------- LEFT PANEL ----------------
		//-----------------------------------------
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//---------------------Settings button---------------------
		int buttonWidth = BAR_HEIGHT - BUTTON_MINUS;
		int buttonHeight = BAR_HEIGHT - BUTTON_MINUS;
		CustomButton settingsButton = new CustomButton("", 0, 0, buttonWidth, buttonHeight);
		settingsButton.setImageFromFile(Consts.GEAR_IMAGE, true);
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settingsBox.open();
			}
		});
		leftPanel.add(settingsButton);

		//-------------------------------------------
		//------------- CENTER PANEL ----------------
		//-------------------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//----------------------Search Box---------------------

		final JTextField searchBox = new JTextField(English.SEARCH);
		searchBox.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, SEARCH_FONT_SIZE));

		searchBox.setForeground(Color.BLACK);
		searchBox.setBackground(Color.WHITE);
		searchBox.setPreferredSize(new Dimension(SEARCH_BOX_WIDTH, (int) (BAR_HEIGHT * HEIGHT_RATIO)));
		searchBox.setOpaque(true);
		searchBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				executeSearch(sm, searchBox.getText());
			}
		});
		searchBox.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (searchBox.getText().equals(English.SEARCH)) {
					searchBox.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		//------------------------Search Button -------------------------
		CustomButton searchButton = new CustomButton(English.SEARCH, 0, 0, SEARCH_BUTTON_WIDTH, (int) (BAR_HEIGHT * HEIGHT_RATIO));
		searchButton.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT, Consts.BUTTON_COLOUR_DARK, true);
		searchButton.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, SEARCH_BUTTON_BORDER_WIDTH);
		searchButton.addActionListener(new ActionListener() {
			@Override
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
		JLabel titleLabel = new JLabel(English.SAFE_BOX);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, TITLE_FONT_SIZE));
		rightPanel.add(titleLabel);

		//-----------------------Log Out------------------------
		CustomButton logOutButton = new CustomButton("", 0, 0, LOGOUT_BUTTON_WIDTH, LOGOUT_BUTTON_HEIGHT);
		logOutButton.setImageIcon(MiscUtils.getBufferedImageFromFile(LARGE_LOGO_IMAGE, logOutButton.getWidth()), false);
		logOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sm.isSuccessfullyDecrypted()) {
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
