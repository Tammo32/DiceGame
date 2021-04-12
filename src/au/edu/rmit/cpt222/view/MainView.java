package au.edu.rmit.cpt222.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;

public class MainView extends JFrame {

   private GameEngine model;

   // declare the main Controller
   private MainController controller;

   // declare sub-views
   private ToolBar toolBar; // contains buttons for preforming system functions
   private GamePanel gamePanel; // displays dice (rolling and final faces)
   private HistoryBox historyBox; // displays up to last 5 games of history
   private GameMenuBar menuBar; // menubar for performing system functions
   private StatusBar statusBar; // statusbar for current game result of player
   private GameDialogBox dialogBox; // dialog box for adding player etc.

   public MainView(GameEngine model) {
      super("CPT222 Ass 1 GUI");
      this.model = model;
      initView();
   }

   public GameMenuBar getGameMenuBar() {
      return this.menuBar;
   }

   public GameDialogBox getDialogBox() {
      return this.dialogBox;
   }

   public GamePanel getGamePanel() {
      return this.gamePanel;
   }

   public HistoryBox getHistoryBox() {
      return this.historyBox;
   }

   public MainController getMainController() {
      return this.controller;
   }

   public GameEngine getModel() {
      return this.model;
   }

   public ToolBar getToolBar() {
      return this.toolBar;
   }

   public StatusBar getStatusBar() {
      return this.statusBar;
   }

   private void initView() {

      // set UI basics
      this.setSize(1200, 800);
      this.setLocationRelativeTo(null);
      // this.setExtendedState(MAXIMIZED_BOTH);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      this.setLayout(new BorderLayout());

      this.controller = new MainController(this);

      // initialise sub-views
      this.toolBar = new ToolBar(this);
      this.gamePanel = new GamePanel(this);
      this.historyBox = new HistoryBox(this);
      this.menuBar = new GameMenuBar(this);
      this.statusBar = new StatusBar(this);
      this.dialogBox = new GameDialogBox(this);

      // add/display sub-views
      add(this.toolBar, BorderLayout.NORTH);
      add(this.gamePanel, BorderLayout.CENTER);
      add(this.historyBox, BorderLayout.WEST);
      add(this.statusBar, BorderLayout.SOUTH);

      this.setJMenuBar(this.menuBar);

      // test historyBox
      // controller.addToHistory();

      // test updateStatusBar
      // this.getStatusBar().setStatusBar();
   }

   // update the sub-views
   public void updateGamePanel(String string) {
      gamePanel.updateStatus(string);
   }
}
