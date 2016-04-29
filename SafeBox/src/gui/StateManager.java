package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.EncryptedStorageManager;
import core.LockManager;
import core.WindowSizeManager;

public class StateManager extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 3815085607279513067L;

	protected JFrame window;

	protected final String PASSWORD_STATE = "passwordState";
	protected final String MAIN_SCREEN_STATE = "mainScreenState";

	private core.EncryptedStorageManager eSM;
	private PasswordState passwordState;
	private MainScreenState mainScreenState;

	static CardLayout cl;

	private boolean successfullyDecrypted = false;

	PlainMessageDialog plainMessageDialog;
	ConfirmDialog confirmDialog;

	boolean isSuccessfullyDecrypted() {
		return successfullyDecrypted;
	}

	void setSuccessfullyDecrypted(boolean successfullyDecrypted) {
		this.successfullyDecrypted = successfullyDecrypted;
	}

	void showPlainMessage(String message) {
		plainMessageDialog.setMessage(message);
		plainMessageDialog.open();
	}

	/**
	 * @param window
	 */
	protected StateManager(final JFrame window) {
		this.window = window;
		eSM = new EncryptedStorageManager();
		//Initialize cards
		//cards = new JPanel(new CardLayout());
		setLayout(new CardLayout());
		cl = (CardLayout) getLayout();

		populateStates();

		window.getContentPane().add(this);
		cl.show(this, PASSWORD_STATE);

		plainMessageDialog = new PlainMessageDialog(this, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, 450, 200, "");
		confirmDialog = new ConfirmDialog(this, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, 450, 220, null);

		MiscUtils.setIcon(window, Consts.LOGO_PATH + Consts.ICON_NAME);

		if (!LockManager.requestLock()) {
			confirmDialog.setMessage(English.MULTIPLE_INSTANCES_OPEN);
			confirmDialog.setButtonText(English.EXIT, English.CONTINUE_TEXT);
			confirmDialog.open();

			if (!confirmDialog.getConfirmation()) {
				System.exit(0);
			}
		}

		init();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (successfullyDecrypted) {
					System.out.println("Saving...");
					eSM.saveFileSystemHandler();
					WindowSizeManager.setSize(window.getWidth(), window.getHeight());
					LockManager.releaseLock();
					System.out.println("Saved.");
				}
			}
		});
	}

	/**
	 * adds all states
	 */
	private void populateStates() {

		passwordState = new PasswordState(this);
		mainScreenState = new MainScreenState(this);

		add(mainScreenState, MAIN_SCREEN_STATE);
		add(passwordState, PASSWORD_STATE);
	}

	protected void setState(String stateName) {
		cl.show(this, stateName);
	}

	protected EncryptedStorageManager getESM() {
		return eSM;
	}

	protected void init() {
		mainScreenState.init();
		passwordState.init();
	}

	protected void update() {
		mainScreenState.update();
		passwordState.update();
	}

	public JFrame getWindow() {
		return window;
	}

}
