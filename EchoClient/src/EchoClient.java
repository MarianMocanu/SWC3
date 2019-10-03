import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: <host name> and <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket echoClient = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoClient.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoClient.getInputStream()));
        ) {
            BufferedReader qwerty = new BufferedReader(new InputStreamReader(System.in));
            String server, client;

            while ((server = in.readLine()) != null) {
                System.out.println("Server: " + server);
                if (server.equalsIgnoreCase("bye")) {
                    break;
                }

                client = qwerty.readLine();
                if (client != null) {
                    //System.out.println("Client: " + client);
                    out.println(client);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Host " + hostName + " does not exist!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
