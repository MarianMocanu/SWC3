import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public EchoHandler(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error when initializing I/O...");
        }
    }

    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when closing the I/O");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String input, output;
                EchoProtocol echoProtocol = new EchoProtocol();
                out.println("Say something!");

                while ((input = in.readLine()) != null) {
                    output = echoProtocol.processClientInput(input);
                    out.println(output);
                    if (output.equalsIgnoreCase("bye")) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Unexpected connection interruption");
                disconnect();
                return;
            }
        }
    }
}
