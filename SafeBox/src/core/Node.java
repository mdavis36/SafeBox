package core;

import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable{
	private static final long serialVersionUID = 221949262324254923L;
	/////////////
	//Variables//
	/////////////
	private Folder data;
	private int globalIndex;
	private Node parent;
	private ArrayList<Node> children;
	
	
	//Constructor Initializes a new node containing the folder/record f , with it's parent = p, without any children
	public Node(Folder f){
		data = f;
		children = new ArrayList<Node>();
	}
	
	///////////
	//GETTERS//
	///////////
	public int getLocalIndex(){
		if (parent == null){ // this should mean we're the root node
			return 0;
		}
		return parent.getChildren().indexOf(this);
	}
	
	public Folder getData(){
		return data;
	}
	
	public Node getParent(){
		return parent;
	}
	
	
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	public Node getChild(int localIndex){
		return children.get(localIndex);
	}
	
	public int getGlobalIndex(){
		return globalIndex;
	}
	
	///////////
	//SETTERS//
	///////////
	public void setGlobalIndex(int globalIndex){
		this.globalIndex = globalIndex;
	}
	
	public void setData(Folder f){
		data = f;
	}
	
	public void setParent(Node p){
		parent = p;
	}
	
	////////////////
	//MANIPULATION//
	////////////////
	public void addChild(Node child){
		children.add(child);
	}
	
	public void removeChild(int index){
		children.remove(index);
	}
	
	/**
	 * @return a multi-line, indented representation of the tree, starting at this node
	 */
	public String deepToString(){
		return getGlobalIndex() + "G [Top] " + getData().getName() + "\n" + recursiveToString(1);
	}
	
	/**
	 * @param depth the number of indents to place before a row, will be incremented on each recursive call
	 * @return a multi-line, indented representation of the tree, starting at this.children
	 */
	private String recursiveToString(int depth){
		String output = "";
		
		for (Node child : children){
			if(child.getData().isRecord()){
				output += MiscUtils.repeatStr("  ", depth) + child.getGlobalIndex() + "G " + child.getLocalIndex() + "L [R] "+ child.getData().getName() + "\n";
			} else {
				output += MiscUtils.repeatStr("  ", depth) + child.getGlobalIndex() + "G " + child.getLocalIndex() + "L [F] "+ child.getData().getName() + "\n";
				output += child.recursiveToString(depth + 1);
			}
		}
		
		return output;
	}
	
	public String toString(){
		return "[name=" + getData().getName() + ", localIndex=" + getLocalIndex() + ", globaIndex=" + getGlobalIndex() + ", children.size()=" + getChildren().size() + "]";
	}
	
	//TODO: Remove once done testing
	/*public static void main(String[] args) {
		Folder f1 = new Folder();
		Folder f2 = new Folder();
		Folder f3 = new Folder();
		f1.setName("Home");
		f2.setName("Websites");
		f3.setName("Contacts");
		Node n1 = new Node(f1, null);
		Node n2 = new Node(f2, n1);
		Node n3 = new Node(f3, n1);
		n1.addChild(n2);
		n1.addChild(n3);
		if(n1.children == null){
			System.out.println("Uh oh");
		}
		else
			System.out.println(n1.toString());
	}*/
}


