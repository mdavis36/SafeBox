package gui;

import com.sun.glass.events.KeyEvent;

import gui.StateManager.ValidStates;

public class PasswordState extends State{
	private static final long serialVersionUID = 1L;

	protected PasswordState(StateManager sm) {
		this.sm = sm;
		//ptitle.setLocation(200, 100);
		
		//add components to list
		
		//add components to JFrame window
		//addComponentsToWindow();
		init();
		clear();
	}

	protected void init() {
		System.out.println("Init : Password State");
	}

	protected void draw() {
		System.out.println("Draw : Password State");
	}

	protected void clear() {
		System.out.println("Clear : Password State");
	}

	public void mouseClicked() {
		sm.setState(ValidStates.MainScreenState);
	}

	public void mouseEntered() {
	}

	public void mouseExited() {
	}

	public void mousePressed() {
	}

	public void mouseReleased() {
	}

	protected void keyPressed(int k) {
		if (k == KeyEvent.VK_M){
			sm.setState(ValidStates.MessageBoxState);
		}
	}

	protected void keyReleased(int k) {
	}

	protected void keyTyped(int k) {
	}


}
