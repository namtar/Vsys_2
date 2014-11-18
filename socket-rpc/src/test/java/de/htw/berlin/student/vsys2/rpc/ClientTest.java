package de.htw.berlin.student.vsys2.both;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by matthias.drummer on 11.11.14.
 */
public class ClientTest {

	private static final String SERVER_PORT = "6700";

	@Ignore
	@Test
	public void test() throws IOException {

		// assumes that the server is already running.

		Client.main(new String[] {"localhost", "6700"});

		System.out.println("free");
		System.out.println("in");
		System.out.println("in");
		System.out.println("free");
		System.out.println("out");
		System.out.println("free");

	}
}
