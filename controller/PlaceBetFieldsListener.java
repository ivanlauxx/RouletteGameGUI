package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.GUI.PlaceBetDialog;

public class PlaceBetFieldsListener implements DocumentListener {
	private PlaceBetDialog dialog;

	public PlaceBetFieldsListener(PlaceBetDialog dialog) {
		this.dialog = dialog;
	}

	// helper to check if bet amount is valid
	private boolean isValid() {
		if (!dialog.getBetAmount().isBlank()) {
			try {
				if (Integer.parseInt(dialog.getBetAmount()) > 0) {
					return true;
				}
			} catch (NumberFormatException e) {
				// noting to do here
			}
		}
		return false;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		dialog.setPlaceBetButtonStatus(isValid());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		dialog.setPlaceBetButtonStatus(isValid());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		dialog.setPlaceBetButtonStatus(isValid());
	}
}
