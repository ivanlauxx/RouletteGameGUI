package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GUI.MainFrame;

public class SpinController implements PropertyChangeListener {
	private GameEngine gameEngine;
	private MainFrame mainFrame;

	public SpinController(GameEngine gameEngine, MainFrame mainFrame) {

		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}

	public void manualSpin() {
		// disable buttons
		mainFrame.setAccess(false);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				gameEngine.spin(1, 500, 25);
			}
		});
		thread.start();
	}

	public void autoSpin() {
		if (isAllBet()) {
			manualSpin();
		}
	}

	private boolean isAllBet() {
		while (!gameEngine.getAllPlayers().isEmpty()) {
			for (Player p : gameEngine.getAllPlayers()) {
				if (p.getBetType() == null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		autoSpin();
	}
}