package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import au.edu.rmit.cpt222.model.comms.commands.callback.GameCallbackCommand;

public class ClientGameEngineCallbackServer {

   private ServerSocket serverSocket;
   private GameEngineClientStub gameEngine;

   public ClientGameEngineCallbackServer(GameEngineClientStub gameEngine) {
      this.gameEngine = gameEngine;
      try {
         this.serverSocket = new ServerSocket(0); // (0) auto allocates port no.
         runCallbackServer();
      }
      catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void runCallbackServer() {
      
      // run on a separate thread to avoid blocking whilst waiting for incoming
      // connections
      // TODO
      Thread myThread = new Thread() {
         public void run() {
            
            while (true) {
               // listen/process incoming ServerStubGameEngineCallback objects
               Socket clientSocket = null;

               try {
                  clientSocket =
                           ClientGameEngineCallbackServer.this.serverSocket
                                    .accept();
                  ObjectInputStream requestStream = new ObjectInputStream (clientSocket.getInputStream());
                  
                  // read in game CALLBACK commands
                  try {
                     
                     GameCallbackCommand command = (GameCallbackCommand) requestStream.readObject();
                     command.execute(gameEngine);
                  }
                  catch (ClassNotFoundException e) {
                     e.printStackTrace();
                  }
                  
               }
               catch (IOException e) {
                  e.printStackTrace();
               }
            }
         }
      };
      myThread.start();
   }

   public HostDetails getHostDetails() {
      // TODO Auto-generated method stub
      return new HostDetails(this.serverSocket.getInetAddress().getHostName(),
                             this.serverSocket.getLocalPort());
   }

}
