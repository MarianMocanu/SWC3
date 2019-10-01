import java.net.*;
import java.io.*;
public class Server
{
  public static void main( String args[]) throws Exception
  {
    ServerSocket socketServer = new ServerSocket(9999);
    System.out.println("Server has successfully started.");
    Socket socket=socketServer.accept();
    System.out.println("Connection with client has been established.");
    BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    while(true)
    {
      //System.out.println("server repeat as long as client not send null");
      String clientMessage, serverMessage;
      while((clientMessage=br.readLine())!=null)
      {
        System.out.println("Client: "+clientMessage);
        System.out.print("You: ");
        serverMessage = kb.readLine();
        //System.out.println("Answer send to client machine");
        bw.write(serverMessage+"\n");
        bw.flush();
      }
      System.out.println("Server is shutting down..");
      socket.close();
      socketServer.close();
      bw.close();
      kb.close();
      System.exit(0);
    }
  }
}