package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Abstract Entity
 * Holds a single id field which is common in every entity
 *
 *
 * @see Data
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 * @see MappedSuperclass makes the child Entities accept the fields described here
 * @see java.io.Serializable
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 100000000L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

}