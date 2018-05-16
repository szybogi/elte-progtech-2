package panel;

import window.Window;

import javax.swing.*;

public class MenuBar extends JMenuBar {

	public MenuBar() {
		ImageIcon iconBox = new ImageIcon(MenuBar.class.getClassLoader().getResource("icons/box32.png"));
		ImageIcon iconWrench = new ImageIcon(MenuBar.class.getClassLoader().getResource("icons/wrench32.png"));
		ImageIcon iconBoxDownload = new ImageIcon(MenuBar.class.getClassLoader().getResource("icons/boxdownload32.png"));
		ImageIcon iconStop = new ImageIcon(MenuBar.class.getClassLoader().getResource("icons/stop32.png"));
		ImageIcon iconSleep = new ImageIcon(MenuBar.class.getClassLoader().getResource("icons/sleep32.png"));
		ImageIcon iconShop = new ImageIcon(MenuBar.class.getClassLoader().getResource("icons/shoppingcart32.png"));
		ImageIcon iconRight = new ImageIcon(MenuBar.class.getClassLoader().getResource("icons/turnright32.png"));
		ImageIcon iconLeft = new ImageIcon(MenuBar.class.getClassLoader().getResource("icons/turnright32.png"));

		JMenu houseMenu = new JMenu("Házak");
		JMenuItem miShowHouses = new JMenuItem("Házak listázása", iconShop);
		JMenuItem miCreateHouse = new JMenuItem("Ház felvétele", iconWrench);
		miShowHouses.addActionListener(e -> Window.getMainWindow().getWindowContent().getScrollPane().setHouseList());
		miCreateHouse.addActionListener(e -> Window.getMainWindow().getWindowContent().getScrollPane().setHouseCreate());
		houseMenu.add(miShowHouses);
		houseMenu.add(miCreateHouse);
		add(houseMenu);


		JMenu allianceMenu = new JMenu("Szövetségek");
		JMenuItem miShowAlliances = new JMenuItem("Szövetségek listázása", iconShop);
		JMenuItem miCreateAlliance = new JMenuItem("Szövetség felvétele", iconWrench);
		miShowHouses.addActionListener(e -> Window.getMainWindow().getWindowContent().getScrollPane().setAllianceGrid());
		miCreateHouse.addActionListener(e -> Window.getMainWindow().getWindowContent().getScrollPane().setAllianceCreate());
		houseMenu.add(miShowAlliances);
		houseMenu.add(miCreateAlliance);
		add(allianceMenu);



		JMenuItem miExit = new JMenuItem("Kilépés", iconSleep);
		miExit.setToolTipText("Az alkalmazásból való kilépés");
		miExit.addActionListener(e -> Window.getMainWindow().dispose());
		add(miExit);

		revalidate();
		repaint();
	}
}