package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

public class DeleteConfirmDialog extends CustomDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4807039006822180146L;
	private final String confirmText = "Are you sure you want to delete?";
	private final String cancel = "Cancel";
	private final String delete = "Delete";
	private CustomButton deleteButton;
	private CustomButton cancelButton;
	private JTextArea confirmationText;
	private boolean confirm;
	
	public DeleteConfirmDialog(StateManager sm, Color c1, Color c2, int w, int h) {
		super(sm, c1, c2, w, h);
		//--------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		confirmationText = new JTextArea(confirmText);
		centerPanel.add(confirmationText);
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		cancelButton = setupButton(cancel, 120, 36);
		deleteButton = setupButton(delete, 120, 36);
		southPanel.add(cancelButton);
		southPanel.add(deleteButton);
		// --------------------Listeners----------------------------------
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				confirm = false;
				close();
			}
		});
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				confirm = true;
				close();
			}
		});
	}
	
	public boolean getConfirmation(){
		return confirm;
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

}
