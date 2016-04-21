package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.glass.events.MouseEvent;

import core.Node;

public class FolderDisplay extends BackgroundPanel{

	private Border border;
	private int boarderWidth = 3;
	
	private static final int DISPLAY_WIDTH = 250;
	private static final int DISPLAT_HEIGHT = 500;
	
	private JPanel toolBar = new JPanel(new BorderLayout(15,0));
	private JPanel centerBox = new JPanel(new FlowLayout(0));
	private JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	StateManager sm;
	Node currentNode;
	
	protected FolderDisplay(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, DISPLAY_WIDTH, sm.window.getHeight(), true));
		this.sm = sm;
		
		currentNode = sm.getESM().getFileSystemHandler().getRoot();
		
		setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAT_HEIGHT));
		setLayout(new BorderLayout(10, 5));
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
				sm.cl.show(sm.cards, sm.PASSWORD_STATE);
			}
		});
		
		JLabel directoryTitle = new JLabel(currentNode.getData().getName());
		directoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		directoryTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		
		CustomButton homeButton = new CustomButton("", 0, 0, 50, 50);
		homeButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		homeButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		
		
		toolBar.add(backButton, BorderLayout.WEST);
		toolBar.add(directoryTitle, BorderLayout.CENTER);
		toolBar.add(homeButton, BorderLayout.EAST);
		//-------------------CENTERBOX---------------------
		
		
		
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
	
	protected void update(){
		System.out.println(currentNode.toString());
		if(currentNode.hasChildren()){
			clearCenter();
			ArrayList<Node> children = currentNode.getChildren();
			System.out.println(children.size());
			for(int i = 0; i < currentNode.getChildren().size(); i++){
				//JLabel l = new JLabel(children.get(i).toString());
				System.out.println(children.get(i).getData().getName());
				//centerBox.add(l, BorderLayout.CENTER);
				
				
			}			
		}		
	}
	
	protected void clearCenter(){
		int count = centerBox.getComponentCount();
		for(int i = 0; i < count; i++){
			centerBox.remove(i);
		}
	}
	
}
