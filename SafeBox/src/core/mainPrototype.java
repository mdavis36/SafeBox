package core;
import java.util.Scanner;

public class mainPrototype {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		// TODO Auto-generated method stub
		EncryptedFileSystemManager efsm = new EncryptedFileSystemManager();
		FileSystemHandler fileSys = efsm.fileSystem;
		Tree tree = efsm.fileSystem.contents;
		Node home = tree.getRoot();
		while (true)
		{
			System.out.println("Current Directory: "+ home.toString());
			System.out.println("Would you like to add a folder or Record?\n1. Folder\n2. Record");
			int choice = scan.nextInt();
			if(choice == 1){
				System.out.println("What would you like to call your Folder?");
				String fName = scan.next();
				home.addChild(fileSys.createFolder(home, fName));
			}
			else if(choice == 2){
				System.out.println("What would you like to call your Record?");
				String rName = scan.next();
				home.addChild(fileSys.createRecord(home, rName));
			}
			
		}
	}

}
