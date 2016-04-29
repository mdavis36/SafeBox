package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.ws.client.ContentNegotiation;

import core.Record;

public class EditFieldDialog extends CustomDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2200655084486626161L;
	private final String error = "No Changes Detected.";
	private final String title = "Edit Field: ";
	private final String cancel = "Cancel";
	private final String delete = "Delete";
	private final String rename = "Save";
	private static String fieldName;
	private static String fieldContent;
	private static Record rec;
	private  int index;
	
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
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(Consts.FONT_STYLE,Font.PLAIN, 22));
		northPanel.add(titleLabel);
		
		
		// --------------------Center panel----------------------------------
		rec = (Record)sm.getESM().getFileSystemHandler().getCurrentRecord().getData();
		this.index = i;
		fieldName = rec.getField(index).getName();
		fieldContent = rec.getField(index).getData();
		
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(10,0,20,0));
		
		nameTextField = new JTextField(fieldName);
		nameTextField.setPreferredSize(Consts.DIALOGUE_TEXT_FIELD_DIMENSION);
		
		contentTextField = new JTextField(fieldContent);
		contentTextField.setPreferredSize(Consts.DIALOGUE_TEXT_FIELD_DIMENSION);
		
		centerPanel.add(nameTextField, BorderLayout.NORTH);
		centerPanel.add(contentTextField, BorderLayout.SOUTH);
		
		
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
				sm.deleteDialog.open();
				if(sm.deleteDialog.getConfirmation()){
					System.out.println("Index in button:" + index);
					rec.deleteField(index);
					sm.update();				
					close();
				}
			}
		});
		
		renameButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(checkForValidText(nameTextField.getText()) && checkForValidText(nameTextField.getText())){
					fieldName = nameTextField.getText();
					fieldContent = contentTextField.getText();
					editField(fieldName,fieldContent);
					sm.update();
					close();
				}
				else{
					contentTextField.setText(fieldContent);
					nameTextField.setText(fieldName);
					sm.showPlainMessage(error);
				}
			}
		});
		
		nameTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(nameTextField.getText())){
					nameTextField.setText(fieldName);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (nameTextField.getText().equals(fieldName)) {
					nameTextField.setText("");
				}
			}
		});
		
		contentTextField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(contentTextField.getText())){
					contentTextField.setText(fieldContent);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (contentTextField.getText().equals(fieldContent)) {
					contentTextField.setText("");
				}
			}
		});
	}
	
	private boolean checkForValidText(String newText){
		if("".equals(newText) || newText.equals(" ")){
			return false;
		}
		else{
			return true;
		}
	}
	
	private void editField(String name, String content){
		System.out.println("The index being changed: " + index);
		rec.getField(index).setName(name);
		rec.getField(index).setData(content);
	}
	@Override
	protected void init() {
		titleLabel.setFocusable(true);
		titleLabel.requestFocus();
			// TODO Auto-generated method stub
			
	}
	
	
	
}
