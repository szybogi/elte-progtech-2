package panel;

import control.Controller;
import control.HouseController;
import logic.ResizeableElement;
import model.House;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;

public class HouseList extends JTable implements ResizeableElement {

	protected HouseController houseController = (HouseController) Controller.HOUSE.getController();

	public HouseList() {
		setLayout(new BorderLayout());
		setSize(currentSize());

		houseController.findAll().forEach(System.out::println);


		House h = House.builder().name("bara").motto("otot").build();
		House h2 = House.builder().name("bara2").motto("otot2").build();
		//houseController.autoPersist(h);
		//houseController.autoPersist(h2);


		houseController.findAll().forEach(System.out::println);


		Vector<Vector<Object>> data = houseController.findAll().map(House::convert).collect(Collectors.toCollection(Vector::new));

		DefaultTableModel tableModel = new DefaultTableModel(data, House.columns);
		setModel(tableModel);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(getModel());
		setRowSorter(sorter);

		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
		for (int i = 0; i < getModel().getColumnCount(); i++) {
			sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
		}
		sorter.setSortKeys(sortKeys);
		setVisible(true);
		revalidate();
		repaint();
	}

	@Override
	public void onResize() {
		setSize(currentSize());
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}