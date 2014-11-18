package de.htw.berlin.student.vsys2.rpc.business;

import de.htw.berlin.student.vsys2.rpc.enums.ServerCommands;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Thread pooled server.
 *
 * @author matthias.drummer
 * @See: http://tutorials.jenkov.com/java-multithreaded-servers/thread-pooled-server.html
 */
public class ThreadPooledServer implements Runnable, Observer {

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
                MultiServerRunnable runnable = new MultiServerRunnable(clientSocket);
                runnable.addObserver(this);
                threadPool.execute(runnable);
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server stopped");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }

        }

    }

    private void handleStop() {

        // let all other threads finish their duty, but do not accept any new connections.
        isStopped = true;
        try {
            System.out.println("Await termination of threads");
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting the other threads to complete.");
        }

        System.out.println("Shutdown");
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ServerCommands) {
            ServerCommands command = (ServerCommands) arg;
            if (command == ServerCommands.QUIT) {
                handleStop();
            }
        }
    }
}
