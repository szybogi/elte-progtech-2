package panel;

import logic.ResizeableElement;
import model.AbstractEntity;
import window.Window;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Table extends JTable implements ResizeableElement {

	protected AbstractEntity selected;

	public Table() {
		setLayout(new BorderLayout());
		setSize(currentSize());
	}

	public void init(Vector<Vector<Object>> data, Vector<Object> columns) {
		panel.TableModel tableModel = new panel.TableModel(data, columns);
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

	@Override
	public void onResize() {
		setSize(currentSize());
	}

	private ListSelectionListener selectionListener = e -> {
		if(e.getFirstIndex() == e.getLastIndex()) {
			AbstractEntity abstractEntity = (AbstractEntity) ((Vector)((TableModel) getModel()).getDataVector().elementAt(convertRowIndexToModel(e.getLastIndex()))).elementAt(0);
			if(this.selected != null && this.selected.equals(abstractEntity)) {
				Window.getMainWindow().getWindowContent().getScrollPane().openEntity(abstractEntity);
			} else {
				this.selected = abstractEntity;
			}
		}
	};


	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}