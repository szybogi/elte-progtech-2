package panel;

import logic.ResizeableElement;
import model.AbstractEntity;
import model.Alliance;
import model.House;
import model.Person;

import javax.swing.*;

public class ScrollPane extends JScrollPane implements ResizeableElement {

	ScrollPane() {
		setSize(currentSize());
		//setHouseList();
		setCharacterCreate();
		setVisible(true);
		revalidate();
		repaint();
	}

	void openEntity(AbstractEntity abstractEntity) {
		if(abstractEntity instanceof House) {
			setHouseCreate((House) abstractEntity);
		} else if(abstractEntity instanceof Person) {
			setCharacterCreate((Person) abstractEntity);
		} else if(abstractEntity instanceof Alliance) {
			setAllianceCreate((Alliance) abstractEntity);
		}
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


	void setAllianceList() {
		onResize();
	}

	void setAllianceCreate() {
		setAllianceCreate(null); // TODO: Alliance
		onResize();
	}

	void setAllianceCreate(Alliance allianceCreate) {
		setViewportView(new PersonList()); // TODO: Alliance
		onResize();
	}

	void setPersonList() {
		setViewportView(new PersonList());
	}

	void setCharacterCreate() {
		setCharacterCreate(null);
	}

	void setCharacterCreate(Person person) {
		setViewportView(new PersonCreate(person));
		onResize();
	}

	@Override
	public void onResize() {
		setSize(currentSize());
		revalidate();
		repaint();
	}
}
