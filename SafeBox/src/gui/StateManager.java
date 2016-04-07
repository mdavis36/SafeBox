package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;

import core.EncryptedFileSystemManager;

public class StateManager implements MouseListener, KeyListener{
	protected JFrame window;
	
	public enum ValidStates{
		PasswordState,
		MainScreenState,
		MessageBoxState
	}
	
	public enum ValidMBoxStates{
		NoMessageBox,
		FirstStateBox
	}
	
	private ValidStates lastState;
	private ValidStates currentState;
	private ValidMBoxStates currentBoxState;
	
	private HashMap<ValidStates, State> states;
	private HashMap<ValidStates, MessageBoxState> boxStates;
	private core.EncryptedFileSystemManager eFSM;
	
	protected StateManager(JFrame window){
		this.window = window;
		window.addMouseListener(this);
		window.addKeyListener(this);
		
		lastState = ValidStates.PasswordState;
		currentState = ValidStates.PasswordState;
		currentBoxState = ValidMBoxStates.NoMessageBox;
		
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
		if (currentState != ValidStates.MessageBoxState){
			states.get(currentState).init();
		}
		else{
			
		}
		
	}
	
	protected void draw(){
		if (currentState != ValidStates.MessageBoxState){
			states.get(currentState).draw();
		}
		else{
			
		}
		
	}
	
	protected void clear(){
		if (currentState != ValidStates.MessageBoxState){
			states.get(currentState).clear();
		}
		else{
			
		}
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


	public void keyPressed(KeyEvent k) {
		states.get(currentState).keyPressed(k.getID());
	}


	public void keyReleased(KeyEvent k) {
		states.get(currentState).keyReleased(k.getID());
	}


	public void keyTyped(KeyEvent k) {
		states.get(currentState).keyTyped(k.getID());
		
	}
	
}
