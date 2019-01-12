package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import core.Field;
import core.Node;
import core.Record;

public class RecordDisplay extends BackgroundPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1255356460279101185L;
	private static final int DISPLAY_WIDTH = 250;
	private Border border;
	private int boarderWidth = 3;

	private StateManager sm;

	private GridBagConstraints c = new GridBagConstraints();
	private GridBagConstraints g = new GridBagConstraints();

	private JPanel fViewer;
	private JPanel fieldPanel;
	private JScrollPane scrollPane;

	private static final int RECORD_TOOL_BAR_HEIGHT = 40;
	private static final int UNIT_INCREMENT = 5;

	private RecordToolBar recordToolBar;
	private CustomButton addFieldButton;
	private Node currentRecord;

	protected RecordDisplay(final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(Consts.ORANGE_PANEL_COLOUR_LIGHT, Consts.ORANGE_PANEL_COLOUR_DARK, DISPLAY_WIDTH, sm.window.getHeight(), true));
		this.sm = sm;
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, Consts.ORANGE_PANEL_COLOUR_DARK);
		setBorder(border);
		setOpaque(true);

		setLayout(new GridBagLayout());

		//-------------------TOOLBAR--------------------
		recordToolBar = new RecordToolBar(sm, DISPLAY_WIDTH, RECORD_TOOL_BAR_HEIGHT);

		//-------------------CENTERBOX---------------------

		fViewer = new JPanel(new BorderLayout());
		fieldPanel = new JPanel(new GridBagLayout());
		scrollPane = new JScrollPane(fViewer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(UNIT_INCREMENT);

		//-------------------BOTTOMBAR---------------------

		addFieldButton = new CustomButton(English.ADD_FIELD, 0, 0, 100, 30);
		addFieldButton.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT, Consts.BUTTON_COLOUR_DARK, true);
		addFieldButton.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, 2);

		addFieldButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Field f = new Field();
				f.setName(English.NAME);
				f.setData(English.CONTENT);
				((Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData()).addField(f);
				init();
				update();

			}
		});

		fViewer.add(fieldPanel, BorderLayout.NORTH);
		fViewer.add(addFieldButton, BorderLayout.PAGE_END);

		setTransparentAdd(true);
		g.weightx = 1;
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

	protected void init() {
		setVisible(false);
	}

	protected void update() {
		currentRecord = sm.getESM().getFileSystemHandler().getCurrentRecord();
		if (currentRecord != null) {
			setVisible(true);
			recordToolBar.update();
			fieldPanel.removeAll();
			fieldPanel.repaint();

			///-------------------------------------------------------------

			Record recordData = (Record) currentRecord.getData();
			if (recordData.isRecord()) {
				FieldBox fb;
				for (int i = 0; i < recordData.getFields().size(); i++) {
					fb = new FieldBox(0, 0, 10, 60, i, sm);
					fb.setAlignmentY(TOP_ALIGNMENT);
					c.weightx = 1;
					c.fill = GridBagConstraints.HORIZONTAL;
					c.ipady = 10;
					c.gridx = 0;
					c.gridy = i;
					fieldPanel.add(fb, c);
					fieldPanel.revalidate();
					fieldPanel.repaint();
				}
			}

			//-------------------------------------------------------------

			revalidate();
			repaint();

		} else {
			recordToolBar.update();
			fieldPanel.removeAll();
			fieldPanel.repaint();
			setVisible(false);
		}
	}

}
