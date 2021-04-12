// code adapted from my 2017 SADI A1
package au.edu.rmit.cpt222.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineCallbackImpl implements GameEngineCallback {

   protected Logger logger = Logger.getLogger("A1");

   @Override
   public void gameResult(Player player, GameStatus result, GameEngine engine) {
      // log to console
      logger.log(Level.INFO, "Game Result: " + player.getPlayerName() +
                             " has " + result + "\n");
   }

   @Override
   public void houseRoll(DicePair dicePair, GameEngine engine) {
      // log to console
      logger.log(Level.INFO,
                 "Player: House, intermediate roll= " + dicePair.toString());
   }

   @Override
   public void houseRollOutcome(DicePair result, GameEngine engine) {
      // log to console
      logger.log(Level.INFO, "Player: House, final roll state= " +
                             result.toString() + "\n");
   }

   @Override
   public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
      // log to console
      logger.log(Level.INFO, "Player: " + player.getPlayerName() +
                             ", intermediate roll= " + dicePair.toString());
   }

   @Override
   public void playerRollOutcome(Player player, DicePair result,
                                 GameEngine engine) {
      // log to console
      logger.log(Level.INFO, "Player: " + player.getPlayerName() +
                             ", final roll state= " + result.toString() + "\n");

   }
   
   // View -> Controller -> Model -> Callback (GUI) -> Controller -> View
   
}
