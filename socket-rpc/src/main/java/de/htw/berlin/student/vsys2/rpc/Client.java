package de.htw.berlin.student.vsys2.rpc;

import de.htw.berlin.student.vsys2.rpc.enums.ServerCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Client that queries the server.
 * <p/>
 * Created by matthias.drummer and ronny.timm on 04.11.14.
 */

public class Client {

    public static void main(String[] args) throws IOException {

//		if (args.length != 2) {
//			System.err.println("Usage: java Client <host name> <port number>");
//			System.exit(1);
//		}
//
//		String hostName = args[0];
//		int portNumber = Integer.parseInt(args[1]);
        String hostName = "localhost";
        int portNumber = 6700;

        try (
                Socket clientSocket = new Socket(hostName, portNumber);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ) {

            System.out.println("Client started.");
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            Scanner stdIn = new Scanner(System.in);
            Object fromServer;
            String fromUser;

            boolean quit = false;
            try {
                while (true) {
                    fromServer = in.readObject();
                    System.out.println(fromServer);
                    if (quit) {
                        System.exit(0);
                    }

                    stdIn.reset();
                    fromUser = stdIn.next();
                    System.out.println("Client: " + fromUser);

                    // no checks, we just assume the correct input of the user
                    List<Object> params = new ArrayList<Object>();
                    params.add(fromUser);

                    if (fromUser.equals("quit")) {
                        quit = true;
                    } else {
                        System.out.println("Gib die Anzahl der Parameter ein:");
                        Integer numberOfParams = Integer.valueOf(stdIn.nextInt());
                        for (int i = 0; i < numberOfParams; i++) {
                            System.out.println("Wert eingeben: ");
                            // http://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner
                            // Values can be: String, Integer, Double.
                            Object value = null;
                            if (stdIn.hasNextInt()) {
                                value = stdIn.nextInt();
                            } else if (stdIn.hasNextDouble()) {
                                value = stdIn.nextDouble();
                                // float und double sind schwer zu unterscheiden, es sei denn man fragt vorher ab, was es sein soll.
                                // Das Selbe Problem haben wir, wenn wir eine Zahl eingeben, die aber als String weitergereicht werden soll.
//                            } else if (stdIn.hasNextFloat()) {
//                                value = stdIn.hasNextFloat();
                            } else {
                                value = stdIn.next();
                            }

                            if (value != null) {
                                params.add(value);
                            }
                        }
                    }
                    out.writeObject(params.toArray());
                    out.flush();

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}