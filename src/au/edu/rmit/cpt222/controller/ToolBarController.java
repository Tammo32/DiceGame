package au.edu.rmit.cpt222.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import au.edu.rmit.cpt222.view.ToolBar;

public class ToolBarController implements ActionListener {

   private ToolBar toolBar;
   private MainController controller;

   public ToolBarController(ToolBar toolBar) {
      this.toolBar = toolBar;
      this.controller = this.toolBar.getMainView().getMainController();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // find out which specific button was invoked
      // 1. e.getSource() // check API
      // 2. e.getActionCommand()
      // USE A SWITCH STATEMENT FOR MULTIPLE BUTTONS
      if (e.getActionCommand().equals(MainController.ADD_PLAYER_COMMAND))
         this.controller.addPlayer();
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