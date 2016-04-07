package gui;

import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class State extends JComponent{
	private static final long serialVersionUID = 1L;
	
	StateManager sm;
	protected ArrayList<JComponent> components;
	
	protected abstract void init();
	protected abstract void draw();
	protected abstract void clear();
	protected abstract void mouseClicked();
	protected abstract void mouseEntered();
	protected abstract void mouseExited();
	protected abstract void mousePressed();
	protected abstract void mouseReleased();
	protected abstract void keyPressed(int k);
	protected abstract void keyReleased(int k);
	protected abstract void keyTyped(int k);
	
	/*protected void addComponentsToWindow(){
		for(int i = 0; i < components.size(); i++){
			sm.window.add(components.get(i));
		}
	}
	
	protected void viewComponentsOnWindow(){
		for(int i = 0; i < components.size(); i++){
			components.get(i).setVisible(true);
			System.out.println(components.get(i).toString());
		}
	}
	
	protected void clearComponentsOnWindow(){
		for(int i = 0; i < components.size(); i++){
			components.get(i).setVisible(false);
		}
	}
	*/
}
