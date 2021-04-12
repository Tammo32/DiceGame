package au.edu.rmit.cpt222.model;

import au.edu.rmit.cpt222.controller.MainController;
import au.edu.rmit.cpt222.model.interfaces.DicePair;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GUIcallbackImpl extends GameEngineCallbackImpl {

	private MainController controller;

	public GUIcallbackImpl(MainController mainController) {
		this.controller = mainController;
	}

	@Override
	public void gameResult(Player player, GameStatus result, GameEngine engine) {
		// console update
		super.gameResult(player, result, engine);
		
		// UI update - testing only
		this.controller.updateResult(player.getPlayerId());
	}

	@Override
	public void houseRoll(DicePair dicePair, GameEngine engine) {
		// TODO Auto-generated method stub
	}

	@Override
	public void houseRollOutcome(DicePair result, GameEngine engine) {
		// TODO Auto-generated method stub
	}

	@Override
	public void playerRoll(Player player, DicePair dicePair, GameEngine engine) {
		// TODO Auto-generated method stub
	   // use the Controller to update the view
	   controller.updateResult(player.getPlayerId());
	   System.out.println("in callback - roll is executing");
	}

	@Override
	public void playerRollOutcome(Player player, DicePair result,
			GameEngine engine) {
		// TODO Auto-generated method stub
	}
}
