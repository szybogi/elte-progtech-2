package logic;

import model.AbstractEntity;

import javax.persistence.EntityManager;

public interface Findable<E extends AbstractEntity> {

	default AbstractEntity find(String name) {
		return entityManager().createNamedQuery(getType().getName() + "." + name, getType())
				.getSingleResult();
	}

	Class<E> getType();

	EntityManager entityManager();
}
