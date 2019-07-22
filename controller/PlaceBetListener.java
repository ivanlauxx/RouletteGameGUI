package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.GUI.MainFrame;
import view.GUI.PlaceBetDialog;

public class PlaceBetListener implements ActionListener {
	private GameEngine gameEngine;
	private MainFrame mainFrame;

	public PlaceBetListener(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PlaceBetDialog dialog = new PlaceBetDialog(gameEngine, mainFrame);
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
}
