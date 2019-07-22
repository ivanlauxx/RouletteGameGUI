package view.GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerListener;
import controller.PlaceBetListener;
import controller.RemovePlayerListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	private JMenu menu;
	private JMenuItem addPlayer;
	private JMenuItem removePlayer;
	private JMenuItem placeBet;

	public Menu(GameEngine gameEngine, MainFrame mainFrame) {
		menu = new JMenu("Menu");
		add(menu);

		addPlayer = new JMenuItem("Add Player");
		menu.add(addPlayer);

		removePlayer = new JMenuItem("Remove Player");
		menu.add(removePlayer);

		placeBet = new JMenuItem("Place Bet");
		menu.add(placeBet);

		// listeners
		addPlayer.addActionListener(new AddPlayerListener(gameEngine, mainFrame));
		removePlayer.addActionListener(new RemovePlayerListener(gameEngine, mainFrame));
		placeBet.addActionListener(new PlaceBetListener(gameEngine, mainFrame));
	}

	public JMenuItem getAddPlayer() {
		return addPlayer;
	}

	public JMenuItem getRemovePlayer() {
		return removePlayer;
	}

	public JMenuItem getPlaceBet() {
		return placeBet;
	}

	public void setMenu(Boolean bool) {
		addPlayer.setEnabled(bool);
		removePlayer.setEnabled(bool);
		placeBet.setEnabled(bool);
	}

	public void noPlayer() {
		removePlayer.setEnabled(false);
		placeBet.setEnabled(false);
	}
}
