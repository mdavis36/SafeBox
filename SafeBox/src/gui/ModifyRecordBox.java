package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import core.Field;
import core.Record;
public class ModifyRecordBox extends ModifyFolderBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6498796871704410150L;
	private final String titleString = "Modify Field";
	private final String button1String = "Delete";
	private final String button2String = "Save";
	private final String errorMessageText = "Not valid Field name/content";
	private static String fieldName;
	private static String fieldContent;
	private static Record rec;
	int index;
	private static PlainMessageBox errorMessage;
	private final JTextField fieldNameField = new JTextField();
	public ModifyRecordBox(final StateManager sm,int i){
		super();
		//super textField is the field content
		rec = (Record)sm.getESM().getFileSystemHandler().getCurrentRecord().getData();
		fieldName = rec.getField(i).getName();
		fieldNameField.setText(fieldName);
		fieldContent = rec.getField(i).getData();
		titleText = titleString;
		button1Text = button1String;
		button2Text = button2String;
		this.sm = sm;
		index = i;
		errorMessage = new PlainMessageBox(sm, errorMessageText);
		errorMessage.setVisible(false);
		setStrings(button1Text, button2Text, fieldContent, titleText);
		init();
	}
	@Override
	protected void init(){
		//textField is content
		userInput.setBorder(new EmptyBorder(20,20,20,20));
		setSize(new Dimension(450,250));
		createDrawCommon();
		panel.setPreferredSize(new Dimension(450,220));
		userInput.setSize(new Dimension(450,100));//Latest change
		userInput.add(textField, BorderLayout.SOUTH);
		fieldNameField.setSize(new Dimension(300,40));
		fieldNameField.setBorder(new EmptyBorder(0,20,20,20));
		userInput.add(fieldNameField, BorderLayout.NORTH);
		panel.add(buttons, BorderLayout.SOUTH);
		panel.add(title,BorderLayout.NORTH);
		panel.add(userInput, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(450,250));
		add(panel);
	}
	@Override
	protected void resetBox() {
		textField.setText(fieldContent);
		fieldNameField.setText(fieldName);
		setVisible(false);
	}

	@Override
	protected void button1Action() {
		rec.deleteField(index);
		sm.update();
		setVisible(false);
	}

	@Override
	protected void button2Action() {
		if(checkForValidText(fieldNameField.getText()) && checkForValidText(textField.getText())){
			fieldName = fieldNameField.getText();
			fieldContent = textField.getText();
			rec.getField(index).setData(fieldContent);
			rec.getField(index).setName(fieldName);
			sm.update();
			setVisible(false);
		}
		else{
			textField.setText(fieldContent);
			fieldNameField.setText(fieldName);
			errorMessage.setVisible(true);
		}
	}

	@Override
	protected void textFieldAction() {
		
	}
	protected void fieldNameFieldAction(){
		
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
