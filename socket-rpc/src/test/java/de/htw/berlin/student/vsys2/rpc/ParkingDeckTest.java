package de.htw.berlin.student.vsys2.both;

import de.htw.berlin.student.vsys2.both.business.ParkingDeck;
import de.htw.berlin.student.vsys2.both.exceptions.IllegalParkingDeckOperationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the {@link de.htw.berlin.student.vsys2.both.business.ParkingDeck}.
 *
 * @author Matthias Drummer
 */
public class ParkingDeckTest {

    private ParkingDeck testInstance;

    @Before
    public void before() {
        this.testInstance = new ParkingDeck();
    }

    @Test(expected = IllegalParkingDeckOperationException.class)
    public void testIllegalLeave() throws IllegalParkingDeckOperationException {

        this.testInstance.leave();
    }

    @Test(expected = IllegalParkingDeckOperationException.class)
    public void testIllegalEnter() throws IllegalParkingDeckOperationException {

        // first check size of parking deck slots available
        short numberOfFreeSlots = testInstance.getNumberOfFreeSlots();
        Assert.assertEquals("Wrong number of parking slots free", numberOfFreeSlots, ParkingDeck.MAX_PARKING_SLOTS);

        // second enter parking deck with as much cars without triggering the exception
        for (int i = 0; i < numberOfFreeSlots; i++) {
            try {
                testInstance.enter();
            } catch (IllegalParkingDeckOperationException e) {
                Assert.fail("No exeption may happen at this point of time.");
            }
            Assert.assertEquals("Wrong number of parking slots free", testInstance.getNumberOfFreeSlots(), ParkingDeck.MAX_PARKING_SLOTS - (i + 1));
        }

        // verify that there are no places left
        Assert.assertTrue(testInstance.getNumberOfFreeSlots() == 0);

        // try to access the full parking deck
        testInstance.enter();

    }
}
