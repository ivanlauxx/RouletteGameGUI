package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.GUI.AddPlayerDialog;

public class AddPlayerFieldsListener implements DocumentListener {
	private AddPlayerDialog dialog;

	public AddPlayerFieldsListener(AddPlayerDialog dialog) {
		this.dialog = dialog;
	}

	// helper to check if fields are valid
	private boolean isValid() {
		if (!dialog.getInputId().isBlank() && !dialog.getInputName().isBlank() && !dialog.getInputPoints().isBlank()) {
			try {
				if (Integer.parseInt(dialog.getInputPoints()) > 0) {
					dialog.errorMessageNon();
					return true;
				} else {
					dialog.errorMessagePositive();
				}
			} catch (NumberFormatException e) {
				dialog.errorMessageNotInt();
			}
		}
		return false;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		dialog.buttonEnable(isValid());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		dialog.buttonEnable(isValid());
		if (dialog.getInputPoints().isBlank()) {
			dialog.errorMessageNon();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		dialog.buttonEnable(isValid());
	}
}
