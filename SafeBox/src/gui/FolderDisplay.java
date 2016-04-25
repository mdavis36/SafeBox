package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
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
	
	private static final int DISPLAY_WIDTH = 250;
	private static final int DISPLAT_HEIGHT = 500;
	
	private JPanel toolBar = new JPanel(new BorderLayout(15,0));
	private JPanel centerBox = new JPanel(new FlowLayout(0));
	private JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	HashMap<Integer, Integer> hMap;
	ArrayList<FolderDisplayButton> fButtons = new ArrayList<FolderDisplayButton>();
	
	FileSystemHandler fsh;
	StateManager sm;
	Node currentNode;
	
	protected JLabel directoryTitle;
	
	/**
	 * @param sm the state is is being added to
	 */
	protected FolderDisplay(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, DISPLAY_WIDTH, sm.window.getHeight(), true));
		this.sm = sm;

		currentNode = sm.getESM().getFileSystemHandler().getRoot();
		
		setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAT_HEIGHT));
		setLayout(new BorderLayout(0, 0));
		setBounds(0, 0, DISPLAY_WIDTH, DISPLAT_HEIGHT);
		
		
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, MiscUtils.BLUE_PANEL_COLOUR_BORDER);
		setBorder(border);
		setOpaque(true);
		
		//-------------------TOOLBAR--------------------
		
		
		CustomButton backButton = new CustomButton("", 0, 0, 50, 50);
		backButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		backButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		backButton.setHorizontalAlignment(SwingConstants.CENTER);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentNode = getCurrentNode();
				if(sm.getESM().getFileSystemHandler().getRoot() != currentNode){
					sm.getESM().getFileSystemHandler().setCurrentNode(currentNode.getParent());
					update();
				}
			}
		});
		
		directoryTitle = new JLabel(currentNode.getData().getName());
		directoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		directoryTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		
		CustomButton homeButton = new CustomButton("", 0, 0, 50, 50);
		homeButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		homeButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.setState(sm.PASSWORD_STATE);
			}
		});
		
		toolBar.add(backButton, BorderLayout.WEST);
		toolBar.add(directoryTitle, BorderLayout.CENTER);
		toolBar.add(homeButton, BorderLayout.EAST);
		//-------------------CENTERBOX---------------------
		
		centerBox.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		
		//-------------------BOTTOMBAR---------------------
		
		CustomButton addRecordOrField = new CustomButton("Add Record / Folder", 0, 0, 25, 25);
		addRecordOrField.setImageFromFile("plus.png", true);
		addRecordOrField.setHorizontalAlignment(SwingConstants.LEFT);
		addRecordOrField.setHorizontalTextPosition(JButton.RIGHT);
		addRecordOrField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(sm.window, "//Add record / folder", null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		bottomBar.add(addRecordOrField);
		
		setTransparentAdd(true);
		add(toolBar, BorderLayout.NORTH);
		add(bottomBar, BorderLayout.SOUTH);
		add(centerBox, BorderLayout.CENTER);
		
	}
	
	private Node getCurrentNode(){
		return sm.getESM().getFileSystemHandler().getCurrent();
	}
	
	protected void init(){
		sm.getESM().getFileSystemHandler().setCurrentNode(sm.getESM().getFileSystemHandler().getRoot());
	}
	
	/**
	 * updates which folder the display is on
	 */
	protected void update(){
		centerBox.removeAll();
		centerBox.repaint();
		System.out.println(sm.getESM().getFileSystemHandler().getCurrent().deepToString());
		currentNode = sm.getESM().getFileSystemHandler().getCurrent();
		
		if(!currentNode.getData().isRecord()){
			directoryTitle.setText(getCurrentNode().getData().getName());
			if(currentNode.hasChildren()){
			
				ArrayList<Node> children = currentNode.getChildren();
			
				FolderDisplayButton fdb;
				for(int i = 0; i < children.size(); i++){
					Node child = children.get(i);
					if (!child.getData().isRecord()){
						fdb = new FolderDisplayButton(child.getData().getName(), 0, 0, DISPLAY_WIDTH, 50, i, sm);
						setTransparentAdd(true);
						centerBox.add(fdb);
						centerBox.revalidate();
						centerBox.repaint();		
					}
				}	
				RecordDisplayButton rdb;
				for(int i = 0; i < children.size(); i++){
					Node child = children.get(i);
					if(child.getData().isRecord()){
						rdb = new RecordDisplayButton(child.getData().getName(), 0, 0, DISPLAY_WIDTH, 50, i, sm);
						centerBox.add(rdb);
						centerBox.revalidate();
						centerBox.repaint();		
					}
				}	
			}
		}
	}
	
	protected void clearCenter(){
		int count = centerBox.getComponentCount();
		for(int i = 0; i < count; i++){
			centerBox.getComponent(i).setVisible(false);
			centerBox.remove(i);
		}
	}
	
}
