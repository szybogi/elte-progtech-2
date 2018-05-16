package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Abstract Entity
 * Holds a single id field which is common in every entity
 *
 * @see Data
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 * @see MappedSuperclass makes the child Entities accept the fields described here
 * @see java.io.Serializable
 */
@Data
@MappedSuperclass
public abstract class AbstractEntity implements Comparable<AbstractEntity>, Serializable {

	private static final long serialVersionUID = -4386653953478566832L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Override
	public int compareTo(AbstractEntity o) {
		return id.compareTo(o.id);
	}
}