package view.GUI;

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.SpinButtonListener;
import controller.SpinController;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class ToolBarPanel extends JToolBar {
	private JButton spinButton;
	private Menu quickAccess;

	public ToolBarPanel(GameEngine gameEngine, MainFrame mainFrame) {
		SpinController spinController = new SpinController(gameEngine, mainFrame);
		spinButton = new JButton("Spin");
		quickAccess = new Menu(gameEngine, mainFrame);
		this.add(spinButton);
		this.add(quickAccess.getAddPlayer());
		this.add(quickAccess.getRemovePlayer());
		this.add(quickAccess.getPlaceBet());
		setVisible(true);

		// listeners
		spinButton.addActionListener(new SpinButtonListener(spinController));
	}

	// methods
	public void setAccess(boolean b) {
		spinButton.setEnabled(b);
		quickAccess.setMenu(b);
	}

	public void noPlayer() {
		quickAccess.getPlaceBet().setEnabled(false);
		quickAccess.getRemovePlayer().setEnabled(false);
	}
}
