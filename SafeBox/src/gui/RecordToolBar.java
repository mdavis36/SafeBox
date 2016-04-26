package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class RecordToolBar extends BackgroundPanel{
	
	private StateManager sm;
	
	private Border border;
	private int boarderWidth = 3;
	
	private String title = "";
	JLabel recordTitle;
	
	
	private JPanel leftPanel = new JPanel(new FlowLayout(0));
	private JPanel centerPanel = new JPanel(new FlowLayout(0));
	private JPanel rightPanel = new JPanel(new FlowLayout(0));
	
	protected RecordToolBar(StateManager sm, int w, int h){
		super(MiscUtils.getBufferedGradImage(MiscUtils.ORANGE_PANEL_COLOUR_DARK2, MiscUtils.ORANGE_PANEL_COLOUR_LIGHT2, w, h, true));
		this.sm = sm;
		
		setSize(new Dimension(w, h));
		setPreferredSize(new Dimension(w, h));
		setLayout(new BorderLayout(20, 0));
		setBounds(0, 0, w, h);	
		//----------------------Border---------------------
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, MiscUtils.ORANGE_PANEL_COLOUR_BORDER);
		setBorder(border);
		setOpaque(true);
		
		//-----------------------------------------
		//------------- LEFT PANEL ----------------
		//-----------------------------------------
				
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));	
		
		//-------------------------------------------
		//------------- CENTER PANEL ----------------
		//-------------------------------------------
			
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));		
		
		//if (sm.getESM().getFileSystemHandler().getCurrentRecord().getData().getName() != null)
		//	title = sm.getESM().getFileSystemHandler().getCurrentRecord().getData().getName();
		
		recordTitle = new JLabel(title);
		recordTitle.setHorizontalAlignment(SwingConstants.CENTER);
		recordTitle.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		centerPanel.add(recordTitle);
		
		//------------------------------------------
		//------------- RIGHT PANEL ----------------
		//------------------------------------------
				
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		setTransparentAdd(true);
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
	}
	
	protected void init(){
		sm.getESM().getFileSystemHandler().setCurrentRecord(null);
		recordTitle.setText("");
	}
	
	
	protected void update(){
		
		if (sm.getESM().getFileSystemHandler().getCurrentRecord() != null){
			recordTitle.setText(sm.getESM().getFileSystemHandler().getCurrentRecord().getData().getName()); 
		}
		setVisible(true);
	}
}
