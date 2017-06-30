package samples.java.net$Socket$ServerSocket;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException {

        List<SocketThread> socketServerList = new ArrayList<>();
        new Thread(new PingClient(socketServerList)).start();

        final int portNumber = 1414;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                Socket socket = serverSocket.accept();
                SocketThread socketThread = new SocketThread(socket);
                Thread th = new Thread(socketThread);
                socketServerList.add(socketThread);
                th.start();
                System.out.println(socket.getLocalPort() + " " + socket.getPort());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class SocketThread implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public SocketThread(Socket socket) {
            this.socket = socket;
        }

        public void out(String str) {
            out.println(str);
        }

        public Socket getSocket() {
            return socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("outputLine");

                String inputLine, outputLine;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        if (socket.getInputStream().available() > 0) {
                            while ((inputLine = in.readLine()) != null) {
                                outputLine = "Server thread name: " + Thread.currentThread().getName() + " | " + inputLine;
                                System.out.println(outputLine);

                                if (socket.getInputStream().available() <= 0) {
                                    break;
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class PingClient implements Runnable {
        private List<SocketThread> socketServerList = new ArrayList<>();

        public PingClient(List<SocketThread> socketServerList) {
            this.socketServerList = socketServerList;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (SocketThread socketThread : socketServerList) {
                    socketThread.out("msgToClient " + socketThread.getSocket().getPort());
                }
            }
        }
    }
}