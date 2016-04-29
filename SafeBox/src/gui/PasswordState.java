package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import core.HintManager;

public class PasswordState extends BackgroundPanel {
	private static final long serialVersionUID = 1L;
	private static final int BUTTON_WIDTH = 270;
	private static final int BUTTON_HEIGHT = BUTTON_WIDTH / 7;
	private static final int PASSWORD_WIDTH = 330;
	private static final Dimension PASSWORD_FIELD_DIMENSION = new Dimension(PASSWORD_WIDTH, 40);
	private static final int STARTUP_WIDTH = 600;
	private static final int STARTUP_HEIGHT = 250;
	private static final int TITLE_LABEL_Y = 75;
	private static final int TITLE_LABEL_WIDTH = 100;
	private static final int TITLE_LABEL_HEIGHT = 50;
	private static final int LOGO_HEIGHT = 20;
	private static final int PASSWORD_FIELD_FONT_SIZE = 24;

	private static final String TITLE = "SafeBox";
	private StateManager sm;

	private JPanel centerPanel;

	private final JPasswordField passWordField;

	StartUpDialog startup;

	protected PasswordState(final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, sm.window.getWidth(), sm.window.getHeight(), true));
		this.sm = sm;

		startup = new StartUpDialog(sm, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, STARTUP_WIDTH, STARTUP_HEIGHT);

		setLayout(new BorderLayout());
		centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;

		setTransparentAdd(true);
		JLabel titleLabel = new JLabel(TITLE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, Consts.DIALOGUE_BOX_TITLE_FONT_SIZE));
		titleLabel.setBounds(sm.window.getWidth() / 2 - 50, TITLE_LABEL_Y, TITLE_LABEL_WIDTH, TITLE_LABEL_HEIGHT);
		centerPanel.add(titleLabel, c);

		c.gridy += 1;
		JLabel lblSafeboxLogo = new JLabel();
		int logoWidth = 160;
		lblSafeboxLogo.setSize(new Dimension(logoWidth, LOGO_HEIGHT));
		BufferedImage logo = MiscUtils.getBufferedImageFromFile(Consts.LOGO_PATH + Consts.BIG_LOGO_NAME, lblSafeboxLogo.getWidth());
		lblSafeboxLogo.setIcon(new ImageIcon(logo));
		lblSafeboxLogo.setSize(new Dimension(logo.getWidth(), logo.getHeight()));
		centerPanel.add(lblSafeboxLogo, c);

		c.gridy += 1;
		setTransparentAdd(false);
		passWordField = new JPasswordField();
		passWordField.setPreferredSize(PASSWORD_FIELD_DIMENSION);
		passWordField.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, PASSWORD_FIELD_FONT_SIZE));
		passWordField.setHorizontalAlignment(SwingConstants.CENTER);
		resetPasswordField(passWordField);
		passWordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isInitTextField(passWordField.getPassword())) {
					passWordField.setText("");
					passWordField.setEchoChar(Consts.ECHO_CHAR);
					passWordField.setEditable(true);
				}
			}
		});
		passWordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					validatePasswordAndMoveForward(passWordField);
				}
			}
		});
		centerPanel.add(passWordField, c);

		c.gridy += 1;
		setTransparentAdd(true);
		CustomButton enterSBButton = setupButton(English.ENTER_SAFEBOX_TITLE);
		enterSBButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validatePasswordAndMoveForward(passWordField);
				init();
			}

		});
		centerPanel.add(enterSBButton, c);

		c.gridy += 1;
		CustomButton forgotPWButton = setupButton(English.FORGOT_PASSWORD_TITLE);
		forgotPWButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sm.showPlainMessage(HintManager.getHint());
				init();
			}
		});
		centerPanel.add(forgotPWButton, c);

		add(centerPanel, BorderLayout.CENTER);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (passWordField.getPassword().length == 0) {
					resetPasswordField(passWordField);
				}
			}
		});
	}

	private void validatePasswordAndMoveForward(JPasswordField passwordField) {
		sm.getESM().setPassword(passwordField.getPassword());

		if (!sm.getESM().fileSystemExists()) { // this should never happen
			return;
		}

		if (sm.getESM().loadFileSystemHandler()) {
			sm.setState(sm.MAIN_SCREEN_STATE);
			sm.setSuccessfullyDecrypted(true);
			sm.init();
			sm.update();
		} else {
			sm.showPlainMessage(English.INCORRECT_PASSWORD_MESSAGE);
			passwordField.setText("");
		}
	}

	protected void update() {

	}

	public void init() {
		resetPasswordField(passWordField);
		if (!sm.getESM().fileSystemExists()) {
			startup.open();
		}
	}

	private void resetPasswordField(JPasswordField p) {
		p.setHorizontalAlignment(SwingConstants.CENTER);
		p.setText(English.PASSWORD_TITLE);
		p.setEchoChar((char) 0);
		p.setEditable(false);
	}

	/**
	 * @param text
	 * @return
	 */
	private CustomButton setupButton(String text) {
		CustomButton b = new CustomButton(text, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		b.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT, Consts.BUTTON_COLOUR_DARK, true);
		return b;
	}

	private boolean isInitTextField(char[] cs) {
		if (English.PASSWORD_TITLE.equals(new String(cs))) {
			return true;
		} else {
			return false;
		}
	}
}
