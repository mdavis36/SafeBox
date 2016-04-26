package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import core.Field;
import core.Node;
import core.Record;

	

public class RecordDisplay extends BackgroundPanel {
	private static final int DISPLAY_WIDTH = 250;
	private Border border;
	private int boarderWidth = 3;
	
	private StateManager sm;
	
	GridBagConstraints g = new GridBagConstraints();
	
	private JPanel toolBar;
	private JPanel fViewer;
	
	private JScrollPane scrollPane;
	private JPanel fieldPanel;
	
	
	private RecordToolBar recordToolBar;
	private CustomButton addFieldButton;
	
	private Node currentRecord;
	
	FieldBox test;
	FieldBox test2;
	FieldBox test3;
	FieldBox test4;
	FieldBox test5;
	FieldBox test6;
	protected RecordDisplay(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(MiscUtils.ORANGE_PANEL_COLOUR_LIGHT, MiscUtils.ORANGE_PANEL_COLOUR_DARK, DISPLAY_WIDTH, sm.window.getHeight(), true));
		this.sm = sm;
		//setLayout(new BorderLayout(0,0));
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, MiscUtils.ORANGE_PANEL_COLOUR_DARK);
		setBorder(border);
		setOpaque(true);
		
	}
	
	protected void init(){
		
		removeAll();
	}
	
	protected void update(){
		currentRecord = sm.getESM().getFileSystemHandler().getCurrentRecord();
		if(currentRecord != null){
			if (recordToolBar != null)
				recordToolBar.removeAll();
			if (scrollPane != null)
				scrollPane.removeAll();
			removeAll();

			toolBar = new JPanel(new BorderLayout(5,5));
			fViewer = new JPanel(new BorderLayout());
			fieldPanel = new JPanel();
			
			setLayout(new GridBagLayout());
			fieldPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();

			recordToolBar = new RecordToolBar(sm, DISPLAY_WIDTH, 40);
			
			///-------------------------------------------------------------
			
			Record recordData = (Record)currentRecord.getData();
			if (recordData.isRecord()){
				FieldBox fb;
				for(int i = 0; i < recordData.getFields().size(); i ++){
					fb = new FieldBox(0,0,600, 100, i, sm);
					fb.setAlignmentY(TOP_ALIGNMENT);
					c.weightx = 1;
					c.fill = GridBagConstraints.HORIZONTAL;
					c.ipady = 10;
					c.gridx = 0;
					c.gridy = i;
					fieldPanel.add(fb, c);
					
				}
			}

			//-------------------------------------------------------------
			
			addFieldButton = new CustomButton("Add Field", 0, 0, 100, 30);
			addFieldButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
			addFieldButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
			addFieldButton.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("you just clicked addField");
					Field f = new Field();
					f.setName("Name");
					f.setData("content");
					((Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData()).addField(f);
					update();
					
				}
			});
			
			
			fViewer.add(fieldPanel, BorderLayout.NORTH);
			fViewer.add(addFieldButton, BorderLayout.CENTER);
			scrollPane = new JScrollPane(fViewer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.getVerticalScrollBar().setUnitIncrement(5);
			
			recordToolBar.update();
			setTransparentAdd(true);
			g.weightx = 1;
			//c.weighty = 1;
			g.fill = GridBagConstraints.HORIZONTAL;
			g.ipady = 10;
			g.gridx = 0;
			g.gridy = 0;
			add(recordToolBar, g);
			g.weightx = 1;
			g.weighty = 1;
			g.fill = GridBagConstraints.BOTH;
			g.gridx = 0;
			g.gridy = 1;
			add(scrollPane, g);
			
		}
	}
	
}


