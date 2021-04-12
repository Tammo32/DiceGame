package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.net.ServerSocket;

import au.edu.rmit.cpt222.model.GameEngineImpl;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;

public class GameEngineServerStub {

   // use server-side GameEngineImpl to perform all game functions
   private GameEngine model = new GameEngineImpl();

   public GameEngineServerStub(int serverPort) {
      // open up server-side socket to listen for incoming client requests
      try {
         ServerSocket serverSocket = new ServerSocket(serverPort);
         System.out.println("Server on port " + serverSocket.getLocalPort() +
                            " is waiting for client connections");

         // loop and accept multiple clients
         while (!serverSocket.isClosed()) {
            // TODO create a new Thread for incoming client/player to process
            // incoming requests
             new RequestTask (this, serverSocket.accept()).start();
         }
      }
      catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }


   public void addGameEngineCallback(HostDetails hostDetails) {
      this.model.addGameEngineCallback(new ServerStubGameEngineCallback(hostDetails));
   }
   
   public GameEngine getModel() {
      return model;
   }

}
