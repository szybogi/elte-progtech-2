package control;

public enum Controller {
	HOUSE(new HouseController()),
	CHARACTER(new CharacterController()),
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