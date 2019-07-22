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
import javax.swing.JTextField;

import controller.PlaceBetButtonListener;
import controller.PlaceBetFieldsListener;
import controller.SpinController;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.model.PlayerViewModel;


@SuppressWarnings("serial")
public class PlaceBetDialog extends JDialog{
	private JLabel playerID;
	private JLabel bet;
	private JLabel betType;
	private JTextField betTextField;
	private JButton placeBetButton;
	private JComboBox<Player> listOfId;
	private JComboBox<BetType> listOfBetType;
	
	public PlaceBetDialog(GameEngine gameEngine,MainFrame mainFrame) {
		this.setTitle("Placing Bet");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//panel 1
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		
		playerID = new JLabel("Player ID:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(playerID, cs);
        
        listOfId = new JComboBox<Player>(new Vector<Player>(gameEngine.getAllPlayers()));
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        //custom Renderer
        PlayerListRenderer renderer = new PlayerListRenderer();
		listOfId.setRenderer(renderer);
        panel.add(listOfId, cs);
        
        bet = new JLabel("Bet Amount:");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(bet, cs);
 
        betTextField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(betTextField, cs);
        
        betType = new JLabel("BetType:");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(betType, cs);
        
        listOfBetType = new JComboBox<BetType>(BetType.values());
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(listOfBetType, cs);
        
        //panel for button
        placeBetButton = new JButton("Place");
        placeBetButton.setEnabled(false);
      	JPanel panel2 = new JPanel();
        panel2.add(placeBetButton);
        
        //insert panel
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.PAGE_END);
        pack();
        
        //spinController
        SpinController spinController = new SpinController(gameEngine, mainFrame);
        //playerViewModel
        PlayerViewModel playerViewModel = new PlayerViewModel(mainFrame, spinController);
        //listeners
        placeBetButton.addActionListener(new PlaceBetButtonListener(gameEngine, this, playerViewModel));  
        //documentListener
        betTextField.getDocument().addDocumentListener(new PlaceBetFieldsListener(this));
	}
	
	//methods
	public String getBetAmount() {
		return betTextField.getText();
	}
	
	public Player getSelectedPlayer() {
		return listOfId.getItemAt(listOfId.getSelectedIndex());
	}
	
	public BetType getSelectedBetType() {
		return listOfBetType.getItemAt(listOfBetType.getSelectedIndex());
	}
	
	public void setPlaceBetButtonStatus(boolean bool)
	{
		placeBetButton.setEnabled(bool);
	}

	public void resetDialog() {
		betTextField.setText(null);
		betTextField.requestFocusInWindow();
	}
}
