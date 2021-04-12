package au.edu.rmit.cpt222.view;

import java.awt.Color;
import java.util.Collection;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;
import com.sun.media.sound.ModelAbstractChannelMixer;

import au.edu.rmit.cpt222.controller.GameDialogBoxController;
import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameDialogBox extends JDialog {

   private MainView mainView;
   private MainController mainController;
   private GameDialogBoxController controller;
   private SimplePlayer player;

   public GameDialogBox(MainView mainView) {
      this.setBackground(Color.BLUE);
      this.mainView = mainView;
      this.controller = new GameDialogBoxController(this);
   }

   public void showAddPlayerInputBox() {
      // collect the user input
      String playerID = JOptionPane.showInputDialog("Enter Player ID", "1");
      String playerName =
               JOptionPane.showInputDialog("Enter Player Name", "Mike");
      String initialCreditPointsInput = JOptionPane
               .showInputDialog("Enter Initial Credit Points", "5000");
      int initialCreditPoints = Integer.parseInt(initialCreditPointsInput);

      // send this data to addPlayer();
      SimplePlayer player =
               new SimplePlayer(playerID, playerName, initialCreditPoints);
      mainView.getModel().addPlayer(player);

      // update StatusBar
      mainView.getStatusBar().setPlayerStatus(playerID, playerName);
      mainView.getStatusBar().setPlayerPoints(initialCreditPoints);

      // update view
//      mainView.getGamePanel().updateStatus(playerName + " entered the game...");
   }

   public void showTopUpCreditInputBox() {
      Collection<Player> players = mainView.getModel().getAllPlayers();

      // get number of players
      int numPlayers = players.size();
      Object[] playerSelections = new Object[numPlayers];
      int[] playerCredits = new int[numPlayers];

      // add players to array for purpose of drop down selection
      int i = 0;
      for (Player p : players) {
         playerSelections[i] = p.getPlayerName();
         playerCredits[i] = p.getPoints();
         i++;
      }
      
      // set initial selection
      String initialSelection = playerSelections[0].toString();

      // create selection pane
      Object selection =
               JOptionPane.showInputDialog(null, "Player to top up:",
                                           "Top up player credit",
                                           JOptionPane.QUESTION_MESSAGE, null,
                                           playerSelections, initialSelection);

      // selection == currentPlayer
      
//      if (players.contains(selection))
//      if (players.contains(selection) {
//         System.out.println("It works");
//      }

      if (player.getPlayerName().equals(selection)) {
       System.out.println("It works");
    }
      
//      System.out.println(players.);
      
      
      Player currentPlayer = mainView.getModel().getPlayer("1");
      
      // enter amount to top up
      String topUpInput = JOptionPane.showInputDialog("Enter amount to top up for " + currentPlayer.getPlayerName() + " (Current balance: " + currentPlayer.getPoints() + ")");
      int topUp = Integer.parseInt(topUpInput);
      
      // set new point total
      int newPoints = currentPlayer.getPoints() + topUp;
      mainView.getModel().setPlayerPoints(currentPlayer.getPlayerId(), newPoints);
      
      // update StatusBar
      mainView.getStatusBar().setPlayerPoints(currentPlayer.getPoints());

   }

   public void showPlaceBetRollInputBox() {
      // collect the bet input
      String currentBetInput =
               JOptionPane.showInputDialog("Enter bet amount", 10);
      int currentBet = Integer.parseInt(currentBetInput);

      // call roll()
      mainController.roll(currentBet);
   }

   public MainView getMainView() {
      return mainView;
   }

}
