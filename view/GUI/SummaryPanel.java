package view.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {
	private GameEngine gameEngine;
	private JTable statsTable;
	private SummaryTableModel tableModel;

	public SummaryPanel(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		// set panel
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 120));
		TitledBorder border = new TitledBorder("Summary");
		this.setBorder(border);
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitlePosition(TitledBorder.TOP);
		// JTable
		tableModel = new SummaryTableModel();
		statsTable = new JTable(tableModel);
		statsTable.setFillsViewportHeight(true);
		statsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		statsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		statsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		statsTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		// add JTable
		JScrollPane scrollPane = new JScrollPane(statsTable);
		this.add(scrollPane, BorderLayout.CENTER);
		// display panel
		setVisible(true);
	}

	// methods
	public void updateSummary() {
		tableModel.setRowCount(0);
		for (Player p : gameEngine.getAllPlayers()) {
			Object[] stats = new Object[] { p.getPlayerId(), p.getPlayerName(), p.getBet(), p.getBetType(),
					p.getPoints() };
			tableModel.addRow(stats);
		}
	}

	public void appendSummary(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			tableModel.setValueAt(list.get(i), i, 5);
		}
	}
}
