import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {


  public static void main(String args[]) throws Exception {
    Socket socket = new Socket("", 9999);
    BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    System.out.println("You are connected to the server.");
    System.out.println("If you want to quit, type 'exit'.");
    System.out.print("You: ");

    String clientMessage, serverMessage = null;
    while (!(clientMessage = kb.readLine()).equals("exit")) {
      //System.out.println(" data  send to server machine");
      bw.write(clientMessage + "\n");
      bw.flush();
      serverMessage = br.readLine();
      //System.out.println(" data  receive from  server machine");
      System.out.println("Server: " + serverMessage);
      System.out.print("You: ");

    }
    System.out.println("You have been disconnected from the server..");
    socket.close();
    bw.close();
    kb.close();
  }
}

