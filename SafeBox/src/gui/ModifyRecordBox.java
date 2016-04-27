package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;

import core.Record;
public class ModifyRecordBox extends ModifyFolderBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6498796871704410150L;
	private final String titleString = "Modify Field";
	private final String button1String = "Delete";
	private final String button2String = "Save";
	int index;
	private final JTextField fieldNameField = new JTextField();
	public ModifyRecordBox(final StateManager sm,int i){
		super();
		//super textField is the field content
		Record recordToEdit = (Record)sm.getESM().getFileSystemHandler().getCurrentRecord().getData();
		String fieldName = recordToEdit.getField(i).getName();
		fieldNameField.setText(fieldName);
		String fieldContent = recordToEdit.getField(i).getData();
		titleText = titleString;
		button1Text = button1String;
		button2Text = button2String;
		state = sm;
		index = i;
		setStrings(button1Text, button2Text, fieldContent, titleText);
		init();
	}
	@Override
	protected void init(){
		createDrawCommon();
		panel.setPreferredSize(new Dimension(450,220));
		userInput.setSize(new Dimension(450,100));
		userInput.add(textField, BorderLayout.SOUTH);
		fieldNameField.setPreferredSize(new  Dimension(300,40));
		userInput.add(fieldNameField, BorderLayout.NORTH);
		panel.add(buttons, BorderLayout.SOUTH);
		panel.add(title,BorderLayout.NORTH);
		panel.add(userInput, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(450,250));
		setSize(new Dimension(450,250));
		add(panel);
	}
	@Override
	protected void resetBox() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void button1Action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void button2Action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void textFieldAction() {
		// TODO Auto-generated method stub
		
	}

}
