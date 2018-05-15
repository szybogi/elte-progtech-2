package logic;

import model.House;

import java.util.List;

public class HouseFacade extends AbstractFacade<House> implements Findable<House> {

	private HouseFacade() {
		super(House.class);
	}

	public void imAlive() {
		System.out.println("ALLLIVE");
	}
}