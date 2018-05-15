package panel;

import window.Window;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Menu extends AbstractPanel {

	private JButton listHouses = new JButton("LIST HOUSES");

	public Menu() {
		System.out.println("menu loaded");

		setSize(1200, 720);

		listHouses.addActionListener(listHousesAction);
		listHouses.setBounds(500, 500, 200, 35);
		listHouses.setVisible(true);
		add(listHouses);

	}

	private ActionListener listHousesAction = e -> {
		Window.getMainWindow().changeContent(new HouseListPanel());
	};

}