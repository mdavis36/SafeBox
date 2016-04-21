package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;

public class MainScreenState extends State{
	private static final long serialVersionUID = 1L;

	protected MainScreenState(final StateManager sm) {
		SearchBar searchBar = new SearchBar(sm);
		FolderDisplay folderDisplay = new FolderDisplay(sm);
		RecordDisplay recordDisplay = new RecordDisplay(sm);
		
		
		setLayout(new BorderLayout(0,0));
		add(searchBar, BorderLayout.NORTH);
		add(folderDisplay, BorderLayout.WEST);
		add(recordDisplay, BorderLayout.CENTER);
		
	}
}
	
