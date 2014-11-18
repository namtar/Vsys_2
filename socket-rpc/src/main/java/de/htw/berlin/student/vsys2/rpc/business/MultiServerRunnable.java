package de.htw.berlin.student.vsys2.rpc.business;

import de.htw.berlin.student.vsys2.rpc.enums.ServerCommands;
import de.htw.berlin.student.vsys2.rpc.util.PardingDeckFactory;

import java.io.*;
import java.net.Socket;
import java.util.Observable;

/**
 * Threads that encapsulated the communication between server and one client.
 * <p/>
 *
 * @author matthias.drummer
 * @author ronny.timm
 */
public class MultiServerRunnable extends Observable implements Runnable {

	private Socket clientSocket = null;

	public MultiServerRunnable(Socket socket) {
		this.clientSocket = socket;
	}

	public void run() {
		try {
			ObjectOutputStream serverOut = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream serverIn = new ObjectInputStream(clientSocket.getInputStream());
			ParkingDeckHandler parkingDeckHandler = new ParkingDeckHandler(PardingDeckFactory.getInstance().getParkingDeck());

			Object[] redArray = null;
			try {
				// read until there is no other input
				// the convention is that the first element in the array is a string and therefore the server command
				while ((redArray = (Object[]) serverIn.readObject()) != null) {

					if (redArray[0].equals(ServerCommands.QUIT.getCommand())) {
						serverOut.writeObject("Bye Bye");
						setChanged();
						notifyObservers(ServerCommands.QUIT);
						break;
					}
					serverOut.writeObject(parkingDeckHandler.handleRequestCommand((String[]) redArray));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			serverOut.close();
			serverIn.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
