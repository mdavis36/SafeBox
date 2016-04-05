package core;

import java.util.ArrayList;


public class FileSystemHandler {
	private int currentIndex;
	public Tree contents;
	
	public FileSystemHandler(){
		currentIndex = 0;
		contents = new Tree();
	}

	
	///////////
	//GETTERS//
	///////////
	public Node getRoot(){
		return contents.getRoot();
	}
	
	public Node getNode(Node parent, int index){
		return parent.getChild(index);
	}
	
	////////////////
	//MANIPULATION//
	////////////////
	public Node createFolder(Node parent, String name){//Creates a Node with the indicated parent and naming the folder with name. Returns the node just created.
		Folder folderToAdd = new Folder();
		folderToAdd.setName(name);
		Node nodeToAdd = new Node(folderToAdd,parent);
		return nodeToAdd;
	}
	
	public Node createRecord(Node parent, String name){
		Record recordToAdd = new Record();
		recordToAdd.setName(name);
		Node nodeToAdd = new Node(recordToAdd,parent);
		
		return nodeToAdd;
	}
	
	public boolean deleteFolder(Node parent,int index){
		//TODO: Not sure if this is the right implementation yet. The true/false I think should be handled with the GUI.
		parent.removeChild(index);
		return true;
		}
	
	public ArrayList<Folder> search(String query){
		//TODO: 
		ArrayList<Folder> toReturn = null;
		
		return toReturn;
	}
}
