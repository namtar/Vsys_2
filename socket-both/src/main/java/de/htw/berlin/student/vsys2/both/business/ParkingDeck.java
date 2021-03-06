package de.htw.berlin.student.vsys2.both.business;

import de.htw.berlin.student.vsys2.both.exceptions.IllegalParkingDeckOperationException;

/**
 * A parking deck.
 * <p/>
 * Created by matthias.drummer and ronny.timm on 04.11.14.
 */
public class ParkingDeck {

    public final static short MAX_PARKING_SLOTS = 5;
    private short numberOfFreeSlots = 5;

    public ParkingDeck() {
    }

    public void enter() throws IllegalParkingDeckOperationException {

        if (numberOfFreeSlots == 0) {
            throw new IllegalParkingDeckOperationException("There are no slots left on the parking deck");
        }

        numberOfFreeSlots--;
    }

    public void leave() throws IllegalParkingDeckOperationException {

        if (numberOfFreeSlots == MAX_PARKING_SLOTS) {
            throw new IllegalParkingDeckOperationException("There can not be more free slots available than the MAX number: " + MAX_PARKING_SLOTS);
        }

        numberOfFreeSlots++;
    }

    public boolean hasFreeSlots() {

        if (numberOfFreeSlots > 0) {
            return true;
        } else {
            return false;
        }
    }

    public short getNumberOfFreeSlots() {
        return numberOfFreeSlots;
    }
}
