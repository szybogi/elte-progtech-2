package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
		@NamedQuery(name = "Character.findAll", query = "select character from Character character"),
		@NamedQuery(name = "Character.findById", query = "select character from Character character where character.id = :id")
})
public class Character extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 69992852286436360L;

	private String name;

	private Integer armyCount;

	@ManyToOne
	@NonNull
	private Status status;

	@ManyToOne
	private House house;

}