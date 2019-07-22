package view.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddPlayerButtonListener;
import controller.AddPlayerFieldsListener;
import model.interfaces.GameEngine;
import view.model.PlayerViewModel;

@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog {
	private JLabel playerID;
	private JLabel playerName;
	private JLabel pointsTitle;
	private JButton addPlayerButton;
	private JTextField IDTextField;
	private JTextField nameTextField;
	private JTextField pointsTextField;
	private JLabel validator;

	public AddPlayerDialog(GameEngine gameEngine, MainFrame mainFrame) {
		this.setTitle("Adding Player");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// panel 1
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		// panel 1 component
		playerID = new JLabel("Player ID:");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(playerID, cs);

		IDTextField = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(IDTextField, cs);

		playerName = new JLabel("Player Name:");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(playerName, cs);

		nameTextField = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(nameTextField, cs);

		pointsTitle = new JLabel("Initial Points:");
		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(pointsTitle, cs);

		pointsTextField = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		panel.add(pointsTextField, cs);

		validator = new JLabel(" ");
		validator.setForeground(Color.RED);
		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 1;
		panel.add(validator, cs);

		// panel for button
		addPlayerButton = new JButton("Add");
		addPlayerButton.setEnabled(false);
		JPanel panel2 = new JPanel();
		panel2.add(addPlayerButton);

		// insert panel
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(panel2, BorderLayout.SOUTH);
		pack();

		// playerViewModel
		PlayerViewModel playerViewModel = new PlayerViewModel(mainFrame, null);
		// listeners
		addPlayerButton.addActionListener(new AddPlayerButtonListener(gameEngine, this, playerViewModel));
		// documentListeners
		AddPlayerFieldsListener addPlayerFieldsListener = new AddPlayerFieldsListener(this);
		IDTextField.getDocument().addDocumentListener(addPlayerFieldsListener);
		nameTextField.getDocument().addDocumentListener(addPlayerFieldsListener);
		pointsTextField.getDocument().addDocumentListener(addPlayerFieldsListener);
	}

	// methods
	public String getInputId() {
		return IDTextField.getText();
	}

	public String getInputName() {
		return nameTextField.getText();
	}

	public String getInputPoints() {
		return pointsTextField.getText();
	}

	public void errorMessageNon() {
		validator.setText(" ");
	}

	public void errorMessagePositive() {
		validator.setText("Invalid Points, Must be positive");
	}

	public void errorMessageNotInt() {
		validator.setText("Invalid Points, Must be integer");
	}

	public void buttonEnable(boolean bool) {
		addPlayerButton.setEnabled(bool);
	}
}
