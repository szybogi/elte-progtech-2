package panel;

import control.*;

import javax.swing.*;
import java.awt.*;

public class AbstractPanel extends JPanel {

	protected HouseController houseController = (HouseController) Controller.HOUSE.getController();
	protected StatusController statusController = (StatusController) Controller.STATUS.getController();
	protected CharacterController characterController = (CharacterController) Controller.CHARACTER.getController();
	protected AllianceController allianceController = (AllianceController) Controller.ALLIANCE.getController();

	protected Color errorColor = new Color(255, 199, 189, 255);
	protected Color neutralColor = new Color(255, 255, 255, 255);
	protected Color backGroundColor = new Color(130, 162, 182, 255);

}