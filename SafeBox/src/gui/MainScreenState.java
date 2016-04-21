package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MainScreenState extends State{
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel = new JPanel();
		
	protected MainScreenState(final StateManager sm) {
		SearchBar searchBar = new SearchBar(sm);
		FolderDisplay folderDisplay = new FolderDisplay(sm);
		RecordDisplay recordDisplay = new RecordDisplay(sm);
		
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
}
	
