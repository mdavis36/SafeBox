package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private final String title = "Edit: ";
	private final String cancel = "Cancel";
	private final String delete = "Delete";
	private final String rename = "Rename";
	private final String initTextField = "New Name";
	private static String name;
	private int index;
	
	private JLabel titleLabel;
	private JLabel nameLabel;
	private JTextField textField;
	private CustomButton cancelButton;
	private CustomButton deleteButton;
	private CustomButton renameButton;

	public EditRecordFolderDialog(final StateManager sm, Color c1, Color c2, int w, int h, int i) {
		super(sm, c1, c2, w, h);
		// --------------------north panel----------------------------------
		index = i;
		name = getName(index, sm);
		titleLabel = new JLabel(title);
		nameLabel = new JLabel(name);
		northPanel.setLayout(new BorderLayout());
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, 24));
		nameLabel.setFont(new Font(Consts.FONT_STYLE, Font.ITALIC, 20));
		//northPanel.setPreferredSize
		northPanel.add(titleLabel, BorderLayout.WEST);
		northPanel.add(nameLabel, BorderLayout.CENTER);

		// --------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textField = new JTextField(initTextField);
		centerPanel.setBorder(new EmptyBorder(20,0,20,0));
		textField.setPreferredSize(new Dimension(300, 30));
		centerPanel.add(textField);

		// --------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		cancelButton = setupButton(cancel, 120, 36);
		deleteButton = setupButton(delete, 120, 36);
		renameButton = setupButton(rename, 120, 36);
		southPanel.add(cancelButton);
		southPanel.add(deleteButton);
		southPanel.add(renameButton);
		
		// --------------------Listeners----------------------------------
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				close();
			}
		});
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				deleteFolder(sm, index);
				close();
			}
		});
		renameButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				renameFolder(textField.getText(),sm);
				close();
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
	

	private String getName(int i, StateManager sm){
		return sm.getESM().getFileSystemHandler().getCurrent().getChild(i).getData().getName();
	}

}
