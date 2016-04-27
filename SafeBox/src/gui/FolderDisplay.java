package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import core.FileSystemHandler;
import core.Node;

public class FolderDisplay extends BackgroundPanel{

	private Border border;
	private int boarderWidth = 3;
	
	private static int DISPLAY_WIDTH = 30;
	private static final int DISPLAY_HEIGHT = 500;
	
	private static final int BUTTON_WIDTH = 50;
	private static final int BUTTON_HEIGHT = 50;
	private static final int BORDER_WIDTH = 2;
	private static final int FONT_SIZE = 18;
	private static final int ADD_BUTTON_DIMENSION = 25;
	
	private static final String IMG_BACK = "res/buttons/back.png";
	private static final String IMG_HOME = "res/buttons/home.png";
	private static final String IMG_PLUS = "plus.png";
	private static final String ADD_RECORD_FOLDER_TITLE = "Add Record / Folder";
	
	private JPanel toolBar = new JPanel(new BorderLayout(15,0));
	private JPanel centerBox = new JPanel(new FlowLayout(0));
	private JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	private NewFolderBox newFolderDialogBox;
	HashMap<Integer, Integer> hMap;
	ArrayList<FolderDisplayButton> fButtons = new ArrayList<FolderDisplayButton>();
	
	ArrayList<Node> children;
	
	FileSystemHandler fsh;
	StateManager sm;
	Node currentNode;
	protected JLabel directoryTitle;
	
	/**
	 * @param sm the state is is being added to
	 */
	protected FolderDisplay(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, DISPLAY_WIDTH, sm.window.getHeight(), true));
		this.sm = sm;
		newFolderDialogBox = new NewFolderBox(sm);
		newFolderDialogBox.setVisible(false);
		currentNode = getFSH().getRoot();

		
		//setSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
		setLayout(new BorderLayout(0, 0));
		//setBounds(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
		
		
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, Consts.BLUE_PANEL_COLOUR_BORDER);
		setBorder(border);
		setOpaque(true);
		
		//-------------------TOOLBAR--------------------
		
		
		CustomButton backButton = setupToolBarButton(IMG_BACK);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentNode = getCurrentNode();
				if(getFSH().getRoot() != currentNode){
					getFSH().setCurrentNode(currentNode.getParent());
					update();
				}
			}
		});
		
		directoryTitle = new JLabel(currentNode.getData().getName());
		directoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		directoryTitle.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, FONT_SIZE));
		
		
		CustomButton homeButton = setupToolBarButton(IMG_HOME);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentNode = getCurrentNode();
				if(getFSH().getRoot() != currentNode){
					getFSH().setCurrentNode(getFSH().getRoot());
					update();
				}
			}
		});
		
		toolBar.add(backButton, BorderLayout.WEST);
		toolBar.add(directoryTitle, BorderLayout.CENTER);
		toolBar.add(homeButton, BorderLayout.EAST);
		//-------------------CENTERBOX---------------------
		
		centerBox.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		
		//-------------------BOTTOMBAR---------------------
		
		CustomButton addRecordOrField = new CustomButton(ADD_RECORD_FOLDER_TITLE, 0, 0, ADD_BUTTON_DIMENSION, ADD_BUTTON_DIMENSION);
		addRecordOrField.setImageFromFile(IMG_PLUS, true);
		addRecordOrField.setHorizontalAlignment(SwingConstants.LEFT);
		addRecordOrField.setHorizontalTextPosition(JButton.RIGHT);
		addRecordOrField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFolderDialogBox.setVisible(true);
			}
		});
		
		bottomBar.add(addRecordOrField);
		
		setTransparentAdd(true);
		add(toolBar, BorderLayout.NORTH);
		add(bottomBar, BorderLayout.SOUTH);
		add(centerBox, BorderLayout.CENTER);
		
	}
	
	protected void init(){
		getFSH().setCurrentNode(getFSH().getRoot());
	}
	
	/**
	 * updates which folder the display is on
	 */
	protected void update(){
		centerBox.removeAll();
		centerBox.repaint();
		currentNode = getCurrentNode();
		
		if(!currentNode.getData().isRecord()){
			directoryTitle.setText(getCurrentNode().getData().getName());
			children = currentNode.getChildren();
			resizeDisplay();
			if(currentNode.hasChildren()){
				FolderDisplayButton fdb;
				for(int i = 0; i < children.size(); i++){
					Node child = children.get(i);
					if (!child.getData().isRecord()){
						fdb = new FolderDisplayButton(child.getData().getName(), 0, 0, DISPLAY_WIDTH, BUTTON_WIDTH, i, sm, FolderDisplayButton.FOLDER);
						centerBox.add(fdb, 0);	
					}else{
						fdb = new FolderDisplayButton(child.getData().getName(), 0, 0, DISPLAY_WIDTH, BUTTON_WIDTH, i, sm, FolderDisplayButton.RECORD);
						centerBox.add(fdb);
					}
					centerBox.revalidate();
					centerBox.repaint();	
				}		
			}
		}
		
	}
	
	private void resizeDisplay(){
		JLabel l = new JLabel();
		l.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, FONT_SIZE));
		int maxLength = (int) (directoryTitle.getPreferredSize().getWidth() + 150);
		l.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, FONT_SIZE));
		int temp = 0;
		for(int i = 0; i < children.size(); i++){
			l.setText(children.get(i).getData().getName());
			temp = (int) (l.getPreferredSize().getWidth() + 200);
			if(temp > maxLength){
				maxLength = temp;
			}
		}
		DISPLAY_WIDTH = maxLength;
		setImage(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, DISPLAY_WIDTH, sm.window.getHeight(), true));
	}
	
	private CustomButton setupToolBarButton(String imgPath){
		CustomButton b = new CustomButton("", 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		b.setImageIcon(MiscUtils.layerBufferedImages(MiscUtils.getBufferedGradImage(Consts.BUTTON_COLOUR_LIGHT, 
																							Consts.BUTTON_COLOUR_DARK, 
																							BUTTON_WIDTH, 
																							BUTTON_HEIGHT, 
																							true), 
															MiscUtils.getBufferedImageFromFile(imgPath, 
																							BUTTON_WIDTH)),
								true);
		b.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, BORDER_WIDTH);
		return b;
	}
	
	private Node getCurrentNode(){
		return getFSH().getCurrent();
	}
	
	private FileSystemHandler getFSH(){
		return sm.getESM().getFileSystemHandler();
	}	
}
