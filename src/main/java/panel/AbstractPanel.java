package panel;

import control.*;

import javax.swing.*;

public class AbstractPanel extends JPanel {

	protected HouseController houseController = (HouseController) Controller.HOUSE.getController();
	protected StatusController statusController = (StatusController) Controller.STATUS.getController();
	protected CharacterController characterController = (CharacterController) Controller.CHARACTER.getController();
	protected AllianceController allianceController = (AllianceController) Controller.ALLIANCE.getController();

}