package view.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.model.PlayerViewModel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements PropertyChangeListener {

	private GameEngine gameEngine;
	private Menu menu;
	private SummaryPanel summaryPanel;
	private ToolBarPanel toolBarPanel;
	private WheelPanel wheelPanel;
	private JLabel status;

	public MainFrame(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		menu = new Menu(gameEngine, this);
		wheelPanel = new WheelPanel();
		summaryPanel = new SummaryPanel(gameEngine);
		toolBarPanel = new ToolBarPanel(gameEngine, this);

		// gamePanel
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(summaryPanel, BorderLayout.SOUTH);
		gamePanel.add(wheelPanel, BorderLayout.CENTER);

		// statusPanel
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new GridLayout());
		Border border = BorderFactory.createRaisedBevelBorder();
		status = new JLabel("Ready", SwingConstants.LEFT);
		status.setBorder(border);
		statusPanel.add(status);

		// add components
		setJMenuBar(menu);
		add(toolBarPanel, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
		add(status, BorderLayout.SOUTH);

		// set main frame
		playerCheck();
		updateSummary();
		setTitle("Roulette Game");
		setSize(600, 600);
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// methods
	public void spinWheel(int position) {
		wheelPanel.setSlot(position);
	}

	public void updateSummary() {
		summaryPanel.updateSummary();
	}

	public void appendSummary(ArrayList<String> list) {
		summaryPanel.appendSummary(list);
	}

	public void setAccess(boolean bool) {
		toolBarPanel.setAccess(bool);
		menu.setMenu(bool);
	}

	public void updateStatus(String message) {
		status.setText(message);
	}

	public void playerCheck() {
		if (gameEngine.getAllPlayers().isEmpty()) {
			menu.noPlayer();
			toolBarPanel.noPlayer();
			updateStatus("Add Player to start");
		} else {
			setAccess(true);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String toModel;
				Player player = (Player) evt.getNewValue();
				if (evt.getPropertyName().equals(PlayerViewModel.BETTED)) {
					toModel = String.format("Player %s: %s-%s betted %d on %s", evt.getPropertyName(),
							player.getPlayerId(), player.getPlayerName(), player.getBet(), player.getBetType());
				} else {
					toModel = String.format("Player %s: %s-%s", evt.getPropertyName(), player.getPlayerId(),
							player.getPlayerName());
				}
				updateStatus(toModel);
				updateSummary();
				playerCheck();
			}
		});
	}
}