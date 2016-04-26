package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FolderDisplayButton extends BackgroundPanel{
	
	private int index;
	
	RenameFolderBox renameDeleteBox;
	/**
	 * @param text name of the folder
	 * @param x x coordinate of button
	 * @param y y coordinate of button
	 * @param width width of the button
	 * @param height height of the button
	 * @param index index of the folder
	 * @param sm the state it is placed on
	 */
	public FolderDisplayButton(String text, int x, int y, int width, int height, final int index, final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, width, height, true));
		this.index = index;
		setSize(new Dimension(width, height));
		setLayout(new FlowLayout(10));
		renameDeleteBox = new RenameFolderBox(sm,index);
		renameDeleteBox.setVisible(false);
	
		CustomButton button = new CustomButton(text, 0, 0, 40, 40);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setHorizontalTextPosition(JButton.RIGHT);
		
		button.setImageFromFile("folder.png", true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sm.getESM().getFileSystemHandler().setCurrentNode(sm.getESM().getFileSystemHandler().getCurrent().getChild(index));
				System.out.println("clicked child : " + index);
				sm.update();
			}
		});
		
		CustomButton edit = new CustomButton("", 0, 0, 20, 20);
		edit.setImageIcon(MiscUtils.layerBufferedImages(MiscUtils.getBufferedGradImage(MiscUtils.BUTTON_COLOUR_LIGHT, 
																						MiscUtils.BUTTON_COLOUR_DARK, 
																						20, 
																						20, 
																						true), 
														MiscUtils.getBufferedImageFromFile("res/buttons/pen.png", 
																						20)),
						true);
		edit.setHorizontalAlignment(SwingConstants.RIGHT);
		
		edit.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				renameDeleteBox.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		add(button);
		add(edit);
		button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3){
					renameDeleteBox.setVisible(true);

				}
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
	
	private void folderAction(){
		
	}
	
	protected int getIndex(){
		return index;
	}
	
	protected void setIndex(int i){
		index = i;
	}
}
