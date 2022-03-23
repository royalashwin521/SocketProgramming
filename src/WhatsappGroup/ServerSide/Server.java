package WhatsappGroup.ServerSide;

import WhatsappGroup.ServerSide.Group.Group;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static ArrayList<Socket> clientSocketList = new ArrayList<>();

    public static void main(String[] args) {
        Server serverobject = new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            while (true){
                System.out.println("Looking for Client");
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected");
                clientSocketList.add(socket);
                Group gp = new Group(serverobject,socket);
                Thread t1 = new Thread(gp);
                t1.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Socket> getClientSocketList() {
        return  clientSocketList;
    }

    public static void setClientSocketList(ArrayList<Socket> clientSocketList) {
        Server.clientSocketList = clientSocketList;
    }


}
