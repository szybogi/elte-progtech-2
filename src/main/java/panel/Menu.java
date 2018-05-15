package panel;

import control.HouseController;
import model.House;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu extends AbstractPanel {

	private HouseController houseController = new HouseController();

	public Menu() {
		System.out.println("menu loaded");

		setSize(1200, 720);

		House house = new House();
		house.setName("lol");
		houseController.autoPersist(house);

		Stream<House> houses = houseController.find("findAll");

		System.out.println(houses.collect(Collectors.toList()));

	}

}