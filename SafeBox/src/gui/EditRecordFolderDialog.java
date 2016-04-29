package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditRecordFolderDialog extends CustomDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = -3569511422230983023L;

	private static String name;
	private int index;

	private JLabel titleLabel;
	private JLabel nameLabel;
	private JTextField textField;
	private CustomButton cancelButton;
	private CustomButton deleteButton;
	private CustomButton renameButton;

	private static final int NAME_FONT_SIZE = 20;
	private static final int CENTER_PANEL_TOP = 20;
	private static final int CENTER_PANEL_BOTTOM = 20;
	private static final int SOUTH_PANEL_HGAP = 10;

	public EditRecordFolderDialog(final StateManager sm, Color c1, Color c2, int w, int h, int i) {
		super(sm, c1, c2, w, h);
		// --------------------north panel----------------------------------
		index = i;
		name = getName(index, sm);
		titleLabel = new JLabel(English.EDIT);
		nameLabel = new JLabel(name);
		northPanel.setLayout(new BorderLayout());
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, Consts.DIALOGUE_BOX_TITLE_FONT_SIZE));
		nameLabel.setFont(new Font(Consts.FONT_STYLE, Font.ITALIC, NAME_FONT_SIZE));
		northPanel.add(titleLabel, BorderLayout.WEST);
		northPanel.add(nameLabel, BorderLayout.CENTER);

		// --------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textField = new JTextField(name);
		centerPanel.setBorder(new EmptyBorder(CENTER_PANEL_TOP, 0, CENTER_PANEL_BOTTOM, 0));
		textField.setPreferredSize(Consts.DIALOGUE_TEXT_FIELD_DIMENSION);
		centerPanel.add(textField);

		// --------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SOUTH_PANEL_HGAP, 0));
		cancelButton = setupButton(English.CANCEL, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		deleteButton = setupButton(English.DELETE, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		renameButton = setupButton(English.RENAME, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		southPanel.add(cancelButton);
		southPanel.add(deleteButton);
		southPanel.add(renameButton);

		// --------------------Listeners----------------------------------
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sm.confirmDialog.setMessage(English.DELETE_RF_MESSAGE);
				sm.confirmDialog.setButtonText(English.CANCEL, English.DELETE);
				sm.confirmDialog.open();
				if (sm.confirmDialog.getConfirmation()) {
					deleteFolder(sm, index);
					sm.update();
					close();
				}
			}
		});
		renameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isValidText(textField.getText())) {
					renameFolder(textField.getText(), sm);
					sm.update();
					close();
				} else {
					sm.showPlainMessage(English.NOT_VALID_NAME);
				}
			}
		});
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textField.getText().equals(English.NEW_NAME)) {
					textField.setText("");
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ("".equals(textField.getText())) {
					textField.setText(English.NEW_NAME);
				}
			}
		});
	}

	protected void renameFolder(String text, StateManager sm) {
		sm.getESM().getFileSystemHandler().getCurrent().getChild(index).getData().setName(text);
		sm.update();
	}

	protected void deleteFolder(StateManager sm, int i) {
		sm.getESM().getFileSystemHandler().deleteFolder(sm.getESM().getFileSystemHandler().getCurrent(), i);
		sm.update();
	}

	private String getName(int i, StateManager sm) {
		return sm.getESM().getFileSystemHandler().getCurrent().getChild(i).getData().getName();
	}

	private boolean isValidText(String text) {
		if (text.equals(English.NEW_NAME) || "".equals(text) || text.equals(" ")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

}
