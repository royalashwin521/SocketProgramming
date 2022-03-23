package WhatsappGroup.Clients;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class GroupReading implements Runnable{
    private final Socket socket;

    public GroupReading(Socket socket) {
        this.socket = socket;
    }

    public void Reading(Socket socket) throws IOException {

        DataInputStream in = new DataInputStream(socket.getInputStream());
        String gotMessage = "";

        while(true){
                gotMessage = in.readUTF();
            System.out.println(gotMessage);
        }
    }

    @Override
    public void run() {
        try {
            Reading(socket);
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
