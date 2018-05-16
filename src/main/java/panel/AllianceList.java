package panel;

import control.AllianceController;
import control.Controller;
import logic.ResizeableElement;
import model.Alliance;

import java.util.Vector;
import java.util.stream.Collectors;

public class AllianceList extends Table implements ResizeableElement {

	private AllianceController allianceController = (AllianceController) Controller.ALLIANCE.getController();

	public AllianceList() {
		super();
		init(allianceController.findAll().map(Alliance::convert).collect(Collectors.toCollection(Vector::new)), Alliance.columns);
	}

}