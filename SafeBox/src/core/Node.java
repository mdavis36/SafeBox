package core;

import java.util.ArrayList;

public class Node {
	/////////////
	//Variables//
	/////////////
	private Folder data;
	private String name;
	private int index;
	private Node parent;
	private ArrayList<Node> children;
	
	
	//Constructor Initializes a new node containing the folder/record f , with it's parent = p, without any children
	public Node(Folder f, Node p){
		data = f;
		children = null;
		parent = p;
	}
	
	///////////
	//GETTERS//
	///////////
	public int getIndex(){
		return index;
	}
	
	public Folder getData(){
		return data;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Node> getChildren(){
		return children;
	}
	
	public Node getChild(int index){
		return children.get(index);
	}
	
	///////////
	//SETTERS//
	///////////
	public void setIndex(int i){
		index = i;
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
}
