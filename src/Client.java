/**
 * Created by user-n8 on 14.06.16.
 */
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        int port = 7888;
        String line = "";
        try {
            InetAddress address = InetAddress.getByName("localhost");
            Socket s = new Socket(address, port);
            DataOutputStream output = new DataOutputStream(s.getOutputStream());
            DataInputStream input = new DataInputStream(s.getInputStream());
            System.out.println("You have been connected to Server. Type in something...");
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                line = keyboard.readLine();
                if (line.equalsIgnoreCase("quit")) {
                    output.writeUTF("by");
                    output.flush();
                    break;
                }
                System.out.println("Sending this line to Server");
                output.writeUTF(line);
                output.flush();
                System.out.println("Server send it back: " + input.readUTF());
                System.out.println("Type in anything else...");
            }
        } catch (UnknownHostException e) {
            e.getMessage();
            System.out.println("Something went wrong");
        } catch (IOException e) {
            e.getMessage();
            System.out.println("Server is not found");
        }
    }
}
