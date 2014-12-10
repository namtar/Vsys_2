package de.htw.berlin.student.vsys2.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for a parking deck.
 *
 * @author by Matthias Drummer on 10.12.2014
 */
public interface ParkingDeckService extends Remote {

    /**
     * Enters the parking deck with the given number of cars.
     *
     * @param numberOfCars the number of cars to enter with
     * @throws java.rmi.RemoteException
     */
    void enter(int numberOfCars) throws RemoteException;

    /**
     * Leaves the parking deck with the given number of cars.
     *
     * @param numberOfCars the number of cars to leave with
     * @throws java.rmi.RemoteException
     */
    void leave(int numberOfCars) throws RemoteException;

    /**
     * Returns the number of free slots left on the parking deck.
     *
     * @return the number of free slots
     * @throws java.rmi.RemoteException
     */
    int getNumberOfFreeSlots() throws RemoteException;

}
