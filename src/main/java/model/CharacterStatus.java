package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NamedQueries({
		@NamedQuery(name = "CharacterStatus.findAll", query = "select status from CharacterStatus status"),
		@NamedQuery(name = "CharacterStatus.findById", query = "select status from CharacterStatus status where status.id = :id")
})
public class CharacterStatus extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -395038896999397305L;

	private String name;

}