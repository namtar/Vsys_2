package de.htw.berlin.student.vsys2.rpc.business;

import de.htw.berlin.student.vsys2.rpc.enums.ServerCommands;
import de.htw.berlin.student.vsys2.rpc.util.ParkingDeckFactory;

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

    private ObjectOutputStream serverOut;
    private ObjectInputStream serverIn;

    public MultiServerRunnable(Socket socket) {

        try {
            serverOut = new ObjectOutputStream(socket.getOutputStream());
            serverIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MultiServerRunnable(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.serverOut = outputStream;
        this.serverIn = inputStream;
    }

    public void run() {
        try {
            ParkingDeckHandler parkingDeckHandler = new ParkingDeckHandler(ParkingDeckFactory.getInstance().getParkingDeck());

            try {
                serverOut.writeObject("Hello Client");
                serverOut.flush();
                // read until there is no other input
                // the convention is that the first element in the array is a string and therefore the server command
                while (true) {
                    System.out.println("Iterate");
                    Object[] redArray = (Object[]) serverIn.readObject();

                    System.out.println(redArray);
                    if (redArray[0].equals(ServerCommands.QUIT.getCommand())) {
                        serverOut.writeObject("Bye Bye");
                        serverOut.flush();
                        setChanged();
                        notifyObservers(ServerCommands.QUIT);
                        break;
                    }
                    serverOut.writeObject(parkingDeckHandler.handleRequestCommand(redArray));
                    serverOut.flush();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            serverOut.close();
            serverIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
