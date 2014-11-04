package de.htw.berlin.student.vsys2.both;

import junit.framework.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Tests for the server and client parts.
 * <p/>
 * Created by matthias.drummer on 04.11.14.
 */
public class SocketTest {

    @Test
    public void testServer() throws InterruptedException {

        Thread serverThread = new Thread() {
            @Override
            public void run() {
                Server.main(new String[]{"6700"});
            }
        };
        serverThread.start();
        Thread.sleep(1000);
        System.out.println("Server started.");

        PrintWriter clientOut = null;
        BufferedReader clientIn = null;
        try {
            Socket clientSocket = new Socket("localhost", 6700);
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

}
