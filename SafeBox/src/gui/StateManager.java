package gui;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.EncryptedStorageManager;

public class StateManager{
	protected JFrame window;

	protected final String PASSWORD_STATE = "passwordState";
	protected final String MAIN_SCREEN_STATE = "mainScreenState";
	
	private core.EncryptedStorageManager eSM;
	
	static JPanel cards;
	static CardLayout cl;
	
	protected StateManager(JFrame window){
		this.window = window;
		
		//Initialize cards
		cards = new JPanel(new CardLayout());
		cl = (CardLayout) cards.getLayout();
		
		populateStates();
		
		window.getContentPane().add(cards);
		cl.show(cards, PASSWORD_STATE);
		
	}
	
	private void populateStates(){
		
		PasswordState passwordState = new PasswordState(this);
		MainScreenState mainScreenState = new MainScreenState(this);
		
		cards.add(mainScreenState, MAIN_SCREEN_STATE);
		cards.add(passwordState, PASSWORD_STATE);
	}
	
	
	protected EncryptedStorageManager getEFSM(){
		return eSM;
	}
	
}
