package de.htw.berlin.student.vsys2.rpc;

import de.htw.berlin.student.vsys2.rpc.business.ParkingDeck;
import de.htw.berlin.student.vsys2.rpc.business.ParkingDeckHandler;
import de.htw.berlin.student.vsys2.rpc.exceptions.IllegalParkingDeckOperationException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by matthias.drummer on 11.11.14.
 */
public class ParkingDeckHandlerTest {

	private ParkingDeckHandler testInstance;

	//	private ParkingDeck parkingDeckMock;
	@Before
	public void before() {

		//		parkingDeckMock = EasyMock.createMock(ParkingDeck.class);

		testInstance = new ParkingDeckHandler(new ParkingDeck());
	}

	@Ignore
	@Test
	public void test() throws IllegalParkingDeckOperationException {
		//
		//		// variable definition
		//
		//		// behaviour definition
		//		EasyMock.expect(parkingDeckMock.getNumberOfFreeSlots()).andReturn((short) 4);
		//		parkingDeckMock.enter();
		//		EasyMock.expectLastCall().once();
		//		parkingDeckMock.enter();
		//		EasyMock.expectLastCall().andThrow(new IllegalParkingDeckOperationException("SomeString"));
		//
		//		// execute
		//		EasyMock.replay(parkingDeckMock);
		//		String result = testInstance.handleRequestCommand("free");
		//		Assert.assertEquals("4", result);
		//		result = testInstance.handleRequestCommand("in");
		//		Assert.assertEquals("Ok", result);
		//		// now we expect fail
		//		result = testInstance.handleRequestCommand("in");
		//		Assert.assertEquals("Fail", result);
		//
		//		EasyMock.verify(parkingDeckMock);
		//		// verify

	}

	@Test
	public void testGetFreeSlots() {

		String result = testInstance.handleRequestCommand(new String[] { "getNumberOfFreeSlots" });
		Assert.assertEquals(Integer.valueOf(result).intValue(), 5);
	}

	@Test
	public void testEnterWithParam() {

		String result = testInstance.handleRequestCommand(new Object[] { "enter", 1 });
		Assert.assertEquals(ParkingDeckHandler.SUCCESS, result);
	}

	@Test
	public void testEnterTooMuchCars() {
		// provoke exception which is catched as InvocationTargetException
		String result = testInstance.handleRequestCommand(new Object[] { "enter", 6 });
		Assert.assertEquals(ParkingDeckHandler.FAIL, result);
	}
}
