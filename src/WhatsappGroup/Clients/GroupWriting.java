package WhatsappGroup.Clients;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GroupWriting implements Runnable{
    private final Socket socket;
    public GroupWriting(Socket socket) {
        this.socket = socket;
    }

    public void Writing(Socket socket) throws IOException {
        Scanner sc = new Scanner(System.in);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String sendMessage = "";
        while (true){
            sendMessage = sc.nextLine();
            out.writeUTF(sendMessage);
            if(sendMessage.equalsIgnoreCase("IAMOUT"))
                break;
        }

    }

    @Override
    public void run() {
        try {
            Writing(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
