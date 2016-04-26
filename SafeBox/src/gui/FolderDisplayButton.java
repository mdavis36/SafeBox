package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class FolderDisplayButton extends BackgroundPanel{
	
	private int index;
	private int type;
	private String imgName;
	protected static final int RECORD = 0;
	protected static final int FOLDER = 1;
	
	private StateManager sm;
	
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
	public FolderDisplayButton(String text, int x, int y, int width, int height, final int index, final StateManager sm, final int type) {
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, width, height, true));
		this.sm = sm;
		this.type = type;
		this.index = index;
		setSize(new Dimension(width, height));
		setLayout(new FlowLayout(10));
		
		renameDeleteBox = new RenameFolderBox(sm,index);
		renameDeleteBox.setVisible(false);
	
		
		if (type == FOLDER){
			imgName = "folder.png";
		} else {
			imgName = "record.png";
		}
		
		CustomButton button = new CustomButton(text, 0, 0, 40, 40);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setHorizontalTextPosition(JButton.RIGHT);
		button.setImageFromFile(imgName, true);
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (type == FOLDER)
					folderAction(e.getButton());
				else
					recordAction();
				
			}
		});
		
		CustomButton edit = new CustomButton("", 0, 0, 20, 20);
		edit.setImageIcon(MiscUtils.layerBufferedImages(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, 
																						MiscUtils.BLUE_PANEL_COLOUR_DARK, 
																						20, 
																						20, 
																						true), 
														MiscUtils.getBufferedImageFromFile("res/buttons/pen.png", 
																						20)),
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
	
	private void folderAction(int b){
		if(b == MouseEvent.BUTTON3){
			renameDeleteBox.setVisible(true);
		}
		sm.getESM().getFileSystemHandler().setCurrentNode(sm.getESM().getFileSystemHandler().getCurrent().getChild(index));
		sm.update();
	}
	
	private void recordAction(){
		sm.getESM().getFileSystemHandler().setCurrentRecord(sm.getESM().getFileSystemHandler().getCurrent().getChild(index));
		sm.update();
	}
	
	protected int getIndex(){
		return index;
	}
	
	protected void setIndex(int i){
		index = i;
	}
}
