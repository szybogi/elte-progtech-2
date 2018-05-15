package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Describes a House
 *
 * @see NamedQueries
 * @see NamedQuery
 * @see AbstractEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
		@NamedQuery(name = "House.findAll", query = "select house from House house"),
		@NamedQuery(name = "House.findById", query = "select house from House house where house.id = :id")
})
public class House extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 100000001L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	private String name;

}