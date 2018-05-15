package control;

import model.House;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Stream;

public class HouseController extends AbstractController<House> {

	HouseController() {
		super(House.class);
	}

	public List<House> findAllByName(String name) {
		return createNamedQuery("findByName")
				.setParameter("name", name)
				.getResultList();
	}

}