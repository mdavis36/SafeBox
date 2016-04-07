package gui;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import gui.StateManager.ValidStates;

public class PasswordState extends State{
	JLabel pword;
	
	protected PasswordState(StateManager sm) {
		this.sm = sm;
		pword = new JLabel();
		components.add(pword);
	}

	@Override
	protected void init() {
		System.out.println("Password State");
		pword.setText("Password State");
		pword.setLocation(50, 50);
	}

	@Override
	protected void draw() {
		for(int i = 0; i < components.size(); i++){
			components.get(i).setVisible(true);
		}
	}

	@Override
	protected void clear() {
		for(int i = 0; i < components.size(); i++){
			components.get(i).setVisible(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		sm.setState(ValidStates.MainScreenState);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
