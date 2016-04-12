package core;

import java.io.Serializable;

public class Tree implements Serializable{
	private static final long serialVersionUID = -3576405232059064152L;
	private Node root;
	//Initializes the root directory to be called Home without a parent.
	public Tree(){
		root = new Node(new Folder(),null);
		root.getData().setName("Home");
		root.setIndex(0);
	}
	
	public Node getRoot(){
		return root;
	}
	public String toString(){
		return root.toString();
	}
}