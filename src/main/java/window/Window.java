package window;

import panel.AbstractPanel;
import panel.Menu;

import javax.swing.*;

/**
 * Window object
 * Used to store Panels which will hold the content
 */
public class Window extends JFrame {

	/**
	 * Main Window global field, created on the start of the program
	 * If this window is closed, the programs process stops
	 */
	private static Window mainWindow;
	private AbstractPanel content;

	/**
	 * No Arg constructor sets the default close operation to exit on close,
	 * so the process stops when the user closes the window
	 */
	private Window() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setTitle("Game of Thrones");
		setVisible(true);

		loadMenu();
	}

	private void loadMenu() {
		changeContent(new Menu());
	}

	private void changeContent(AbstractPanel panel) {
		if(content != null) {
			content.setVisible(false);
			remove(content);
		}
		content = panel;
		add(content);
		content.setVisible(true);
	}

	/**
	 * Lazy instantiation of the Singleton main window
	 * @return the main window
	 */
	public static Window getMainWindow() {
		if(mainWindow == null) {
			mainWindow = new Window();
		}
		return mainWindow;
	}

}