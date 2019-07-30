package client;

import java.io.*;
import java.net.Socket;

public class ClientController {
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private GUI g;
    private String nic;

    ClientController(GUI g) {
        this.g = g;
    }


    public boolean connect(String ip, int port, String nic) {
        //TODO nic pass
        try {
            clientSocket = new Socket(ip, port);
            this.nic = nic;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            new ReadMsg().start();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void sendMsg(String str) {
        try {
            out.write(nic + ": " + str + "\n");
            out.flush();
        } catch (IOException e) {
            downService();
        }
    }

    private void downService() {
        try {
            if (!clientSocket.isClosed()) {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine();
                    g.displayMsg(str);
                }
            } catch (IOException e) {
                ClientController.this.downService();
            }
        }
    }
}
