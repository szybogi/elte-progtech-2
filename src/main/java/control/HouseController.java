package control;

import model.House;

import javax.swing.*;
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

	public Stream<House> findAllMinus(List<House> houses) {
		if(houses != null) {
			return findAll().filter(house -> !houses.contains(house));
		} else return findAll();
	}

	public Vector<Object> convertNameDelete(House house) {
		Vector<Object> vector = new Vector<>();
		vector.add(house);
		//vector.add("rem");
		return vector;
	}
}