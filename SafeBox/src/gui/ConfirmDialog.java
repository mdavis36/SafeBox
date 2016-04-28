package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ConfirmDialog extends CustomDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4807039006822180146L;
	private String confirmText;
	private final String cancel = "Cancel";
	private final String delete = "Delete";
	private CustomButton deleteButton;
	private CustomButton cancelButton;
	private JLabel confirmationText;
	private boolean confirm;
	private static JDialog snapTo;
	
	private static final int CONFIRM_TEXT_FONT_SIZE = 22;
	private static final int SOUTH_PANEL_HGAP = 10;
	
	public ConfirmDialog(StateManager sm, Color c1, Color c2, int w, int h, String message) {
		super(sm, c1, c2, w, h);
		confirmText = message;
		//--------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		confirmationText = new JLabel(confirmText);
		confirmationText.setFont(new Font(Consts.FONT_STYLE,Font.PLAIN, CONFIRM_TEXT_FONT_SIZE));
		centerPanel.add(confirmationText);
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SOUTH_PANEL_HGAP, 0));
		cancelButton = setupButton(cancel, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		deleteButton = setupButton(delete, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
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
	
	public void setMessage(String message){
		confirmText = message;
		confirmationText.setText(confirmText);
	}
	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

}
