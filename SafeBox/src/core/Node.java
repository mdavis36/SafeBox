package core;

import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable{
	private static final long serialVersionUID = 221949262324254923L;
	/////////////
	//Variables//
	/////////////
	private Folder data;
	private int index;
	private Node parent;
	private ArrayList<Node> children;
	
	
	//Constructor Initializes a new node containing the folder/record f , with it's parent = p, without any children
	public Node(Folder f, Node p){
		data = f;
		children = new ArrayList<Node>();
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
	
	public String toString(){
		String output = data.getName()+"\n   ";
		if(children !=null){
			int length = children.size();
			for(int i = 0; i < length; i++){
				int index = i+1;
				if(children.get(i).getData().isRecord()){
					output += index + ". [R]"+ children.get(i).getData().getName() + "\n   ";
				}
				else{
					output += index + ". [F]"+ children.get(i).getData().getName() + "\n   ";
				}
			}
		}
		return output;
		
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


