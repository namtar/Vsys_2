package de.htw.berlin.student.vsys2.both.business;

import de.htw.berlin.student.vsys2.both.exceptions.TerminateServerException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Thread pooled server.
 *
 * @author matthias.drummer
 * @See: http://tutorials.jenkov.com/java-multithreaded-servers/thread-pooled-server.html
 */
public class ThreadPooledServer implements Runnable {

	private int serverPort = 6700;
	private ServerSocket serverSocket = null;
	private boolean isStopped = false;
	private Thread runningThread = null;
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	/**
	 * Constructor.
	 *
	 * @param serverPort the port on which the server shall run to.
	 */
	public ThreadPooledServer(int serverPort) {
		this.serverPort = serverPort;
	}

	@Override
	public void run() {
		synchronized (this) {
			runningThread = Thread.currentThread();
		}

		createServerSocket();

		while (!isStopped()) {
			try {
				Socket clientSocket = serverSocket.accept();
				threadPool.execute(new MultiServerRunnable(clientSocket));
			} catch (IOException e) {
				if (isStopped()) {
					System.out.println("Server stopped");
					return;
				}
				throw new RuntimeException("Error accepting client connection", e);
			} catch (TerminateServerException e) {
				handleStop();
			}

		}

	}

	private void handleStop() {

		// let all other threads finish their duty, but do not accept any new connections.
		try {
			threadPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Interrupted while waiting the other threads to complete.");
		}

		threadPool.shutdown();
		try {
			serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error while closing the server socket.");
		}
		System.exit(0);
	}

	public synchronized boolean isStopped() {
		return isStopped;
	}

	public synchronized void stop() {
		try {
			isStopped = true;
			serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Error stopping server", e);
		}
	}

	private void createServerSocket() {
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot create server on port: " + serverPort);
		}
	}
}
