package view.GUI;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private MainFrame mainFrame;

	public GameEngineCallbackGUI(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void nextSlot(Slot slot, GameEngine engine) {
		int position = slot.getPosition();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String message = String.format("Spinning: %s", slot.toString());
				mainFrame.updateStatus(message);
				mainFrame.spinWheel(position);
			}
		});
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		// final slot(result)
		mainFrame.spinWheel(winningSlot.getPosition());
		// calculate result
		engine.calculateResult(winningSlot);
		// update status
		String message = String.format("Result Slot: %s", winningSlot.toString());
		mainFrame.updateStatus(message);
		// print result(update summaryPanel)
		mainFrame.updateSummary();
		ArrayList<String> list = new ArrayList<String>();
		for (Player p : engine.getAllPlayers()) {
			list.add(isWon(p, winningSlot));
			p.resetBet();
		}
		mainFrame.appendSummary(list);
		// enable buttons(if player exist)
		mainFrame.setAccess(true);
		mainFrame.playerCheck();
	}

	// helper to check is player has won
	private String isWon(Player player, Slot winningSlot) {
		String winBet;
		if (player.getBetType() != null) {
			if (winningSlot.getColor().toString().equals("GREEN0")
					|| winningSlot.getColor().toString().equals("GREEN00")) {
				winBet = "ZEROS";
			} else {
				winBet = winningSlot.getColor().toString();
			}
			if (player.getBetType().toString().equals(winBet)) {
				return "WON  ";
			}
			return "LOST  ";
		}
		return null;
	}

}
