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

import core.Record;

public class EditFieldDialog extends CustomDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = -2200655084486626161L;
	private static String fieldName;
	private static String fieldContent;
	private static Record rec;
	private int index;

	private JLabel titleLabel;
	private JTextField nameTextField;
	private JTextField contentTextField;
	private CustomButton cancelButton;
	private CustomButton deleteButton;
	private CustomButton renameButton;

	public EditFieldDialog(final StateManager sm, Color c1, Color c2, int w, int h, int i) {
		super(sm, c1, c2, w, h);
		// --------------------north panel----------------------------------
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titleLabel = new JLabel(English.EDIT_FIELD);
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, Consts.DIALOGUE_BOX_TITLE_FONT_SIZE));
		northPanel.add(titleLabel);

		// --------------------Center panel----------------------------------
		rec = (Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData();
		this.index = i;
		fieldName = rec.getField(index).getName();
		fieldContent = rec.getField(index).getData();
		centerPanel.setLayout(new BorderLayout());
		nameTextField = new JTextField(fieldName);
		contentTextField = new JTextField(fieldContent);
		centerPanel.setBorder(new EmptyBorder(Consts.CENTER_PANEL_TOP, 0, Consts.CENTER_PANEL_BOTTOM, 0));
		nameTextField.setPreferredSize(Consts.DIALOGUE_TEXT_FIELD_DIMENSION);
		contentTextField.setPreferredSize(Consts.DIALOGUE_TEXT_FIELD_DIMENSION);
		centerPanel.add(nameTextField, BorderLayout.NORTH);
		centerPanel.add(contentTextField, BorderLayout.SOUTH);

		// --------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, Consts.SOUTH_PANEL_HGAP, 0));
		cancelButton = setupButton(English.CANCEL, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		deleteButton = setupButton(English.DELETE, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		renameButton = setupButton(English.SAVE, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
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
				sm.confirmDialog.setMessage(English.DELETE_MESSAGE);
				sm.confirmDialog.open();
				if (sm.confirmDialog.getConfirmation()) {
					System.out.println("Index in button:" + index);
					rec.deleteField(index);
					sm.update();
					close();
				}
			}
		});
		renameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkForValidText(nameTextField.getText()) && checkForValidText(nameTextField.getText())) {
					fieldName = nameTextField.getText();
					fieldContent = contentTextField.getText();
					editField(fieldName, fieldContent);
					sm.update();
					close();
				} else {
					contentTextField.setText(fieldContent);
					nameTextField.setText(fieldName);
					sm.showPlainMessage(English.NO_CHANGES_DETECTED);
				}
			}
		});
		nameTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nameTextField.getText().equals(fieldName)) {
					nameTextField.setText("");
				}
			}
		});
		contentTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contentTextField.getText().equals(fieldContent)) {
					contentTextField.setText("");
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (contentTextField.getText().equals("")) {
					contentTextField.setText(fieldContent);
				}
				if (nameTextField.getText().equals("")) {
					nameTextField.setText(fieldName);
				}
			}
		});
	}

	private boolean checkForValidText(String newText) {
		if ("".equals(newText) || newText.equals(" ")) {
			return false;
		} else {
			return true;
		}
	}

	private void editField(String name, String content) {
		System.out.println("The index being changed: " + index);
		rec.getField(index).setName(name);
		rec.getField(index).setData(content);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

}
