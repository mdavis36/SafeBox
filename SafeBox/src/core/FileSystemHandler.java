package core;

import java.io.Serializable;
import java.util.ArrayList;


public class FileSystemHandler implements Serializable{
	private static final long serialVersionUID = -5417133060210085474L;
	private Node currentNode;
	public Tree contents;
	
	@Override
	public String toString() {
		return "FileSystemHandler [currentNode=" + currentNode + ", contents=\n" + contents + "]";
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
	
	public Node getCurrent(){
		return currentNode;
	}
	
	public Tree getContents(){
		return contents;
	}
	////////////////
	//MANIPULATION//
	////////////////
	public Node createFolder(Node parent, String name){//Creates a Node with the indicated parent and naming the folder with name. Returns the node just created.
		Folder folderToAdd = new Folder();
		folderToAdd.setName(name);
		
		Node nodeToAdd = new Node(folderToAdd);
		contents.addNode(parent, nodeToAdd);
		return nodeToAdd;
	}
	
	public Node createRecord(Node parent, String name){
		Record recordToAdd = new Record();
		recordToAdd.setName(name);
		Node nodeToAdd = new Node(recordToAdd);
		contents.addNode(parent, nodeToAdd);
		return nodeToAdd;
	}
	
	public boolean deleteFolder(Node parent, int index){

		parent.removeChild(index);
		
		return true;
		}
	
	public ArrayList<Node> search(String query, Node startNode){
		ArrayList<Node> toReturn = new ArrayList<Node>();
		int size = contents.getMaxGlobalIndex();
		String lowercaseQuery = query.toLowerCase();
		String name = "";
		for(int i = 0; i <=size; i++){
			name = contents.getNode(i).getData().getName().toLowerCase();
			if(name.contains(lowercaseQuery)){
				toReturn.add(contents.getNode(i));
			}
		}
		return toReturn;
	}
	/*
	public static void main(String[] args){
		FileSystemHandler fsh = new FileSystemHandler();
		fsh.createFolder(fsh.getCurrent(), "One Folder");
		fsh.createRecord(fsh.getCurrent(), "Two Folder");
		fsh.createRecord(fsh.getCurrent(), "Not searched");
		fsh.createRecord(fsh.getCurrent().getChild(0), "Three Folder");
		ArrayList<Node> temp = fsh.search("folder", fsh.getContents().getRoot());
		for(int i = 0; i < temp.size(); i++){
			System.out.println(temp.get(i));
		}
	}*/
}
