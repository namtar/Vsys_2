package de.htw.berlin.student.vsys2.rpc;

import de.htw.berlin.student.vsys2.rpc.business.ThreadPooledServer;

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

//		if (args.length != 1) {
//			System.err.println("Usage: java Server <port number>");
//			System.exit(1);
//		}
//
//		int portNumber = Integer.parseInt(args[0]);
        int portNumber = 6700;


		ThreadPooledServer pooledServer = new ThreadPooledServer(portNumber);
		new Thread(pooledServer).start();
        System.out.println("Server started");
	}
}
