package de.htw.berlin.student.vsys2.both;

import de.htw.berlin.student.vsys2.both.Server;

/**
 * Tests for the server and client parts.
 * <p/>
 * Created by matthias.drummer on 04.11.14.
 */
public class SocketTest {

	public void testServer() {

		Server.main(new String[] {"6700"});
	}

}
