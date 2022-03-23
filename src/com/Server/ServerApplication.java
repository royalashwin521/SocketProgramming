package com.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication{

    public static void main(String[] args) throws IOException {

        ServerApplication serverApplication = new ServerApplication();
      Socket clientSocket = serverApplication.startServer(9090);
        System.out.println("Client COnnected");

        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream in = new DataInputStream(clientSocket.getInputStream());

//      serverApplication.sendMessage(out,"Your Location is Vaishali,Jaipur,Rajasthan,India ");
      serverApplication.readMessage(in);
//        serverApplication.chatting(out,in);

        out.close();
        in.close();
        clientSocket.close();
    }

//    public void chatting(DataOutputStream out, DataInputStream in) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String gotMessage="";
//        String sendMessage="";
//        while(!gotMessage.equalsIgnoreCase("IAMOUT")) {
//            gotMessage = in.readUTF();
//            System.out.println(gotMessage);
//            sendMessage = reader.readLine();
//            out.writeUTF(sendMessage);
//        }
//    }
    public void chatting(DataOutputStream out, DataInputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message;
        while(!(message = in.readUTF()).equals("IAMOUT")) {
            System.out.println(message);
            out.writeUTF(reader.readLine());
        }
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

    public Socket startServer(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
