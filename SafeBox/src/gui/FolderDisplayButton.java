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
	private static final int EDIT_BUTTON_WIDTH = 20;
	private static final int FOLDER_BUTTON_WIDTH = 40;
	private static final int LAYOUT_ARG = 10;
	
	protected static final int RECORD = 0;
	protected static final int FOLDER = 1;
	
	private StateManager sm;
	
	private RenameFolderBox renameDeleteBox;
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
		super(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, width, height, true));
		this.sm = sm;
		this.type = type;
		this.index = index;
		setSize(new Dimension(width, height));
		setLayout(new FlowLayout(LAYOUT_ARG));
		final EditRecordFolderDialog b = new EditRecordFolderDialog(sm, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, 450, 200, index);
		b.setVisible(false);
		renameDeleteBox = new RenameFolderBox(sm,index);
		renameDeleteBox.setVisible(false);
	
		
		if (type == FOLDER){
			imgName = "folder.png";
		} else {
			imgName = "record.png";
		}
		
		CustomButton button = new CustomButton(text, 0, 0, FOLDER_BUTTON_WIDTH, FOLDER_BUTTON_WIDTH);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setHorizontalTextPosition(JButton.RIGHT);
		button.setImageFromFile(imgName, true);
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressed(e);
			}
		});
		
		
		CustomButton edit = new CustomButton("", 0, 0, EDIT_BUTTON_WIDTH, EDIT_BUTTON_WIDTH);
		edit.setImageIcon(MiscUtils.layerBufferedImages(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, 
																						Consts.BLUE_PANEL_COLOUR_DARK, 
																						EDIT_BUTTON_WIDTH, 
																						EDIT_BUTTON_WIDTH, 
																						true), 
														MiscUtils.getBufferedImageFromFile(Consts.PEN_IMAGE, 
																						EDIT_BUTTON_WIDTH)),
						true);
		edit.setHorizontalAlignment(SwingConstants.RIGHT);
		edit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				b.setVisible(true);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressed(e);
			}
		});
		
		
		add(edit);
		add(button);
		
	}
	
	private void pressed(MouseEvent e){
		if (type == FOLDER)
			folderAction(e.getButton());
		else
			recordAction();
		
	}
	
	private void folderAction(int b){
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
