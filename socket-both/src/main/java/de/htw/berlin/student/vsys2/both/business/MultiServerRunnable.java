package de.htw.berlin.student.vsys2.both.business;

import de.htw.berlin.student.vsys2.both.enums.ServerCommands;
import de.htw.berlin.student.vsys2.both.util.PardingDeckFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            PrintWriter serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ParkingDeckHandler parkingDeckHandler = new ParkingDeckHandler(PardingDeckFactory.getInstance().getParkingDeck());

            String inputLine;
            serverOut.println("Hello Client");
            while ((inputLine = serverIn.readLine()) != null) {
                if (inputLine.equals(ServerCommands.QUIT.getCommand())) {
                    serverOut.println("Bye Bye young Padavan.");
                    serverOut.println(inputLine);
                    setChanged();
                    notifyObservers(ServerCommands.QUIT);
                    break;
                }
                serverOut.println(parkingDeckHandler.handleRequestCommand(inputLine));
            }
            serverOut.close();
            serverIn.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
