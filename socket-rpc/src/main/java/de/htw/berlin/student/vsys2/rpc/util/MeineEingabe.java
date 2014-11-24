package de.htw.berlin.student.vsys2.rpc.util;

/**
 * Created by Ronny on 23.11.2014.
 */
public class MeineEingabe {

	public static boolean checkString(String[] valuesToCompare, String textToCheck) {
		boolean valueOk = false;
		for (int i = 0; i < valuesToCompare.length; i++) {
			if (valuesToCompare[i].equals(textToCheck)) {
				valueOk = true;
			}
		}
		return valueOk;
	}

	public static boolean checkIfInt(String text, int min, int max) {
		boolean valueOk = false;
		int wert = 0;
		try {
			wert = Integer.parseInt(text);
			if (wert >= min && wert <= max) {
				valueOk = true;
			}
		} catch (NumberFormatException nfe) {
		}
		return valueOk;
	}

	public static boolean checkUserInput(String[] input) {
		boolean inputOk = false;
		String[] allowedText1 = { "leave", "enter" };
		String[] allowedText2 = { "hasFreeSlots", "quit"};
		if (input.length == 1 && checkString(allowedText2, input[0])) {
			inputOk = true;
		} else if (input.length == 2
				&& checkString(allowedText1, input[0])
				&& checkIfInt(input[1], 0, Integer.MAX_VALUE)) {
			inputOk = true;
		} else {
			System.out.println("wrong Input! Pleas repeat");
		}
		return inputOk;
	}

	public static void checkLeaveEnter(String[] params) {
		for (int i = 0; i < params.length; i++) {
			if (params[i].equals("leave") || params[i].equals("enter")) {
				System.out.println("Anzahl der Autos to " + params[i] + " are " + params[i + 1]);
			}
		}
	}
}
