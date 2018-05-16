package model;

import control.StatusController;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
		@NamedQuery(name = "Status.findAll", query = "select status from Status status"),
		@NamedQuery(name = "Status.findById", query = "select status from Status status where status.id = :id"),
		@NamedQuery(name = "Status.findByName", query = "select status from Status status where lower(status.name) = lower(:name)")
})
public class Status extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -395038896999397305L;

	@Column(unique = true)
	private String name;

	@OneToMany
	private List<Person> people;

	@Override
	public String toString() {
		switch (name) {
			case StatusController.PERSON_STATUS_ALIVE: return "Él";
			case StatusController.PERSON_STATUS_DEAD: return "Halott";
			default: return "";
		}
	}
}