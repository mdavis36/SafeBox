package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

public class MainScreenState extends State{
	private static final long serialVersionUID = 1L;

	protected MainScreenState(final StateManager sm) {
		this.sm = sm;
		
		SearchBar searchBar = new SearchBar(sm);
		FolderDisplay folderDisplay = new FolderDisplay(sm);
		RecordDisplay recordDisplay = new RecordDisplay(sm);
		
		
		setLayout(new BorderLayout(0,0));
		add(searchBar, BorderLayout.NORTH);
		add(folderDisplay, BorderLayout.WEST);
		add(recordDisplay, BorderLayout.CENTER);
		
	}
}
	
