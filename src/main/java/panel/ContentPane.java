package panel;

import logic.ResizeableElement;

import javax.swing.*;
import java.awt.*;

public class ContentPane extends JLayeredPane implements ResizeableElement {

	private ScrollPane scrollPane;

	public ContentPane() {
		setSize(currentSize());
		setLayout(null);
		setBackground(new Color(130, 162, 182, 255));

		scrollPane = new ScrollPane();
		add(scrollPane);
		setVisible(true);
		revalidate();
		repaint();
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	@Override
	public void onResize() {
		setSize(currentSize());
		revalidate();
		repaint();
	}
}