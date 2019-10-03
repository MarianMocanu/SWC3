import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        while (true) {
            try (
                    ServerSocket serverSocket = new ServerSocket(port);
                    //

                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                String input, output;

                EchoProtocol echoProtocol = new EchoProtocol();
                output = echoProtocol.processClientInput(null);
                out.println(output);

                while ((input = in.readLine()) != null) {
                    output = echoProtocol.processClientInput(input);
                    out.println(output);
                    if (output.equalsIgnoreCase("bye")) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port " +
                        port + " or listening for a connection");
                System.out.println(e.getMessage());
            }

        }
    }
}

