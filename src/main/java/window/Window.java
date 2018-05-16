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
 * Ez az ablak osztály tárolja a megjelenítendő elemeket.
 */
public class Window extends JFrame implements ComponentListener, ResizeableElement {
	/**
	 * Fő ablak mező, a program indulásakor készül el, ha bezárják, a program megáll
	 */
	private static Window mainWindow;

	/**
	 * Konstruktor
	 */
	private Window() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Ablak bezáráskor megáll a process
		setSize(currentSize()); // Beállítja az alapértelmezett méretet
		setTitle(PropertyLoader.getProperties().getProperty(PropertyLoader.WINDOW_TITLE)); // Beállítja az ablak címét
		setResizable(PropertyLoader.getProperties().getBoolean(PropertyLoader.WINDOW_RESIZABLE)); // Beállítja az ablak átméretezhetőségét
		setLocationRelativeTo(null); // Középen induljon az ablak

		setJMenuBar(new MenuBar()); // Beállítja a menüsávot
		setContentPane(new ContentPane());

		addComponentListener(this);
		getRootPane().setFocusable(true);
		requestFocus();
		setVisible(true);
		revalidate();
		repaint();
	}

	/**
	 * Lusta inicializációja az ablaknak
	 *
	 * @return főablak
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

	/**
	 * A globális Property objektumban frissíti a jelenlegi ablakméretet
	 * Majd meghívja a onResize függvényt az összes ez a Window-ban található ResizeableElement interface-es elemre
	 * @param e event
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		PropertyLoader.getProperties().setProperty(PropertyLoader.WINDOW_WIDTH, Integer.toString(getWidth() - 16));
		PropertyLoader.getProperties().setProperty(PropertyLoader.WINDOW_HEIGHT, Integer.toString(getHeight() - getJMenuBar().getHeight() - 40));
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
		return (ContentPane) getContentPane();
	}
}