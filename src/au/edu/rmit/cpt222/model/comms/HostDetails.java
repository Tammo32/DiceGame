package au.edu.rmit.cpt222.model.comms;

public class HostDetails {

   private String hostName;
   private int portNumber;
   
   public HostDetails(String hostName, int portNumber) {
      this.hostName = hostName;
      this.portNumber = portNumber;
   }

   public String getHostName() {
      return hostName;
   }

   public int getPortNumber() {
      return portNumber;
   }
   
}
