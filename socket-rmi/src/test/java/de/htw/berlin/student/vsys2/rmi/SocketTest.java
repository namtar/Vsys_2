package de.htw.berlin.student.vsys2.rmi;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Tests for the server and client parts.
 * <p/>
 * Created by matthias.drummer and ronny.timm on 04.11.14.
 */
public class SocketTest {

	private static final String SERVER_PORT = "6700";

	@Ignore
	@Test
	public void testServer() throws InterruptedException {

		createServerThread();

		PrintWriter clientOut = null;
		BufferedReader clientIn = null;
		try {
			Socket clientSocket = new Socket("localhost", Integer.valueOf(SERVER_PORT));
			clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
			clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			clientOut.println("free");
			String fromServer;
			while ((fromServer = clientIn.readLine()) != null) {
				String response = fromServer;
				System.out.println(response);
				Assert.assertEquals("There must be 5 parking slots available", Integer.valueOf(response), new Integer(5));
				break;
			}
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} finally {
			if (clientOut != null) {
				clientOut.close();
			}
			if (clientIn != null) {
				try {
					clientIn.close();
				} catch (IOException e) {
					Assert.fail(e.getMessage());
				}
			}
		}
	}

	@Ignore
	@Test
	public void testInFail() throws InterruptedException {

		createServerThread();

		PrintWriter clientOut = null;
		BufferedReader clientIn = null;
		try {
			Socket clientSocket = new Socket("localhost", Integer.valueOf(SERVER_PORT));
			clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
			clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			clientOut.println("in");
			String fromServer;
			int cnt = 1;
			while ((fromServer = clientIn.readLine()) != null) {
				String response = fromServer;

				if (cnt == 5) {
					// if we have the fifth iteration there must be a fail
					Assert.assertEquals("If there is the fifth iteration a fail must occur.", "Fail", response);
					break;
				} else {
					Assert.assertEquals("Ok", response);
				}

				cnt++;
				clientOut.println("in");
			}
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} finally {
			if (clientOut != null) {
				clientOut.close();
			}
			if (clientIn != null) {
				try {
					clientIn.close();
				} catch (IOException e) {
					Assert.fail(e.getMessage());
				}
			}
		}
	}

	private void createServerThread() throws InterruptedException {
		Thread serverThread = new Thread() {

			@Override
			public void run() {
				Server.main(new String[] { SERVER_PORT });
			}
		};
		serverThread.start();
		Thread.sleep(1000);
		System.out.println("Server started.");
	}
}
