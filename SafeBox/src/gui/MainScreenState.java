package gui;

import gui.StateManager.ValidStates;

public class MainScreenState extends State{
	private static final long serialVersionUID = 1L;

	protected MainScreenState(StateManager sm) {
		this.sm = sm;

		//mtitle.setLocation(200, 200);
		
		//add components to list
		
		//add components to JFrame window
		//addComponentsToWindow();
		init();
		clear();
	}
	
	@Override
	protected void init() {
		System.out.println("Init : MainScreen State");
	}

	@Override
	protected void draw() {
		//viewComponentsOnWindow();
		System.out.println("Draw : MainScreen State");
	}

	@Override
	protected void clear() {
		//clearComponentsOnWindow();
		System.out.println("Clear : MainScreen State");
	}

	@Override
	public void mouseClicked() {
		sm.setState(ValidStates.PasswordState);
	}

	@Override
	public void mouseEntered() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyTyped(int k) {
		// TODO Auto-generated method stub
		
	}



}
