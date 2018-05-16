package panel;

import logic.Managable;
import logic.ResizeableElement;
import model.House;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class HouseCreate extends Panel implements Managable, ResizeableElement {

	private House house;

	private JFormattedTextField nameField;
	private JFormattedTextField mottoField;
	private JFormattedTextField crestName;
	private JLabel crestField;

	public HouseCreate(House house) {
		draw();

		if (house != null) {
			this.house = house;
		} else {
			this.house = new House();
		}
		read();


		setVisible(true);
		revalidate();
		repaint();
	}

	@Override
	public void read() {
		nameField.setText(house.getName());
		mottoField.setText(house.getMotto());
		crestName.setText(house.getCrestName());
		crestField.setIcon(house.getCrestIcon());
	}

	@Override
	public void save() {
		houseController.autoPersist(house);
		window.Window.getMainWindow().getWindowContent().getScrollPane().setHouseList();
	}

	@Override
	public Boolean validation() {
		house.setName(nameField.getText());
		house.setMotto(mottoField.getText());
		house.setCrestName(crestName.getText());

		Boolean valid = true;

		if(house.getName() == null || house.getName().isEmpty() || !houseController.findAllByName(house.getName()).isEmpty()) {
			nameField.setBackground(errorColor);
			valid = false;
		} else {
			nameField.setBackground(neutralColor);
		}

		if(house.getMotto() == null || house.getMotto().isEmpty()) {
			mottoField.setBackground(errorColor);
			valid = false;
		} else {
			mottoField.setBackground(neutralColor);
		}

		return valid;
	}

	private ActionListener crestUploadButtonAction = e -> {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		final File f = chooser.getSelectedFile();
		if (f == null) {
			return;
		}
		SwingWorker sw = new SwingWorker() {
			@Override
			protected Object doInBackground() throws Exception {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(ImageIO.read(new File(f.getAbsolutePath())), "png", baos);
				house.setCrest(baos.toByteArray());

				house.convertBlobToIcon();
				return null;
			}

			@Override
			protected void done() {
				super.done();
				crestField.setIcon(house.getCrestIcon());
			}
		};
		sw.execute();
	};

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


		JLabel mottoLabel = new JLabel("Mottó:");
		GridBagConstraints gbc_mottoLabel = new GridBagConstraints();
		gbc_mottoLabel.anchor = GridBagConstraints.EAST;
		gbc_mottoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_mottoLabel.gridx = 0;
		gbc_mottoLabel.gridy = 1;
		add(mottoLabel, gbc_mottoLabel);

		mottoField = new JFormattedTextField();
		GridBagConstraints gbc_mottoField = new GridBagConstraints();
		gbc_mottoField.insets = new Insets(0, 0, 5, 5);
		gbc_mottoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_mottoField.gridx = 1;
		gbc_mottoField.gridy = 1;
		add(mottoField, gbc_mottoField);

		JLabel crestNameLabel = new JLabel("Címer neve:");
		GridBagConstraints gbc_crestNameLabel = new GridBagConstraints();
		gbc_crestNameLabel.anchor = GridBagConstraints.EAST;
		gbc_crestNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_crestNameLabel.gridx = 0;
		gbc_crestNameLabel.gridy = 2;
		add(crestNameLabel, gbc_crestNameLabel);

		crestName = new JFormattedTextField();
		GridBagConstraints gbc_crestName = new GridBagConstraints();
		gbc_crestName.insets = new Insets(0, 0, 5, 5);
		gbc_crestName.fill = GridBagConstraints.HORIZONTAL;
		gbc_crestName.gridx = 1;
		gbc_crestName.gridy = 2;
		add(crestName, gbc_crestName);

		JLabel crestLabel = new JLabel("Címer:");
		GridBagConstraints gbc_crestLabel = new GridBagConstraints();
		gbc_crestLabel.anchor = GridBagConstraints.EAST;
		gbc_crestLabel.insets = new Insets(0, 0, 5, 5);
		gbc_crestLabel.gridx = 0;
		gbc_crestLabel.gridy = 3;
		add(crestLabel, gbc_crestLabel);

		crestField = new JLabel();
		crestField.setToolTipText("Kép");
		GridBagConstraints gbc_crestField = new GridBagConstraints();
		gbc_crestField.insets = new Insets(0, 0, 5, 5);
		gbc_crestField.fill = GridBagConstraints.HORIZONTAL;
		gbc_crestField.gridx = 1;
		gbc_crestField.gridy = 3;
		add(crestField, gbc_crestField);

		JButton crestUploadButton = new JButton("Feltöltés");
		GridBagConstraints gbc_crestUploadButton = new GridBagConstraints();
		gbc_crestUploadButton.insets = new Insets(0, 0, 5, 5);
		gbc_crestUploadButton.fill = GridBagConstraints.EAST;
		gbc_crestUploadButton.gridx = 1;
		gbc_crestUploadButton.gridy = 3;
		crestUploadButton.addActionListener(crestUploadButtonAction);
		add(crestUploadButton, gbc_crestUploadButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBackground(getBackground());

		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.insets = new Insets(0, 0, 5, 5);
		gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane.gridx = 1;
		gbc_buttonPane.gridy = 4;
		add(buttonPane, gbc_buttonPane);

		JButton okButton = new JButton("Mentés");
		okButton.setActionCommand("Mentés");
		okButton.addActionListener(e -> saveAndValidate());
		buttonPane.add(okButton);

		JButton cancelButton = new JButton("Vissza a listához");
		cancelButton.setActionCommand("Vissza a listához");
		cancelButton.addActionListener(e -> window.Window.getMainWindow().getWindowContent().getScrollPane().setHouseList());
		buttonPane.add(cancelButton);
	}

	@Override
	public void onResize() {
		setSize(currentSize());
		revalidate();
		repaint();
	}

}