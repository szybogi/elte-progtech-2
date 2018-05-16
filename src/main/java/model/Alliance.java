package model;

import logic.Rowable;
import lombok.*;
import panel.Panel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
		@NamedQuery(name = "Alliance.findAll", query = "select alliance from Alliance alliance"),
		@NamedQuery(name = "Alliance.findAllByFilter", query = "select alliance from Alliance alliance " +
				"where (:dateFrom is null or alliance.dateFrom <= :dateFrom and :dateTo is null or alliance.dateTo >= :dateTo) " +
				""),
		@NamedQuery(name = "Alliance.findById", query = "select alliance from Alliance alliance where alliance.id = :id")
})
public class Alliance extends AbstractEntity implements Rowable, Serializable {

	private static final long serialVersionUID = 1497535008803109978L;

	@Temporal(TemporalType.DATE)
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	private Date dateTo;

	@ManyToMany
	private List<House> houses = new ArrayList<>();

	@Override
	public Vector<Object> convert() {
		Vector<Object> data = new Vector<>();
		data.add(this);
		data.add(dateFrom != null ? Panel.dateFormat.format(dateFrom) : "");
		data.add(dateTo != null ? Panel.dateFormat.format(dateTo) : "");
		return data;
	}

	public static Vector<Object> columns = new Vector<>();

	static {
		columns.add("Szövetségesek");
		columns.add("Kezdete");
		columns.add("Vége");
	}

	@Override
	public String toString() {
		return houses.stream().map(House::toString).collect(Collectors.joining(", "));
	}
}