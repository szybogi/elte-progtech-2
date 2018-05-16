package control;

public enum Controller {
	HOUSE(new HouseController()),
	PERSON(new PersonController()),
	ALLIANCE(new AllianceController()),
	STATUS(new StatusController());

	private AbstractController controller;

	Controller(AbstractController controller) {
		this.controller = controller;
	}

	public AbstractController getController() {
		return controller;
	}
}