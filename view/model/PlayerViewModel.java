package view.model;

import java.beans.PropertyChangeSupport;

import controller.SpinController;
import model.interfaces.Player;
import view.GUI.MainFrame;

public class PlayerViewModel {
	public final static String ADDED = "Added";
	public final static String REMOVED = "Removed";
	public final static String BETTED = "Betted";
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public PlayerViewModel(MainFrame mainFrame, SpinController spinController) {
		pcs.addPropertyChangeListener(mainFrame);
		pcs.addPropertyChangeListener(spinController);
	}

	public void setViewModel(String evt, Player player) {
		pcs.firePropertyChange(evt, null, player);
	}
}
