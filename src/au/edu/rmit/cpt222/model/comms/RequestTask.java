package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import au.edu.rmit.cpt222.model.comms.commands.GameCommand;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;

// this is where the main work of handling client requests will be done
public class RequestTask extends Thread {

   private GameEngineServerStub gameEngineServerStub;
   private Socket clientSocket;

   public RequestTask(GameEngineServerStub gameEngineServerStub,
                      Socket clientSocket) {
      // retrieve reference to the model for later use
      // this.model = gameEngineServerStub.getModel();

      this.clientSocket = clientSocket;
   }

   @Override
   public void run() {

      System.out.println("Client was connected from: " +
                         clientSocket.getPort());

      // open streams to perform communication with the client
      try {
         ObjectOutputStream responseStream =
                  new ObjectOutputStream(clientSocket.getOutputStream());
         ObjectInputStream requestStream =
                  new ObjectInputStream(clientSocket.getInputStream());

         // create main loop to receive command objects from the client
         while (true) {
            // read in command (WATCH WORKSHOP 2)
            try {
               GameCommand command = (GameCommand) requestStream.readObject();
               command.execute(this.gameEngineServerStub, responseStream);
            }
            catch (ClassNotFoundException e) {
               e.printStackTrace();
            }
         }

      }
      catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}
