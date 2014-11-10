package de.htw.berlin.student.vsys2.both.exceptions;

/**
 * Exception to be thrown if the operation is illegal on a parking deck.
 * <p/>
 * Created by matthias.drummer and ronny.timm on 04.11.14.
 */
public class IllegalParkingDeckOperationException extends Exception {

	public IllegalParkingDeckOperationException(String s) {
		super(s);
	}
}
