package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import core.FileSystemHandler;
import core.Node;

public class FolderDisplay extends BackgroundPanel{

	private Border border;
	private int boarderWidth = 3;
	
	private static int DISPLAY_WIDTH = 30;
	private static final int DISPLAY_HEIGHT = 500;
	
	private static final int BUTTON_WIDTH = 40;
	private static final int BUTTON_HEIGHT = 40;
	private static final int BORDER_WIDTH = 2;
	private static final int FONT_SIZE = 18;
	private static final int ADD_BUTTON_DIMENSION = 25;
	
	private static final String IMG_BACK = "res/buttons/back.png";
	private static final String IMG_HOME = "res/buttons/home.png";
	private static final String IMG_PLUS = "plus.png";
	private static final String ADD_RECORD_FOLDER_TITLE = "Add Record / Folder";
	
	private JPanel toolBar;
	
	private JPanel fViewer;
	private JPanel folderPanel;
	private JScrollPane scrollPane;
	private JPanel bottomBar;
	private BackgroundPanel top;
	
	GridBagConstraints c = new GridBagConstraints();
	private NewFolderBox newFolderDialogBox;
	HashMap<Integer, Integer> hMap;
	ArrayList<FolderDisplayButton> fButtons = new ArrayList<FolderDisplayButton>();
	
	AddRecordFieldDialog b;
	
	ArrayList<Node> children;
	
	FileSystemHandler fsh;
	StateManager sm;
	Node currentNode;
	protected JLabel directoryTitle;
	
	/**
	 * @param sm the state is is being added to
	 */
	protected FolderDisplay(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, DISPLAY_WIDTH , sm.window.getHeight(), true));
		this.sm = sm;
		newFolderDialogBox = new NewFolderBox(sm);
		newFolderDialogBox.setVisible(false);
		currentNode = getFSH().getRoot();
		setLayout(new BorderLayout(0, 0));	
		
		b = new AddRecordFieldDialog(sm, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, 450, 200);
		
		
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, Consts.BLUE_PANEL_COLOUR_BORDER);
		setBorder(border);
		setOpaque(true);
		
		
		top = new BackgroundPanel(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, DISPLAY_WIDTH, sm.window.getHeight(), true));
		top.setTransparentAdd(true);
		top.setLayout(new GridBagLayout());
		top.setOpaque(false);
		top.setBorder(border);
		
		toolBar = new JPanel(new BorderLayout(15,0));
		toolBar.setOpaque(false);
		toolBar.setBorder(null);
		
		
		fViewer = new JPanel(new BorderLayout());
		folderPanel = new JPanel(new GridBagLayout());
		scrollPane = new JScrollPane(fViewer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		scrollPane.setBorder(null);
		bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
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
		directoryTitle.setOpaque(false);
		
		
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
		setTransparentAdd(true);
		toolBar.add(backButton, BorderLayout.WEST);
		toolBar.add(directoryTitle, BorderLayout.CENTER);
		toolBar.add(homeButton, BorderLayout.EAST);
		//-------------------CENTERBOX---------------------
		
		
		//-------------------BOTTOMBAR---------------------
		
		CustomButton addRecordOrField = new CustomButton(ADD_RECORD_FOLDER_TITLE, 0, 0, ADD_BUTTON_DIMENSION, ADD_BUTTON_DIMENSION);
		addRecordOrField.setImageFromFile(IMG_PLUS, true);
		addRecordOrField.setHorizontalAlignment(SwingConstants.LEFT);
		addRecordOrField.setHorizontalTextPosition(JButton.RIGHT);
		addRecordOrField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//newFolderDialogBox.setVisible(true);
				
				b.setVisible(true);
			}
		});
		
		fViewer.add(folderPanel, BorderLayout.NORTH);
		
		setTransparentAdd(true);
		bottomBar.add(addRecordOrField);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		top.add(toolBar, c);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 1;
		top.add(scrollPane, c);
		
		
		
		add(top, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		//add(fViewer, BorderLayout.CENTER);
	}
	
	protected void init(){
		getFSH().setCurrentNode(getFSH().getRoot());
	}
	
	/**
	 * updates which folder the display is on
	 */
	protected void update(){
		folderPanel.removeAll();
		folderPanel.repaint();
		currentNode = getCurrentNode();
		children = currentNode.getChildren();
		resizeDisplay();
		if(!currentNode.getData().isRecord()){
			directoryTitle.setText(getCurrentNode().getData().getName());
			
			if(currentNode.hasChildren()){
				folderPanel.setVisible(true);
				FolderDisplayButton fdb;
				for(int i = 0; i < children.size(); i++){
					c.gridy = i;
					c.anchor = GridBagConstraints.NORTHWEST;
					c.fill = GridBagConstraints.HORIZONTAL;
					Node child = children.get(i);
					if (!child.getData().isRecord()){
						fdb = new FolderDisplayButton(child.getData().getName(), 0, 0, DISPLAY_WIDTH, BUTTON_HEIGHT, i, sm, FolderDisplayButton.FOLDER);
						//fdb.setAlignmentX(TOP_ALIGNMENT);
						folderPanel.add(fdb, c);	
					}else{
						fdb = new FolderDisplayButton(child.getData().getName(), 0, 0, DISPLAY_WIDTH, BUTTON_HEIGHT, i, sm, FolderDisplayButton.RECORD);
						//fdb.setAlignmentX(TOP_ALIGNMENT);
						folderPanel.add(fdb, c);
					}
					folderPanel.revalidate();
					folderPanel.repaint();	
				}		
			} else{
				folderPanel.setVisible(false);
			}
		}
		fViewer.repaint();
	}
	
	private void resizeDisplay(){
		JLabel l = new JLabel();
		l.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, FONT_SIZE));
		int maxLength = (int) (directoryTitle.getPreferredSize().getWidth() + 130);
		l.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, BUTTON_HEIGHT / 2));
		int temp = 0;
		
		for(int i = 0; i < children.size(); i++){
			l.setText(children.get(i).getData().getName());
			temp = (int) (l.getPreferredSize().getWidth() + 100);
			if(temp > maxLength){
				maxLength = temp;
			}
		}
		DISPLAY_WIDTH = maxLength;
		System.out.println("children coutn :" + children.size() + "DisplayWidth : " + DISPLAY_WIDTH);
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
		return b;
	}
	
	private Node getCurrentNode(){
		return getFSH().getCurrent();
	}
	
	private FileSystemHandler getFSH(){
		return sm.getESM().getFileSystemHandler();
	}	
}
