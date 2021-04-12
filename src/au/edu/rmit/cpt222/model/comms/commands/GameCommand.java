package au.edu.rmit.cpt222.model.comms.commands;

import java.io.ObjectOutputStream;
import java.io.Serializable;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;

public interface GameCommand extends Serializable {
   
   public void execute(GameEngineServerStub serverStub, ObjectOutputStream responseStream);

}
