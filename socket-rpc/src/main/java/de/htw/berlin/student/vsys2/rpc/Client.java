package de.htw.berlin.student.vsys2.rpc;

import de.htw.berlin.student.vsys2.rpc.enums.ServerCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;

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
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		) {

			System.out.println("Client started.");
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;

			boolean quit = false;
			while ((fromServer = in.readLine()) != null) {
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
							out.println(fromUser.toLowerCase());

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

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}