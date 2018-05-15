package panel;

import window.Window;

import javax.swing.*;
import java.awt.event.ActionListener;

public class HouseListPanel extends AbstractPanel {

	private JButton backButton = new JButton("BACK");

	public HouseListPanel() {
		setSize(1200, 720);
		backButton.addActionListener(backAction);
		backButton.setBounds(500, 500, 200, 35);
		backButton.setVisible(true);
		add(backButton);
	}

	private ActionListener backAction = e -> {
		Window.getMainWindow().changeContent(new Menu());
	};
}
