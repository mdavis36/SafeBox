package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddRecordFieldDialog extends CustomDialog{

	private final String error = "Not a valid name.";
	private static final String ADD_A_RECORD_FOLDER = "Add a Record / Folder";
	private static final String CANCEL = "Cancel";
	private static final String ADD_RECORD = "Add Record";
	private static final String ADD_FOLDER = "Add Folder";
	private static final String initTextField = "Folder/Record Name";
	
	private static final int SOUTH_PANEL_LAYOUT_HGAP = 10;

	private JLabel title;
	
	private JTextField textField;
	
	private CustomButton cancelButton;
	private CustomButton addRecordButton;
	private CustomButton addFolderButton;
	
	public AddRecordFieldDialog(final StateManager sm, Color c1, Color c2, int w, int h) {
		super(sm, c1, c2, w, h);
		//--------------------north panel----------------------------------
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		title = new JLabel(ADD_A_RECORD_FOLDER);
		title.setFont(new Font(Consts.FONT_STYLE,Font.PLAIN, Consts.DIALOGUE_BOX_TITLE_FONT_SIZE));
		northPanel.add(title);
		
		
		//--------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textField = new JTextField();
		textField.setText(initTextField);
		textField.setPreferredSize(Consts.DIALOGUE_TEXT_FIELD_DIMENSION);
		centerPanel.add(textField);
		
		
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SOUTH_PANEL_LAYOUT_HGAP, 0));
		
		cancelButton = setupButton(CANCEL, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				close();
			}
		});
		
		addRecordButton = setupButton(ADD_RECORD, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		addRecordButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(isValidName(textField.getText())){
					addRecord();
					close();
				}
				else{
					sm.showPlainMessage(error);
				}
			}
		});
		addFolderButton = setupButton(ADD_FOLDER, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		addFolderButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(isValidName(textField.getText())){
					addFolder();
					close();
				}
				else{
					sm.showPlainMessage(error);
				}
			}
		});
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(textField.getText().equals(initTextField)){
					textField.setText(null);
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if ("".equals(textField.getText())){
					textField.setText(initTextField);
				}
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
	
	private boolean isValidName(String text){
		if(text.equals(initTextField) || "".equals(text)|| text.equals(" ")){
			return false;
		}
		else{
			return true;
		}
	}
	
	@Override
	protected void init() {
		textField.setText(initTextField);
	}
}


