package gui;

import java.awt.Color;

import javax.swing.JLabel;

public class AddRecordFieldDialog extends CustomDialog{

	JLabel test;
	
	public AddRecordFieldDialog(Color c1, Color c2, int w, int h) {
		super(c1, c2, w, h);
		test = new JLabel("Testing subclass");
		centerPanel.add(test);
	}

}
