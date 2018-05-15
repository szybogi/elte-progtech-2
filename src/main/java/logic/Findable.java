package logic;

import model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.stream.Stream;

public interface Findable<E extends AbstractEntity> {

	default Stream<E> find(String name, Map<String, String> args) {
		return createQuery(name, args).getResultStream();
	}

	default Stream<E> find(String name) {
		return find(name, null);
	}

	default E findSingle(String name, Map<String, String> args) {
		return createQuery(name, args).getSingleResult();
	}

	default E findSingle(String name) {
		return findSingle(name, null);
	}

	default TypedQuery<E> createQuery(String name, Map<String, String> args) {
		TypedQuery<E> query = entityManager().createNamedQuery(getType().getSimpleName() + "." + name, getType());
		if(args != null) {
			args.forEach(query::setParameter);
		}
		return query;
	}

	Class<E> getType();

	EntityManager entityManager();

}