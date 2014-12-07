package de.htw.berlin.student.vsys2.rmi.business;

import de.htw.berlin.student.vsys2.rmi.exceptions.IllegalParkingDeckOperationException;

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

	/**
	 * Adds the given number of cars to the parking deck. Note that we use the object Integer because of reflection.
	 * If we would use the primitive type we had to write a mapper.
	 *
	 * @param numberOfCars the number of cars to add
	 * @throws IllegalParkingDeckOperationException
	 */
	public void enter(Integer numberOfCars) throws IllegalParkingDeckOperationException {

		if (numberOfFreeSlots == 0 || (numberOfFreeSlots - numberOfCars) < 0) {
			throw new IllegalParkingDeckOperationException("There are no slots left on the parking deck");
		}

		numberOfFreeSlots -= numberOfCars;
	}

	public void leave() throws IllegalParkingDeckOperationException {

		if (numberOfFreeSlots == MAX_PARKING_SLOTS) {
			throw new IllegalParkingDeckOperationException("There can not be more free slots available than the MAX number: " + MAX_PARKING_SLOTS);
		}

		numberOfFreeSlots++;
	}

	/**
	 * Reduce the amount of cars on the parking deck by the given number. Note that we use the Object Integer because of reflection.
	 * If we would use the primitive integer we must write a mapper.
	 *
	 * @param numberOfCars the number of cars to reduce
	 * @throws IllegalParkingDeckOperationException
	 */
	public void leave(Integer numberOfCars) throws IllegalParkingDeckOperationException {

		if (numberOfFreeSlots == MAX_PARKING_SLOTS || (numberOfFreeSlots + numberOfCars) > MAX_PARKING_SLOTS) {
			throw new IllegalParkingDeckOperationException("There can not be more free slots available than the MAX number: " + MAX_PARKING_SLOTS);
		}

		numberOfFreeSlots += numberOfCars;
	}

	public boolean hasFreeSlots() {

		if (numberOfFreeSlots > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getNumberOfFreeSlots() {
		return String.valueOf(numberOfFreeSlots);
	}
}
