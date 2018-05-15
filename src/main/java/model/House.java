package model;

import logic.Rowable;
import lombok.*;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;
import java.sql.Clob;
import java.util.List;
import java.util.Vector;

import static panel.AbstractPanel.scaleImage;

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
		@NamedQuery(name = "House.findById", query = "select house from House house where house.id = :id"),
		@NamedQuery(name = "House.findByName", query = "select house from House house where house.name = :name")
})
public class House extends AbstractEntity implements Rowable, Serializable {

	private static final long serialVersionUID = -8049832814482377930L;

	@Column(unique = true)
	private String name;

	@Lob
	private Clob crest;

	private String motto;

	@ManyToMany(mappedBy = "houses")
	private List<Alliance> alliances;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Character> characters;

	@Transient
	private ImageIcon crestIcon;

	{
		SwingWorker sw = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				setCrestIcon(new ImageIcon(scaleImage(120, 120, ImageIO.read(getCrest().getAsciiStream()))));
				return null;
			}

			@Override
			protected void done() {
				super.done();
			}
		};
		sw.execute();
	}

	@Override
	public Vector<Object> convert() {
		Vector<Object> data = new Vector<>();
		data.add(this);
		data.add(motto);
		return data;
	}

	public static Vector<Object> columns = new Vector<>();

	static {
		columns.add("Név");
		columns.add("Mottó");
	}

	@Override
	public String toString() {
		return name;
	}
}