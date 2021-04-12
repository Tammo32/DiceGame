package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.view.GameDialogBox;

public class GameDialogBoxController implements ActionListener {
   private GameDialogBox dialogBox;

   public GameDialogBoxController(GameDialogBox gameDialogBox) {
      this.dialogBox = gameDialogBox;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // find out which button was clicked by the user
      // 1. e.getSource();
      // 2. e.getActionCommand(); - check Java API
      
      // addPlayer dialog command
      if (e.getActionCommand() == MainController.ADD_PLAYER_COMMAND) {
         dialogBox.getMainView().getMainController().addPlayer();
      }
      
      // topUpCredit dialog command
      if (e.getActionCommand() == MainController.TOP_UP_CREDIT_COMMAND) {
         dialogBox.getMainView().getMainController().topUpCredit();;
      }
      
      // placeBet dialog command
      if (e.getActionCommand() == MainController.PLACE_BET_ROLL_COMMAND) {
         dialogBox.getMainView().getMainController().placeBetRoll();;
      }
      
   }
}
