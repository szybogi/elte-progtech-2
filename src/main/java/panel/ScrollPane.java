package panel;

import logic.ResizeableElement;

import javax.swing.*;

public class ScrollPane extends JScrollPane implements ResizeableElement {

	private HouseGrid houseGrid;

	ScrollPane() {
		setViewportView(houseGrid);
		setSize(currentSize());
		houseGrid = new HouseGrid();
		setHouseGrid();
		setVisible(true);
		revalidate();
		repaint();
	}

	void setHouseGrid() {
		setViewportView(houseGrid);
		onResize();
	}

	@Override
	public void onResize() {
		setSize(currentSize());
		revalidate();
		repaint();
	}
}
