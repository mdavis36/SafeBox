package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	private static final String TITLE = "SafeBox";
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	
	public static void main(String[] args){		
		JFrame window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setSize(WIDTH, HEIGHT);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		new StateManager(window);
	}
}
