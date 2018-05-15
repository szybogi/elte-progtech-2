package control;

import model.Status;

public class StatusController extends AbstractController<Status> {

	private Status statusDead;
	private Status statusAlive;

	private static final String CHARACTER_STATUS_DEAD = "DEAD";
	private static final String CHARACTER_STATUS_ALIVE = "ALIVE";

	StatusController() {
		super(Status.class);
	}

	public Status getCharacterStatusDead() {
		if (statusDead == null) {
			statusDead = findByName(CHARACTER_STATUS_DEAD);
		}
		return statusDead;
	}

	public Status getCharacterStatusAlive() {
		if (statusAlive == null) {
			statusAlive = findByName(CHARACTER_STATUS_ALIVE);
		}
		return statusAlive;
	}

	private Status findByName(String name) {
		return createNamedQuery("findByName")
				.setParameter("name", name)
				.getSingleResult();
	}

}