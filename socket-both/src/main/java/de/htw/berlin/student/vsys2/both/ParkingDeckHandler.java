package de.htw.berlin.student.vsys2.both;

/**
 * Class that interprets the commands sent from a client and mentions the concurrency when accessing the parking deck Object.
 * <p/>
 * Created by matthias.drummer on 04.11.14.
 */
public class ParkingDeckHandler {

	private static final String FAIL = "Fail";

	private ParkingDeck parkingDeck;

	public ParkingDeckHandler(ParkingDeck parkingDeck) {
		this.parkingDeck = parkingDeck;
	}

	public String handleRequestCommand(String requestCommand) {

		ServerCommands command = ServerCommands.getFromCommand(requestCommand.toLowerCase());
		if (command != null) {

			synchronized (parkingDeck) {
				// TODO: critital paths......
			}
		}

		return FAIL;
	}
}
