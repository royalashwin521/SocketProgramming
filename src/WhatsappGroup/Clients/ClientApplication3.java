package WhatsappGroup.Clients;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientApplication3 {

    public static void main(String[] args) throws IOException, InterruptedException {
        ClientApplication1 clientApplication = new ClientApplication1();
//        Socket clientSocket = clientApplication.makeConnectionWithServer("192.168.0.47",8000);
        Socket clientSocket = clientApplication.makeConnectionWithServer("localhost",8000);

//        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
//        DataInputStream in = new DataInputStream(clientSocket.getInputStream());

//        clientApplication.sendmessage(out,"Hello Server. Who are you");
//        clientApplication.readmessage(in);
        clientApplication.chatting(clientSocket);
//        out.close();
//        in.close();
        clientSocket.close();
    }

    public void chatting(Socket socket) throws IOException, InterruptedException {
        //Thread for reading
        GroupReading groupreading = new GroupReading(socket);
        Thread t1 = new Thread(groupreading);
        t1.start();

        //Thread for writing
        GroupWriting groupWriting = new GroupWriting(socket);
        Thread t2 = new Thread(groupWriting);
        t2.start();

        t1.join();
        t2.join();

    }

    public void sendMessage(DataOutputStream out,String message) throws IOException {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.flush();
        }
    }

    public void readMessage(DataInputStream in) throws IOException {
        try{
            System.out.println(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket makeConnectionWithServer(String IP,int portNumber){
        try {
            return new Socket(IP, portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
