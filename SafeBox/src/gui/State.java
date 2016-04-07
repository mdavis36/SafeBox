package gui;

import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;

public abstract class State extends JComponent implements MouseListener{
	
	StateManager sm;
	ArrayList<JComponent> components = new ArrayList<JComponent>();
	
	protected abstract void init();
	protected abstract void draw();
	protected abstract void clear();
	
}
