package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FolderDisplayButton extends CustomButton{
	
	private int index;
	
	public FolderDisplayButton(String text, int x, int y, int width, int height, final int index, final StateManager sm) {
		super(text, x, y, width, height);
		// TODO Auto-generated constructor stub
		this.index = index;
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sm.getESM().getFileSystemHandler().setCurrentNode(sm.getESM().getFileSystemHandler().getCurrent().getChild(index));
				System.out.println("clicked child : " + index);
				sm.update();
			}
		});
	}
	
	protected int getIndex(){
		return index;
	}
	
	protected void setIndex(int i){
		index = i;
	}
}
