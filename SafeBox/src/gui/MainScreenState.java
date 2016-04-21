package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;

public class MainScreenState extends State{
	private static final long serialVersionUID = 1L;

	protected MainScreenState(final StateManager sm) {
		SearchBar searchBar = new SearchBar(sm);
		FolderDisplay folderDisplay = new FolderDisplay(sm);
		RecordDisplay recordDisplay = new RecordDisplay(sm);
		
		BorderLayout bl = new BorderLayout(0, 0);
		bl.setVgap(0);
		bl.setHgap(0);
		
		setLayout(bl);
		add(searchBar, BorderLayout.NORTH);
		add(folderDisplay, BorderLayout.WEST);
		add(recordDisplay, BorderLayout.CENTER);
		
	}
}
	
