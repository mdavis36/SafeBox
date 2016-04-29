package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import core.WindowSizeManager;

public class Main {

	private static final String TITLE = "SafeBox";
	private static final int DEFAULT_HEIGHT = 600;
	private static final int DEFAULT_WIDTH = 816;

	public static void main(String[] args) {

		int width = DEFAULT_WIDTH;
		int height = DEFAULT_HEIGHT;

		JFrame window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		if (WindowSizeManager.getWidth() >= DEFAULT_WIDTH && WindowSizeManager.getHeight() >= DEFAULT_HEIGHT) {
			width = WindowSizeManager.getWidth();
			height = WindowSizeManager.getHeight();
			System.out.println("Starting with stored size " + width + "x" + height);
		}
		window.setSize(width, height);
		window.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		StateManager sm = new StateManager(window);
		window.add(sm);
	}
}
