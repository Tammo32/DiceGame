//code adapted from my 2017 SADI A1
package au.edu.rmit.cpt222.model;

import java.util.UUID;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class SimplePlayer implements Player {

   private String id;
   private String name;
   private int points;
   private int bet;
   private GameStatus gameResult;
   private DicePair rollResult;

   public SimplePlayer(String name) {
      // generate random id
      this(UUID.randomUUID().toString(), name, Player.DEFAULT_NUM_POINTS);
   }

   public SimplePlayer(String id, String name) {
      this(id, name, Player.DEFAULT_NUM_POINTS);
   }

   public SimplePlayer(String id, String name, int creditPoints) {
      this.id = id;
      this.name = name;
      this.points = creditPoints;
   }

   @Override
   public int getBet() {
      return this.bet;
   }

   @Override
   public GameStatus getGameResult() {
      return this.gameResult;
   }

   @Override
   public String getPlayerId() {
      return this.id;
   }

   @Override
   public String getPlayerName() {
      return this.name;
   }

   @Override
   public int getPoints() {
      return this.points;
   }

   @Override
   public DicePair getRollResult() {
      return this.rollResult;
   }

   // DEFAULT BET
   public void placeBet() throws InsufficientFundsException {
      placeBet(Player.DEFAULT_BET);
   }

   @Override
   public void placeBet(int bet) throws InsufficientFundsException {
      assert bet >= 0 : "invalid bet value";

      if (bet <= this.points)
         this.bet = bet;
      else
         throw new InsufficientFundsException();
   }

   @Override
   public void resetBet() throws InsufficientFundsException {
      // set to default bet
      // this.bet = Player.DEFAULT_BET;
      // if (bet < 0)
      // throw new IllegalArgumentException();
      // if (bet > this.getPoints())
      // throw new InsufficientFundsException();

      if (Player.DEFAULT_BET <= this.points)
         this.bet = Player.DEFAULT_BET;
      else
         throw new InsufficientFundsException();

      // or 0?
      // this.bet = 0;
   }

   @Override
   public void setGameResult(GameStatus status) {
      this.gameResult = status;
      if (status == GameStatus.LOST) {
         this.setPoints(points - this.getBet());
      }
      else if (status == GameStatus.DREW) {
         this.setPoints(points);
      }
      else if (status == GameStatus.WON) {
         this.setPoints(points + this.getBet());
      }
   }

   @Override
   public void setPlayerName(String playerName) {
      this.name = playerName;
   }

   @Override
   public void setPoints(int points) {
      this.points = points;
   }

   @Override
   public void setRollResult(DicePair rollResult) {
      this.rollResult = rollResult;
   }

   @Override
   public String toString() {
      return String
               .format("Player: id= %s, name= %s, bet amount= %s, roll result= %s, " +
                       "Game Outcome = %s, Total Credit Points = %s",
                       id, name, bet, rollResult, gameResult, points);
   }
}
