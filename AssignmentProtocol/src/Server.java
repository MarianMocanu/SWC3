import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("<Port number> was not entered!");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        while (true) {

            try (
                    ServerSocket serverSocket = new ServerSocket(port);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                String input, output;
                Protocol myProtocol = new Protocol();
                output = myProtocol.processInput(null);
                out.println(output);

                while ((input = in.readLine()) != null) {
                    out.println(myProtocol.processInput(input));
                    if (output.equalsIgnoreCase("Find somebody else to talk to! Nobody listens to you like I do!"))
                        break;
                }
            } catch (IOException e) {
                System.out.println("Exception caught when initializing the server socket!");
            }
        }
    }
}
