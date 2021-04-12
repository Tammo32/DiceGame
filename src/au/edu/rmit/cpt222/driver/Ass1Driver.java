package au.edu.rmit.cpt222.driver;

import javax.swing.SwingUtilities;

import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.view.MainView;

public class Ass1Driver {

	public static void main(String[] args) {
		// initialise the Model
		GameEngine model = new GameEngineImpl();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// initialise the View and map the Model and the View
				MainView mainView = new MainView(model);
				mainView.setVisible(true);
			}
		});
	}
}

/** MVC
Model - data manipulation and business (game) rules/logic
View - the actual user interface elements
Controller - 
   1) provide a mapping between the View and the Model
   2) respond to end-user interactions
   
View -> Controller -> Model 
   1) when the model returns the data to be displayed in the view:
         View <- Controller <- Model 
   2) when the model performs game execution
         View <- Controller <- Callback <- Model
      
- all communication between the Controller and the Model should be performed strictly via the GameEngine
*/