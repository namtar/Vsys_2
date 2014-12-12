package de.htw.berlin.student.vsys2.rmi.business;

import de.htw.berlin.student.vsys2.rmi.util.ParkingDeckFactory;

import java.util.TimerTask;

/**
 * A timer task to collect statistic informations about the utilization of the parking deck.
 *
 * @author by Matthias Drummer on 12.12.2014
 */
public class StatisticsTimer extends TimerTask {

    @Override
    public void run() {

        ParkingDeck parkingDeck = ParkingDeckFactory.getInstance().getParkingDeck();
        synchronized (parkingDeck) {
            parkingDeck.addUsedSlotsForStatistic();
        }
    }
}
