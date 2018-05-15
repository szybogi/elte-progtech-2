package logic;

import model.AbstractEntity;

import java.util.List;

public enum EntityFinder {
	ID(AbstractEntity.class),
	ALL(List.class);

	private Class returnType;

	EntityFinder(Class returnType) {
		this.returnType = returnType;
	}

	public Class getReturnType() {
		return returnType;
	}
}