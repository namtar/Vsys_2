package de.htw.berlin.student.vsys2.rmi;

import de.htw.berlin.student.vsys2.rmi.service.ParkingDeckService;
import de.htw.berlin.student.vsys2.rmi.service.ParkingDeckServiceImpl;
import de.htw.berlin.student.vsys2.rmi.service.ServerConstans;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
        Registry registry = LocateRegistry.createRegistry(ServerConstans.SERVER_PORT);
        registry.bind(ServerConstans.RMI_ID, parkingDeckService);
        System.out.println("Server started");
    }
}
