package de.htw.berlin.student.vsys2.both.business;

import de.htw.berlin.student.vsys2.both.enums.ServerCommands;
import de.htw.berlin.student.vsys2.both.exceptions.IllegalParkingDeckOperationException;

/**
 * Class that interprets the commands sent from a client and mentions the concurrency when accessing the parking deck Object.
 * <p/>
 * Created by matthias.drummer and ronny.timm on 04.11.14.
 */
public class ParkingDeckHandler {

    private static final String FAIL = "Fail";
    private static final String SUCCESS = "Ok";

    private ParkingDeck parkingDeck;

    public ParkingDeckHandler(ParkingDeck parkingDeck) {
        this.parkingDeck = parkingDeck;
    }

    public String handleRequestCommand(String requestCommand) {

        ServerCommands command = ServerCommands.getFromCommand(requestCommand.toLowerCase());
        String result = FAIL;
        if (command != null) {

            synchronized (parkingDeck) {
                switch (command) {
                    case FREE:
                        result = String.valueOf(parkingDeck.getNumberOfFreeSlots());
                        break;
                    case IN:
                        try {
                            parkingDeck.enter();
                            result = SUCCESS;
                        } catch (IllegalParkingDeckOperationException e) {
                            result = FAIL;
                        }
                        break;
                    case OUT:
                        try {
                            parkingDeck.leave();
                            result = SUCCESS;
                        } catch (IllegalParkingDeckOperationException e) {
                            result = FAIL;
                        }
                        break;
                    default:
                        throw new UnsupportedOperationException("The operation is not supported: " + command);
                }
            }
        }
        return result;
    }
}
