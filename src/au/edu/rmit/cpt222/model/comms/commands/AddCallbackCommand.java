package au.edu.rmit.cpt222.model.comms.commands;

import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;
import au.edu.rmit.cpt222.model.comms.HostDetails;

public class AddCallbackCommand extends AbstractGameCommand {

   private HostDetails hostDetails;
   
   public AddCallbackCommand (HostDetails hostDetails) {
      this.hostDetails = hostDetails;
   }
   
   @Override
   public void execute(GameEngineServerStub serverStub,
                       ObjectOutputStream responseStream) {
      serverStub.addGameEngineCallback(hostDetails);

   }

}
