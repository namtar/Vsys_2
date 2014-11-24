package de.htw.berlin.student.vsys2.rpc;

import de.htw.berlin.student.vsys2.rpc.enums.ServerCommands;
import de.htw.berlin.student.vsys2.rpc.util.MeineEingabe;

import java.io.*;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Client that queries the server.
 * <p/>
 * Created by matthias.drummer and ronny.timm on 04.11.14.
 */

public class Client {

	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err.println("Usage: java Client <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		try (
				Socket clientSocket = new Socket(hostName, portNumber);
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
		) {

			System.out.println("Client started.");
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
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

					boolean inputCorrect = false;
					while (!inputCorrect) {
						fromUser = stdIn.readLine();
						System.out.println("Client: " + fromUser);

						String[] arrayFromUser = fromUser.split(" ");
						if (MeineEingabe.checkUserInput(arrayFromUser)) {
							List<Object> params = new ArrayList<Object>();
							for (int k = 0; k < arrayFromUser.length - 1; k++) {
								params.add(arrayFromUser[k]);
							}

							MeineEingabe.checkLeaveEnter(arrayFromUser);

							out.writeObject(params.toArray());
							out.flush();

							inputCorrect = true;
							break;
						}
					}
					if (!inputCorrect) {
						System.out.println("Command not supported");
					}
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