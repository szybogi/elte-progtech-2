package panel;

import control.Controller;
import control.HouseController;
import logic.ResizeableElement;
import model.House;
import window.Window;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;

public class HouseList extends JTable implements ResizeableElement {

	private HouseController houseController = (HouseController) Controller.HOUSE.getController();

	private House selectedHouse;

	HouseList() {
		setLayout(new BorderLayout());
		setSize(currentSize());
		Vector<Vector<Object>> data = houseController.findAll().map(House::convert).collect(Collectors.toCollection(Vector::new));
		TableModel tableModel = new TableModel(data, House.columns);
		tableModel.addImageColumn(2);
		setModel(tableModel);
		getSelectionModel().addListSelectionListener(selectionListener);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) getModel());
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
		for (int i = 0; i < getModel().getColumnCount() - 1; i++) {
			sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
		}
		sorter.setSortKeys(sortKeys);
		setRowSorter(sorter);
		setVisible(true);
		revalidate();
		repaint();
	}

	private ListSelectionListener selectionListener = e -> {
		if(e.getFirstIndex() == e.getLastIndex()) {
			House house = (House) ((Vector)((DefaultTableModel) getModel()).getDataVector().elementAt(convertRowIndexToModel(e.getLastIndex()))).elementAt(0);
			if(this.selectedHouse != null && this.selectedHouse.equals(house)) {
				Window.getMainWindow().getWindowContent().getScrollPane().setHouseCreate(house);
			} else {
				this.selectedHouse = house;
			}
		}
	};

	@Override
	public void onResize() {
		setSize(currentSize());
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}