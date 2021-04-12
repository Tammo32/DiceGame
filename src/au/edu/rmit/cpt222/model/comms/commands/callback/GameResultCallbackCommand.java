package au.edu.rmit.cpt222.model.comms.commands.callback;

import au.edu.rmit.cpt222.model.comms.GameEngineClientStub;
import au.edu.rmit.cpt222.model.interfaces.GameEngine.GameStatus;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameResultCallbackCommand extends AbstractCallbackCommand {

   private Player player;
   private GameStatus result;

   public GameResultCallbackCommand(Player player, GameStatus result) {
      super();
      this.player = player;
      this.result = result;
   }

   @Override
   public void execute(GameEngineClientStub localModel) {
      localModel.getCallback().gameResult(player, result, localModel);
   }

}
