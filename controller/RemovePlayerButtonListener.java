package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import view.GUI.RemovePlayerDialog;
import view.model.PlayerViewModel;

public class RemovePlayerButtonListener implements ActionListener {
	private GameEngine gameEngine;
	private RemovePlayerDialog dialog;
	private PlayerViewModel playerViewModel;
	
	public RemovePlayerButtonListener(GameEngine gameEngine, RemovePlayerDialog dialog, PlayerViewModel playerViewModel) {
		this.gameEngine = gameEngine;
		this.dialog = dialog;
		this.playerViewModel = playerViewModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameEngine.removePlayer(dialog.getSelectedPlayer())) {
			playerViewModel.setViewModel(PlayerViewModel.REMOVED, dialog.getSelectedPlayer());
			JOptionPane.showMessageDialog(dialog, "Player Removed");
			dialog.dispose();
		}
	}
}
