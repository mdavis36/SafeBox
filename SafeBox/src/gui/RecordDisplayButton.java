package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RecordDisplayButton extends BackgroundPanel{
	
	private int index;
	private static final int FOLDER = 0;
	private static final int RECORD = 0;
	RenameFolderBox renameDeleteBox;

	
	
	

	
	
	
	public RecordDisplayButton(String text, int x, int y, int width, int height, final int index, final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, width, height, true));
		this.index = index;
		setSize(new Dimension(width, height));
		setLayout(new FlowLayout(10));
		renameDeleteBox = new RenameFolderBox(sm,index);
		renameDeleteBox.setVisible(false);
		
		CustomButton button = new CustomButton(text, 0, 0, 40, 40);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setHorizontalTextPosition(JButton.RIGHT);
		button.setImageFromFile("record.png", true);
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				sm.getESM().getFileSystemHandler().setCurrentRecord(sm.getESM().getFileSystemHandler().getCurrent().getChild(index));
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
		edit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				renameDeleteBox.setVisible(true);
			}
		});
		add(button);//, BorderLayout.CENTER);
		add(edit);
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
