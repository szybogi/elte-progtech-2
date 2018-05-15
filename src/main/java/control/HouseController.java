package control;

import logic.Findable;
import model.House;

public class HouseController extends AbstractController<House> implements Findable<House> {

	public HouseController() {
		super(House.class);
	}

	public void imAlive() {
		System.out.println("ALLLIVE");
	}
}