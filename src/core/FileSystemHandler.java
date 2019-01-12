package core;

import java.io.Serializable;
import java.util.ArrayList;

public class FileSystemHandler implements Serializable {
	private static final long serialVersionUID = -5417133060210085474L;
	private Node currentNode;
	private Node currentRecord;
	public Tree contents;

	@Override
	public String toString() {
		return "FileSystemHandler [currentNode=" + currentNode + ", contents=\n" + contents + "]";
	}

	public FileSystemHandler() {
		contents = new Tree();
		currentNode = contents.getRoot();
		currentRecord = null;
	}

	///////////
	//GETTERS//
	///////////

	public void setCurrentNode(Node n) {
		currentNode = n;
	}

	public void setCurrentRecord(Node n) {
		currentRecord = n;
	}

	public Node getRoot() {
		return contents.getRoot();
	}

	public Node getCurrentRecord() {
		return currentRecord;
	}

	public Node getCurrent() {
		return currentNode;
	}

	public Tree getContents() {
		return contents;
	}

	////////////////
	//MANIPULATION//
	////////////////
	/**
	 * @param parent the parent of the new node
	 * @param name the new folder's name
	 * @return the node just created
	 */
	public Node createFolder(Node parent, String name) {
		Folder folderToAdd = new Folder();
		folderToAdd.setName(name);

		Node nodeToAdd = new Node(folderToAdd);
		contents.addNode(parent, nodeToAdd);
		return nodeToAdd;
	}

	/**
	 * @param parent the parent of the new node
	 * @param name the new record's name
	 * @return the node just created
	 */
	public Node createRecord(Node parent, String name) {
		Record recordToAdd = new Record();
		recordToAdd.setName(name);
		Node nodeToAdd = new Node(recordToAdd);
		contents.addNode(parent, nodeToAdd);
		return nodeToAdd;
	}

	public void deleteFolder(Node n) {
		int localIndex = n.getLocalIndex();
		Node parent = n.getParent();
		deleteFolder(parent, localIndex);
	}

	public boolean deleteFolder(Node parent, int index) {
		if (parent == null) {
			return false;
		} else {
			Node temp = parent.getChild(index);
			removeFromList(temp.getGlobalIndex());
			parent.removeChild(index);
			int size = temp.getChildren().size();
			if (temp.hasChildren()) {
				for (int i = size - 1; i >= 0; i--) {
					if (temp.getChild(0).hasChildren()) {
						deleteFolder(temp, 0);
					}
					contents.getNodeList().set(temp.getChild(0).getGlobalIndex(), null);
					temp.getChildren().remove(0);
				}
			}
			return true;
		}
	}

	public ArrayList<Node> search(String query, Node startNode) {
		ArrayList<Node> toReturn = new ArrayList<Node>();
		int size = contents.getMaxGlobalIndex();
		String lowercaseQuery = query.toLowerCase();
		Node node;

		for (int i = 1; i <= size; i++) {
			node = contents.getNodeByGlobalIndex(i);

			if (node == null) {
				continue;
			}

			if (node.getData().getName().toLowerCase().contains(lowercaseQuery)) {
				toReturn.add(contents.getNodeByGlobalIndex(i));
			}
		}
		return toReturn;
	}

	public void removeFromList(int gIndex) {
		ArrayList<Node> list = contents.getNodeList();
		list.set(gIndex, null);
	}
}
