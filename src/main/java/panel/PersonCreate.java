package panel;

import logic.Managable;
import logic.ResizeableElement;

import javax.swing.*;
import model.Person;

import java.awt.*;

public class PersonCreate extends Panel implements Managable, ResizeableElement {

	private Person person;

	private JFormattedTextField nameField;
	private JFormattedTextField armyField;

	public PersonCreate(Person person) {
		draw();

		if (person != null) {
			this.person = person;
		} else {
			this.person = new Person();
		}
		read();

		setVisible(true);
		revalidate();
		repaint();
	}

	@Override
	public void read() {
		nameField.setText(person.getName());
		armyField.setText(person.getArmyCount() != null ? person.getArmyCount().toString() : "");
	}

	@Override
	public void save() {
		personController.autoPersist(person);
		window.Window.getMainWindow().getWindowContent().getScrollPane().setPersonList();
	}

	@Override
	public Boolean validation() {
		Boolean valid = true;

		person.setName(nameField.getText());

		if(person.getArmyCount() == null) {
			person.setArmyCount(0);
		}

		if (armyField.getText().chars().allMatch(java.lang.Character::isDigit)) {
			person.setArmyCount(Integer.parseInt(armyField.getText()));
		} else {
			armyField.setBackground(errorColor);
			valid = false;
		}

		if(person.getName() == null || person.getName().isEmpty()) {
			nameField.setBackground(errorColor);
			valid = false;
		} else {
			nameField.setBackground(neutralColor);
		}

		return valid;
	}


	private void draw() {
		setSize(currentSize());
		setLayout(null);
		setBackground(backGroundColor);

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{65, 307, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_contentPanel);

		JLabel nameLabel = new JLabel("Név:");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 0;
		add(nameLabel, gbc_nameLabel);

		nameField = new JFormattedTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		add(nameField, gbc_nameField);


		JLabel armyLabel = new JLabel("Sereg méret:");
		GridBagConstraints gbc_armyLabel = new GridBagConstraints();
		gbc_armyLabel.anchor = GridBagConstraints.EAST;
		gbc_armyLabel.insets = new Insets(0, 0, 5, 5);
		gbc_armyLabel.gridx = 0;
		gbc_armyLabel.gridy = 1;
		add(armyLabel, gbc_armyLabel);

		armyField = new JFormattedTextField();
		GridBagConstraints gbc_armyField = new GridBagConstraints();
		gbc_armyField.insets = new Insets(0, 0, 5, 5);
		gbc_armyField.fill = GridBagConstraints.HORIZONTAL;
		gbc_armyField.gridx = 1;
		gbc_armyField.gridy = 1;
		add(armyField, gbc_armyField);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBackground(getBackground());

		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.insets = new Insets(0, 0, 5, 5);
		gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane.gridx = 1;
		gbc_buttonPane.gridy = 2;
		add(buttonPane, gbc_buttonPane);

		JButton okButton = new JButton("Mentés");
		okButton.setActionCommand("Mentés");
		okButton.addActionListener(e -> saveAndValidate());
		buttonPane.add(okButton);

		JButton cancelButton = new JButton("Vissza a listához");
		cancelButton.setActionCommand("Vissza a listához");
		cancelButton.addActionListener(e -> window.Window.getMainWindow().getWindowContent().getScrollPane().setPersonList());
		buttonPane.add(cancelButton);

	}


	@Override
	public void onResize() {
		setSize(currentSize());
		revalidate();
		repaint();
	}

}
