package au.edu.rmit.cpt222.model.comms.commands.callback;

import java.io.Serializable;

import au.edu.rmit.cpt222.model.comms.GameEngineClientStub;

public interface GameCallbackCommand extends Serializable {

   public void execute(GameEngineClientStub localModel);

}
