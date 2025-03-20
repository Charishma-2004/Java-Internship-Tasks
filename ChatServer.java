import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<Socket> clientSockets = new HashSet<>();

    public static void main(String[] args) {
        int port = 12345; // Define port number
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("💬 Server started on port " + port);

            while (true) {
                // Accept client connections
                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);
                System.out.println("✅ New client connected!");

                // Start a new thread for each client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("📩 Received: " + message);
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                System.err.println("Error with client: " + e.getMessage());
            }
        }

        // Method to broadcast message to all clients
        private void broadcastMessage(String message) {
            for (Socket s : clientSockets) {
                try {
                    PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
                    writer.println(message);
                } catch (IOException e) {
                    System.err.println("Error broadcasting: " + e.getMessage());
                }
            }
        }
    }
}