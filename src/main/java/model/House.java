package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Clob;
import java.util.List;

/**
 * Describes a House
 *
 * @see NamedQueries
 * @see NamedQuery
 * @see AbstractEntity
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
		@NamedQuery(name = "House.findAll", query = "select house from House house"),
		@NamedQuery(name = "House.findById", query = "select house from House house where house.id = :id")
})
public class House extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -8049832814482377930L;

	private String name;

	@Lob
	private Clob crest;

	private String motto;

	@ManyToMany(mappedBy = "houses")
	private List<Alliance> alliances;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Character> characters;

}