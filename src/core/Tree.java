package core;

import java.io.Serializable;
import java.util.ArrayList;

public class Tree implements Serializable {
	private static final long serialVersionUID = -3576405232059064152L;
	private Node root;
	ArrayList<Node> nodeList;
	private int maxGlobalIndex = 0;

	//Initializes the root directory to be called Home without a parent.
	public Tree() {
		nodeList = new ArrayList<Node>();
		root = new Node(new Folder());
		root.setParent(null);
		root.getData().setName("Home");
		root.setGlobalIndex(0);
		nodeList.add(root);
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public void addNode(Node parent, Node child) {
		maxGlobalIndex++;
		child.setGlobalIndex(maxGlobalIndex);
		child.setParent(parent);
		parent.addChild(child);
		nodeList.add(child);
	}

	/**
	 * @param globalIndex the global index of the node to get
	 * @return a node if found, or null if it doesn't exist
	 */
	public Node getNodeByGlobalIndex(int globalIndex) {
		if (nodeList == null) {
			return null;
		}

		try {
			return nodeList.get(globalIndex);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public Node getNode(Node parent, int localIndex) {
		return parent.getChild(localIndex);
	}

	public Node getRoot() {
		return root;
	}

	public int getMaxGlobalIndex() {
		return maxGlobalIndex;
	}

	@Override
	public String toString() {
		return "Tree [\n" + root.deepToString() + "]";
	}
}