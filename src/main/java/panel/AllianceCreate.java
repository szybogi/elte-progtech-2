package panel;

import logic.Managable;
import logic.ResizeableElement;
import model.Alliance;
import model.House;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import java.util.stream.Collectors;

public class AllianceCreate extends Panel implements Managable, ResizeableElement {

	private Alliance alliance;

	private JFormattedTextField dateFromField;
	private JFormattedTextField dateToField;
	private JComboBox<House> houseJComboBox;

	public AllianceCreate(Alliance alliance) {


		if (alliance != null) {
			this.alliance = alliance;
		} else {
			this.alliance = new Alliance();
		}
		draw();
		read();

		setVisible(true);
		revalidate();
		repaint();
	}


	@Override
	public void read() {
		try {
			if(dateFromField != null) {
				dateFromField.setText(dateFromField.getFormatter().valueToString(alliance.getDateFrom()));
			}
			if(dateToField != null) {
				dateToField.setText(dateToField.getFormatter().valueToString(alliance.getDateTo()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void save() {
		allianceController.autoPersist(alliance);
		window.Window.getMainWindow().getWindowContent().getScrollPane().setAllianceList();
	}

	@Override
	public Boolean validation() {
		Boolean valid = true;

		if(dateFromField != null && dateFromField.getText() != null && !dateFromField.getText().isEmpty()) {
			try {
				alliance.setDateFrom((Date) dateFromField.getFormatter().stringToValue(dateFromField.getText()));
			} catch (ParseException e) {
				valid = false;
				dateFromField.setBackground(errorColor);
			}
		} else if(dateFromField != null) {
			valid = false;
			dateFromField.setBackground(errorColor);
		}

		if(dateToField != null && dateToField.getText() != null && !dateToField.getText().isEmpty()) {
			try {
				alliance.setDateTo((Date) dateToField.getFormatter().stringToValue(dateToField.getText()));
			} catch (ParseException e) {
				valid = false;
				dateToField.setBackground(errorColor);
			}
		}

		if(dateFromField != null && !allianceController.validDateRange(alliance.getHouses(), alliance.getDateFrom(), alliance.getDateTo())) {
			dateFromField.setBackground(errorColor);
			valid = false;
		}

		return valid;
	}


	private void draw() {
		validation();
		removeAll();
		setSize(currentSize());
		setLayout(null);
		setBackground(backGroundColor);

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{65, 307, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_contentPanel);

		JLabel dateFromLabel = new JLabel("Kezdete:");
		GridBagConstraints gbc_dateFromLabel = new GridBagConstraints();
		gbc_dateFromLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateFromLabel.anchor = GridBagConstraints.EAST;
		gbc_dateFromLabel.gridx = 0;
		gbc_dateFromLabel.gridy = 0;
		add(dateFromLabel, gbc_dateFromLabel);

		dateFromField = new JFormattedTextField(dateFormat);
		dateFromField.addKeyListener(dateAdapter);
		GridBagConstraints gbc_dateFromField = new GridBagConstraints();
		gbc_dateFromField.insets = new Insets(0, 0, 5, 5);
		gbc_dateFromField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateFromField.gridx = 1;
		gbc_dateFromField.gridy = 0;
		add(dateFromField, gbc_dateFromField);


		JLabel dateToLabel = new JLabel("Vége:");
		GridBagConstraints gbc_dateToLabel = new GridBagConstraints();
		gbc_dateToLabel.anchor = GridBagConstraints.EAST;
		gbc_dateToLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateToLabel.gridx = 0;
		gbc_dateToLabel.gridy = 1;
		add(dateToLabel, gbc_dateToLabel);

		dateToField = new JFormattedTextField(dateFormat);
		dateToField.addKeyListener(dateAdapter);
		GridBagConstraints gbc_dateToField = new GridBagConstraints();
		gbc_dateToField.insets = new Insets(0, 0, 5, 5);
		gbc_dateToField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateToField.gridx = 1;
		gbc_dateToField.gridy = 1;
		add(dateToField, gbc_dateToField);



		Vector<House> houses = houseController.findAllMinus(alliance.getHouses()).collect(Collectors.toCollection(Vector::new));
		houseJComboBox = new JComboBox<>(houses);
		houseJComboBox.setEnabled(!houses.isEmpty());
		GridBagConstraints gbc_houseJComboBox = new GridBagConstraints();
		gbc_houseJComboBox.anchor = GridBagConstraints.EAST;
		gbc_houseJComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_houseJComboBox.gridx = 1;
		gbc_houseJComboBox.gridy = 2;
		add(houseJComboBox, gbc_houseJComboBox);


		JButton houseAddButton = new JButton("Hozzáadás");
		houseAddButton.setEnabled(!houses.isEmpty() || alliance.getHouses().size() >= 2);
		houseAddButton.addActionListener(e -> {
			if (!alliance.getHouses().contains((House) houseJComboBox.getSelectedItem())) {
				alliance.getHouses().add((House) houseJComboBox.getSelectedItem());
				houseJComboBox.setBackground(neutralColor);
			} else {
				houseJComboBox.setBackground(errorColor);
			}
			draw();
		});


		GridBagConstraints gbc_houseAddButton = new GridBagConstraints();
		gbc_houseAddButton.anchor = GridBagConstraints.EAST;
		gbc_houseAddButton.insets = new Insets(0, 0, 5, 5);
		gbc_houseAddButton.gridx = 0;
		gbc_houseAddButton.gridy = 2;
		add(houseAddButton, gbc_houseAddButton);


		JLabel tableLabel = new JLabel("Felek:");
		GridBagConstraints gbc_tableLabel = new GridBagConstraints();
		gbc_tableLabel.anchor = GridBagConstraints.EAST;
		gbc_tableLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tableLabel.gridx = 0;
		gbc_tableLabel.gridy = 3;
		add(tableLabel, gbc_tableLabel);


		GridBagConstraints gbc_tableField = new GridBagConstraints();
		gbc_tableField.insets = new Insets(0, 0, 5, 5);
		gbc_tableField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tableField.gridx = 1;
		gbc_tableField.gridy = 3;

		Table table = new Table();
		Vector<Object> column = new Vector<>();
		column.add("Név");

		Vector<Vector<Object>> abstractEntities = new Vector<>();
		if(alliance.getHouses() != null) {
			abstractEntities = alliance.getHouses().stream().map(house -> houseController.convertNameDelete(house)).collect(Collectors.toCollection(Vector::new));
		}
		table.init(abstractEntities, column);

		add(table, gbc_tableField);


		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBackground(getBackground());


		JButton okButton = new JButton("Mentés");
		okButton.setActionCommand("Mentés");
		okButton.addActionListener(e -> saveAndValidate());
		buttonPane.add(okButton);

		JButton cancelButton = new JButton("Vissza a listához");
		cancelButton.setActionCommand("Vissza a listához");
		cancelButton.addActionListener(e -> window.Window.getMainWindow().getWindowContent().getScrollPane().setPersonList());
		buttonPane.add(cancelButton);

		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.insets = new Insets(0, 0, 5, 5);
		gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane.gridx = 1;
		gbc_buttonPane.gridy = 4;
		add(buttonPane, gbc_buttonPane);
		read();
	}

	@Override
	public void onResize() {
		setSize(currentSize());
		revalidate();
		repaint();
	}

}
