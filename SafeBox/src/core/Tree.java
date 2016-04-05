package core;

public class Tree {
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
}