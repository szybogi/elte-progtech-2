package control;

import model.Alliance;
import model.House;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class AllianceController extends AbstractController<Alliance> {

	AllianceController() {
		super(Alliance.class);
	}

	public Stream<Alliance> findAllByFilter(Boolean active, List<House> houses) {
		return createNamedQuery("findAllByFilter")
				.setParameter("date", new Date())
				.setParameter("active", active)
				.setParameter("contains", houses != null)
				.setParameter("houses", houses)
				.getResultStream();
	}

}