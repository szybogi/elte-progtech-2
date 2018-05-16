package model;

import logic.Rowable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Vector;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
		@NamedQuery(name = "Person.findAll", query = "select person from Person person"),
		@NamedQuery(name = "Person.findById", query = "select person from Person person where person.id = :id")
})
public class Person extends AbstractEntity implements Rowable, Serializable {

	private static final long serialVersionUID = 69992852286436360L;

	private String name;

	private Integer armyCount;

	@ManyToOne
	@NonNull
	private Status status;

	@ManyToOne
	private House house;

	@Override
	public Vector<Object> convert() {
		Vector<Object> data = new Vector<>();
		data.add(this);
		data.add(house);
		data.add(armyCount);
		data.add(status);
		return data;
	}

	public static Vector<Object> columns = new Vector<>();

	static {
		columns.add("Név");
		columns.add("Ház");
		columns.add("Sereg méret");
		columns.add("Státusz");
	}

	@Override
	public String toString() {
		return name;
	}

}