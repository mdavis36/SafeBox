package gui;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import core.*;
public class RenameFolderBox extends ModifyFolderBox {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7808736537984137344L;
	
	private static final String NOT_VALID_MESSAGE = "Not a valid name.";
	
	private final String titleString = "Rename:";
	private final String button1String = "Delete";
	private final String button2String = "Save";
	private String currentName;
	int index;
	
	private PlainMessageDialog messageBox;
	
	private final JLabel nameOfFolder = new JLabel();
	
	public RenameFolderBox(final StateManager sm, int i){
		super();
		this.sm = sm;
		titleText = titleString;
		button1Text = button1String;
		button2Text = button2String;
		nameOfFolder.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, 24));//TODO: Change to constant
		
		//messageBox = new PlainMessageDialog(sm, NOT_VALID_MESSAGE);
		
		this.index = i;
		setName(index, sm);
		nameOfFolder.setText(currentName);
		setStrings(button1Text, button2Text, currentName, titleText);
		init();
		title.add(nameOfFolder,BorderLayout.WEST);		
	}
	
	public void setName(String name){
		currentName = name;
		nameOfFolder.setText(currentName);
	}
	
	@Override
	protected void resetBox() {
		sm.update();
		setVisible(false);
		textField.setText(currentName);
	}
	
	private boolean checkForValidText(String text){
		if(text.equals(currentName) || "".equals(text) || text.equals(" ")){
			return false;
		}
		else{
			return true;
		}
	}
	private void renameFolder(String name, final StateManager state){
		state.getESM().getFileSystemHandler().getCurrent().getChild(index).getData().setName(name);
		state.update();
	}
	
	public void setIndex(int i ){
		this.index = i;
	}
	
	public void setName(int i , final StateManager state){
		currentName = state.getESM().getFileSystemHandler().getCurrent().getChild(i).getData().getName();
		nameOfFolder.setText(currentName);
	}
	
	public String getName(){
		return currentName;
	}
	
	private void deleteFolder(final StateManager sm, int i){
		sm.getESM().getFileSystemHandler().deleteFolder(sm.getESM().getFileSystemHandler().getCurrent(), i);
		sm.update();
	}
	
	@Override
	protected void button2Action(){
		if(checkForValidText(textField.getText())){
			renameFolder(textField.getText(),sm);
			resetBox();
		}
		else{
			//messageBox.setText(NOT_VALID_MESSAGE);
			//messageBox.setVisible(true);
			return;
		}
	}
	
	@Override
	protected void button1Action() {
		deleteFolder(sm, index);
		resetBox();
	}
	
	@Override
	protected void textFieldAction() {
		if(textField.getText().equals(currentName)){
			textField.setText("");
		}	
	}
}
