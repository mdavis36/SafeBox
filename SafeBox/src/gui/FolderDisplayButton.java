package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FolderDisplayButton extends BackgroundPanel{
	
	private int index;
	
	private static final int IMAGE_WIDTH = 20;
	private static final String FOLDER_IMAGE = "folder.png";
	private static final int FOLDER_BUTTON_WIDTH = 40;
	private static final int LAYOUT_ARG = 10;
	
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
		setLayout(new FlowLayout(LAYOUT_ARG));
		renameDeleteBox = new RenameFolderBox(sm,index);
		renameDeleteBox.setVisible(false);
	
		CustomButton button = new CustomButton(text, 0, 0, FOLDER_BUTTON_WIDTH, FOLDER_BUTTON_WIDTH);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setHorizontalTextPosition(JButton.RIGHT);
		button.setImageFromFile(FOLDER_IMAGE, true);
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3){
					renameDeleteBox.setVisible(true);
				}
				sm.getESM().getFileSystemHandler().setCurrentNode(sm.getESM().getFileSystemHandler().getCurrent().getChild(index));
				sm.update();
			}
		});
		
		CustomButton edit = new CustomButton("", 0, 0, IMAGE_WIDTH, IMAGE_WIDTH);
		edit.setImageIcon(MiscUtils.layerBufferedImages(MiscUtils.getBufferedGradImage(MiscUtils.BUTTON_COLOUR_LIGHT, 
																						MiscUtils.BUTTON_COLOUR_DARK, 
																						IMAGE_WIDTH, 
																						IMAGE_WIDTH, 
																						true), 
														MiscUtils.getBufferedImageFromFile(MiscUtils.PEN_IMAGE, 
																						IMAGE_WIDTH)),
						true);
		edit.setHorizontalAlignment(SwingConstants.RIGHT);
		edit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				renameDeleteBox.setVisible(true);
			}
		});
		
		
		add(button);
		add(edit);
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
