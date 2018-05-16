package panel;

import control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {

	protected HouseController houseController = (HouseController) Controller.HOUSE.getController();
	protected StatusController statusController = (StatusController) Controller.STATUS.getController();
	protected CharacterController characterController = (CharacterController) Controller.CHARACTER.getController();
	protected AllianceController allianceController = (AllianceController) Controller.ALLIANCE.getController();

	protected Color errorColor = new Color(255, 199, 189, 255);
	protected Color neutralColor = new Color(255, 255, 255, 255);
	protected Color backGroundColor = new Color(130, 162, 182, 255);


	public static BufferedImage scaleImage(int w, int h, BufferedImage img) {
		BufferedImage bi;
		bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(img, 0, 0, w, h, null);
		g2d.dispose();
		return bi;
	}

}