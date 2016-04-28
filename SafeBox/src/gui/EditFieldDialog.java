package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
	private final String error = "No Changes Detected.";
	private final String title = "Edit Field: ";
	private final String cancel = "Cancel";
	private final String delete = "Delete";
	private final String rename = "Rename";
	private static String fieldName;
	private static String fieldContent;
	private static Record rec;
	private static int index;
	
	private JLabel titleLabel;
	private JTextField nameTextField;
	private JTextField contentTextField;
	private CustomButton cancelButton;
	private CustomButton deleteButton;
	private CustomButton renameButton;
	private PlainMessageDialog errorMessage;
	
	public EditFieldDialog(final StateManager sm, Color c1, Color c2, int w, int h, int i) {
		super(sm, c1, c2, w, h);
		errorMessage = new PlainMessageDialog(sm, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, 450, 180,error);
		errorMessage.setVisible(false);
		// --------------------north panel----------------------------------
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(Consts.FONT_STYLE,Font.PLAIN, 22));
		northPanel.add(titleLabel);
		
		
		// --------------------Center panel----------------------------------
		rec = (Record)sm.getESM().getFileSystemHandler().getCurrentRecord().getData();
		index = i;
		fieldName = rec.getField(i).getName();
		System.out.println("Child " + i);
		fieldContent = rec.getField(i).getData();
		centerPanel.setLayout(new BorderLayout());
		nameTextField = new JTextField(fieldName);
		contentTextField = new JTextField(fieldContent);
		centerPanel.setBorder(new EmptyBorder(10,0,20,0));
		nameTextField.setPreferredSize(new Dimension(300, 30));
		contentTextField.setPreferredSize(new Dimension(300, 30));
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
				rec.deleteField(index);
				sm.update();
				close();
			}
		});
		renameButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(checkForValidText(nameTextField.getText()) && checkForValidText(nameTextField.getText())){
					fieldName = nameTextField.getText();
					fieldContent = contentTextField.getText();
					rec.getField(index).setData(fieldContent);
					rec.getField(index).setName(fieldName);
					sm.update();
					close();
				}
				else{
					contentTextField.setText(fieldContent);
					nameTextField.setText(fieldName);
					errorMessage.setVisible(true);
				}
			}
		});
		nameTextField.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (nameTextField.getText().equals(fieldName)) {
					nameTextField.setText("");
				}
			}
		});
		contentTextField.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (contentTextField.getText().equals(fieldContent)) {
					contentTextField.setText("");
				}
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (contentTextField.getText().equals("")) {
					contentTextField.setText(fieldContent);
				}
				if (nameTextField.getText().equals("")) {
					nameTextField.setText(fieldName);
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
	
}
