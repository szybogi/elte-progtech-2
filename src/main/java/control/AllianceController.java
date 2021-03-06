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

	public Boolean validDateRange(List<House> houses, Date from, Date to) {
		Stream<Alliance> allianceStream = houses.stream().flatMap(house -> house.getAlliances().stream());
		return allianceStream.count() == 0 || allianceStream
				.allMatch(alliance -> (from != null && to == null && alliance.getDateTo() == null && !from.equals(alliance.getDateFrom()))
						|| (from != null && to == null && alliance.getDateTo() != null && (from.before(alliance.getDateFrom()) || from.after(alliance.getDateTo())))
						|| (from != null && to != null && alliance.getDateTo() == null && (from.before(alliance.getDateFrom()) && to.before(alliance.getDateFrom()) || from.after(alliance.getDateFrom()) && to.after(alliance.getDateFrom())))
						|| (from != null && to != null && alliance.getDateTo() != null && (from.before(alliance.getDateFrom()) && to.before(alliance.getDateFrom()) || from.after(alliance.getDateTo()) && to.after(alliance.getDateTo()))));
	}

}