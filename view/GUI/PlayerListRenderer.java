package view.GUI;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerListRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof Player) {
			Player player = (Player) value;
			setText(player.getPlayerId() + "-" + player.getPlayerName());
		}
		return this;
	}
}
