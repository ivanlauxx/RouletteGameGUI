package view.GUI;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SummaryTableModel extends DefaultTableModel {
	private static String[] columnIDs = { "ID", "Name", "Bet", "BetType", "Points", "Win/Lose" };

	public SummaryTableModel() {
		super(columnIDs, 0);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
