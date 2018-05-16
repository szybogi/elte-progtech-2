package panel;

import logic.ResizeableElement;
import model.House;

import javax.swing.*;

public class ScrollPane extends JScrollPane implements ResizeableElement {

	ScrollPane() {
		setSize(currentSize());
		//setHouseList();
		setHouseCreate();
		setVisible(true);
		revalidate();
		repaint();
	}

	void setHouseList() {
		setViewportView(new HouseList());
		onResize();
	}

	void setHouseCreate() {
		setHouseCreate(null);
	}

	void setHouseCreate(House house) {
		setViewportView(new HouseCreate(house));
		onResize();
	}

	void setAllianceGrid() {
		onResize();
	}

	void setAllianceCreate() {
		setViewportView(new HouseList()); // TODO: Alliance
		//setViewportView(new HouseCreate()); // TODO: Alliance
		onResize();
	}

	@Override
	public void onResize() {
		setSize(currentSize());
		revalidate();
		repaint();
	}
}
