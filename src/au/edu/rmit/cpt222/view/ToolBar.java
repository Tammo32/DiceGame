package au.edu.rmit.cpt222.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.controller.ToolBarController;

public class ToolBar extends JPanel {
	
	private ToolBarController controller;
   private MainView mainView;
	
	// declare buttons
	private JButton addPlayerButton;
	private JButton topUpCreditButton;
	private JButton placeBetRollButton;
	private JButton exitButton;
	
	public ToolBar(MainView mainView) {
		this.setBackground(Color.GRAY);

		this.mainView = mainView;
		this.controller = new ToolBarController(this);

		// create addPlayerButton
		this.addPlayerButton = new JButton(MainController.ADD_PLAYER_COMMAND);
		this.addPlayerButton.setActionCommand(MainController.ADD_PLAYER_COMMAND);
		add(this.addPlayerButton);
		this.addPlayerButton.addActionListener(this.controller);
		
		// create topUpCreditButton
		this.topUpCreditButton = new JButton(MainController.TOP_UP_CREDIT_COMMAND);
		this.topUpCreditButton.setActionCommand(MainController.TOP_UP_CREDIT_COMMAND);
		add(this.topUpCreditButton);
		this.topUpCreditButton.addActionListener(this.controller);
		
	    // create placeBetRollButton
      this.placeBetRollButton = new JButton(MainController.PLACE_BET_ROLL_COMMAND);
      this.placeBetRollButton.setActionCommand(MainController.PLACE_BET_ROLL_COMMAND);
      add(this.placeBetRollButton);
      this.placeBetRollButton.addActionListener(this.controller);
      
      // create exitButton
      this.exitButton = new JButton(MainController.EXIT_COMMAND);
      this.exitButton.setActionCommand(MainController.EXIT_COMMAND);
      add(this.exitButton);
      this.exitButton.addActionListener(this.controller);
	}

	public MainView getMainView() {
		return this.mainView;
	}

}
