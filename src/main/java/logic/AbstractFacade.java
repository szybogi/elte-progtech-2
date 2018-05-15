package logic;

import model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Backbone for Facades, the managing classes of the entities
 * @param <E> type of the facade, has to be an AbstractEntity
 */
public class AbstractFacade<E extends AbstractEntity> implements AutoCloseable, Findable<E> {

	private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("thrones");

	private EntityManager entityManager;
	private Class<E> c;

	AbstractFacade(Class<E> c) {
		this.c = c;
		entityManager = emFactory.createEntityManager();
	}

	/**
	 * Persists the entity in a new Transaction
	 * @param entity to be persisted
	 */
	public void persist(E entity) {
		begin();
		entityManager.persist(entity);
	}

	/**
	 * Merges the data in the entity in the persistence
	 * @param entity to be merged
	 */
	public void merge(E entity) {
		begin();
		entityManager.merge(entity);
	}

	/**
	 * Decides whether the entity needs to be merged or persisted
	 * since every Id is auto generated we can rely on the "Nullness"
	 * of the Id to see if the entity is in the Persistence or not
	 * @param entity to be persisted or merged
	 */
	public void autopersist(E entity) {
		begin();
		if(entity.getId() == null) {
			persist(entity);
		} else {
			merge(entity);
		}
	}
	/**
	 * Removes the entity from persistence
	 * @param entity to be removed
	 */
	public void remove(E entity) {
		begin();
		entityManager.remove(entity);
	}
	/**
	 * Begins the transaction
	 *
	 * Since this is Java SE environment which is not handled by JTA
	 * we have to manually start and commit every transaction
	 */
	private void begin() {
		entityManager.getTransaction().begin();
	}

	/**
	 * Commits the transaction
	 */
	public void commit() {
		entityManager.getTransaction().commit();
	}

	@Override
	public void close() throws Exception {
		entityManager.close();
		emFactory.close();
	}

	@Override
	public Class<E> getType() {
		return c;
	}

	@Override
	public EntityManager entityManager() {
		return entityManager;
	}
}