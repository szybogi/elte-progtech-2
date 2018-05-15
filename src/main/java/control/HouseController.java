package control;

import model.House;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class HouseController extends AbstractController<House> {

	HouseController() {
		super(House.class);
	}

	public void imAlive() {
		createNamedQuery("asd");
		System.out.println("ALLLIVE");
	}

}