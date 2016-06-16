/**
 * Created by user-n8 on 14.06.16.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.*;

public class Server {
    public Server(){
        int PORT = 7888;
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while(true) {
                System.out.println("Waiting users connection...");
                Socket socket = ss.accept();
                ClientThread client = new ClientThread(socket);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
class ClientThread implements Runnable{
    private Socket socket;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }
        public void run(){
            try {
                System.out.println("user connected");
                System.out.println(Thread.currentThread());
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                String str = null;
                while (true) {
                    str = input.readUTF();
                    if (str.equals("by")) {
                        System.out.println("Client is disconnected");
                        socket.close();
                    }
                    System.out.println(str);
                    //sending string back
                    output.writeUTF("I get some string from you - " + str);
                    output.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
