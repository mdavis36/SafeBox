package gui;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

import gui.StateManager.ValidStates;

public class PasswordState extends State{
	
	protected PasswordState(StateManager sm) {
		this.sm = sm;
		//ptitle.setLocation(200, 100);
		
		//add components to list

		
		//add components to JFrame window
		//addComponentsToWindow();
		init();
		clear();
	}

	@Override
	protected void init() {
		System.out.println("Init : Password State");
	}

	@Override
	protected void draw() {
		//viewComponentsOnWindow();
		System.out.println("Draw : Password State");
	}

	@Override
	protected void clear() {
		//clearComponentsOnWindow();
		System.out.println("Clear : Password State");
	}

	@Override
	public void mouseClicked() {
		sm.setState(ValidStates.MainScreenState);
		
	}

	@Override
	public void mouseEntered() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased() {
		// TODO Auto-generated method stub
		
	}


}
