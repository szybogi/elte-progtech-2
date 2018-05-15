package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
		@NamedQuery(name = "Character.findAll", query = "select character from Character character"),
		@NamedQuery(name = "Character.findById", query = "select character from Character character where character.id = :id")
})
public class Alliance extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1497535008803109978L;

	@Temporal(TemporalType.DATE)
	private Date from;

	@Temporal(TemporalType.DATE)
	private Date to;

	@ManyToMany
	private List<House> houses;
}
