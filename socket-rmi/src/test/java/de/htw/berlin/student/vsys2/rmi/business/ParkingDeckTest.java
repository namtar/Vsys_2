package de.htw.berlin.student.vsys2.rmi.business;

import de.htw.berlin.student.vsys2.rmi.exceptions.IllegalParkingDeckOperationException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests new functionality of the parking deck class.
 * <p/>
 * Created by matthias.drummer on 16.12.14.
 */
public class ParkingDeckTest {

	@Test
	public void testStatistics() throws IllegalParkingDeckOperationException {

		ParkingDeck deck = new ParkingDeck();

		double statisticValue = deck.getStatistic();
		Assert.assertTrue(statisticValue == 0d);

		deck.enter(1);
		deck.addUsedSlotsForStatistic();

		statisticValue = deck.getStatistic();

		Assert.assertTrue(statisticValue == 20d);

		deck.enter(2);
		deck.addUsedSlotsForStatistic();

		statisticValue = deck.getStatistic();

		// 1+3 = 4 / 2 * 100 / 5
		Assert.assertTrue("But was: " + statisticValue, statisticValue == 40d);

		deck.addUsedSlotsForStatistic();

		statisticValue = deck.getStatistic();

		// 1+3 + 3 = 7 / 3 * 100 / 5
		Integer conv = Double.valueOf(statisticValue * 10).intValue();

		statisticValue = (conv.doubleValue() / 10);
		Assert.assertTrue("But was: " + statisticValue, statisticValue == 46.6d);
	}

	@Test
	public void testFullWorkloadStatistics() throws IllegalParkingDeckOperationException {

		ParkingDeck deck = new ParkingDeck();

		deck.enter(5);
		deck.addUsedSlotsForStatistic();

		// 5 / 1 * 100 / 5
		double statisticValue = deck.getStatistic();
		Assert.assertTrue("But was: " + statisticValue, statisticValue == 100d);

	}
}
