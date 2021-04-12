package au.edu.rmit.cpt222.model.comms.commands;

import java.io.ObjectOutputStream;

import au.edu.rmit.cpt222.model.comms.GameEngineServerStub;

public abstract class AbstractGameCommand implements GameCommand {
   
   @Override
   public String toString() {
      return this.getClass().getName();
   }

}
