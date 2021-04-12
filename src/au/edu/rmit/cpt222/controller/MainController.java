package au.edu.rmit.cpt222.controller;

import java.util.ArrayList;
import java.util.List;

import au.edu.rmit.cpt222.model.DicePairImpl;
import au.edu.rmit.cpt222.model.GUIcallbackImpl;
import au.edu.rmit.cpt222.model.SimplePlayer;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;
import au.edu.rmit.cpt222.view.MainView;

public class MainController {

   // GameEngine model = this.toolBar.getMainView().getModel();
   private MainView mainView;
   private GameEngine model;
   private SimplePlayer player;
   private GUIcallbackImpl callbacks;

   // declare static values for listener commands
   public static final String ADD_PLAYER_COMMAND = "Add Player";
   public static final String TOP_UP_CREDIT_COMMAND = "Top Up Credit";
   public static final String PLACE_BET_ROLL_COMMAND = "Place Bet/Roll";
   public static final String EXIT_COMMAND = "Exit";

   // delay default values
   public final static int INITIAL_DELAY = 1;
   public final static int FINAL_DELAY = 100;
   public final static int DELAY_INCREMENT = 20;

   public MainController(MainView mainView) {
      this.mainView = mainView;
      this.model = mainView.getModel();
      this.model.addGameEngineCallback(new GUIcallbackImpl(this));

      // not needed in the assignment - testing only
//       populateModel();
   }

   // store history here - use an array/list
   // List<String> history = new ArrayList<String>();
   // h1 = callbacks.gameResult(player, player.getGameResult(), model);

   // declare the history array, allocate memory for 5 results
   public String[] history = new String[5];

   // add to history array and call to update view (UI)
   public void addToHistory() {
      history[0] = "test 1";
      history[1] = "test 2";
      history[2] = "test 3";
      history[3] = "test 4";
      history[4] = "test 5";

      // USING STRING BUILDER TO BUILD HISTORY STRING
      StringBuilder historyString = new StringBuilder();
      for (String i : history) {
         historyString.append(i + "\n");

      }

      // update the history box view
      this.mainView.getHistoryBox().updateStatus(historyString.toString());
      // test output in console
      System.out.println(historyString.toString());

   }

   /** methods for calling different model functions */

   // add player
   public void addPlayer() {
      mainView.getDialogBox().showAddPlayerInputBox();
   }

   // top up credit
   public void topUpCredit() {
      mainView.getDialogBox().showTopUpCreditInputBox();
   }

   // place bet then initiate roll
   public void placeBetRoll() {
      mainView.getDialogBox().showPlaceBetRollInputBox();
   }

   public void roll(int currentBet) {
      // new Thread() {
      // @Override
      // public void run() {
      // // connect to the model and call rollPlayer();
      // // this.model.rollPlayer(null, 0, 0, 0);
      // try {
      // player.placeBet(currentBet);
      //
      // }
      // // test insufficient funds exception
      // catch (InsufficientFundsException e) {
      // }
      //
      ////// model.rollPlayer(player, INITIAL_DELAY, FINAL_DELAY,
      ////// DELAY_INCREMENT);
      ////
      ////// DicePair playerPair = new DicePairImpl();
      //
      // }
      // }.start();
      // this.model.calculateResult();

      // update UI
      // mainView.getGamePanel().updateStatus(player.getPlayerName());
      // this.addToHistory();

      new Thread() {
         @Override
         public void run() {
            System.out.println("Current bet is: " + currentBet);
         }
      };

   }

   // exit application
   public void exit() {
      System.exit(0);
   }

   /** methods for asking the View to perform UI updates */
   // sample method
   public void updateResult(String playerId) {
      this.mainView.getGamePanel().updateStatus(playerId);

      // Test output to show here
      System.out.println("In controller - asking view to update itself");
   }
   
   // test method - not needed in real assignment
   private void populateModel() {
      // create two sample players
      Player theShark = new SimplePlayer("1", "The Shark", 566565);
      Player theRoller = new SimplePlayer("2", "The Roller", 654645);

      // add players to the model
      this.model.addPlayer(theShark);
      this.model.addPlayer(theRoller);

      // set test bets
      try {
         theShark.placeBet(10);
      }
      // test insufficient funds exception
      catch (InsufficientFundsException e) {
      }
   }

}
