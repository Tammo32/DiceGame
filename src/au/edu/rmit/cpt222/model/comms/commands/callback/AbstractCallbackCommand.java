package au.edu.rmit.cpt222.model.comms.commands.callback;

import au.edu.rmit.cpt222.model.comms.GameEngineClientStub;

public abstract class AbstractCallbackCommand implements GameCallbackCommand {

   public void execute(GameEngineClientStub localModel) {
      // TODO Auto-generated method stub

   }

   @Override
   public String toString() {
      return this.getClass().getName();
   }
}
