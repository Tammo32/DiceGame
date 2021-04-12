package au.edu.rmit.cpt222.view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private MainView mainView;

	private JLabel mainLabel = new JLabel("New game started...");

	public GamePanel(MainView mainView) {
		this.setBackground(Color.WHITE);
		this.mainView = mainView;
		this.add(this.mainLabel);
	}

	// sample view update method - test only
	public void updateStatus(String playerId) {
		this.mainLabel.setText(playerId);
	}
}
