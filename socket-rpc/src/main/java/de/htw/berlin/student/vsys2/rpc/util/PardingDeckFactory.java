package de.htw.berlin.student.vsys2.rpc.util;

import de.htw.berlin.student.vsys2.rpc.business.ParkingDeck;

/**
 * Parking Deck factory.
 *
 * @author by Matthias Drummer on 04.11.2014
 * @See: http://www.wikijava.org/wiki/Singleton_Factory_patterns_example
 */
public final class PardingDeckFactory {

    private static PardingDeckFactory INSTANCE = new PardingDeckFactory();

    private ParkingDeck parkingDeck;

    private PardingDeckFactory() {
    }

    public static PardingDeckFactory getInstance() {
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
