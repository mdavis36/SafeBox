package core;

import java.io.Serializable;
import java.util.ArrayList;


public class FileSystemHandler implements Serializable{
	private static final long serialVersionUID = -5417133060210085474L;
	private Node currentNode;
	public Tree contents;
	
	@Override
	public String toString() {
		return "FileSystemHandler [currentNode=" + currentNode + ", contents=" + contents + "]";
	}


	public FileSystemHandler(){
		contents = new Tree();
		currentNode = contents.getRoot();
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
	
	public Node getCurrent(){
		return currentNode;
	}
	////////////////
	//MANIPULATION//
	////////////////
	public Node createFolder(Node parent, String name){//Creates a Node with the indicated parent and naming the folder with name. Returns the node just created.
		Folder folderToAdd = new Folder();
		folderToAdd.setName(name);
		Node nodeToAdd = new Node(folderToAdd,parent);		
		parent.addChild(nodeToAdd);
		return nodeToAdd;
	}
	
	public Node createRecord(Node parent, String name){
		Record recordToAdd = new Record();
		recordToAdd.setName(name);
		Node nodeToAdd = new Node(recordToAdd,parent);
		
		return nodeToAdd;
	}
	
	public boolean deleteFolder(Node parent, int index){
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
