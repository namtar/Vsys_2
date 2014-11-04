package de.htw.berlin.student.vsys2.both;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Server class.
 * <p/>
 * Created by matthias.drummer on 04.11.14.
 */
public class Server {

//	private final static int MAX_CLIENTS = 10;

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Usage: java Server <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);
		//
		//		try (
		//				ServerSocket serverSocket = new ServerSocket(portNumber);
		//				Socket clientSocket = serverSocket.accept();
		//				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		//				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//		) {
		//
		//			String inputLine, outputLine;
		//
		//			// Initiate conversation with client
		//			KnockKnockProtocol kkp = new KnockKnockProtocol();
		//			outputLine = kkp.processInput(null);
		//			out.println(outputLine);
		//
		//			while ((inputLine = in.readLine()) != null) {
		//				outputLine = kkp.processInput(inputLine);
		//				out.println(outputLine);
		//				if (outputLine.equals("Bye."))
		//					break;
		//			}

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);

			System.out.println("Started Server on Port: " + portNumber);
			boolean listening = true;
			while(listening) {
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
					e.printStackTrace();
				}
			}
		}
	}
}
