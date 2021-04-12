package au.edu.rmit.cpt222.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import au.edu.rmit.cpt222.controller.MenuBarController;
import au.edu.rmit.cpt222.controller.MainController;

public class GameMenuBar extends JMenuBar {

   private MainView mainView;
   private MenuBarController controller;
   
   // declare menu item
   private JMenu optionsMenu = new JMenu("Options");

   // declare sub-menu items
   private JMenuItem addPlayerItem = new JMenuItem("Add player...");
   private JMenuItem topUpCreditItem = new JMenuItem("Top up credit...");
   private JMenuItem placeBetRollItem = new JMenuItem("Place bet / Roll");
   private JMenuItem exitItem = new JMenuItem("Exit");

   public GameMenuBar(MainView mainView) {
      this.mainView = mainView;
      this.controller = new MenuBarController(this);

      new JMenuBar();

      // add optionsMenu to mainView
      add(optionsMenu);

      // add optionsMenu items
      optionsMenu.add(addPlayerItem);
      optionsMenu.add(topUpCreditItem);
      optionsMenu.add(placeBetRollItem);
      optionsMenu.add(exitItem);

      // set Mnemonics
      optionsMenu.setMnemonic(KeyEvent.VK_O);
      addPlayerItem.setMnemonic(KeyEvent.VK_A);
      topUpCreditItem.setMnemonic(KeyEvent.VK_T);
      placeBetRollItem.setMnemonic(KeyEvent.VK_P);
      exitItem.setMnemonic(KeyEvent.VK_X);

      // set Action Commands
      addPlayerItem.setActionCommand(MainController.ADD_PLAYER_COMMAND);
      topUpCreditItem.setActionCommand(MainController.TOP_UP_CREDIT_COMMAND);
      placeBetRollItem.setActionCommand(MainController.PLACE_BET_ROLL_COMMAND);
      exitItem.setActionCommand(MainController.EXIT_COMMAND);

      // set Action Listeners
      addPlayerItem.addActionListener(this.controller);
      topUpCreditItem.addActionListener(this.controller);
      placeBetRollItem.addActionListener(this.controller);
      exitItem.addActionListener(this.controller);
      
   }
   
   public MainView getMainView() {
      return mainView;
   }

}
