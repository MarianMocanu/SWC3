import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(new EchoHandler(clientSocket));
                
//                Thread serverThread = new Thread(new EchoHandler(clientSocket));
//                serverThread.start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when initializing the server socket");
        }

    }
}

