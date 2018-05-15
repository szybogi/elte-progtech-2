package window;

import logic.ComponentTools;
import logic.PropertyLoader;
import logic.ResizeableElement;
import panel.ContentPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import panel.MenuBar;

/**
 * Window object
 * Used to store Panels which will hold the content
 */
public class Window extends JFrame implements ComponentListener, ResizeableElement {
	/**
	 * Main Window global field, created on the start of the program
	 * If this window is closed, the programs process stops
	 */
	private static Window mainWindow;

	private ContentPane windowContent;

	/**
	 * No Arg constructor sets the default close operation to exit on close,
	 * so the process stops when the user closes the window
	 */
	private Window() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(currentSize());
		setTitle(PropertyLoader.getProperties().getProperty(PropertyLoader.WINDOW_TITLE));
		//loadMenu();

		setResizable(PropertyLoader.getProperties().getBoolean(PropertyLoader.WINDOW_RESIZABLE));

		setLocationRelativeTo(null);

		setJMenuBar(new MenuBar());

		windowContent = new ContentPane();
		setContentPane(windowContent);

		addComponentListener(this);
		getRootPane().setFocusable(true);
		requestFocus();
		setVisible(true);
		revalidate();
		repaint();
	}

	/**
	 * Lazy instantiation of the Singleton main window
	 *
	 * @return the main window
	 */
	public static Window getMainWindow() {
		if (mainWindow == null) {
			mainWindow = new Window();
		}
		return mainWindow;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics2D = (Graphics2D) g;
		if (PropertyLoader.getProperties().getBoolean(PropertyLoader.GRAPHICS_ANTIALIASING)) {
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		PropertyLoader.getProperties().setProperty(PropertyLoader.WINDOW_WIDTH, Integer.toString(getWidth() - 16));
		PropertyLoader.getProperties().setProperty(PropertyLoader.WINDOW_HEIGHT, Integer.toString(getHeight() - 32));
		ComponentTools.findComponents(this, ResizeableElement.class).forEach(ResizeableElement::onResize);
		revalidate();
		repaint();
		requestFocus();
	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}

	@Override
	public void onResize() {

	}

	public ContentPane getWindowContent() {
		return windowContent;
	}
}