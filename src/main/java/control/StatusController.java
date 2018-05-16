package control;

import model.Status;

public class StatusController extends AbstractController<Status> {

	private Status statusDead;
	private Status statusAlive;

	public static final String PERSON_STATUS_DEAD = "dead";
	public static final String PERSON_STATUS_ALIVE = "alive";

	StatusController() {
		super(Status.class);
	}

	public Status getPersonStatusDead() {
		if (statusDead == null) {
			statusDead = findByName(PERSON_STATUS_DEAD);
		}
		return statusDead;
	}

	public Status getPersonStatusAlive() {
		if (statusAlive == null) {
			statusAlive = findByName(PERSON_STATUS_ALIVE);
		}
		return statusAlive;
	}

	private Status findByName(String name) {
		return createNamedQuery("findByName")
				.setParameter("name", name)
				.getSingleResult();
	}

}