package de.htw.berlin.student.vsys2.both.business;

import de.htw.berlin.student.vsys2.both.enums.ServerCommands;
import de.htw.berlin.student.vsys2.both.util.PardingDeckFactory;

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
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ParkingDeckHandler parkingDeckHandler = new ParkingDeckHandler(PardingDeckFactory.getInstance().getParkingDeck());

            String inputLine;
            while ((inputLine = serverIn.readLine()) != null) {
                if (inputLine.equals(ServerCommands.QUIT.getCommand())) {
                    serverOut.println("Bye Bye young Padavan.");
                    serverOut.println(inputLine);
                    break;
                    // TODO: resourcen freigeben .... System.exit();
                }
                serverOut.println(parkingDeckHandler.handleRequestCommand(inputLine));
                //				outputLine = kkp.processInput(inputLine);
                //				out.println(outputLine);
                //				if (outputLine.equals("Bye"))
                //					break;

            }
            serverOut.close();
            serverIn.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
