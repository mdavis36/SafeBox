package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;

import core.EncryptedFileSystemManager;

public class StateManager implements MouseListener{
	protected JFrame window;
	
	public enum ValidStates{
		PasswordState,
		MainScreenState
	}
	private ValidStates currentState;
	private ValidStates currentBoxState;
	
	private HashMap<ValidStates, State> states;
	private HashMap<ValidStates, MessageBoxState> boxStates;
	private core.EncryptedFileSystemManager eFSM;
	
	protected StateManager(JFrame window){
		this.window = window;
		window.addMouseListener(this);
		
		currentState = ValidStates.PasswordState;
		currentBoxState = null;
		
		populateStates();
		init();
		draw();
	}
	
	private void populateStates(){
		states = new HashMap<ValidStates, State>();
		
		PasswordState passwordState = new PasswordState(this);
		MainScreenState mainScreenState = new MainScreenState(this);
		
		states.put(ValidStates.PasswordState, passwordState);
		states.put(ValidStates.MainScreenState, mainScreenState);
	}
	
	protected void init(){
		states.get(currentState).init();
	}
	
	protected void draw(){
		states.get(currentState).draw();
	}
	
	protected void clear(){
		states.get(currentState).clear();
	}
	
	protected void setState(ValidStates state){
		clear();
		currentState = state;
		init();
		draw();
	}
	
	protected EncryptedFileSystemManager getEFSM(){
		return eFSM;
	}

	public void mouseClicked(MouseEvent e) {
		states.get(currentState).mouseClicked();
	}

	public void mouseEntered(MouseEvent e) {
		states.get(currentState).mouseEntered();
	}

	public void mouseExited(MouseEvent e) {
		states.get(currentState).mouseExited();
	}

	public void mousePressed(MouseEvent e) {
		states.get(currentState).mousePressed();
	}

	public void mouseReleased(MouseEvent e) {
		states.get(currentState).mouseReleased();
	}
	
}
