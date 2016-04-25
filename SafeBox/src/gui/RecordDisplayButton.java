package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RecordDisplayButton extends BackgroundPanel{
	
	private int index;
	private static final int FOLDER = 0;
	private static final int RECORD = 0;
	
	
	public RecordDisplayButton(String text, int x, int y, int width, int height, final int index, final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, width, height, true));
		this.index = index;
		setSize(new Dimension(width, height));
		setLayout(new FlowLayout(10));
	
		CustomButton button = new CustomButton(text, 0, 0, 40, 40);
	
		
		
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setHorizontalTextPosition(JButton.RIGHT);
		
		button.setImageFromFile("folder.png", true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		
		add(button, BorderLayout.CENTER);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
	}
	
	private void recordAction(){
		
	}
	
	protected int getIndex(){
		return index;
	}
	
	protected void setIndex(int i){
		index = i;
	}
}
