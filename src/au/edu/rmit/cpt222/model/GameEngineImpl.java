//code adapted from my 2017 SADI A1
package au.edu.rmit.cpt222.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineImpl implements GameEngine {

   // house delay default values
   public final static int INITIAL_DELAY = 1;
   public final static int FINAL_DELAY = 100;
   public final static int DELAY_INCREMENT = 20;

   // lists, maps, sets
   // list -> use if need to access the whole collection
   // map -> use this due to frequent lookups (key = player ID)
   // concurrentHashMap -> for multi-player, multi-threaded application

   private Map<String, Player> players =
            new ConcurrentHashMap<String, Player>();
   private Set<GameEngineCallback> geCallbacks = Collections
            .newSetFromMap(new ConcurrentHashMap<GameEngineCallback, Boolean>());
   
   
   
   private DicePair houseDice;

   @Override
   public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
      this.geCallbacks.add(gameEngineCallback);
   }

   @Override
   public String addPlayer(Player player) {
      // assert that player is not null
      assert player.getPlayerId() != null;

      // check if playerID is unique first (if not, return NULL)
      if (!this.players.containsKey(player.getPlayerId())) {
         // perform lookup->map
         this.players.put(player.getPlayerId(), player);
         return player.getPlayerId();
      }
      else {
         return null;
      }
   }

   @Override
   public void calculateResult() {
      // (1) roll for the house
      rollHouse(INITIAL_DELAY, FINAL_DELAY, DELAY_INCREMENT);

      // (2) process bets
      processBets();
   }

   private void processBets() {
      /**
       * TODO: This method goes through all players and applies win/loss/draw
       * outcome to update game status and betting points values.
       */
      // retrieve total of house dice pair
      int houseScore = this.houseRoll().getTotalScore();

      // WILL NEED TO LOOP FOR MULTIPLE PLAYERS HERE IN ASSIGNMENT 2
      // loop through callbacks
      for (GameEngineCallback geCallback : this.geCallbacks) {
         // loop through players
         for (Player player : players.values()) {
            if (player.getRollResult() != null) {
               int playerScore = player.getRollResult().getTotalScore();

               // Compare house vs player
               // Set game result (WIN, DRAW, LOSE)
               if (playerScore == houseScore) {
                  player.setGameResult(GameStatus.DREW);
               }
               else if (playerScore < houseScore) {
                  player.setGameResult(GameStatus.LOST);
               }
               else if (playerScore > houseScore) {
                  player.setGameResult(GameStatus.WON);
               }
               // Broadcast result
               geCallback.gameResult(player, player.getGameResult(), this);
            }
         }
         
      }

      /**
       * Call GameEngineCallback.gameResult(Player, GameStatus, GameEngine) with
       * final result for EACH player. That is, broadcast the results of all the
       * players to each and every player participating in the game.
       */
//      // TODO -> is this loop duplicating the callback?
//      for (GameEngineCallback geCallback : this.geCallbacks)
//         for (Player player : this.players.values())
//            if (player.getRollResult() != null) {
//               geCallback.gameResult(player, player.getGameResult(), this);
//            }
   }

   @Override
   public Collection<Player> getAllPlayers() {
      // return collection, not map
      return Collections
               .unmodifiableCollection(new ArrayList<Player>(this.players
                        .values()));
   }

   @Override
   public Player getPlayer(String playerId) {
      return this.players.get(playerId);
   }

   @Override
   public void placeBet(Player player, int betPoints)
            throws InsufficientFundsException {
      // check if player is stored in players collection first
      if (this.players.containsKey(player.getPlayerId())) {
         this.getPlayer(player.getPlayerId()).placeBet(betPoints);
      }
   }

   @Override
   public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
      this.geCallbacks.remove(gameEngineCallback);
   }

   @Override
   public boolean removePlayer(String playerId) {
      // assertion that playerID is not null - necessary??
      assert playerId != null;
      if (this.players.remove(playerId) != null)
         return true;
      else
         return false;
   }

   @Override
   public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
      // Assert that delay inputs are default
      assert initialDelay == INITIAL_DELAY;
      assert finalDelay == FINAL_DELAY;
      assert delayIncrement == DELAY_INCREMENT;

      DicePair hDicePair = new DicePairImpl();
      for (GameEngineCallback callback : this.geCallbacks) {
         for (int i = initialDelay; i < finalDelay; i += delayIncrement) {
            try {
               Thread.sleep(delayIncrement);
            }
            catch (InterruptedException e) {
               e.printStackTrace();
            }
            hDicePair = new DicePairImpl();
            callback.houseRoll(hDicePair, this);
         }
         callback.houseRollOutcome(hDicePair, this);
      }
      this.setHouseRoll(hDicePair);
   }

   private DicePair houseRoll() {
      return this.houseDice;
   }

   private void setHouseRoll(DicePair houseDicePair) {
      this.houseDice = houseDicePair;

   }

   @Override
   public void rollPlayer(Player player, int initialDelay, int finalDelay,
                          int delayIncrement) {
      // Assert player is not null
      assert player.getPlayerId() != null;
      // Assert that delay inputs are default
      assert initialDelay == INITIAL_DELAY;
      assert finalDelay == FINAL_DELAY;
      assert delayIncrement == DELAY_INCREMENT;

      /**
       * 1. start at initialDelay then increment by delayIncrement each time a
       * new number is shown on the dice faces;
       */
      DicePair pDicePair = new DicePairImpl();
      for (GameEngineCallback callback : this.geCallbacks) {
         for (int i = initialDelay; i < finalDelay; i += delayIncrement) {
            try {
               Thread.sleep(delayIncrement);
            }
            catch (InterruptedException e) {
               e.printStackTrace();
            }
            /**
             * 2. call GameEngineCallback.playerRoll(...) or houseRoll(...) each
             * time a pair of new dice faces are shown until delay is equal or
             * greater than finalDelay;
             */
            pDicePair = new DicePairImpl();
            callback.playerRoll(player, pDicePair, this);
         }
         /**
          * 3. call GameEngineCallback.playerRollOutcome(...) or
          * houseRollOutcome(...) with final result for player or house;
          */
         callback.playerRollOutcome(player, pDicePair, this);
      }
      /**
       * 4. update the player with final result so it can be retrieved later
       */
      player.setRollResult(pDicePair);
   }

   @Override
   public void setPlayerPoints(String playerId, int totalPoints) {
      for (Player p : players.values())
         p.setPoints(totalPoints);
   }
   
}
