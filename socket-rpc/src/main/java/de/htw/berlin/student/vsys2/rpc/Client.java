package de.htw.berlin.student.vsys2.rpc;

import de.htw.berlin.student.vsys2.rpc.enums.ServerCommands;

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

						for (ServerCommands commands : ServerCommands.values()) {
							if (commands.getCommand().equals(fromUser.toLowerCase())) {
								List<Object> params = new ArrayList<Object>();

								params.add(fromUser.toLowerCase());
								if (commands == ServerCommands.IN || commands == ServerCommands.OUT) {
									System.out.println("Anzahl Autos");
									Integer numberOfCars = Integer.valueOf(stdIn.readLine());
									params.add(numberOfCars);
								}

								out.writeObject(params.toArray());

								if (commands == ServerCommands.QUIT) {
									quit = true;
								}
								inputCorrect = true;
								break;
							}
						}
						if (!inputCorrect) {
							System.out.println("Command not supported");
						}
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