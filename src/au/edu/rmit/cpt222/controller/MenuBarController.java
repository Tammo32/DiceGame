package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.view.GameMenuBar;

public class MenuBarController implements ActionListener {

   private GameMenuBar menuBar;
   private MainController controller;

   public MenuBarController(GameMenuBar gameMenuBar) {
      this.menuBar = gameMenuBar;
      this.controller = this.menuBar.getMainView().getMainController();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // should use a switch statement
      if (e.getActionCommand() == MainController.ADD_PLAYER_COMMAND) {
         this.controller.addPlayer();
      }
      if (e.getActionCommand() == MainController.TOP_UP_CREDIT_COMMAND) {
         this.controller.topUpCredit();
      }
      if (e.getActionCommand() == MainController.PLACE_BET_ROLL_COMMAND) {
         this.controller.placeBetRoll();
      }
      if (e.getActionCommand() == MainController.EXIT_COMMAND) {
         this.controller.exit();
      }
   }
}