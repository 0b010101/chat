package server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;
    public LinkedList<ServerSomthing> serverList = new LinkedList<>();

    Server() {
        try {
            server = new ServerSocket(9000, 50, InetAddress.getLocalHost());
            System.out.println("Servers is started");
        } catch (IOException e) {}


        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket));
                } catch (IOException eu) {
                    socket.close();
                }
            }
        } catch (IOException en) {} finally {
            try {
                server.close();
            } catch (IOException r) { }
        }
    }

    class ServerSomthing extends Thread {

        private Socket socket;
        private BufferedReader in;
        private BufferedWriter out;


        public ServerSomthing(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            start();
        }
        @Override
        public void run() {
            String word;
            try {
                try {
                    while (true) {
                        word = in.readLine();
                        System.out.println("Echoing: " + word);
                        for (ServerSomthing vr : Server.this.serverList) {
                            vr.send(word);
                        }
                    }
                } catch (NullPointerException ignored) {}


            } catch (IOException e) {
                this.downService();
            }
        }

        private void send(String msg) {
            try {
                out.write(msg + "\n");
                out.flush();
            } catch (IOException ignored) {}

        }

        private void downService() {
            try {
                if(!socket.isClosed()) {
                    socket.close();
                    in.close();
                    out.close();
                    for (ServerSomthing vr : Server.this.serverList) {
                        if(vr.equals(this)) vr.interrupt();
                        Server.this.serverList.remove(this);
                    }
                }
            } catch (IOException ignored) {}
        }
    }
}