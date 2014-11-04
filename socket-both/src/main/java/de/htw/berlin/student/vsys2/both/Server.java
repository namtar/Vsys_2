package de.htw.berlin.student.vsys2.both;

import de.htw.berlin.student.vsys2.both.business.MulitServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

/**
 * Server class.
 * <p/>
 * Created by matthias.drummer on 04.11.14.
 */
public class Server {

//	private final static int MAX_CLIENTS = 10;

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);

            System.out.println("Started Server on Port: " + portNumber);
            boolean listening = true;
            while (listening) {
                new MulitServerThread(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            if (serverSocket == null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    LOGGER.severe(e.getMessage());
                }
            }
        }
    }
}
