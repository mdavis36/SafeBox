package gui;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.EncryptedStorageManager;

public class StateManager{
	protected JFrame window;

	protected final String PASSWORD_STATE = "passwordState";
	protected final String MAIN_SCREEN_STATE = "mainScreenState";
	
	private core.EncryptedStorageManager eSM;
	
	static JPanel cards;
	static CardLayout cl;
	
	private boolean successfullyDecrypted = false;
	
	boolean isSuccessfullyDecrypted() {
		return successfullyDecrypted;
	}

	void setSuccessfullyDecrypted(boolean successfullyDecrypted) {
		this.successfullyDecrypted = successfullyDecrypted;
	}

	protected StateManager(JFrame window){
		this.window = window;
		eSM = new EncryptedStorageManager();
		//Initialize cards
		cards = new JPanel(new CardLayout());
		cl = (CardLayout) cards.getLayout();
		
		populateStates();
		
		window.getContentPane().add(cards);
		cl.show(cards, PASSWORD_STATE);
		
		if(!eSM.fileSystemExists()){
			JOptionPane.showMessageDialog(window, "This is the first time running", null, JOptionPane.PLAIN_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(window, "A file system Exists", null, JOptionPane.PLAIN_MESSAGE);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
		    @Override
		    public void run(){
		    	if (successfullyDecrypted){
			    	System.out.println("Saving...");
			        eSM.saveFileSystemHandler();
			        System.out.println("Saved.");
		    	}
		    }
		});
		
		
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
