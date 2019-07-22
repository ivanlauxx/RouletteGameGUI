package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.GUI.AddPlayerDialog;
import view.GUI.MainFrame;

public class AddPlayerListener implements ActionListener {
	private GameEngine gameEngine;
	private MainFrame mainFrame;

	public AddPlayerListener(GameEngine gameEngine, MainFrame mainFrame) {
		this.gameEngine = gameEngine;
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AddPlayerDialog dialog = new AddPlayerDialog(gameEngine, mainFrame);
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
}
