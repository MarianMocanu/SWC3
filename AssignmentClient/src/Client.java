import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: <host name> and <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket clientSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            BufferedReader qwerty = new BufferedReader(new InputStreamReader(System.in));
            String input, output;

            while ((input = in.readLine()) != null) {
                System.out.println( input);
                if (input.equalsIgnoreCase("Find somebody else to talk to! Nobody listens to you like I do!")) {
                    break;
                }

                output = qwerty.readLine();
                if (output != null) {
                    //System.out.println("Client: " + client);
                    out.println(output);
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
