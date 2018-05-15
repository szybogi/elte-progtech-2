package panel;

import control.Controller;
import control.HouseController;
import logic.ResizeableElement;
import model.House;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;

public class HouseList extends AbstractPanel implements ResizeableElement {

	protected HouseController houseController = (HouseController) Controller.HOUSE.getController();
	private Table table = new Table();

	private House selectedHouse;

	public HouseList() {
		setLayout(new BorderLayout());
		setSize(currentSize());

		Vector<Vector<Object>> data = houseController.findAll().map(House::convert).collect(Collectors.toCollection(Vector::new));

		DefaultTableModel tableModel = new DefaultTableModel(data, House.columns);
		table.setModel(tableModel);

		table.getSelectionModel().addListSelectionListener(selectionListener);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);

		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
		for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
		}
		sorter.setSortKeys(sortKeys);



		add(table);







		setVisible(true);
		revalidate();
		repaint();
	}

	private ListSelectionListener selectionListener = e -> {
		House house = (House) ((Vector)((DefaultTableModel) table.getModel()).getDataVector().get(table.getSelectedRow())).get(0);
		setSelectedHouse(house);
	};

	public void setSelectedHouse(House selectedHouse) {
		this.selectedHouse = selectedHouse;
	}

	@Override
	public void onResize() {
		setSize(currentSize());
	}

}