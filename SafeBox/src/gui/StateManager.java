package gui;

import java.util.HashMap;

import javax.swing.JFrame;

import core.EncryptedFileSystemManager;

public class StateManager {
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
		populateStates();
		
		currentState = ValidStates.PasswordState;
		currentBoxState = null;
		
	}
	
	private void populateStates(){
		states = new HashMap<ValidStates, State>();
		
		PasswordState passwordState = new PasswordState(this);
		
		states.put(ValidStates.PasswordState, passwordState);
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
	
}
