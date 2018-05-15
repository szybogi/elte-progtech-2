package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Abstract Entity
 * Holds a single id field which is common in every entity
 *
 * @see Entity
 * @see Data
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 * @see RequiredArgsConstructor
 * @see RequiredArgsConstructor
 * @see java.io.Serializable
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 100000000L;

	@Id
	protected Integer id;

}