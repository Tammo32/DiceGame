package au.edu.rmit.cpt222.model.comms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;

import au.edu.rmit.cpt222.model.comms.commands.AddCallbackCommand;
import au.edu.rmit.cpt222.model.comms.commands.AddPlayerCommand;
import au.edu.rmit.cpt222.model.exceptions.InsufficientFundsException;
import au.edu.rmit.cpt222.model.interfaces.GameEngine;
import au.edu.rmit.cpt222.model.interfaces.GameEngineCallback;
import au.edu.rmit.cpt222.model.interfaces.Player;

public class GameEngineClientStub implements GameEngine {

   // communication process:
   // - open Socket
   // - open input/output streams to this Socket
   // - open input/output streams to read/write data from/to the server
   // - close the streams
   // - close the Socket

   // declare streams
   private ObjectOutputStream requestStream;
   private ObjectInputStream responseStream;
   // declare (local) callback
   private GameEngineCallback callback;

   private ClientGameEngineCallbackServer callbackServer;

   public GameEngineClientStub() {

      // open a socket from our client <-> server communication
      try {
         Socket clientSocket =
                  new Socket(Config.SERVER_HOST, Config.SERVER_PORT);
         // open streams
         this.requestStream =
                  new ObjectOutputStream(clientSocket.getOutputStream());
         this.responseStream =
                  new ObjectInputStream(clientSocket.getInputStream());

         this.callbackServer = new ClientGameEngineCallbackServer(this);
         this.registerGECallbackServer(callbackServer.getHostDetails());

         // TODO close streams and Socket (when the client/player exits the game
         // via the GUI) - IMPORTANT

         // if exit(0) == true???
         requestStream.close();
         responseStream.close();
         clientSocket.close();
      }
      catch (UnknownHostException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public GameEngineCallback getCallback() {
      return callback;
   }

   @Override
   public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
      // this.requestStream.writeObject(new AddCallbackCommand());

   }

   // all local model's methods should ask the server-side to perform some
   // function

   @Override
   public String addPlayer(Player player) {
      // TODO - need to implement command design pattern - watch workshop 2
      try {
         this.requestStream.writeObject(new AddPlayerCommand(player));
         try {
            String playerID = (String) responseStream.readObject();

            // TESTING
            System.out.println(" id coming back from the server: " + playerID);

         }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
      }
      catch (IOException e) {
         e.printStackTrace();
      }
      // String playerID = responseStream.readObject();
      return null;
   }

   @Override
   public void calculateResult() {
      // TODO - need to implement command design pattern - watch workshop 2
      // requestStream.writeObject(CalculateResultsCommand ());
   }

   @Override
   public Collection<Player> getAllPlayers() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Player getPlayer(String playerId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void placeBet(Player player, int betPoints)
            throws InsufficientFundsException {
      // TODO Auto-generated method stub

   }

   @Override
   public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
      // TODO Auto-generated method stub

   }

   @Override
   public boolean removePlayer(String playerId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
      // TODO Auto-generated method stub

   }

   @Override
   public void rollPlayer(Player player, int initialDelay, int finalDelay,
                          int delayIncrement) {
      // TODO Auto-generated method stub

   }

   @Override
   public void setPlayerPoints(String playerId, int totalPoints) {
      // TODO Auto-generated method stub

   }

   private void registerGECallbackServer(HostDetails hostDetails) {
      try {
         this.requestStream.writeObject(new AddCallbackCommand(hostDetails));
      }
      catch (IOException e) {
         e.printStackTrace();
      }
   }

}
