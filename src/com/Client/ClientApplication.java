package com.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class ClientApplication {

    public static void main(String[] args) throws IOException {
        ClientApplication clientApplication = new ClientApplication();
        Socket clientSocket = clientApplication.makeConnectionWithServer("localhost",8000);

        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream in = new DataInputStream(clientSocket.getInputStream());

//        clientApplication.sendmessage(out,"Hello Server. Who are you");
//        clientApplication.readmessage(in);
        clientApplication.chatting(out,in);
        out.close();
        in.close();
        clientSocket.close();
    }

    public void chatting(DataOutputStream out, DataInputStream in) throws IOException {
        Scanner sc = new Scanner(System.in);
        String gotMessage = "";
        while(!(gotMessage.equalsIgnoreCase("IAMOUT"))){
            out.writeUTF(sc.nextLine());
            gotMessage = in.readUTF();
            System.out.println(gotMessage);
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

    public Socket makeConnectionWithServer(String IP,int portNumber){
        try {
            return new Socket(IP, portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
