package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GUI.AddPlayerDialog;
import view.model.PlayerViewModel;
import model.SimplePlayer;

public class AddPlayerButtonListener implements ActionListener {
	private GameEngine gameEngine;
	private AddPlayerDialog dialog;
	private PlayerViewModel playerViewModel;

	public AddPlayerButtonListener(GameEngine gameEngine, AddPlayerDialog dialog, PlayerViewModel playerViewModel) {
		this.gameEngine = gameEngine;
		this.dialog = dialog;
		this.playerViewModel = playerViewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String playerID;
		String playerName;
		int initialPoints;
		// get input from dialog
		playerID = dialog.getInputId();
		playerName = dialog.getInputName();
		initialPoints = Integer.parseInt(dialog.getInputPoints());
		Player player = new SimplePlayer(playerID, playerName, initialPoints);
		// pass input to gameEngine
		gameEngine.addPlayer(player);
		// update viewModel
		playerViewModel.setViewModel(PlayerViewModel.ADDED, player);
		// clean up
		dialog.dispose();
	}
}
