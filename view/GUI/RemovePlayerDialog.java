package view.GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.RemovePlayerButtonListener;
import controller.SpinController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.model.PlayerViewModel;

@SuppressWarnings("serial")
public class RemovePlayerDialog extends JDialog {
	private JLabel playerID;
	private JComboBox<Player> listOfId;
	private JButton removeButton;

	public RemovePlayerDialog(GameEngine gameEngine, MainFrame mainFrame) {
		this.setTitle("Removing Player");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// panel 1
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;

		playerID = new JLabel("Player ID to remove:");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(playerID, cs);

		listOfId = new JComboBox<Player>(new Vector<Player>(gameEngine.getAllPlayers()));
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		// customer Renderer
		PlayerListRenderer renderer = new PlayerListRenderer();
		listOfId.setRenderer(renderer);
		panel.add(listOfId, cs);

		// panel for button
		removeButton = new JButton("Remove");
		JPanel panel2 = new JPanel();
		panel2.add(removeButton);

		// insert panel
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(panel2, BorderLayout.PAGE_END);
		pack();

		// spinController
		SpinController spinController = new SpinController(gameEngine, mainFrame);
		// playerViewModel
		PlayerViewModel playerViewModel = new PlayerViewModel(mainFrame, spinController);
		// listeners
		removeButton.addActionListener(new RemovePlayerButtonListener(gameEngine, this, playerViewModel));
	}

	// methods
	public Player getSelectedPlayer() {
		return listOfId.getItemAt(listOfId.getSelectedIndex());
	}
}
