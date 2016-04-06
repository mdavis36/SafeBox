package gui;

import java.util.HashMap;

import core.EncryptedFileSystemManager;

public class StateManager {
	private int currentState = 0;
	private int currentBoxState = 0;
	
	private enum ValidStates{
		
	}
	private HashMap<ValidStates, State> states;
	private HashMap<ValidStates, MessageBoxState> boxStates;
	private core.EncryptedFileSystemManager eFSM;
	
	protected StateManager(){
		
	}
	
	private void populateStates(){
		
	}
	
	protected void init(){
		
	}
	
	protected void draw(){
		
	}
	
	protected void clear(){
		
	}
	
	protected void setState(ValidStates state){
		
	}
	
	protected EncryptedFileSystemManager getEFSM(){
		return eFSM;
	}
	
}
