package de.htw.berlin.student.vsys2.rmi.service;

import de.htw.berlin.student.vsys2.rmi.business.ParkingDeck;
import de.htw.berlin.student.vsys2.rmi.exceptions.IllegalParkingDeckOperationException;
import de.htw.berlin.student.vsys2.rmi.util.ParkingDeckFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation of the {@link de.htw.berlin.student.vsys2.rmi.service.ParkingDeckService}.
 *
 * @author by Matthias Drummer on 10.12.2014
 */
public class ParkingDeckServiceImpl extends UnicastRemoteObject implements ParkingDeckService {

    private ParkingDeck parkingDeck;


    public ParkingDeckServiceImpl() throws RemoteException {
        super();

        parkingDeck = ParkingDeckFactory.getInstance().getParkingDeck();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enter(int numberOfCars) throws IllegalParkingDeckOperationException {
        synchronized (parkingDeck) {
            parkingDeck.enter(numberOfCars);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void leave(int numberOfCars) throws IllegalParkingDeckOperationException {
        synchronized (parkingDeck) {
            parkingDeck.leave(numberOfCars);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfFreeSlots() {
        synchronized (parkingDeck) {
            return Integer.valueOf(parkingDeck.getNumberOfFreeSlots());
        }
    }
}
