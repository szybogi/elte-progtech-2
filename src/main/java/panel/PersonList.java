package panel;

import control.PersonController;
import control.Controller;
import logic.ResizeableElement;
import model.Person;

import java.util.Vector;
import java.util.stream.Collectors;

public class PersonList extends Table implements ResizeableElement {

	private PersonController personController = (PersonController) Controller.PERSON.getController();

	public PersonList() {
		super();
		init(personController.findAll().map(Person::convert).collect(Collectors.toCollection(Vector::new)), Person.columns);
	}

}