package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

public class ConfirmDialog extends CustomDialog {

	private static final long serialVersionUID = -4807039006822180146L;
	private String confirmText;
	private CustomButton rightButton;
	private CustomButton leftButton;
	private JTextArea confirmationText;
	private boolean confirm;

	private static final int CONFIRM_TEXT_FONT_SIZE = 22;
	private static final int SOUTH_PANEL_HGAP = 10;

	public ConfirmDialog(StateManager sm, Color c1, Color c2, int w, int h, String message) {
		super(sm, c1, c2, w, h);
		confirmText = message;
		//--------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		confirmationText = new JTextArea(confirmText);
		confirmationText.setEditable(false);
		confirmationText.setLineWrap(true);
		confirmationText.setWrapStyleWord(true);
		confirmationText.setOpaque(false);
		confirmationText.setPreferredSize(Consts.DIALOG_CONFIRM_TEXT_DIMENSION);
		confirmationText.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, CONFIRM_TEXT_FONT_SIZE));
		centerPanel.add(confirmationText);
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SOUTH_PANEL_HGAP, 0));
		leftButton = setupButton(null, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		rightButton = setupButton(null, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		southPanel.add(leftButton);
		southPanel.add(rightButton);
		// --------------------Listeners----------------------------------
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirm = false;
				close();
			}
		});
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirm = true;
				close();
			}
		});
	}

	public void setButtonText(String left, String right) {
		leftButton.setText(left);
		rightButton.setText(right);
	}

	public boolean getConfirmation() {
		return confirm;
	}

	public void setMessage(String message) {
		confirmText = message;
		confirmationText.setText(confirmText);
	}

	@Override
	protected void init() {

	}

}
