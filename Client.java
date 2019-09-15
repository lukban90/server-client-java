import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Client {

    private static String HOST = "127.0.0.1";
    private static int PORT = 8000;

    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream output = null;

    public Client(String address, int port) {

        // Create Connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // Take Input from Terminal
            input = new BufferedReader(new InputStreamReader(System.in));
            // input = new DataInputStream(System.in);

            // Send Output to Server Socket
            output = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // Read Until "END" is typed
        String line = "";
        while (!line.equals("END")) {
            try {
                line = input.readLine();
                output.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // Close Connection
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException i) {

        }
    }

    public static void main(String args[]) {
        Client client = new Client(HOST, PORT);
    }
}