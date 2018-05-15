package panel;

import model.Alliance;
import model.House;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu extends AbstractPanel {


	public Menu() {
		System.out.println("menu loaded");

		setSize(1200, 720);

		House houseBaratheon = House.builder().name("baratheon").build();
		House houseStark = House.builder().name("stark").build();

		Alliance alliance = Alliance.builder()
				.houses(Arrays.asList(houseBaratheon, houseStark))
				.build();
		//houseController.autoPersist(houseBaratheon);
		//houseController.autoPersist(houseStark);
		allianceController.autoPersist(alliance);



		Stream<House> houses = houseController.createNamedQuery("findAll").getResultStream();

		System.out.println(houses.collect(Collectors.toList()));

	}

}