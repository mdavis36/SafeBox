package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordDisplayButton extends CustomButton{

	private int index;
	
	public RecordDisplayButton(String text, int x, int y, int width, int height, int index, final StateManager sm) {
		super(text, x, y, width, height);
		// TODO Auto-generated constructor stub
		this.index = index;
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
