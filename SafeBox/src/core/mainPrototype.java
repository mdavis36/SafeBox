package core;

import java.util.ArrayList;
import java.util.Scanner;

public class mainPrototype {
	public static Node search(Scanner scan, FileSystemHandler fsh, Node current) {
		System.out.println("What would you like to search your Record/Folder names for?\n");
		String query = scan.nextLine();
		ArrayList<Node> results = fsh.search(query, fsh.getContents().getRoot());
		int size = results.size();
		boolean back = false;
		while (!back) {
			if (size == 0) {
				System.out.println("Nothing found.\n");
				return current;
			} else {
				System.out.println("Results:");
				for (int i = 0; i < size; i++) {
					if (results.get(i).getData().isRecord()) {
						System.out.println("   " + (i + 1)+ ". " + "[R]"
								+ results.get(i).getData().getName());
					} else {
						System.out.println("   " + (i + 1) + ". "+"[F]"
								+ results.get(i).getData().getName());
					}
				}
				System.out.println("Select Folder/Record Number or \n[B]ack");
			}

			String choice = scan.nextLine();
			if (choice.equalsIgnoreCase("B")) {
				back = true;
				return current;
			} else if (!choice.isEmpty() && choice.charAt(0) > 48 && choice.charAt(0) <= 57) {
				int index = Integer.parseInt(choice);
				if (index <= results.size() && index > 0) {
					return results.get(index - 1);
				}
				
			}
		}
		return current;
	}

	public static void main(String[] args) {
		///////////////
		//SETUP BEGIN//
		///////////////
		Scanner scan = new Scanner(System.in);
		String choice = "";
		boolean exit = false;
		ArrayList<String> path;
		String directoryPath;
		EncryptedStorageManager esm = new EncryptedStorageManager();
		do {
			System.out.println("Enter a password: "); //user creates or enters existing password at startup
			choice = scan.nextLine();
		} while (choice.isEmpty()); //in case user presses enter
		esm.setPassword(choice.toCharArray()); // this is used
																// for all
																// encryption/decryption
		if (!esm.fileSystemExists()) { // if this is the first run
			System.out.println("No filesystem exists, creating one!");
			esm.initNewFileSystem(); // make a blank filesystem
		} else {
			if (esm.loadFileSystemHandler()) { // try to load the saved
												// filesystem
				System.out.println("Successfully loaded FSH!");
			} else {
				while (!esm.loadFileSystemHandler()){
				do {
					System.out.println("WRONG! Enter different password: "); //user creates or enters existing password at startup
					choice = scan.nextLine();
				} while (choice.isEmpty()); //in case user presses enter
				esm.setPassword(choice.toCharArray());
				}
			}
		}
		Tree tree = esm.fileSystem.contents;
		Node current = tree.getRoot();
		Node root = tree.getRoot();
		FileSystemHandler fsh = esm.getFileSystemHandler();
		// ///////////
		// SETUP END//
		// ///////////

		while (!exit) {
			path = new ArrayList<String>();
			directoryPath = "";

			// If you aren't at the Home directory
			if (current != root) {
				Node temp = current;
				while (temp != root) {
					path.add(temp.getData().getName());
					temp = temp.getParent();
				}
				path.add(root.getData().getName());
				int dirHeight = path.size() - 1;
				for (int i = dirHeight; i >= 0; i--) {
					directoryPath += path.get(i) + " | ";
				}
			}

			// Record Screen Start
			if (current.getData().isRecord()) {
				System.out.println("Record: " + directoryPath
						+ current.getData().toString());
				System.out.println("[N]ew Field\n[B]ack\n[E]xit");
				choice = scan.nextLine();
				if (choice.equals("B") || choice.equals("b")) {
					current = current.getParent();
				} else if (choice.equals("E") || choice.equals("e")) {
					exit = true;
				} else if (choice.equals("N") || choice.equals("n")) {
					System.out.println("What is the name of the field?");
					String fName = scan.nextLine();
					System.out.println("What is the content of the field?");
					String fContents = scan.nextLine();
					Field toAdd = new Field();
					toAdd.setName(fName);
					toAdd.setData(fContents);
					((Record) current.getData()).addField(toAdd);
				}
			}
			// Record Screen End

			// Directory Screen Start
			else {
				System.out.println("Directory: " + directoryPath);// +
																	// current.toString());
				System.out.println(current.toString());
				System.out.println("\nSelect Folder/Record Number or \nNew [F]older\nNew [R]ecord\n[S]earch\n[B]ack\n[E]xit");
				choice = scan.nextLine();
				if (choice.equals("F") || choice.equals("f")) {
					System.out.println("What would you like to call your Folder?");
					String fName = scan.nextLine();
					fsh.createFolder(fsh.getCurrent(), fName);
				} else if (choice.equalsIgnoreCase("R")) {
					System.out.println("What would you like to call your Record?");
					String rName = scan.nextLine();
					fsh.createRecord(current, rName);
					current = current.getChild(current.getChildren().size() - 1);
					System.out.println("What is the name of the field?");
					String fName = scan.nextLine();
					Record temp = (Record) current.getData();
					Field fieldToAdd = new Field();
					fieldToAdd.setName(fName);
					System.out.println("What is the content of the field?");
					String fContent = scan.nextLine();
					fieldToAdd.setData(fContent);
					temp.addField(fieldToAdd);

				} else if (choice.equalsIgnoreCase("B")) {
					if (current.getParent() != null) {
						current = current.getParent();
					} else {
						System.out.println("You are at the home directory!");
					}
				} else if (choice.equalsIgnoreCase("E")) {
					exit = true;
				} else if (choice.equalsIgnoreCase("S")) {
					current = search(scan, fsh, current);
				} else if (!choice.isEmpty() && 
					choice.charAt(0) > 48 && choice.charAt(0) <= 57) {
					int index = Integer.parseInt(choice);
					if (index <= current.getChildren().size() && index > 0) {
						current = current.getChild(index - 1);
					}
				}
			}
			// Directory Screen End
		}
		scan.close();
		System.out.println("Encrypting data...");
		esm.saveFileSystemHandler(); // save the FileSystemHandler to the disk
		System.out.println("Data saved and encrypted. Bye!");
	}

}
