package com.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class YuvrajClient {
    public static void main(String[] args) {
        try {
            InetAddress addr = Inet4Address.getByName("localhost");
            Socket socket = new Socket(addr,6666);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("Hello ");
            dataOutputStream.flush();
            dataOutputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}