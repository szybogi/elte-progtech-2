package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class Alliance extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1497535008803109978L;

	@Temporal(TemporalType.DATE)
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	private Date dateTo;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<House> houses;
}
