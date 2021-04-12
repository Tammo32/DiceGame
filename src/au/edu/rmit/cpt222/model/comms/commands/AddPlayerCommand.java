package au.edu.rmit.cpt222.model.comms.commands;

import java.io.IOException;
import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class AddPlayerCommand extends AbstractGameCommand {

   private Player player;

   public AddPlayerCommand(Player player) {
      this.player = player;
   }

   // ^^` do the same for each other command in the system (from client stub)
   @Override
   public void execute(GameEngineServerStub serverStub,
                       ObjectOutputStream responseStream) {

      String playerID = serverStub.getModel().addPlayer(this.player);
      try {
         responseStream.writeObject(playerID);
      }
      catch (IOException e) {
         e.printStackTrace();
      }
   }

}
