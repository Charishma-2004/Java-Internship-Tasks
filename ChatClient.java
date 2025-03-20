import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Or replace with server IP
        int port = 12345;

        try (Socket socket = new Socket(serverAddress, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read user input and send to server
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("🗣 You can now start chatting...");

            // Create a thread to read incoming messages
            new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println("\n📢 Server: " + serverResponse);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading messages: " + e.getMessage());
                }
            }).start();

            // Read user input and send to server
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
         }
    }
}