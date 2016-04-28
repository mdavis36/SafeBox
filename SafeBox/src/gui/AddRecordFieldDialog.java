package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddRecordFieldDialog extends CustomDialog{

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
		title = new JLabel(English.ADD_A_RECORD_FOLDER);
		title.setFont(new Font(Consts.FONT_STYLE,Font.PLAIN, Consts.DIALOGUE_BOX_TITLE_FONT_SIZE));
		northPanel.add(title);
		
		
		//--------------------Center panel----------------------------------
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textField = new JTextField();
		textField.setText(English.initTextField);
		textField.setPreferredSize(Consts.DIALOGUE_TEXT_FIELD_DIMENSION);
		centerPanel.add(textField);
		
		
		//--------------------South panel----------------------------------
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, SOUTH_PANEL_LAYOUT_HGAP, 0));
		
		cancelButton = setupButton(English.CANCEL, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				close();
			}
		});
		
		addRecordButton = setupButton(English.ADD_RECORD, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		addRecordButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(isValidName(textField.getText())){
					addRecord();
					close();
				}
				else{
					sm.showPlainMessage(English.error);
				}
			}
		});
		addFolderButton = setupButton(English.ADD_FOLDER, Consts.DIALOGUE_BOX_BUTTON_WIDTH, Consts.DIALOGUE_BOX_BUTTON_HEIGHT);
		addFolderButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(isValidName(textField.getText())){
					addFolder();
					close();
				}
				else{
					sm.showPlainMessage(English.error);
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if ("".equals(textField.getText())){
					textField.setText(English.initTextField);
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
		if(text.equals(English.initTextField) || "".equals(text)|| text.equals(" ")){
			return false;
		}
		else{
			return true;
		}
	}
	
	@Override
	protected void init() {
		textField.setText(English.initTextField);
	}
}


