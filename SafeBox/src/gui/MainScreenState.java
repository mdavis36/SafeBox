package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MainScreenState extends BackgroundPanel{
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel = new JPanel();
	private SearchBar searchBar;
	private FolderDisplay folderDisplay;
	private RecordDisplay recordDisplay;
	protected MainScreenState(final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, sm.window.getWidth(), sm.window.getHeight(), true));
		setTransparentAdd(true);
		searchBar = new SearchBar(sm);
		folderDisplay = new FolderDisplay(sm);
		recordDisplay = new RecordDisplay(sm);
		
		mainPanel.setLayout(new BorderLayout(0,0));		
		mainPanel.add(folderDisplay, BorderLayout.WEST);
		mainPanel.add(recordDisplay, BorderLayout.CENTER);
		
		BorderLayout bl = new BorderLayout(0, 0);
		bl.setVgap(0);
		bl.setHgap(0);
		
		setLayout(bl);
		add(searchBar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		
	}

	protected void update() {
		folderDisplay.update();
	}
}
	
