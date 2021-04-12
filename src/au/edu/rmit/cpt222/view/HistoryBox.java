package au.edu.rmit.cpt222.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HistoryBox extends JPanel {

   private MainView mainView;
   private JLabel historyLabel = new JLabel("HISTORY");

   public HistoryBox(MainView mainView) {
      this.setBackground(Color.CYAN);
      this.mainView = mainView;
//      add(new JLabel("HISTORY", SwingConstants.CENTER));
      this.add(this.historyLabel);
   }
   
   public void updateStatus(String historyString) {
      this.historyLabel.setText(historyString);
   }
}
