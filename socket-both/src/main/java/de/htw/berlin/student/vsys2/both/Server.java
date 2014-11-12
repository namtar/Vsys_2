package de.htw.berlin.student.vsys2.both;

import de.htw.berlin.student.vsys2.both.business.MultiServerRunnable;
import de.htw.berlin.student.vsys2.both.business.ThreadPooledServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

/**
 * Server class.
 * <p/>
 *
 * @author matthias.drummer
 * @author ronny.timm
 */
public class Server {

	//	private final static int MAX_CLIENTS = 10;

	private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Usage: java Server <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		ThreadPooledServer pooledServer = new ThreadPooledServer(portNumber);
		new Thread(pooledServer).start();
	}
}
