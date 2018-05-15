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
		@NamedQuery(name = "Alliance.findAll", query = "select alliance from Alliance alliance"),
		@NamedQuery(name = "Alliance.findAllByFilter", query = "select alliance from Alliance alliance " +
				"where (:active = false or alliance.dateFrom <= :date and alliance.dateTo >= :date) " +
				"and (:contains = false or alliance.houses in (:houses))"),
		@NamedQuery(name = "Alliance.findById", query = "select alliance from Alliance alliance where alliance.id = :id")
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