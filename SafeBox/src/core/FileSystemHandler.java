package core;

import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;

public class FileSystemHandler {
	private int currentIndex;
	private TreeModel contents;
	
	public FileSystemHandler(){
		
	}

	public ArrayList<Folder> search(String query){
		//TODO: 
		ArrayList<Folder> temp = null;
		return temp;
	}
	
	public Folder getRoot(){
		//TODO: 
		return new Folder();
	}
	
	public Folder getNode(int Node){
		//TODO: 
		return new Folder();
	}
	
	public Folder createFolder(){
		//TODO: 
		return new Folder();
	}
	
	public Folder createRecord(){
		//TODO: 
		return new Folder();
	}
	
	public boolean deleteFolder(int node){
		//TODO: 
		return true;
	}
}
