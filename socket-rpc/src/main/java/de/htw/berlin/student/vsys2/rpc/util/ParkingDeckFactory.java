package de.htw.berlin.student.vsys2.rpc.util;

import de.htw.berlin.student.vsys2.rpc.business.ParkingDeck;

/**
 * Parking Deck factory.
 *
 * @author by Matthias Drummer on 04.11.2014
 * @See: http://www.wikijava.org/wiki/Singleton_Factory_patterns_example
 */
public final class ParkingDeckFactory {

    private static ParkingDeckFactory INSTANCE = new ParkingDeckFactory();

    private ParkingDeck parkingDeck;

    private ParkingDeckFactory() {
    }

    public static ParkingDeckFactory getInstance() {
        return INSTANCE;
    }

    public ParkingDeck getParkingDeck() {
        if (parkingDeck == null) {
            synchronized (this) {
                if (parkingDeck == null) {
                    parkingDeck = new ParkingDeck();
                }
            }
        }
        return parkingDeck;
    }
}
