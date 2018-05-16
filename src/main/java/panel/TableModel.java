package panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TableModel extends DefaultTableModel {

	private List<Integer> imageColumns = new ArrayList<>();
	private List<Integer> buttonColumns = new ArrayList<>();


	public TableModel(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public void setImageColumns(List<Integer> imageColumns) {
		this.imageColumns = imageColumns;
	}

	public void addImageColumn(Integer column) {
		imageColumns.add(column);
	}

	public void removeColumn(Integer column) {
		imageColumns.remove(column);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if(buttonColumns.contains(column)) {
			return JButton.class;
		} else if(imageColumns.contains(column)) {
			return ImageIcon.class;
		} else {
			return super.getColumnClass(column);
		}
	}
}
