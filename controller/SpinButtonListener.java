package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpinButtonListener implements ActionListener {
	private SpinController spinController;

	public SpinButtonListener(SpinController spinController) {
		this.spinController = spinController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		spinController.manualSpin();
	}
}
