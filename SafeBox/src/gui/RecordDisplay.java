package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

	

public class RecordDisplay extends BackgroundPanel {
	private static final int DISPLAY_WIDTH = 250;
	private Border border;
	private int boarderWidth = 3;
	
	private StateManager sm;
	
	GridBagConstraints g = new GridBagConstraints();
	
	private JPanel toolBar = new JPanel(new BorderLayout(5,5));
	
	private JScrollPane scrollPane;
	private JPanel fieldViewver = new JPanel();
	
	
	RecordToolBar recordToolBar;
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
		setLayout(new GridBagLayout());
		
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, MiscUtils.ORANGE_PANEL_COLOUR_DARK);
		setBorder(border);
		setOpaque(true);
		
		
		
		fieldViewver.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		scrollPane = new JScrollPane(fieldViewver, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setAlignmentY(TOP_ALIGNMENT);
		recordToolBar = new RecordToolBar(sm, DISPLAY_WIDTH, 40);
		
		test = new FieldBox(0, 0, 600, 100, 1, sm);
		c.weightx = 1;
		//c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		fieldViewver.add(test, c);
		
		
		test2 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.weightx = 1;
		//c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 1;
		fieldViewver.add(test2, c);
		
		test3 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 2;
		//fieldViewver.add(test3, c);
		
		
		test4 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 3;
		//fieldViewver.add(test4, c);
		
		test5 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 4;
		//fieldViewver.add(test5, c);
		
		
		test6 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 5;
		//fieldViewver.add(test6, c);
		
		
		JButton btnNewButton_1 = new JButton("record display");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 217, 102));
		btnNewButton_1.setPreferredSize(new Dimension(600, 600));
		//add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.PASSWORD_STATE);
			}
		});
		
		
	}
	
	protected void init(){
		recordToolBar.init();
		removeAll();
	}
	
	protected void update(){
		if(sm.getESM().getFileSystemHandler().getCurrentRecord() != null){
			recordToolBar.update();
			setTransparentAdd(true);
			add(recordToolBar, BorderLayout.NORTH);
			add(scrollPane);
			repaint();
		}
	}
	
}


