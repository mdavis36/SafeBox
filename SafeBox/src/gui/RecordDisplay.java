package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
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
	JPanel fViewer = new JPanel(new BorderLayout());
	
	private JScrollPane scrollPane;
	private JPanel fieldPanel = new JPanel();
	
	
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
		
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//scrollPane = new JScrollPane(fieldViewver, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		recordToolBar = new RecordToolBar(sm, DISPLAY_WIDTH, 40);
		
		test = new FieldBox(0, 0, 600, 100, 1, sm);
		test.setAlignmentY(TOP_ALIGNMENT);
		c.weightx = 1;
		//c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		fieldPanel.add(test, c);
		
		
		test2 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.weightx = 1;
		//c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 1;
		fieldPanel.add(test2, c);
		
		test3 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 2;
		fieldPanel.add(test3, c);
		
		
		test4 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 3;
		fieldPanel.add(test4, c);
		
		test5 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 4;
		fieldPanel.add(test5, c);
		
		
		test6 = new FieldBox(0, 0, 600, 100, 1, sm);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 5;
		fieldPanel.add(test6, c);
		
		
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
		
		fViewer.add(fieldPanel, BorderLayout.NORTH);
		scrollPane = new JScrollPane(fViewer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
	}
	
	protected void init(){
		recordToolBar.init();
		removeAll();
	}
	
	protected void update(){
		if(sm.getESM().getFileSystemHandler().getCurrentRecord() != null){
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
			repaint();
		}
	}
	
}


