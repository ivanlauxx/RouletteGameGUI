package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GUI.PlaceBetDialog;
import view.model.PlayerViewModel;

public class PlaceBetButtonListener implements ActionListener {
	private GameEngine gameEngine;
	private PlaceBetDialog dialog;
	private PlayerViewModel playerViewModel;

	public PlaceBetButtonListener(GameEngine gameEngine, PlaceBetDialog dialog, PlayerViewModel playerViewModel) {
		this.gameEngine = gameEngine;
		this.dialog = dialog;
		this.playerViewModel = playerViewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player player;
		int bet = 0;
		BetType betType;
		player = dialog.getSelectedPlayer();
		bet = Integer.parseInt(dialog.getBetAmount());
		betType = dialog.getSelectedBetType();
		if (gameEngine.placeBet(player, bet, betType)) {
			// update viewModel
			playerViewModel.setViewModel(PlayerViewModel.BETTED, player);
			JOptionPane.showMessageDialog(dialog, "Bet placed.", "PlaceBet", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(dialog, "Invalid bet amount.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		// clean up
		dialog.resetDialog();
		dialog.dispose();
	}
}
