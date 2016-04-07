package gui;

import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Main {

	private static final String TITLE = "SafeBox";
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	
	private static StateManager sm;
	
	public static void main(String[] args){		
		JFrame window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.addMouseListener((MouseListener) sm);
		window.setSize(WIDTH, HEIGHT);
		window.setVisible(true);
	
		sm = new StateManager(window);
		sm.init();
		sm.draw();
	}
}
