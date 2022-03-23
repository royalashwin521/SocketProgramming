package WhatsappGroup.ServerSide.Group;

import WhatsappGroup.ServerSide.Server;

import java.io.*;
import java.net.Socket;

public class Group implements Runnable{
    private Socket socket;
    private Server serverObj;

    public Group(Server serverObject, Socket socket) {
        this.socket = socket;
        this.serverObj = serverObject;
    }

    public Group() {

    }

    @Override
    public void run() {
        try {
            chatting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chatting() throws IOException {

        DataInputStream in = new DataInputStream(socket.getInputStream());
        String message;

        while(!((message = in.readUTF()).equals("IAMOUT"))) {
            sendMessageToGroup(message);
        }
        in.close();
        //Advice: use ThreadGroup to notify and wait all threads in gp.
        for (Socket otherSocket : Server.getClientSocketList()) {
            if (socket == otherSocket) {
                System.out.println("the leaving socket: "+socket+" the removed from Socket list: "+otherSocket);
                Server.getClientSocketList().remove(socket);
                System.out.println("socket is removed");
                break;
            }
        }
        System.out.println("socket is getting close");
        socket.close();
    }

    public void sendMessageToGroup(String message) throws IOException {
//        System.out.println(Server.getClientSocketList());
        for (Socket otherSocket : Server.getClientSocketList()) {
            if (!(socket == otherSocket)) {
                DataOutputStream out = (DataOutputStream) getOutputSocketStream(otherSocket);
                out.writeUTF(message);
            }
        }
    }

    public Object getOutputSocketStream(Socket socket) throws IOException {
        return new DataOutputStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
