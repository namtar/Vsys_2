package de.htw.berlin.student.vsys2.both;

import de.htw.berlin.student.vsys2.both.business.ParkingDeck;
import de.htw.berlin.student.vsys2.both.business.ParkingDeckHandler;
import de.htw.berlin.student.vsys2.both.exceptions.IllegalParkingDeckOperationException;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by matthias.drummer on 11.11.14.
 */
public class ParkingDeckHandlerTest {

	private ParkingDeckHandler testInstance;
	private ParkingDeck parkingDeckMock;

	@Before
	public void before() {

		parkingDeckMock = EasyMock.createMock(ParkingDeck.class);

		testInstance = new ParkingDeckHandler(parkingDeckMock);
	}

	@Test
	public void test() throws IllegalParkingDeckOperationException {

		// variable definition

		// behaviour definition
		EasyMock.expect(parkingDeckMock.getNumberOfFreeSlots()).andReturn((short) 4);
		parkingDeckMock.enter();
		EasyMock.expectLastCall().once();
		parkingDeckMock.enter();
		EasyMock.expectLastCall().andThrow(new IllegalParkingDeckOperationException("SomeString"));

		// execute
		EasyMock.replay(parkingDeckMock);
		String result = testInstance.handleRequestCommand("free");
		Assert.assertEquals("4", result);
		result = testInstance.handleRequestCommand("in");
		Assert.assertEquals("Ok", result);
		// now we expect fail
		result = testInstance.handleRequestCommand("in");
		Assert.assertEquals("Fail", result);

		EasyMock.verify(parkingDeckMock);
		// verify

	}
}
