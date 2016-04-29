package core;

import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {
	private static final long serialVersionUID = 221949262324254923L;
	/////////////
	//Variables//
	/////////////
	private Folder data;
	private int globalIndex;
	private Node parent;
	private ArrayList<Node> children;

	//Constructor Initializes a new node containing the folder/record f , with it's parent = p, without any children
	public Node(Folder f) {
		data = f;
		children = new ArrayList<Node>();
	}

	public Node(Folder f, ArrayList<Node> children) {
		data = f;
		this.children = children;
	}

	///////////
	//GETTERS//
	///////////
	public int getLocalIndex() {
		if (parent == null) { // this should mean we're the root node
			return 0;
		}
		return parent.getChildren().indexOf(this);
	}

	public Folder getData() {
		return data;
	}

	public Node getParent() {
		return parent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public Node getChild(int localIndex) {
		return children.get(localIndex);
	}

	public int getGlobalIndex() {
		return globalIndex;
	}

	///////////
	//SETTERS//
	///////////
	public void setGlobalIndex(int globalIndex) {
		this.globalIndex = globalIndex;
	}

	public void setData(Folder f) {
		data = f;
	}

	public void setParent(Node p) {
		parent = p;
	}

	////////////////
	//MANIPULATION//
	////////////////
	/**
	 * @param child the node added as a child of the current node
	 */
	public void addChild(Node child) {
		children.add(child);
	}

	/**
	 * @param index the spot where a child node is
	 */
	public void removeChild(int index) {
		children.remove(index);
	}

	/**
	 * @return a multi-line, indented representation of the tree, starting at this node
	 */
	public String deepToString() {
		return getGlobalIndex() + "G [Top] " + getData().getName() + "\n" + recursiveToString(1);
	}

	/**
	 * @param depth the number of indents to place before a row, will be incremented on each recursive call
	 * @return a multi-line, indented representation of the tree, starting at this.children
	 */
	private String recursiveToString(int depth) {
		String output = "";

		for (Node child : children) {
			if (child.getData().isRecord()) {
				output += MiscUtils.repeatStr("  ", depth) + child.getGlobalIndex() + "G " + child.getLocalIndex() + "L [R] " + child.getData().getName() + "\n";
			} else {
				output += MiscUtils.repeatStr("  ", depth) + child.getGlobalIndex() + "G " + child.getLocalIndex() + "L [F] " + child.getData().getName() + "\n";
				output += child.recursiveToString(depth + 1);
			}
		}

		return output;
	}

	@Override
	public String toString() {
		String outputString = data.getName();
		if (children.size() == 0) {
			outputString += "\n\tThis folder is empty! Add something!";
		}
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).getData().isRecord()) {
				outputString += "\n\t" + (i + 1) + ". [R]" + children.get(i).getData().getName();
			} else {
				outputString += "\n\t" + (i + 1) + ". [F]" + children.get(i).getData().getName();
			}
		}
		return outputString;
	}

	/**
	 * @return whether of not the node has children
	 */
	public boolean hasChildren() {
		if (children.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
