package de.htw.berlin.student.vsys2.rmi;

import de.htw.berlin.student.vsys2.rmi.exceptions.IllegalParkingDeckOperationException;
import de.htw.berlin.student.vsys2.rmi.service.ParkingDeckService;
import de.htw.berlin.student.vsys2.rmi.service.ServerConstans;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Client that queries the server.
 * <p/>
 * Created by matthias.drummer and ronny.timm on 04.11.14.
 */

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {

        System.out.println("Client started.");

        Registry registry = LocateRegistry.getRegistry("localhost", ServerConstans.SERVER_PORT);
        ParkingDeckService parkingDeckService = (ParkingDeckService) registry.lookup(ServerConstans.RMI_ID);

        Scanner stdIn = new Scanner(System.in);

        boolean done = false;
        while (!done) {

            System.out.println("Menü");
            System.out.println(1 + " für Enter.");
            System.out.println(2 + " für Leave.");
            System.out.println(3 + " freie Plätze.");
            System.out.println(4 + " Statistik");
            System.out.println(5 + " Beenden");

            int input = stdIn.nextInt();
            if (input == 5) {
                stdIn.close();
                break;
            } else if (input == 1 || input == 2) {

                System.out.println("Gib die Anzahl der Fahrzeuge ein: ");
                int numberOfCars = stdIn.nextInt();

                if (input == 1) {
                    try {
                        parkingDeckService.enter(numberOfCars);
                    } catch (IllegalParkingDeckOperationException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        parkingDeckService.leave(numberOfCars);
                    } catch (IllegalParkingDeckOperationException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (input == 3) {
                System.out.println("Anzahl freie Plätze: " + parkingDeckService.getNumberOfFreeSlots());
            } else if (input == 4) {
                System.out.println("Statistik: " + parkingDeckService.getStatistic());
            }
        }


    }
}