package de.htw.berlin.student.vsys2.both;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Threads that encapsulated the communication between server and one client.
 * <p/>
 * Created by matthias.drummer on 04.11.14.
 */
public class MulitServerThread extends Thread {

	private Socket socket = null;

	public MulitServerThread(Socket socket) {
		super("Multi Server Thread");
		this.socket = socket;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			ParkingDeckHandler parkingDeckHandler = new ParkingDeckHandler();

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				//				outputLine = kkp.processInput(inputLine);
				//				out.println(outputLine);
				//				if (outputLine.equals("Bye"))
				//					break;
				if(inputLine.equals(ServerCommands.QUIT.getCommand())) {
					out.println("Bye Bye young Padavan.");
					// TODO: resourcen freigeben .... System.exit();
				}

			}
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
