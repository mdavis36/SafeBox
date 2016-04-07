package gui;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import com.sun.media.jfxmedia.control.VideoFormat;
import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing.Validation;

import gui.StateManager.ValidStates;

public class MainScreenState extends State{
	JLabel pword;
	
	protected MainScreenState(StateManager sm) {
		this.sm = sm;
		pword = new JLabel();
		components.add(pword);
	}
	
	@Override
	protected void init() {
		System.out.println("MainScreen State");
		pword.setText("Main Screen State");
		pword.setLocation(100, 100);
		clear();
	}

	@Override
	protected void draw() {
		
	}

	@Override
	protected void clear() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		sm.setState(ValidStates.PasswordState);
		
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
