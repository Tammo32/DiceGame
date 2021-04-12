package au.edu.rmit.cpt222.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import au.edu.rmit.cpt222.controller.GameDialogBoxController;
import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.SimplePlayer;

public class StatusBar extends JPanel {

   private SimplePlayer player;
   private MainController mainController;

   private JLabel playerDetails = new JLabel("PlayerID: , Name: ", JLabel.LEFT);
   private JLabel betAmount = new JLabel("Current bet amount: ", JLabel.CENTER);
   private JLabel playersPoints =
            new JLabel("Player's points: ", JLabel.RIGHT);

   public StatusBar(MainView mainView) {
      setLayout(new GridLayout(1, 3));
      add(playerDetails);
      add(betAmount);
      add(playersPoints);

      playerDetails.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      betAmount.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      playersPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK));
   }

   public void updateStatusBarPlayer(String pDetails) {
      playerDetails.setText(pDetails);
   }

   public void updateStatusBarBet(String bAmount) {
      betAmount.setText(bAmount);
   }

   public void updateStatusBarPoints(String pPoints) {
      playersPoints.setText(pPoints);
   }

//   public void setStatusBar() {
//      // StringBuilders to create current player, bet amount, player points
//      StringBuilder pDetailsString = new StringBuilder();
//      pDetailsString.append("PlayerID: ");
//      pDetailsString.append(player.getPlayerId().toString());
//      pDetailsString.append(", Name: ");
//      pDetailsString.append(player.getPlayerName());
//
//      StringBuilder betAmountString = new StringBuilder();
//      betAmountString.append("Current bet amount: ");
//      betAmountString.append(player.getBet());
//
//      StringBuilder pPointsString = new StringBuilder();
//      pPointsString.append("Player's points: ");
//      pPointsString.append(player.getPoints());
//
//      // update UI
//      this.updateStatusBarPlayer(pDetailsString.toString());
//      this.updateStatusBarBet(betAmountString.toString());
//      this.updateStatusBarPoints(pPointsString.toString());
//   }

   public void setPlayerStatus(String playerID, String playerName) {
      StringBuilder pDetailsString = new StringBuilder();
      pDetailsString.append("PlayerID: ");
      pDetailsString.append(playerID);
      pDetailsString.append(", Name: ");
      pDetailsString.append(playerName);
      
      this.updateStatusBarPlayer(pDetailsString.toString());
   }

   public void setPlayerBet(int bet) {
      StringBuilder betAmountString = new StringBuilder();
      betAmountString.append("Current bet amount: ");
      betAmountString.append(player.getBet());
      
      this.updateStatusBarBet(betAmountString.toString());
   }

   public void setPlayerPoints(int points) {
      StringBuilder pPointsString = new StringBuilder();
      pPointsString.append("Player's points: ");
      pPointsString.append(points);
      
      this.updateStatusBarPoints(pPointsString.toString());
   }

}
