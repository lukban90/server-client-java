import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;

public class Server {

    private static int PORT = 8000;

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started");

            System.out.println("Waiting for Client...");

            socket = server.accept();
            System.out.println("Client Accepted");

            // Takes input from the client socket
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String line = "";
            while (!line.equals("END")) {
                try {
                    line = input.readUTF();
                    System.out.println(line);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing Connection");

            // Close Connection
            socket.close();
            input.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(PORT);
    }
}