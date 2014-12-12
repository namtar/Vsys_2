package de.htw.berlin.student.vsys2.rmi.service;

import de.htw.berlin.student.vsys2.rmi.exceptions.IllegalParkingDeckOperationException;

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
     * @throws de.htw.berlin.student.vsys2.rmi.exceptions.IllegalParkingDeckOperationException
     */
    void enter(int numberOfCars) throws RemoteException, IllegalParkingDeckOperationException;

    /**
     * Leaves the parking deck with the given number of cars.
     *
     * @param numberOfCars the number of cars to leave with
     * @throws java.rmi.RemoteException
     * @throws de.htw.berlin.student.vsys2.rmi.exceptions.IllegalParkingDeckOperationException
     */
    void leave(int numberOfCars) throws RemoteException, IllegalParkingDeckOperationException;

    /**
     * Returns the number of free slots left on the parking deck.
     *
     * @return the number of free slots
     * @throws java.rmi.RemoteException
     */
    int getNumberOfFreeSlots() throws RemoteException;

    /**
     * Gets the collected parking deck utilization statistic value.
     *
     * @return the utilization value
     * @throws RemoteException
     */
    double getStatistic() throws RemoteException;
}
