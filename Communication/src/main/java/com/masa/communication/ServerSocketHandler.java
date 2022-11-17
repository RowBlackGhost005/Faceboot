package com.masa.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that accepts the connection attemps to this Peer and pass them to the Communication
 * class.
 * This class runs in a separeted Thread to not interrupt the main loop.
 * This object should be alive the same time than Communication Component.
 * 
 * @author Luis Angel Marin
 */
public class ServerSocketHandler implements Runnable{

    private ServerSocket serverSocket;
    private Communication communication;
    
    /**
     * Creates a new ServerSocketHandler that will accept connection in the given ServerSocket
     * and pass them into the given Communication object.
     * 
     * @param server ServerSocket to listen.
     * @param communication Communication object to pass the new connections.
     */
    public ServerSocketHandler(ServerSocket server, Communication communication){
        this.serverSocket = server;
        this.communication = communication;
    }

    /**
     * Starts this ServerSocket to start listen to new connection request.
     */
    @Override
    public void run() {
        while (true) {

            Socket clientSocket = null;

            try {
                //Waits till a connection is requested
                clientSocket = serverSocket.accept();
                
                communication.handlePeer(clientSocket);

            } catch (IOException e) {
                System.out.println("Error while trying to accept a new Peer");
            }
        }
    }

    /**
     * Returns the ServerSocket of this object.
     * @return ServerSocket.
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
