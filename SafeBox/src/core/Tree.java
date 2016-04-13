package core;

import java.io.Serializable;

public class Tree implements Serializable{
	private static final long serialVersionUID = -3576405232059064152L;
	private Node root;
	
	private int maxGlobalIndex = 0;
	
	//Initializes the root directory to be called Home without a parent.
	public Tree(){
		root = new Node(new Folder());
		root.setParent(null);
		root.getData().setName("Home");
		root.setGlobalIndex(0);
	}
	
	public void addNode(Node parent, Node child){
		maxGlobalIndex++;
		child.setGlobalIndex(maxGlobalIndex);
		child.setParent(parent);
		
		parent.addChild(child);
	}
	
	public Node getNode(Node parent, int localIndex){
		return parent.getChild(localIndex);
	}
	
	public Node getRoot(){
		return root;
	}
	
	public String toString(){
		return "Tree [\n" + root.deepToString() + "]";
	}
}