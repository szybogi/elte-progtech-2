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

public class HouseList extends Table {

	private HouseController houseController = (HouseController) Controller.HOUSE.getController();

	HouseList() {
		super();
		init(houseController.findAll().map(House::convert).collect(Collectors.toCollection(Vector::new)), House.columns);
		((TableModel) getModel()).addImageColumn(3);
		setRowHeight(120);
	}

}