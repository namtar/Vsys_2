package de.htw.berlin.student.vsys2.rmi.business;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class that interprets the commands sent from a client and mentions the concurrency when accessing the parking deck Object.
 * <p/>
 * Created by matthias.drummer and ronny.timm on 04.11.14.
 */
public class ParkingDeckHandler {

	public static final String FAIL = "Fail";
	public static final String SUCCESS = "Ok";

	private ParkingDeck parkingDeck;

	public ParkingDeckHandler(ParkingDeck parkingDeck) {
		this.parkingDeck = parkingDeck;
	}

	public String handleRequestCommand(Object[] requestCommand) {

		System.out.println("Handle: " + requestCommand);
		String result = null;
		String methodName = (String) requestCommand[0];
        Class[] types = null;
        Object[] params = null;

        if (requestCommand.length > 1) {
            // we have parameters
            types = new Class[requestCommand.length - 1];
            params = new Object[requestCommand.length - 1];
            for (int i = 1; i < requestCommand.length; i++) {
                types[i - 1] = requestCommand[i].getClass();
                params[i - 1] = requestCommand[i];
            }
        }

		try {
			Method method = ParkingDeck.class.getDeclaredMethod(methodName, types);
			synchronized (parkingDeck) {

				Object invokeReturn = method.invoke(parkingDeck, params);
				if (invokeReturn != null) {
					result = (String) invokeReturn;
				} else {
					result = SUCCESS;
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			result = FAIL;
			System.out.println("Blaa");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			result = FAIL;
			System.out.println("Blaa2");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
}
