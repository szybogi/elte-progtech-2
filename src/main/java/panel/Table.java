package panel;

import javax.swing.*;

public class Table extends JTable {

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}