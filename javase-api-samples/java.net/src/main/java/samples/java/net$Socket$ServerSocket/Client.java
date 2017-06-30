package samples.java.net$Socket$ServerSocket;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {

        final String hostName = "127.0.0.1";
        final int portNumber = 1414;

        try (
                java.net.Socket kkSocket = new java.net.Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        ) {

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (kkSocket.getInputStream().available() > 0) {
                    while ((fromServer = in.readLine()) != null) {
                        System.out.println("Client: " + fromServer);

                        if (kkSocket.getInputStream().available() <= 0) {
                            break;
                        }
                    }
                }

                if (System.in.available() > 0) {
                    while ((fromUser = stdIn.readLine()) != null) {
                        out.println(fromUser);

                        if (System.in.available() <= 0) {
                            break;
                        }
                    }
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
