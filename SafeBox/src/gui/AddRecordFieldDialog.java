package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddRecordFieldDialog extends CustomDialog{

	
	private static final String TITLE = "Add a Record / Folder";
	private static final String CANCEL_TEXT = "Cancel";
	private static final String ADD_RECORD_TEXT = "Add Record";
	private static final String ADD_FOLDER_TEXT = "Add Folder";
	
	private JLabel title;
	
	private JTextField textField;
	
	private CustomButton cancelButton;
	private CustomButton addRecordButton;
	private CustomButton addFolderButton;
	
	public AddRecordFieldDialog(StateManager sm, Color c1, Color c2, int w, int h) {
		super(sm, c1, c2, w, h);
		//--------------------north panel----------------------------------
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		title = new JLabel(TITLE);
		northPanel.add(title);
		
		
		//--------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(300, 30));
		centerPanel.add(textField);
		
		
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		
		cancelButton = setupButton(CANCEL_TEXT, 120, 36);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				close();
			}
		});
		
		addRecordButton = setupButton(ADD_RECORD_TEXT, 120, 36);
		addRecordButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				addRecord();
				close();
			}
		});
		addFolderButton = setupButton(ADD_FOLDER_TEXT, 120, 36);
		addFolderButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				addFolder();
				close();
			}
		});
		southPanel.add(cancelButton);
		southPanel.add(addRecordButton);
		southPanel.add(addFolderButton);
	}
	
	private void addRecord(){
		sm.getESM().getFileSystemHandler().createRecord(sm.getESM().getFileSystemHandler().getCurrent(), textField.getText());
	}
	
	private void addFolder(){
		sm.getESM().getFileSystemHandler().createFolder(sm.getESM().getFileSystemHandler().getCurrent(), textField.getText());
	}
	
}
