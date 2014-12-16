package de.htw.berlin.student.vsys2.rmi;

import de.htw.berlin.student.vsys2.rmi.business.StatisticsTimer;
import de.htw.berlin.student.vsys2.rmi.service.ParkingDeckService;
import de.htw.berlin.student.vsys2.rmi.service.ParkingDeckServiceImpl;
import de.htw.berlin.student.vsys2.rmi.service.ServerConstants;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.logging.Logger;

/**
 * Server class.
 * <p/>
 *
 * @author matthias.drummer
 * @author ronny.timm
 */
public class Server {

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        ParkingDeckService parkingDeckService = new ParkingDeckServiceImpl();
        Registry registry = LocateRegistry.createRegistry(ServerConstants.SERVER_PORT);
        registry.bind(ServerConstants.RMI_ID, parkingDeckService);
        System.out.println("Server started");

        Timer timer = new Timer();
        timer.schedule(new StatisticsTimer(), 0, 10000);
        System.out.println("Statistics timer started.");
    }
}
